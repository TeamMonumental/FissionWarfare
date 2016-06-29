package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.block.BlockReinforced;
import tm.fissionwarfare.proxy.ClientProxy;
import tm.fissionwarfare.util.RenderUtil;

public class RenderReinforcedBlock implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
		
		if (block instanceof BlockReinforced) {
        	
			GL11.glPushMatrix();
			
			int color = 0xFFFFFF - BlockReinforced.getColorDifference(meta);
			
        	RenderUtil.renderInventroyBlock(renderer, block, color);
        	
        	GL11.glPopMatrix();
        }
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		
		int meta = world.getBlockMetadata(x, y, z);
		
        renderer.renderStandardBlock(block, x, y, z);
        
        if (block instanceof BlockReinforced && meta < 5 && meta > 0) {
        	
        	BlockReinforced reinforced = (BlockReinforced)block;
        	
        	GL11.glPushMatrix();
        	
        	renderer.setOverrideBlockTexture(reinforced.broken_overlay[meta - 1]);
        	
        	renderer.renderStandardBlock(reinforced, x, y, z);
        	
        	renderer.clearOverrideBlockTexture();
        	
        	GL11.glPopMatrix();
        }       
        
        return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.REINFORCED_RENDER_ID;
	}
}
