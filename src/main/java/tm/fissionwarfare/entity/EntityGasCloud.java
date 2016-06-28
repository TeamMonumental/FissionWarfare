package tm.fissionwarfare.entity;

import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGasCloud extends Entity {

	private static final double MAX_LIFE = 20 * 10;
	private static final double DAMAGE = 3;	
	private double range;
	
	public EntityGasCloud(World world) {
		super(world);
		setSize(0, 0);
	}

	public EntityGasCloud(World world, double x, double y, double z) {
		super(world);
		setPosition(x, y, z);
	}

	@Override
	public void entityInit() {}

	@Override
	public void onUpdate() {		
		super.onUpdate();
				
		range = 10 + (ticksExisted * (0.05));

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

		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>(worldObj.loadedEntityList);

		for (Object obj : list) {

			if (obj instanceof EntityLivingBase) {

				EntityLivingBase living = (EntityLivingBase) obj;
				
				if (living.getDistance(posX, posY, posZ) <= range) {

					living.attackEntityFrom(DamageSource.magic, (float) DAMAGE);
					living.addPotionEffect(new PotionEffect(Potion.poison.id, 250, 0));
					living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300, 2));
					living.addPotionEffect(new PotionEffect(Potion.confusion.id, 250, 0));
					living.addPotionEffect(new PotionEffect(Potion.blindness.id, 300, 2));
				}
			}
		}
	}
	
	private void doEffects() {
		
		double x = posX + (rand.nextDouble() * (range * 2)) - range;
		double y = posY;
		double z = posZ + (rand.nextDouble() * (range * 2)) - range;
		
		double xx = posX + (rand.nextDouble() * (range * 1.5)) - range;
		double yy = posY;
		double zz = posZ + (rand.nextDouble() * (range * 1.5)) - range;
		
		for(int i = 1; i < range; i++){
			
			worldObj.spawnParticle("hugeexplosion", x, y, z, 0.0, 0.0, 0.0);
			worldObj.spawnParticle("hugeexplosion", xx, yy, zz, 0.0, 0.0, 0.0);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {}
}
