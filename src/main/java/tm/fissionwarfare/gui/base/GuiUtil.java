package tm.fissionwarfare.gui.base;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.util.math.MathUtil;

public class GuiUtil {

	private static Minecraft mc = Minecraft.getMinecraft();
	
	private static int zLevel = 10;
	private static final int TEXTURE_SIZE = 256;
		
	public static void drawStatusPanel(int energy, int maxEnergy, int progress, int maxProgress, int x, int y, int mouseX, int mouseY) {
		
		GL11.glEnable(GL11.GL_BLEND);
		
		GuiRect rect = new GuiRect(x + 1, y + 15, 18, 46);
		GuiRect rect2 = new GuiRect(rect.x + 5, rect.y + 5, rect.width - 10, rect.height - 10);
		
		mc.getTextureManager().bindTexture(Reference.GUI_TEXTURES);
		drawRect(x, y, 0, 0, 0, 19, 76);		
		
		drawScaledHeightRect(x + 4, y + 58, 19, 0, 0, 12, 40, progress, maxProgress);
		drawScaledHeightRect(x + 7, y + 55, 19, 40, 0, 6, 34, energy, maxEnergy);
		
		if (rect.contains(mouseX, mouseY) && ! rect2.contains(mouseX, mouseY)) {
			drawHoveringTextBox("Progress: " + MathUtil.scaleInt(progress, maxProgress, 100) + "%", mouseX, mouseY, rect);
		}
				
		else drawHoveringTextBox(energy + " / " + maxEnergy + " RF", mouseX, mouseY, rect2);
	}
		
	public static void drawHoveringTextBox(String text, int mouseX, int mouseY, GuiRect rect) {
		
		if (rect.contains(mouseX, mouseY)) {
						
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, 100);
			
			mc.getTextureManager().bindTexture(Reference.GUI_TEXTURES);
			drawCappedRect(mouseX + 1, mouseY - 13, 0, 227, 0, mc.fontRenderer.getStringWidth(text) + 5, 13, 256);
			mc.fontRenderer.drawString(text, mouseX + 4, mouseY - 10, 0xFFFFFF);
			
			GL11.glPopMatrix();
			
			GL11.glColor4f(1, 1, 1, 1);		
		}
	}
	
	public static void drawBottomInfoBox(String text, int x, int y, int color) {
		
		int width = mc.fontRenderer.getStringWidth(text);
		
		mc.getTextureManager().bindTexture(Reference.GUI_TEXTURES);
		drawCappedRect(x - (width / 2) - 3, y, 0, 230, 0, width + 5, 1, 256);
		drawCappedRect(x - (width / 2) - 3, y + 1, 0, 230, 0, width + 5, 10, 256);
		drawCenteredString(text, x, y + 1, color);
	}
	
	public static void drawCenteredString(String text, int x, int y, int color) {
				
		mc.fontRenderer.drawString(text, x - (mc.fontRenderer.getStringWidth(text) / 2), y, color);
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	public static void drawLimitedString(String text, int x, int y, int textLimit, int color) {
				
		String temp = text;
				
		if (temp.length() > textLimit) {
			
			temp = temp.substring(0, textLimit - 1);
			temp += "...";
		}	
		
		mc.fontRenderer.drawString(temp, x, y, color);
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	public static void drawCappedRect(int x, int y, int u, int v, int zLevel, int width, int height, int maxWidth) {
		
		drawRect(x, y, u, v, zLevel, width - 2, height);
		drawRect(x + width - 2, y, u + maxWidth - 2, v, zLevel, 2, height);
	}
	
	public static void drawScaledHeightRect(int x, int y, int u, int v, int zLevel, int width, int height, int value, int maxValue) {
		int scale = MathUtil.scaleInt(value, maxValue, height);
		drawRect(x, y - scale, u, v + height - scale, zLevel, width, scale);
	}
	
	public static void drawRect(int x, int y, int u, int v, int zLevel, int width, int height) {
		
		int maxX = x + width;
		int maxY = y + height;
		
		int maxU = u + width;
		int maxV = v + height;
		
		float pixel = 1F / TEXTURE_SIZE;
		
        Tessellator tessellator = Tessellator.instance;
        
        tessellator.startDrawingQuads();
        
        tessellator.addVertexWithUV(x, maxY, zLevel, u * pixel, maxV * pixel);
        tessellator.addVertexWithUV(maxX, maxY, zLevel, maxU * pixel, maxV * pixel);
        tessellator.addVertexWithUV(maxX, y, zLevel, maxU * pixel, v * pixel);
        tessellator.addVertexWithUV(x, y, zLevel, u * pixel, v * pixel);
        
        tessellator.draw();
    }	
}
