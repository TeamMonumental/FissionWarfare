package tm.fissionwarfare.entity;

import net.minecraft.block.BlockDirectional;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
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
		if (ticksExisted % 40 == 0) {
			doDamage();
		}
		
		if (worldObj.isRemote) {
			doEffects();
		}
	}

	private void doDamage() {
		System.out.println("DAMAGE");
		int posXX = (int)posX;
		int posYY = (int)posY;
		int posZZ = (int)posZ;
		BlockExplosive basicBomb = (BlockExplosive)InitBlocks.basicExplosive;
		EntityExplosive bomb = new EntityExplosive(worldObj, posXX, posYY + 10, posZZ, basicBomb);
			worldObj.spawnEntityInWorld(bomb);
		
	}

	private void doEffects() {
		System.out.println("BOOM");
	}

	@Override
	public void entityInit() {
		System.out.println("INIT");
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
		
	}

}
