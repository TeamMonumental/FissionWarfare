package tm.fissionwarfare.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tm.fissionwarfare.entity.EntityExplosive;

public class RenderExplosive extends Render {

	private RenderBlocks blockRenderer = new RenderBlocks();

	public RenderExplosive() {
		this.shadowSize = 0.5F;
	}

	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {

		EntityExplosive entityExplosive = (EntityExplosive) entity;
		Block block = entityExplosive.block;

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 0.5F, (float) z);

		float f3;

		if ((float) entityExplosive.fuse - f2 + 1.0F < 10.0F) {

			f3 = 1.0F - ((float) entityExplosive.fuse - f2 + 1.0F) / 10.0F;

			if (f3 < 0.0F) {
				f3 = 0.0F;
			}

			if (f3 > 1.0F) {
				f3 = 1.0F;
			}

			f3 *= f3;
			f3 *= f3;
			float f4 = 1.0F + f3 * 0.3F;
			GL11.glScalef(f4, f4, f4);
		}

		f3 = (1.0F - ((float) entityExplosive.fuse - f2 + 1.0F) / 100.0F) * 0.8F;
		bindTexture(TextureMap.locationBlocksTexture);
		blockRenderer.renderBlockAsItem(block, 0, entityExplosive.getBrightness(f2));

		if (entityExplosive.fuse / 5 % 2 == 0) {

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f3);
			this.blockRenderer.renderBlockAsItem(block, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}