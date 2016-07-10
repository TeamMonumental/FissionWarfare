package tm.fissionwarfare.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tm.fissionwarfare.util.FissionUtil;
import tm.fissionwarfare.util.math.Location;

public class EntityFissionHeart extends Entity implements IBossDisplayData {
	
	private float life = getMaxHealth();
	private int range = 3;
	
	public EntityFissionHeart(World world) {
		super(world);
		setSize(0, 0);
	}
	
	public EntityFissionHeart(World world, double x, double y, double z) {
		super(world);
		setPosition(x, y, z);
	}

	@Override
	public float getMaxHealth() {
		return 7500;
	}

	@Override
	public float getHealth() {
		return life;
	}
		
	@Override
	public void entityInit() {
		
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
			
		if (life <= 0) {
			
			if (ticksExisted % 30 == 0) {
				removeFission();
			}
		}
		
		else life--;
		
		if (worldObj.isRemote) {
			doEffects();
		}
	}
	
	private void removeFission() {
		
		FissionUtil.removeFission(new Location(worldObj, (int)posX, (int)posY, (int)posZ), range);
		
		if (worldObj.isRemote) {
			worldObj.playSoundAtEntity(this, "mob.zombie.remedy", 1, 1);
		}
		
		range += 2;		
		
		if (range > 75) {
			setDead();
		}
	}

	private void doEffects() {
	
		BossStatus.setBossStatus(this, false);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
		life = nbt.getFloat("lifeLeft");
		range = nbt.getInteger("range");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
		nbt.setFloat("lifeLeft", life);
		nbt.setInteger("range", range);
	}
}
