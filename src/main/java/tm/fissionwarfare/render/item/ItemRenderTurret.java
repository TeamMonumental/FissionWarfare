package tm.fissionwarfare.render.item;

import org.lwjgl.opengl.GL11;

public class ItemRenderTurret extends ItemRenderBase {
	
	public ItemRenderTurret() {
		super("turret");
	}
	
	@Override
	public void renderForAll() {
		GL11.glScalef(0.6F, 0.6F, 0.6F);
	}

	@Override
	public void renderInventory() {
		GL11.glTranslatef(-0.25F, -1F, 0.25F);
	}

	@Override
	public void renderFloatingEntity() {}

	@Override
	public void renderFirstPerson() {
		GL11.glRotatef(25, 0, 0, 1);
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glTranslatef(1.4F, 0F, -0.5F);
	}

	@Override
	public void renderThirdPerson() {
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glRotatef(-60, 1, 0, 0);
		GL11.glRotatef(-45, 0, 1, 0);
		GL11.glRotatef(25, 0, 0, 1);
		
		GL11.glTranslatef(1.4F, -0.5F, 0);
	}
}
