package tm.fissionwarfare.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.item.ItemAxeBase;
import tm.fissionwarfare.item.ItemHoeBase;
import tm.fissionwarfare.item.ItemPickaxeBase;
import tm.fissionwarfare.item.ItemShovelBase;
import tm.fissionwarfare.item.ItemSwordBase;

public class ToolSet {
	
	public ItemSwordBase sword;
	public ItemPickaxeBase pickaxe;
	public ItemShovelBase shovel;
	public ItemAxeBase axe;
	public ItemHoeBase hoe;
	
	public ToolSet(String imagePath, ToolMaterial toolMaterial, Item recipeItem, boolean hasCraftingRecipe){
		
		sword = new ItemSwordBase(imagePath + "_sword", toolMaterial);
		pickaxe = new ItemPickaxeBase(imagePath + "_pickaxe", toolMaterial);
		shovel = new ItemShovelBase(imagePath + "_shovel", toolMaterial);
		axe = new ItemAxeBase(imagePath + "_axe", toolMaterial);
		hoe = new ItemHoeBase(imagePath + "_hoe", toolMaterial);
		
		if(hasCraftingRecipe){
			
			GameRegistry.addRecipe(new ItemStack(sword), new Object[] {
				"X", "X", "S", 'S', Items.stick, 'X', recipeItem
			});	
						
			GameRegistry.addRecipe(new ItemStack(pickaxe), new Object[] {
				"XXX", " S ", " S ", 'S', Items.stick, 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {
				"X", "S", "S", 'S', Items.stick, 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(axe), new Object[] {
				"XX", "XS", " S", 'S', Items.stick, 'X', recipeItem
			});			
			
			GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {
				"XX", " S", " S", 'S', Items.stick, 'X', recipeItem
			});	
		}
	}

}
