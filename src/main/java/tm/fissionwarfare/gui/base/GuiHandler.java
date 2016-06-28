package tm.fissionwarfare.gui.base;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tm.fissionwarfare.tileentity.ITileEntityGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);
				
		if (tileentity instanceof ITileEntityGuiHandler) {
			return ((ITileEntityGuiHandler) tileentity).getTileContainer(player);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (tileentity instanceof ITileEntityGuiHandler) {
			return ((ITileEntityGuiHandler) tileentity).getTileGuiContainer(player);
		}
		
		return null;
	}
}
