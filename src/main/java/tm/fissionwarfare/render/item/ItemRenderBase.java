package tm.fissionwarfare.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import tm.fissionwarfare.Reference;

public abstract class ItemRenderBase implements IItemRenderer {

	public String modelName, textureName;
	public IModelCustom model;
	public ResourceLocation texture;
	
	public ItemRenderBase(String modelName, String texturePath) {
		this.textureName = texturePath;
		this.modelName = modelName;
		
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":obj/" + modelName + ".obj"));
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/" + texturePath + ".png");
	}
	
	public ItemRenderBase(String name) {
		this(name, name);
	}
	
	public abstract void renderForAll();
	public abstract void renderInventory();
	public abstract void renderFloatingEntity();
	public abstract void renderFirstPerson();
	public abstract void renderThirdPerson();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY;
	}	
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		renderForAll();
		
		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			renderInventory();
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			renderFloatingEntity();
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			renderFirstPerson();
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			renderThirdPerson();
		}	
						
		render();		
	}
	
	private void render() {

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
	}
}
