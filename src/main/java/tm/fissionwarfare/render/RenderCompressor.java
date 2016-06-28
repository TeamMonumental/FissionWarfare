package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import tm.fissionwarfare.Reference;

public class RenderCompressor extends ModelBiped {

	private IModelCustom model;
	private ResourceLocation texture;

	public RenderCompressor() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":obj/compressor.obj"));
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/compressor.png");
	}

	@Override
	public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
				
		if (entity instanceof EntityPlayer) {
			
			GL11.glPushMatrix();	
			
			GL11.glTranslatef(0, 0.4F, 0.1F);
			GL11.glScalef(0.35F, 0.35F, 0.35F);
			
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glRotatef(180, 0, 1, 0);
			
			if (isSneak) {
				GL11.glRotatef(30, 1, 0, 0);
				GL11.glTranslatef(0, 0, -0.6F);
			}
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			this.model.renderAll();
			
			GL11.glPopMatrix();
		}
	}
}
