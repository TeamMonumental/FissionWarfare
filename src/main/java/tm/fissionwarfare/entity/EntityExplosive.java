package tm.fissionwarfare.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.block.BlockExplosive;
import tm.fissionwarfare.explosion.type.EnumExplosionType;
import tm.fissionwarfare.util.math.Vector3d;

public class EntityExplosive extends Entity implements IEntityAdditionalSpawnData {
	
	public BlockExplosive block;
	public int fuse;
	
	public EntityExplosive(World world) {
		super(world);
	}

	public EntityExplosive(World world, int x, int y, int z, BlockExplosive block) {
		this(world);
		this.block = block;
		this.fuse = block.getExplosion().getFuseTime();
		setPosition(x + 0.5D, y + 0.5D, z + 0.5D);
		worldObj.playSoundAtEntity(this, "game.tnt.primed", 2F, 1F);
	}

	@Override
	public void entityInit() {
		preventEntitySpawning = true;
		setSize(0.95F, 0.95F);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
	}
	
	public void onUpdate() {
		
		super.onUpdate();
		
		moveEntity(motionX, motionY, motionZ);
		
		if (!onGround) motionY -= 0.02D;	
		else motionY = 0;

		if (!isDead && fuse <= 0) {
			
			setDead();
			explode();
		}
		
		fuse--;
		worldObj.spawnParticle("smoke", posX, posY + 0.5, posZ, 0, 0, 0);
	}

	private void explode() {
		
		IExplosionType explosion = block.getExplosion().getExplosionType();
		
		explosion.setup(worldObj, getVector());
			
		if (!worldObj.isRemote) {
			explosion.doBlockDamage();
			explosion.doPlayerDamage();
		} 
		
		else explosion.doEffects();
	}
	
	public Vector3d getVector() {
		return new Vector3d(posX, posY, posZ);
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0F;
	}
	
	@Override
	public boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("fuse", fuse);
		nbt.setInteger("block", Block.getIdFromBlock(block));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		fuse = nbt.getInteger("fuse");
		block = (BlockExplosive) Block.getBlockById(nbt.getInteger("block"));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound nbt = new NBTTagCompound();
		writeEntityToNBT(nbt);
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		NBTTagCompound nbt = ByteBufUtils.readTag(additionalData);
		readEntityFromNBT(nbt);
	}
}
