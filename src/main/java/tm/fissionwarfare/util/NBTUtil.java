package tm.fissionwarfare.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtil {

	public static NBTTagCompound getNBT(ItemStack is) {
		
		if (is.getTagCompound() == null) {
			is.setTagCompound(new NBTTagCompound());
		}

		return is.getTagCompound();
	}
}
