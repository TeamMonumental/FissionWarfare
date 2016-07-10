package tm.fissionwarfare.entity;

import net.minecraft.block.BlockDirectional;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.init.InitBlocks;

public class EntityFlakey extends Entity{
	
	private static final double MAX_LIFE = 20 * 10;

	public EntityFlakey(World world) {
		super(world);
		setSize(0, 0);
	}
	
	public EntityFlakey(World world, double x, double y, double z){
		super(world);
		setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
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
		int posXX = (int)posX;
		int posYY = (int)posY;
		int posZZ = (int)posZ;
		
		
		double velX = net.minecraft.util.MathHelper.getRandomDoubleInRange(rand, -.3D, .3D);
		double velZ = net.minecraft.util.MathHelper.getRandomDoubleInRange(rand, -.3D, .3D);
		
		BlockExplosive basicBomb = (BlockExplosive)InitBlocks.basic_explosive;
		EntityExplosive bomb = new EntityExplosive(worldObj, posXX, posYY + 30, posZZ, basicBomb);
		if (!worldObj.isRemote){
			worldObj.setWorldTime(18000);
			worldObj.setRainStrength(1.0F);
			worldObj.setThunderStrength(1.0F);
			worldObj.spawnEntityInWorld(bomb);
			bomb.addVelocity(velX, .05D, velZ);
		}
	}

	private void doEffects() {

	}

	@Override
	public void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}

}

