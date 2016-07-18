package tm.fissionwarfare.entity;

import net.minecraft.block.BlockDirectional;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.init.InitBlocks;

public class EntityFlakey extends Entity {
	
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
			
			if (!worldObj.isRemote){
				
				WorldInfo worldinfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();
				
				worldinfo.setThundering(false);
				worldinfo.setRaining(false);				
			}
	        
			setDead();
		}
		
		if (ticksExisted % 20 == 0) {
			doDamage();
		}
	}

	private void doDamage() {
		
		int posXX = (int)posX;
		int posYY = (int)posY;
		int posZZ = (int)posZ;	
		
		double velX = MathHelper.getRandomDoubleInRange(rand, -0.3D, 0.3D);
		double velZ = MathHelper.getRandomDoubleInRange(rand, -0.3D, 0.3D);
		
		BlockExplosive basicBomb = (BlockExplosive)InitBlocks.basic_explosive;
		EntityExplosive bomb = new EntityExplosive(worldObj, posXX, posYY + 30, posZZ, basicBomb);
		
		if (!worldObj.isRemote){
			
			worldObj.setWorldTime(18000);
			
			WorldInfo worldinfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();
			
	        worldinfo.setRaining(true);
	        worldinfo.setThundering(true);
	        worldinfo.setThunderTime(40);
			
			worldObj.spawnEntityInWorld(bomb);
			bomb.addVelocity(velX, 0.05D, velZ);
		}
	}
	
	@Override
	public void entityInit() {
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
	}
}

