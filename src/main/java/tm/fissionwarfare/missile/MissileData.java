package tm.fissionwarfare.missile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.explosion.type.EnumExplosionType;

public class MissileData {

	private int accuracyTier = 0;
	private int fuelTier = 0;
	private EnumExplosionType explosionType;

	public void readFromNBT(NBTTagCompound nbt) {

		accuracyTier = nbt.getInteger("accuracy");
		fuelTier = nbt.getInteger("speed");

		if (nbt.hasKey("explosionType")) {
			explosionType = EnumExplosionType.valueOf(nbt.getString("explosionType"));
		}
	}
	
	public int getMaxBlockDistance(){
		return getFuelTier() * FWConfig.missileBlockRangeMultiplier;
	}

	public void writeToNBT(NBTTagCompound nbt) {

		nbt.setInteger("accuracy", accuracyTier);
		nbt.setInteger("speed", fuelTier);

		if (explosionType != null) {
			nbt.setString("explosionType", explosionType.name());
		}
	}

	public void setAccuracyTier(int accuracy) {
		this.accuracyTier = accuracy;
	}

	public void setFuelTier(int fuel) {
		this.fuelTier = fuel;
	}

	public void setExplosionType(EnumExplosionType explosionType) {
		this.explosionType = explosionType;
	}

	public int getAccuracyTier() {
		return accuracyTier;
	}

	public int getFuelTier() {
		return fuelTier;
	}

	public EnumExplosionType getExplosionType() {
		return explosionType;
	}

	public static MissileData getDataFromItem(ItemStack stack) {

		NBTTagCompound nbt = stack.getTagCompound();

		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		
		MissileData data = new MissileData();

		data.readFromNBT(nbt);

		return data;
	}

	public static ItemStack setDataToItem(ItemStack stack, MissileData data) {

		NBTTagCompound nbt = stack.getTagCompound();
		
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		
		data.writeToNBT(nbt);
		
		stack.setTagCompound(nbt);
		
		return stack;
	}
}
