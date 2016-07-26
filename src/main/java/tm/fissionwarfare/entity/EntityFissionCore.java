package tm.fissionwarfare.entity;

import java.util.concurrent.CopyOnWriteArrayList;

import com.sun.jmx.snmp.InetAddressAcl;

import cofh.core.entity.EntityLightningBoltFake;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tm.fissionwarfare.util.FissionUtil;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.MathUtil;

public class EntityFissionCore extends Entity implements IEntityAdditionalSpawnData, IBossDisplayData {
	
	public float life = getMaxHealth();
	private int range = 5;
	
	public EntityFissionCore(World world) {
		super(world);
		setSize(0.5F, 2.5F);
	}
	
	public EntityFissionCore(World world, double x, double y, double z) {
		super(world);
		setPosition(x, y, z);
	}

	@Override
	public float getMaxHealth() {
		return 5000;
	}

	@Override
	public float getHealth() {
		return life;
	}
		
	@Override
	public void entityInit() {}
	
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
		
		if (ticksExisted % 20 == 0) {
			doDamage();
		}
	}
	
	private void removeFission() {
		
		FissionUtil.removeFission(new Location(worldObj, (int)posX - 1, (int)posY, (int)posZ - 1), range);
		
		if (worldObj.isRemote) {
			worldObj.playSound(posX, posY, posZ, "mob.zombie.remedy", 10, 0.5F + (float)MathUtil.scaleDouble(range, 75, 1), false);
		}
		
		range += 5;		
		
		if (range > 75) {
			setDead();
			worldObj.spawnEntityInWorld(new EntityLightningBolt(worldObj, posX, posY, posZ));
		}
	}
	
	private void doDamage() {

		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>(worldObj.loadedEntityList);

		for (Object obj : list) {

			if (obj instanceof EntityLivingBase) {

				EntityLivingBase living = (EntityLivingBase) obj;
				
				if (living.getDistance(posX, posY, posZ) <= 10) {

					living.addPotionEffect(new PotionEffect(Potion.wither.id, 250, 0));
					living.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 300, 5));
				}
			}
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

	@Override
	public void readSpawnData(ByteBuf buffer) {
		readEntityFromNBT(ByteBufUtils.readTag(buffer));
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound tag = new NBTTagCompound();
		writeEntityToNBT(tag);
		ByteBufUtils.writeTag(buffer, tag);
	}
}
