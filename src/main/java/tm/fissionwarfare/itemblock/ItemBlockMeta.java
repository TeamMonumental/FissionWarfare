package tm.fissionwarfare.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMeta extends ItemBlockWithMetadata {

	public ItemBlockMeta(Block block) {
		super(block, block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return getUnlocalizedName() + "_" + is.getItemDamage();
	}
}
