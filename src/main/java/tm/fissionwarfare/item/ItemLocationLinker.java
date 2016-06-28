package tm.fissionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.tileentity.machine.TileEntityControlPanel;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.ItemLoreUtil;
import tm.fissionwarfare.util.NBTUtil;
import tm.fissionwarfare.util.UnitChatMessage;

public class ItemLocationLinker extends ItemBase {

	public ItemLocationLinker() {
		super("location_linker", InitTabs.tabWarfare);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		list.add(EnumChatFormatting.GOLD + "Block Location");
		list.add(EnumChatFormatting.GOLD + "X: " + EnumChatFormatting.AQUA + NBTUtil.getNBT(is).getInteger("X"));
		list.add(EnumChatFormatting.GOLD + "Z: " + EnumChatFormatting.AQUA + NBTUtil.getNBT(is).getInteger("Z"));
		list.add("");
		
		if (ItemLoreUtil.addShiftLore(list)) {
								
			list.add("Binds locations to Control Panels");
			list.add("Right-click : Links a location.");
			list.add("Sneak Right-click : Sends coords to machine.");
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int i, float f1, float f2, float f3) {
		
		UnitChatMessage message = new UnitChatMessage("Location Linker", player);
		
		if (!world.isRemote) {
						
			if (player.isSneaking()) {
				
				if (world.getTileEntity(x, y, z) instanceof TileEntityControlPanel) {
					
					TileEntityControlPanel tileEntity = (TileEntityControlPanel)world.getTileEntity(x, y, z);
					
					tileEntity.targetX = NBTUtil.getNBT(is).getInteger("X");
					tileEntity.targetZ = NBTUtil.getNBT(is).getInteger("Z");
					
					tileEntity.update();
					
					player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);
					
					message.printMessage(EnumChatFormatting.GREEN, "Control Panel's coords set to " + NBTUtil.getNBT(is).getInteger("X") + ", " + NBTUtil.getNBT(is).getInteger("Z"));
					return true;
				}
				
				else {
					message.printMessage(EnumChatFormatting.RED, "That isn't a Control Panel!");
				}
			}
			
			else {
				
				NBTUtil.getNBT(is).setInteger("X", x);	
				NBTUtil.getNBT(is).setInteger("Z", z);
			
				player.worldObj.playSoundAtEntity(player, "random.click", 1F, 1F);
			
				message.printMessage(EnumChatFormatting.GREEN, "Coords set to " + x + ", " + z);
			}	
			
			return true;					
		}	
		
		return false;
	}	
}
