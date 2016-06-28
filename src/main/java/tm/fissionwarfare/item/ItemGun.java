package tm.fissionwarfare.item;

import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.damage.DamageSourceCustom;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.key.KeyBindings;
import tm.fissionwarfare.packet.ServerPacketHandler;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.GunData;
import tm.fissionwarfare.util.GunProfile;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.GunTraceUtil;
import tm.fissionwarfare.util.math.Vector3d;

public class ItemGun extends ItemBase {

	Random rand = new Random();
	
	public GunProfile profile;
	
	public ItemGun(String name, GunProfile profile) {
		super(name, InitTabs.tabWarfare);
		this.profile = profile;
		setMaxStackSize(1);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean b) {
			
		if (!(entity instanceof EntityPlayer)) {
			return;
		}
		
		EntityPlayer player = (EntityPlayer)entity;
		GunData data = new GunData(stack);
		
		if (world.isRemote && (Minecraft.getMinecraft().currentScreen != null || player.getCurrentEquippedItem() != stack || !Minecraft.getMinecraft().gameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindUseItem))) {
			data.usingTicks = 0;
			FissionWarfare.network.sendToServer(new ServerPacketHandler("stop.use%" + slot));
		}
		
		if (player.getCurrentEquippedItem() == stack) {
			
			if (world.isRemote) {
				
				if (data.ammo < profile.maxAmmo) {
				
					if (Minecraft.getMinecraft().currentScreen == null) {
										
						if (Keyboard.isKeyDown(KeyBindings.reloadGunButton.getKeyCode())) {
							
							FissionWarfare.network.sendToServer(new ServerPacketHandler("reload"));
							data.time = 0;
						}
						
						else world.playSoundAtEntity(player, "random.click", 1, 0.5F);
					}
				}			
			}
				
			else {					
				
				if (data.ammo < profile.maxAmmo && data.reloading && hasAmmo(player, profile.shotsPerFire)) {
					
					data.time++;
					
					if (data.time >= profile.reloadTime) {
					
						data.time = 0;
						
						for (int i = 0; i < profile.shotsPerFire; i++) {									
							player.inventory.consumeInventoryItem(InitItems.bullet);
						}											
						
						data.ammo++;
						world.playSoundAtEntity(player, "random.click", 1, data.ammo == profile.maxAmmo ? 0.5F : 2 - (float)(getDurabilityForDisplay(stack)));
					}
				}	
				
				else {					
					data.reloading = false;
				}
			}
		}	
		
		else {
			data.scope = 0;
			data.reloading = false;
		}
		
		data.flush();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		
		GunData data = new GunData(is);
	
		if (profile.isAuto || data.usingTicks == 0) {
			
			shootBullet(world, is, player, data, 0);			
		}
		
		data.usingTicks++;
				
		data.flush();
				
		return is;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity) {
		
		if (player instanceof EntityPlayer && entity instanceof EntityPlayer) {
			
			entity.attackEntityFrom(new DamageSourceCustom(((EntityPlayer)entity).getDisplayName() + " was knocked out by " + ((EntityPlayer)player).getDisplayName() + "'s gun"), 4.0F);
		}
			
		else entity.attackEntityFrom(DamageSource.generic, 2.0F);
		
		return true;
	}	
	
	public boolean hasAmmo(EntityPlayer player, int ammo) {

		if (player.capabilities.isCreativeMode) return true;
		
		int amount = 0;
		
		for (ItemStack slot : player.inventory.mainInventory) {
					
			if (slot != null && slot.getItem() == InitItems.bullet) {				
				amount += slot.stackSize;							
			}	
			
			if (amount >= profile.shotsPerFire) {				
				return true;	
			}
		}	
		
		return false;
	}
		
	public void shootBullet(World world, ItemStack is, EntityPlayer player, GunData data, int hurtTime) {
		
		data.reloading = false;
		
		if (data.ammo > 0) {		

			for (int i = 0; i < profile.shotsPerFire; i++) {
				
				float randomPitch = profile.accuracy == 0 ? 0 : rand.nextInt(profile.accuracy * 2) - profile.accuracy;
				float randomYaw = profile.accuracy == 0 ? 0 : rand.nextInt(profile.accuracy * 2) - profile.accuracy;
				
				if (!world.isRemote) GunTraceUtil.doGunTrace(player, new Vector3d(player.posX, 1.62F + player.posY, player.posZ), new Angle2d((-player.rotationPitch) + randomPitch, (-player.rotationYaw - 90) + randomYaw), world, profile.damage, profile.distance);
				
				world.playSoundAtEntity(player, profile.sound.getSoundPath(), 1, 1);
			}
			
			data.ammo--;	
			
			player.rotationPitch -= profile.recoil;			
		}
			
		else world.playSoundAtEntity(player, "random.click", 1, 0.5F);
		
		data.flush();
	}	
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		GunData data = new GunData(is);
		
		list.add(EnumChatFormatting.GOLD + "Ammo: " + EnumChatFormatting.AQUA + data.ammo + "/" + profile.maxAmmo);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 72000;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack is) {
		
		GunData data = new GunData(is);
		
		return 1 - ((1.0F / profile.maxAmmo) * data.ammo);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack is) {
		
		GunData data = new GunData(is);
		
		return data.ammo < profile.maxAmmo;
	}
}