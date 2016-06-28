package tm.fissionwarfare.tileentity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tm.fissionwarfare.tileentity.ITileEntityGuiHandler;

public abstract class TileEntityInventoryBase extends TileEntityBase implements ISidedInventory, ITileEntityGuiHandler {
	
	public ItemStack[] slots = new ItemStack[getSizeInventory()];
	public Slot[] containerSlots = new Slot[getSizeInventory()];
	
	private int[] inputSlots, sideInputSlots, extractSlots;
	private ItemStack[] filters = new ItemStack[slots.length];
	
	public void setExtractSlots(int... extractSlots) {
		this.extractSlots = extractSlots;
	}
	
	public void setInputSlots(int... inputSlots) {
		this.inputSlots = inputSlots;
	}
	
	public void setSideInputSlots(int... sideInputSlots) {
		this.sideInputSlots = sideInputSlots;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		if (this instanceof ISidedInventory) {
			
		}
		
		if (this.slots[i] != null) {

			ItemStack itemstack;

			if (this.slots[i].stackSize <= j) {

				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;

			} 
			
			else {

				itemstack = this.slots[i].splitStack(j);

				if (this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}

				return itemstack;
			}
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {

		if (this.slots[i] != null) {

			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}

		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		this.slots[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {

			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public String getInventoryName() {
		return null;
	}
	
	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {		
		return containerSlots[slot] != null && containerSlots[slot].isItemValid(stack);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int dir) {
		
		int[] slots = new int[getSizeInventory()];
		
		for (int i = 0; i < slots.length; i++) {
			slots[i] = i;
		}
		
		return slots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int dir) {

		if (dir == 1 && inputSlots != null) {
			
			for (int id : inputSlots) {
			
				if (id == slot) {
					
					return true; 
				}
			}
		}
		
		if (dir > 1 && sideInputSlots != null) {
			
			for (int id : sideInputSlots) {
				
				if (id == slot) return true; 
			}
		}
		
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int dir) {
		
		if (dir == 0 && extractSlots != null) {
		
			for (int id : extractSlots) {
			
				if (id == slot) return true; 
			}
		}
		
		return false;
	}	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	
		for (int i = 0; i < slots.length; i++) {

			NBTTagCompound tempTag = nbt.getCompoundTag("slot_" + i);

			if (!tempTag.getBoolean("null")) {

				slots[i] = ItemStack.loadItemStackFromNBT(tempTag);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	
		for (int i = 0; i < slots.length; i++) {

			NBTTagCompound tempTag = new NBTTagCompound();

			if (slots[i] != null) {

				slots[i].writeToNBT(tempTag);
				tempTag.setBoolean("null", false);
			} 
			
			else tempTag.setBoolean("null", true);

			nbt.setTag("slot_" + i, tempTag);
		}
	}
}
