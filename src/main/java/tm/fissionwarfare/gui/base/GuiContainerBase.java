package tm.fissionwarfare.gui.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public abstract class GuiContainerBase extends GuiContainer {

	public GuiContainerBase(Container container, EntityPlayer player, TileEntityInventoryBase tileEntity) {
		super(container);
		this.xSize = getGuiSize();
		this.ySize = getGuiSize();
	}
	
	public abstract String getGuiTextureName();
	public abstract String getGuiTitle();
		
	public abstract void drawGuiBackground(int mouseX, int mouseY);
	public abstract void drawGuiForeground(int mouseX, int mouseY);
	
	public int getGuiSize() {	
		return 176;
	}
	
	public int getScreenX() {
		return (this.width - getGuiSize()) / 2;
	}
	
	public int getScreenY() {
		return (this.height - getGuiSize()) / 2;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		
		updateScreen();
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextureName() + ".png"));
		drawTexturedModalRect(getScreenX(), getScreenY(), 0, 0, getGuiSize(), getGuiSize());
		
		drawGuiBackground(mouseX, mouseY);	
		
		GuiUtil.drawCenteredString(getGuiTitle(), getScreenX() + getGuiSize() / 2, getScreenY() + 6, 0xCCCCCC);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {	
		
		super.drawScreen(mouseX, mouseY, f);
		drawGuiForeground(mouseX, mouseY);
	}
}
