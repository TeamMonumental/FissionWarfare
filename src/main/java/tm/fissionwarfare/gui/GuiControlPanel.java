package tm.fissionwarfare.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.gui.base.GuiButtonRect;
import tm.fissionwarfare.gui.base.GuiEnergyContainerBase;
import tm.fissionwarfare.gui.base.GuiNumberFieldRect;
import tm.fissionwarfare.packet.ServerPacketHandler;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class GuiControlPanel extends GuiEnergyContainerBase {

	private long lastTime;
	private long targetTime = 5;
	private float rot;
	
	private TileEntityLaunchPad tileEntity;
	
	private GuiNumberFieldRect xField, zField;
	private GuiButtonRect launchButton;
	
	public GuiControlPanel(Container container, EntityPlayer player, TileEntityLaunchPad tileEntity) {
		super(container, player, tileEntity);
		this.tileEntity = (TileEntityLaunchPad) tileEntity;
	}

	@Override
	public String getGuiTextureName() {
		return "control_panel";
	}

	@Override
	public String getGuiTitle() {
		return "Control Panel";
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		Keyboard.enableRepeatEvents(true);
		
		xField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 17, getScreenY() + 32, 51, 8);
		zField = new GuiNumberFieldRect(fontRendererObj, getScreenX() + 17, getScreenY() + 54, 51, 8);
		
		if (tileEntity.getControlPanel() != null) {
			
			xField.setText("" + tileEntity.getControlPanel().targetX);
			zField.setText("" + tileEntity.getControlPanel().targetZ);
		}
		
		launchButton = new GuiButtonRect(0, getScreenX() + 107, getScreenY() + 41, 52, tileEntity.launching ? "Abort" : "Launch", buttonList);
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		
		launchButton.displayString = tileEntity.launching ? "Abort" : "Launch";
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == launchButton.id) {
			
			FissionWarfare.network.sendToServer(new ServerPacketHandler("toggle.launch%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord + "%" + player.getDisplayName()));
		}
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		if (tileEntity.getControlPanel() != null && tileEntity.missile != null) {
					
			ItemStack stack = tileEntity.missile;
		
			if (System.currentTimeMillis() - lastTime >= targetTime) {
				lastTime = System.currentTimeMillis();
				rot += 1F;
				rot %= 360;
			}
			
			EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld);
		
			entityItem.setEntityItemStack(stack);
			entityItem.hoverStart = 0;
			
			GL11.glPushMatrix();
			GL11.glTranslated(getScreenX() + 88, getScreenY() + 76, 100);
			GL11.glScaled(16, 16, -16);
			GL11.glRotated(180, 1, 0, 0);
        
			if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
				GL11.glRotated(rot, 0, 1, 0);
			}
			
			else GL11.glRotated(0, 0, 1, 0);
      
			RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0, 0.0, 0, 0, 0);
			GL11.glPopMatrix();
		}
		
		xField.drawTextBox();
		zField.drawTextBox();
		
		fontRendererObj.drawString("X:", getScreenX() + 7, getScreenY() + 34, 0xCCCCCC);
		fontRendererObj.drawString("Z:", getScreenX() + 7, getScreenY() + 56, 0xCCCCCC);
	}
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {}
	
	private void setCoords(GuiTextField field, int index) {
			
		if (field.getText() != null && !field.getText().trim().isEmpty()) {

			int coord;
			
			if (index == 0) {				
				coord = parseInt(tileEntity.getControlPanel().targetX, field.getText());				
				tileEntity.getControlPanel().targetX = coord;
			}
			
			else {				
				coord = parseInt(tileEntity.getControlPanel().targetZ, field.getText());								
				tileEntity.getControlPanel().targetZ = coord;
			}
			
			FissionWarfare.network.sendToServer(new ServerPacketHandler("set.coords%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord + "%" + index + "%" + coord));
		}
	}
		
	private int parseInt(int i, String text) {
		
		try  {
			i = Integer.parseInt(text);
		}
		
		catch (NumberFormatException e) {
			
		}
		
		return i;
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
			
		xField.textboxKeyTyped(c, i);
		zField.textboxKeyTyped(c, i);
		
		if (tileEntity.getControlPanel() != null) {
			
			setCoords(xField, 0);
			setCoords(zField, 1);
		}
    }

	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    xField.mouseClicked(x, y, i);
	    zField.mouseClicked(x, y, i);
	}
}
