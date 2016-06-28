package tm.fissionwarfare.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.Reference;

public class InitTabs {

	public static final CreativeTabs tabMain = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return InitItems.circuit;
		}
		
		public ItemStack getIconItemStack() {
			return new ItemStack(getTabIconItem(), 1, 4);
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return Reference.MOD_NAME + " - Main";
		}
	};
	
	public static final CreativeTabs tabWarfare = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return InitItems.missile;
		}
		
		public ItemStack getIconItemStack() {
			return new ItemStack(getTabIconItem(), 1, 4);
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return Reference.MOD_NAME + " - Warfare";
		}
	};
}
