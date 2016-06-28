package tm.fissionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.render.RenderCompressor;
import tm.fissionwarfare.util.ItemLoreUtil;
import tm.fissionwarfare.util.NBTUtil;
import tm.fissionwarfare.util.math.MathUtil;

public class ItemCompressor extends ItemArmor implements IEnergyContainerItem {
	
	public static final int MAX_ENERGY_STORED = 250000;
	private static final int MAX_ENERGY_TRANSFER = 2000;
	
	public ItemCompressor() {
		super(ArmorMaterial.CLOTH, Reference.armorIDCompressor, 1);
		String imageName = "compressor";
		setUnlocalizedName(imageName);
		setTextureName(Reference.MOD_ID + ":" + imageName);
		setCreativeTab(InitTabs.tabWarfare);
		setMaxDamage(0);
		GameRegistry.registerItem(this, imageName);		
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		
		list.add(EnumChatFormatting.GOLD + "RF : " + EnumChatFormatting.AQUA + NBTUtil.getNBT(stack).getInteger("energy") + " / " + getMaxEnergyStored(stack));		
		list.add("");
		
		if (ItemLoreUtil.addShiftLore(list)) {
						
			list.add("This item is placed in your chestplate slot.");
			list.add("Used for the Nail Gun to operate.");
		}
    }
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		
		list.add(new ItemStack(this));
		
		ItemStack stack = new ItemStack(this);
		NBTUtil.getNBT(stack).setInteger("energy", MAX_ENERGY_STORED);
		
		list.add(stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
		
		RenderCompressor model = new RenderCompressor();
		
		if (model != null) {
			
			model.isSneak = entityLiving.isSneaking();			
		}
		
		return model;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - MathUtil.scaleDouble(getEnergyStored(stack), getMaxEnergyStored(stack), 1);
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}
	
	@Override
    public boolean showDurabilityBar(ItemStack stack) {		
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	public int receiveEnergy(ItemStack stack, int maxReceive, boolean sim) {
		
		int energy = this.getEnergyStored(stack);
        int energyReceived = Math.min(this.getMaxEnergyStored(stack) - energy, Math.min(maxReceive, MAX_ENERGY_TRANSFER));
        
        if (!sim) {
            energy += energyReceived;
            NBTUtil.getNBT(stack).setInteger("energy", energy);
        }
        
        return energyReceived;
	}
	
	@Override
	public int extractEnergy(ItemStack stack, int maxExtract, boolean sim) {
		
		int energy = this.getEnergyStored(stack);
	    int energyExtracted = Math.min(energy, Math.min(maxExtract, MAX_ENERGY_TRANSFER));
	    
	    if (!sim) {
	    	energy -= energyExtracted;
	    	NBTUtil.getNBT(stack).setInteger("energy", energy);
	    }
	    
	    return energyExtracted;
	}
	
	@Override
	public int getEnergyStored(ItemStack stack) {
		return NBTUtil.getNBT(stack).getInteger("energy");
	}
	
	@Override
	public int getMaxEnergyStored(ItemStack stack) {
		return MAX_ENERGY_STORED;
	}
	
	@Override
	public int getColor(ItemStack stack) {
		return 0xFFFFFF;
	}
	
	@Override
	public boolean hasColor(ItemStack stack) {
		return false;
	}
}
