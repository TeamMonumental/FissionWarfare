package tm.fissionwarfare.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import tm.fissionwarfare.Reference;

public abstract class RenderTileEntityBase extends TileEntitySpecialRenderer {

	public String modelName, textureName;
	public IModelCustom model;
	public ResourceLocation texture;
	
	public RenderTileEntityBase(String modelName, String textureName) {
		this.textureName = textureName;
		this.modelName = modelName;
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":obj/" + modelName + ".obj"));
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/" + textureName + ".png");
	}
	
	public RenderTileEntityBase(String name) {
		this(name, name);
	}
}
