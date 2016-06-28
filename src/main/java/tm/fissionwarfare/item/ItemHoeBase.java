package tm.fissionwarfare.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.Item.ToolMaterial;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.init.InitTabs;

public class ItemHoeBase extends ItemHoe {

	private String imagePath;
	private ToolMaterial toolMaterial;

	public ItemHoeBase(String imagePath, ToolMaterial toolMaterial) {
		super(toolMaterial);
		this.imagePath = imagePath;
		this.toolMaterial = toolMaterial;
		setTextureName(Reference.MOD_ID + ":tools/" + imagePath);
		setCreativeTab(InitTabs.tabMain);
		setUnlocalizedName(imagePath);
		GameRegistry.registerItem(this, imagePath);
	}

}
