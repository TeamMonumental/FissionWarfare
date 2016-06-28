package tm.fissionwarfare.render.item;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;

public class ItemRenderShotgun extends ItemRenderBase {

	public ItemRenderShotgun() {
		super("shotgun", "guns/shotgun");
	}

	@Override
	public void renderForAll() {
		
	}

	@Override
	public void renderInventory() {
		GL11.glRotatef(-55, 1, 0, 0);
		GL11.glRotatef(-70, 0, 1, 0);
		GL11.glRotatef(-10, 1, 0, 1);
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glTranslatef(0, -0.2F, 0.45F);
	}

	@Override
	public void renderFloatingEntity() {
		
	}

	@Override
	public void renderFirstPerson() {
		GL11.glRotatef(23, 0, 0, 1);
		GL11.glRotatef(77, 0, 1, 0);
		GL11.glRotatef(0, 1, 0, 0);
		GL11.glTranslatef(0.3F, 0, 1.5F);
	}

	@Override
	public void renderThirdPerson() {
		
		GL11.glRotatef(10, 1, 0, 0);
		GL11.glRotatef(12, 0, 1, 0);
		GL11.glRotatef(15, 0, 0, 1);
		
		GL11.glTranslatef(0.6F, -0.3F, 1.1F);
	}	
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.INVENTORY;
	}
}
