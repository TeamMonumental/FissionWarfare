package tm.fissionwarfare.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class InitToolMaterials {
	
	public static Item.ToolMaterial toolMaterialSteel;
	
	public static void init() {
		toolMaterialSteel = EnumHelper.addToolMaterial("Steel", 2, 500, 6.0F, 2.0F, 14);
	}

}
