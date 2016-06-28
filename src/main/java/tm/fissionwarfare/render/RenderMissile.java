package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import tm.fissionwarfare.Reference;

public class RenderMissile extends Render {

	private IModelCustom model;
	private ResourceLocation texture;
	
	public RenderMissile() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":obj/missile.obj"));
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/missile.png");
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y, z);
		GL11.glScalef(0.469F, 0.469F, 0.469F);
		GL11.glRotatef(45, 0, 1, 0);
		
		if (entity.motionY < 0) {
			GL11.glRotatef(180, 1, 0, 0);
		}		
		
		Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture(entity));
		model.renderAll();
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
