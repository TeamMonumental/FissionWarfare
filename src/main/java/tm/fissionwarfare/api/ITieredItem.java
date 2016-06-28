package tm.fissionwarfare.api;

import net.minecraft.item.ItemStack;

public interface ITieredItem {

	public int getTier(ItemStack stack);
}
