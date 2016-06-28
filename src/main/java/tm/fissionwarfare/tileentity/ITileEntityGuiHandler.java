package tm.fissionwarfare.tileentity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public interface ITileEntityGuiHandler {

	public Container getTileContainer(EntityPlayer player);
	
	public GuiContainer getTileGuiContainer(EntityPlayer player);
}
