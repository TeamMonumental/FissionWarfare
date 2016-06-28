package tm.fissionwarfare.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.itemblock.ItemBlockMeta;

public class BlockMetaBase extends BlockBase {

	public BlockMetaBase(String imageName, Material material, int harvestLevel, float hardness, float resistance, SoundType stepSound, Class<? extends ItemBlockMeta> itemBlock) {
		super(imageName, material, harvestLevel, hardness, resistance, stepSound, false);
		setCreativeTab(InitTabs.tabMain);
		GameRegistry.registerBlock(this, itemBlock, imageName);
	}
}
