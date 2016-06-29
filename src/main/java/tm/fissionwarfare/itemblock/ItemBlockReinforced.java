package tm.fissionwarfare.itemblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.block.BlockReinforced;

public class ItemBlockReinforced extends ItemBlockMeta {

	public ItemBlockReinforced(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		
		int meta = is.getItemDamage();
		
		int num = 0;
			
		for (int i = 1; i <= 10; i++) {
				
			if (meta >= i * 5) num = i;
		}	
				
		return getUnlocalizedName() + "_" + num;
	}	
}
