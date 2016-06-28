package tm.fissionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tm.fissionwarfare.tileentity.base.TileEntityInventoryBase;

public class SlotDisplay extends Slot {

	public SlotDisplay(IInventory inv, TileEntityInventoryBase tileEntity, int id, int x, int y, Item... filters) {
		super(inv, id, x, y);
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return false;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}
