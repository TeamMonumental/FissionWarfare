package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public class BlockBase extends Block {

	public String imageName;
	float pixel = 1F/16F;
	
	public BlockBase(String name, String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {		
		super(material);
		this.imageName = imageName;
		setBlockName(name);
		setBlockTextureName(Reference.MOD_ID + ":" + imageName);
		setHarvestLevel("pickaxe", harvestLevel);
		setStepSound(stepSound);
		setHardness(hardness);
		setResistance(resistance);
		
		if (isRegistered) {
			setCreativeTab(InitTabs.tabMain);
			GameRegistry.registerBlock(this, name);
		}
	}
	
	public BlockBase(String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {
		this(imageName, imageName, material, harvestLevel, hardness, resistance, stepSound, isRegistered);
	}
	
	public BlockBase(String imageName, Material material, int harvestLevel, float hardness, float resistance, Block.SoundType stepSound) {
		this(imageName, imageName, material, harvestLevel, hardness, resistance, stepSound, true);
	}
	
	public BlockBase setBounds(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
		setBlockBounds(xStart * pixel, yStart * pixel, zStart * pixel, xEnd * pixel, yEnd * pixel, zEnd * pixel);
		return this;
	}
}
