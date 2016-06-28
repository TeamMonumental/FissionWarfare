package tm.fissionwarfare.tileentity.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.gui.GuiMissileFactory;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerMissileFactory;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityMissileFactory extends TileEntityEnergyBase {
	
	public int energyCost = 0;
	
	public TileEntityMissileFactory() {
		setInputSlots(0, 1, 2);
		setSideInputSlots(3);
		setExtractSlots(4);
	}
	
	@Override
	public int getMaxEnergy() {
		return 200000;
	}

	@Override
	public int getMaxReceive() {
		return 50000;
	}

	@Override
	public int getMaxExtract() {
		return 200000;
	}

	@Override
	public int getMaxProgress() {
		return 20 * 5;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (isReady()) {						
			progress++;
		}
		
		else progress = 0;
		
		if (isDoneAndReset()) {
			
			storage.extractEnergy(energyCost, false);
			
			ItemStack stack = new ItemStack(InitItems.missile);
			
			MissileData data = new MissileData();
			
			data.setExplosionType(((BlockExplosive)Block.getBlockFromItem(slots[0].getItem())).getExplosion());
			data.setAccuracyTier(slots[1].getItemDamage() + 1);
			data.setFuelTier(slots[2].getItemDamage() + 1);
			
			setInventorySlotContents(4, MissileData.setDataToItem(stack, data));
						
			for (int i = 0; i < 3; i++) {
				decrStackSize(i, 1);
			}
			
			decrStackSize(3, 16);
		}
	}
	
	public boolean isReady() {
		
		if (hasItems()) {
			energyCost = ((slots[1].getItemDamage() + 1) * 20000) + ((slots[2].getItemDamage() + 1) * 20000);
		}
		
		else {
			energyCost = 0;
			return false;
		}
			
		return slots[3] != null && slots[3].stackSize >= 16 && slots[4] == null && canExtractEnergy(energyCost);
	}
	
	public boolean hasItems() {
			
		for (int i = 0; i < 3; i++) {
			
			if (slots[i] == null) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerMissileFactory(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiMissileFactory(getTileContainer(player), player, this);
	}
}
