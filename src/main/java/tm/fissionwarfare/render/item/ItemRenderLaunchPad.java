package tm.fissionwarfare.render.item;

import org.lwjgl.opengl.GL11;

public class ItemRenderLaunchPad extends ItemRenderBase {

	public ItemRenderLaunchPad() {
		super("launch_pad");
	}

	@Override
	public void renderForAll() {
		GL11.glScalef(0.6F, 0.6F, 0.6F);		
	}

	@Override
	public void renderInventory() {
		GL11.glScalef(0.75F, 0.75F, 0.75F);
		GL11.glTranslatef(0, -0.4F, 0);
	}

	@Override
	public void renderFloatingEntity() {}

	@Override
	public void renderFirstPerson() {
		
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		
		GL11.glRotatef(100, 1, 0, 0);
		GL11.glRotatef(-65, 0, 1, 0);
		GL11.glRotatef(100, 0, 0, 1);
		
		GL11.glTranslatef(0.2F, -0.4F, -2F);
	}

	@Override
	public void renderThirdPerson() {
		
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glRotatef(35, 0, 1, 0);
		GL11.glRotatef(15, 0, 0, 1);
		
		GL11.glTranslatef(0.2F, -0.6F, 1.7F);
	}
}
