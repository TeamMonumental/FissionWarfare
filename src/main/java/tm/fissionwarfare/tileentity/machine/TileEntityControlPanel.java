package tm.fissionwarfare.tileentity.machine;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.tileentity.base.TileEntityBase;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityControlPanel extends TileEntityBase implements ISecurity {
	
	public SecurityProfile profile = new SecurityProfile();
	
	public int targetX, targetZ;

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
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
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		profile.readFromNBT(nbt);
		
		targetX = nbt.getInteger("targetX");
		targetZ = nbt.getInteger("targetZ");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		profile.writeToNBT(nbt);
		
		nbt.setInteger("targetX", targetX);
		nbt.setInteger("targetZ", targetZ);
	}
}
