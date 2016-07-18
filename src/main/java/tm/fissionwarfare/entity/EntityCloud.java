package tm.fissionwarfare.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityCloud extends Entity {
	
	private static final double MAX_LIFE = 300;
	private double range;

	public EntityCloud(World world) {
		super(world);
		setSize(0, 0);
	}
	
	public EntityCloud(World world, double x, double y, double z) {
		super(world);
		setPosition(x, y, z);
	}

	@Override
	public void entityInit() {}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		range = 5 + (ticksExisted * (0.05));
		
		if (ticksExisted >= MAX_LIFE) {
			setDead();
		}
		
		if (ticksExisted % 20 == 0) {
			doDamage();
		}
		
		if (worldObj.isRemote) {
			doEffects();
		}
	}

	private void doDamage() {
		
	}

	private void doEffects() {
		
		double x = posX + (rand.nextDouble() * (range * 1.5)) - range;
		double y = posY;
		double z = posZ + (rand.nextDouble() * (range * 1.5)) - range;
		
		double xx = posX + (rand.nextDouble() * (range * 1.)) - range;
		double yy = posY;
		double zz = posZ + (rand.nextDouble() * (range * 1.)) - range;
		
		for(int i = 1; i < range; i++){
			
			worldObj.spawnParticle("hugeexplosion", x, y, z, 0.0, 0.0, 0.0);
			worldObj.spawnParticle("hugeexplosion", xx, yy, zz, 0.0, 0.0, 0.0);
		}
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}

}
