package tm.fissionwarfare.tileentity.base;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import tm.fissionwarfare.util.math.Location;

public class TileEntityBase extends TileEntity {

	public Location getLocation() {
		return new Location(worldObj, xCoord, yCoord, zCoord);
	}
	
	public void update() {
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.func_147479_m(xCoord, yCoord, zCoord);
	}
	
	public void readSyncNBT(NBTTagCompound nbt) {

	}
	
	public void writeSyncNBT(NBTTagCompound nbt) {

	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
	
		writeSyncNBT(syncData);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {		
		readSyncNBT(pkt.func_148857_g());
	}
}
