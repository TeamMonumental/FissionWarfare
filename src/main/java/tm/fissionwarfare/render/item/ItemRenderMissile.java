package tm.fissionwarfare.render.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;

public class ItemRenderMissile extends ItemRenderBase {

	public ItemRenderMissile() {
		super("missile");
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public void renderForAll() {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public void renderInventory() {
			
		GL11.glScalef(0.3F, 0.3F, 0.3F);
		
		GL11.glRotatef(-50, 1, 0, 0);
		GL11.glRotatef(-20, 1, 1, 0);
		
		GL11.glTranslatef(0, -6F, 0);
	}
	
	@Override
	public void renderFloatingEntity() {}

	@Override
	public void renderFirstPerson() {
						
		GL11.glScalef(0.4F, 0.4F, 0.4F);
		
		GL11.glRotatef(100, 1, 0, 0);
		GL11.glRotatef(-65, 0, 1, 0);
		GL11.glRotatef(100, 0, 0, 1);
		
		GL11.glTranslatef(-2.2F, -5.5F, -2F);
	}

	@Override
	public void renderThirdPerson() {
		
		GL11.glScalef(0.9F, 0.9F, 0.9F);
		
		GL11.glRotatef(12, 1, 0, 0);
		GL11.glRotatef(12, 0, 1, 0);
		GL11.glRotatef(10, 0, 0, 1);
		
		GL11.glTranslatef(0.95F, -6, 0.4F);
	}
}
