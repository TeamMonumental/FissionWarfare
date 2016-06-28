package tm.fissionwarfare.inventory;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public class SlotFilter extends Slot {
	
	public List<Item> itemFilters;
	public List<Object> objFilters;
	
	public SlotFilter(IInventory inv, TileEntityInventoryBase tileEntity, int id, int x, int y, Item... filters) {
		super(inv, id, x, y);
			
		this.itemFilters = new ArrayList<Item>();		
		
		for (Item item : filters) {
			this.itemFilters.add(item);
		}
		
		tileEntity.containerSlots[id] = this;
	}
	
	public SlotFilter(IInventory inv, TileEntityInventoryBase tileEntity, int id, int x, int y, Object... filters) {
		super(inv, id, x, y);
					
		this.objFilters = new ArrayList<Object>();		
		
		for (Object obj : filters) {
			this.objFilters.add(obj);
		}
		
		tileEntity.containerSlots[id] = this;
	}
	
	public boolean isFilter(ItemStack stack) {
				
		if (this.itemFilters != null) {
			
			for (Item itemFilter : this.itemFilters) {
				
				if (itemFilter == stack.getItem()) return true; 
			}		
		}		
		
		if (this.objFilters != null) {
			
			for (Object objectFilter : this.objFilters) {	
				
				if (stack.getItem() instanceof ItemBlock) {
					
					ItemBlock item = (ItemBlock)stack.getItem();
					
					if (item.field_150939_a.getClass().equals(objectFilter) || item.field_150939_a.getClass().isInstance(objectFilter)) return true;
				}
				
				if (stack.getItem().getClass().isInstance(objectFilter)) return true;
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return isFilter(stack);
	}
}
