package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class RenderLaunchPad extends RenderTileEntityBase {

	public RenderLaunchPad() {
		super("launch_pad");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f1) {
		
		TileEntityLaunchPad tileEntityLP = (TileEntityLaunchPad)tileEntity;
		
		GL11.glPushMatrix();
		
		if (tileEntityLP.missile != null) {
			
			ItemStack stack = new ItemStack(tileEntityLP.missile.getItem(), 1, 0);
			EntityItem entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);	
			
			entItem.hoverStart = 0;
			
			GL11.glPushMatrix();						
			RenderItem.renderInFrame = true;
			GL11.glTranslatef((float)x + 0.5F, (float)y + 0.44F, (float)z + 0.5F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glRotatef(45, 0, 1, 0);
			RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
			GL11.glPopMatrix();	
		}
		
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glScaled(0.85D, 0.85D, 0.85D);
		
		GL11.glRotatef(-90 * tileEntity.getBlockMetadata(), 0, 1, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();	
		
		GL11.glPopMatrix();
	}	
}
