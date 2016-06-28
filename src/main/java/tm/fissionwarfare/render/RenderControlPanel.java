package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.proxy.ClientProxy;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class RenderControlPanel extends RenderTileEntityBase {

	public RenderControlPanel() {
		super("control_panel");
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f1) {
		
		GL11.glPushMatrix();
				
		GL11.glTranslated(x + 0.5D, y, z + 0.5D);
		GL11.glScaled(0.85D, 0.85D, 0.85D);
		
		GL11.glRotatef(-90 * tileEntity.getBlockMetadata(), 0, 1, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();	
		
		GL11.glPopMatrix();
	}	
}
