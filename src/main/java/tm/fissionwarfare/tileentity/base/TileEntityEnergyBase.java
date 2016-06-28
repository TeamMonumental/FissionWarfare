package tm.fissionwarfare.tileentity.base;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityEnergyBase extends TileEntityInventoryBase implements IEnergyHandler {

	public int progress = 0;
	public boolean enabled = true;
	
	public abstract int getMaxEnergy();
	public abstract int getMaxReceive();
	public abstract int getMaxExtract();
	public abstract int getMaxProgress();
	
	public EnergyStorage storage = new EnergyStorage(getMaxEnergy(), getMaxReceive(), getMaxExtract());
	
	public boolean isDone() {
		return progress >= getMaxProgress();
	}
	
	public boolean isDoneAndReset() {
		
		if (isDone()) {
			progress = 0;
			return true;
		}
		
		return false;
	}
	
	public boolean canExtractEnergy(int energy) {
		return storage.getEnergyStored() >= energy;
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}
	
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {	
		return storage.extractEnergy(maxExtract, simulate);
	}
		
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readSyncNBT(nbt);		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeSyncNBT(nbt);
	}
	
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		progress = nbt.getInteger("progress");
		enabled = nbt.getBoolean("enabled");
		storage.readFromNBT(nbt);
	}
	
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);		
		nbt.setInteger("progress", progress);
		nbt.setBoolean("enabled", enabled);
		storage.writeToNBT(nbt);
	}
}
