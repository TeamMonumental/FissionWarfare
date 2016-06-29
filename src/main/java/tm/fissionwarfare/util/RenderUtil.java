package tm.fissionwarfare.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import tm.fissionwarfare.block.BlockExplosive;

public class RenderUtil {

	public static void renderInventroyBlock(RenderBlocks renderer, Block block, int color) {
		
		Tessellator tes = Tessellator.instance;
    		
		IIcon icon = block.getIcon(0, 0);
		
    	GL11.glPushMatrix();
    	GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
    	GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    	    	        	
    	tes.startDrawingQuads();
		tes.setNormal(0.0F, -1.0F, 0.0F);
		tes.setColorOpaque_I(color);
	    renderer.renderFaceYNeg(block, 0, 0, 0, icon);		    
	    tes.draw();
	    
	    tes.startDrawingQuads();
		tes.setNormal(0.0F, 1.0F, 0.0F);	
		tes.setColorOpaque_I(color - 0x202020);
	    renderer.renderFaceYPos(block, 0, 0, 0, icon);		    
	    tes.draw();
	    
	    tes.startDrawingQuads();
		tes.setNormal(0.0F, 0, -1.0F);	
		tes.setColorOpaque_I(color);
	    renderer.renderFaceZNeg(block, 0, 0, 0, icon);		    
	    tes.draw();
	    
	    tes.startDrawingQuads();
		tes.setNormal(0.0F, 0.0F, 1.0F);
		tes.setColorOpaque_I(color);
	    renderer.renderFaceZPos(block, 0, 0, 0, icon);		    
	    tes.draw();
	    
	    tes.startDrawingQuads();
		tes.setNormal(-1.0F, 0.0F, 0.0F);	
		tes.setColorOpaque_I(color);
	    renderer.renderFaceXNeg(block, 0, 0, 0, icon);		    
	    tes.draw();
	    
	    tes.startDrawingQuads();
		tes.setNormal(1.0F, 0.0F, 0.0F);
		tes.setColorOpaque_I(color);
	    renderer.renderFaceXPos(block, 0, 0, 0, icon);		    
	    tes.draw();
	    
	    GL11.glPopMatrix();
	}
}
