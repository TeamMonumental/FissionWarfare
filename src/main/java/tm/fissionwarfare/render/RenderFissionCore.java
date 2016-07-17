package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import tm.fissionwarfare.Reference;

public class RenderFissionCore extends Render {

	private IModelCustom model;
	private ResourceLocation texture;
	
	public RenderFissionCore() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":obj/fission_core.obj"));
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/fission_core.png");
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y, z);
		
		GL11.glRotatef(45, 0, 1, 0);
				
		GL11.glRotatef(entity.ticksExisted % 360, 0, 1, 0);			
		
		Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture(entity));
		model.renderAll();
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
