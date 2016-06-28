package tm.fissionwarfare.entity;

import cofh.lib.audio.ISoundSource;
import cofh.lib.util.helpers.SoundHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.item.ItemMissile;
import tm.fissionwarfare.missile.MissileData;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.sounds.MissileSound;
import tm.fissionwarfare.util.EffectUtil;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.Vector3d;

public class EntityMissile extends Entity implements IEntityAdditionalSpawnData, ISoundSource {
	
	public enum MissileState {
		LAUNCHING, GOING_UP, GOING_DOWN
	}
	
	public ItemStack missileStack;
	
	public int targetX, targetZ;
	
	public MissileState state = MissileState.LAUNCHING;
	
	public EntityMissile(World world) {
		super(world);
		setSize(1.3F, 6.5F);
	}
	
	public EntityMissile(World world, double x, double y, double z, int targetX, int targetZ, ItemStack missileStack) {
		super(world);
		setPosition(x + 0.5, y + 0.1F, z + 0.5F);
		this.missileStack = missileStack;
		this.targetX = targetX;
		this.targetZ = targetZ;
	}

	@Override
	public void onUpdate() {		
		super.onUpdate();
		
		MissileData missileData = MissileData.getDataFromItem(missileStack);
		
		int speed = (missileData.getFuelTier() + 1);
		
		noClip = !(state == MissileState.GOING_DOWN);
		
		moveEntity(motionX, motionY, motionZ);
		
		if (state == MissileState.GOING_DOWN && onGround) {		
			
			if (missileData != null && missileData.getExplosionType() != null) {
				
				IExplosionType explosion = missileData.getExplosionType().getExplosionType();
												
				explosion.setup(worldObj, getVector());
					
				if (!worldObj.isRemote) {
					
					explosion.doBlockDamage();
					explosion.doPlayerDamage();
				}
			
				explosion.doEffects();		
			}
			
			setDead();
		}
		
		if (state == MissileState.LAUNCHING && motionY < 3) {
			
			motionY += (0.001F * (speed * 0.3D));
			
			if (motionY >= 0.15F) {
				
				FWSound.missile_fire.play(worldObj, posX, posY, posZ, 3F, 1F);
				
				if (worldObj.isRemote) {
					doBurstEffect();				
				}
				
				state = MissileState.GOING_UP;
			}
		}
		
		if (state == MissileState.GOING_UP && motionY < 3) {
			
			motionY += 0.2F;
		}
			
		if (state == MissileState.GOING_UP && posY > 300) {
				
			setPosition(targetX + 0.5F, 300, targetZ + 0.5F);
			motionY = -speed;
			state = MissileState.GOING_DOWN;
		}
		
		if (worldObj.isRemote) {
			doEffects();
		}
	}
	
	@SideOnly(Side.CLIENT)	
	public void doBurstEffect() {
		
		for (int i = 0; i < 360; i++) {
			
			double velX = (double)(-MathHelper.sin(i / 180.0F * (float)Math.PI));
	        double velZ = (double)(MathHelper.cos(i / 180.0F * (float)Math.PI));
			
			EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, posX, posY - 0.5D, posZ, velX, MathHelper.getRandomDoubleInRange(rand, -0.2D, 0D), velZ));
			++i;
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, posX, posY - 0.5D, posZ, velX, MathHelper.getRandomDoubleInRange(rand, -0.2D, 0D), velZ));
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void doEffects() {
		
		for (int i = 0; i < 6; i++) {

			double yMotion = (state == MissileState.GOING_DOWN) ? 0.5D : -0.5D;
			
			double offset = 0.5D;
			double yOffset = (state == MissileState.GOING_DOWN) ? 1D : 0;
			
			double randX = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0.1D);
			double randZ = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0.1D);
			
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, posX, posY + yOffset, posZ, randX, yMotion * 2, randZ));			
			
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, posX + offset, posY + yOffset, posZ, randX, yMotion, randZ));			
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, posX - offset, posY + yOffset, posZ, randX, yMotion, randZ));
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, posX, posY + yOffset, posZ + offset, randX, yMotion, randZ));
			EffectUtil.spawnEffect(new EntityMissileSmokeFX(worldObj, posX, posY + yOffset, posZ - offset, randX, yMotion, randZ));					
			
			if (i % 5 == 0) {
				
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, posX, posY + yOffset, posZ, randX, yMotion * 2, randZ));
				
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, posX + offset, posY + yOffset, posZ, randX, yMotion, randZ));
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, posX - offset, posY + yOffset, posZ, randX, yMotion, randZ));
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, posX, posY + yOffset, posZ + offset, randX, yMotion, randZ));
				EffectUtil.spawnEffect(new EntityMissileFlameFX(worldObj, posX, posY + yOffset, posZ - offset, randX, yMotion, randZ));
			}
		}
	}
	
	public Vector3d getVector() {
		return new Vector3d(posX, posY, posZ);
	}
	
	@Override
	protected void entityInit() {
		
		if (worldObj.isRemote) {
			SoundHelper.playSound(getSound());
		}
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {

		targetX = tag.getInteger("targetX");
		targetZ = tag.getInteger("targetZ");
		
		state = MissileState.valueOf(tag.getString("state"));
		
		NBTTagCompound stackTag = tag.getCompoundTag("stackData");
		
		missileStack = ItemStack.loadItemStackFromNBT(stackTag);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		
		tag.setInteger("targetX", targetX);
		tag.setInteger("targetZ", targetZ);
		
		tag.setString("state", state.name());
		
		NBTTagCompound stackTag = new NBTTagCompound();
		tag.setTag("stackData", stackTag);
		
		if (missileStack != null) missileStack.writeToNBT(stackTag);
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

	@Override
	@SideOnly(Side.CLIENT)
	public ISound getSound() {
		return new MissileSound(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldPlaySound() {
		return !isDead;
	}
}