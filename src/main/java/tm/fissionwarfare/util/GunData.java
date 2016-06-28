package tm.fissionwarfare.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;

public class GunData {
	
	private final ItemStack stack;
	public int time, ammo, scope, usingTicks;
	public boolean reloading;
	
	public GunData(ItemStack stack) {
		this.stack = stack;
		init();
	}
	
	private void init() {
		
		time = NBTUtil.getNBT(stack).getInteger("Time");
		ammo = NBTUtil.getNBT(stack).getInteger("Ammo");		
		scope = NBTUtil.getNBT(stack).getInteger("Scope");
		usingTicks = NBTUtil.getNBT(stack).getInteger("UsingTicks");
		
		reloading = NBTUtil.getNBT(stack).getBoolean("Reloading");
	}
	
	public void flush() {
		
		int clampedTime = MathHelper.clamp_int(time, 0, 512);
		int clampedAmmo = MathHelper.clamp_int(ammo, 0, 512);
		int clampedScope = MathHelper.clamp_int(scope, 0, 512);

		NBTUtil.getNBT(stack).setInteger("Time", clampedTime);
		NBTUtil.getNBT(stack).setInteger("Ammo", clampedAmmo);
		NBTUtil.getNBT(stack).setInteger("Scope", clampedScope);
		NBTUtil.getNBT(stack).setInteger("UsingTicks", usingTicks);
		
		NBTUtil.getNBT(stack).setBoolean("Reloading", reloading);
	}
}
