package tm.fissionwarfare.gui.base;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.Reference;

public abstract class GuiScreenBase extends GuiScreen {

	public abstract String getGuiTextureName();

	public abstract int getGuiSizeX();
	public abstract int getGuiSizeY();	
	
	public abstract void drawGuiBackground(int mouseX, int mouseY);
	public abstract void drawGuiForeground(int mouseX, int mouseY);	
	
	public abstract boolean canCloseWithInvKey();
	
	public int getScreenX() {
		return (this.width - getGuiSizeX()) / 2;
	}
	
	public int getScreenY() {
		return (this.height - getGuiSizeY()) / 2;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f1) {
		updateScreen();
		
		drawDefaultBackground();
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextureName() + ".png"));
		drawTexturedModalRect(getScreenX(), getScreenY(), 0, 0, getGuiSizeX(), getGuiSizeY());	
		
		drawGuiBackground(mouseX, mouseY);		
		super.drawScreen(mouseX, mouseY, f1);		
		drawGuiForeground(mouseX, mouseY);
	}
	
	@Override
	protected void keyTyped(char c, int keyID) {
		super.keyTyped(c, keyID);
		
		if (canCloseWithInvKey() && keyID == mc.gameSettings.keyBindInventory.getKeyCode()) {
			mc.thePlayer.closeScreen();
		}
	}
}
