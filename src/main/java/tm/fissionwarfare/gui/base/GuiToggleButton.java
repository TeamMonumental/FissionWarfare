package tm.fissionwarfare.gui.base;

import java.util.List;

import net.minecraft.client.Minecraft;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.packet.ServerPacketHandler;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class GuiToggleButton extends GuiButtonRect {

	public TileEntityEnergyBase tileEntity;
	
	public GuiToggleButton(int id, int x, int y, TileEntityEnergyBase tileEntity, List buttonList) {
		super(id, x, y, 32, "OFF", buttonList);
		this.tileEntity = tileEntity;
		displayString = tileEntity.enabled ? "ON" : "OFF";
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		super.drawButton(mc, mouseX, mouseY);
		displayString = tileEntity.enabled ? "ON" : "OFF";
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		
		GuiRect rect = new GuiRect(xPosition, yPosition, width, height);
		
		if (rect.contains(mouseX, mouseY)) {
			FissionWarfare.network.sendToServer(new ServerPacketHandler("toggle.tileEntity%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
		}	
		
		return super.mousePressed(mc, mouseX, mouseY);		
	}
}
