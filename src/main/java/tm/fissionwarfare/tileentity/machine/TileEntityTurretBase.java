package tm.fissionwarfare.tileentity.machine;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.gui.GuiTurret;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerTurret;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.MathUtil;
import tm.fissionwarfare.util.math.RaytraceUtil;
import tm.fissionwarfare.util.math.Vector3d;
import tm.fissionwarfare.util.math.RaytraceUtil.HitType;

public abstract class TileEntityTurretBase extends TileEntityEnergyBase implements ISecurity {
	
	private Random rand = new Random();
	
	public SecurityProfile profile = new SecurityProfile();
	public Angle2d angle = new Angle2d(0, 0);	
	public Entity target;
	
	public TileEntityTurretBase() {
		setInputSlots(0);
		setSideInputSlots(0);
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		profile.cleanTeam(worldObj);		
		angle.pitch = MathHelper.clamp_double(angle.pitch, -60, 60);		
		updateBlock();
		
		if (!worldObj.isRemote && enabled) {			
			
			if (!isDone()) {
				progress++;
			}
			
			if (target == null) {
				
				angle.yaw++;
				target = findTarget();				
			} 
			
			else {
				
				angle = getAngleFromTarget();
				
				checkTarget();
				
				if (canFire() && hasEnergyAndAmmo()) {
					fire();
					
					storage.extractEnergy(getEnergyCost(), false);
					
					for (Object o : worldObj.loadedEntityList) {
						
						if (o != null && o instanceof EntityPlayerMP && ((EntityPlayer) o).getDistance(xCoord, yCoord, zCoord) <= 30) {
							FissionWarfare.network.sendTo(new ClientPacketHandler("playsound%" + FWSound.turret_fire.getSoundPath() + "%" + xCoord + "%" + yCoord + "%" + zCoord + "%" + 3F), (EntityPlayerMP)o);
						}						
					}				
										
					decrStackSize(0, 1);
					
					if (!canShellFitInHopper()) {
						
						EntityItem entityItem = new EntityItem(worldObj, xCoord + 0.5F, yCoord + 1F, zCoord + 0.5F, new ItemStack(InitItems.cartridge));						
						worldObj.spawnEntityInWorld(entityItem);
					}
					
					progress = 0;
				}
			}
		}	
	}
	
	public abstract int getEnergyCost();
	
	public abstract Entity findTarget();
	public abstract void checkTarget();
	public abstract boolean canFire();
	public abstract void fire();
	
	public boolean hasEnergyAndAmmo() {
		
		boolean energy = canExtractEnergy(getEnergyCost());
		boolean ammo = getStackInSlot(0) != null;
		
		return energy && ammo;
	}
	
	public Angle2d getAngleFromTarget() {
		return Angle2d.getAngleFromVectors(getTurretVector(), getTargetVector());
	}
	
	public Vector3d getTurretVector() {
		return new Vector3d(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D);
	}
	
	public Vector3d getTargetVector() {
		return new Vector3d(target.posX + 0.5D, target.posY + 1D, target.posZ + 0.5D);
	}
	
	public void updateBlock() {
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public boolean canShellFitInHopper() {
		
		if (worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityHopper) {				
			
			TileEntityHopper hopper = (TileEntityHopper) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
			
			for (int s = 0; s < hopper.getSizeInventory(); s++) {
				
				if (hopper.getStackInSlot(s) == null) {
					hopper.setInventorySlotContents(s, new ItemStack(InitItems.cartridge));
					return true;
				}
				
				else if (hopper.getStackInSlot(s).getItem() == InitItems.cartridge && hopper.getStackInSlot(s).stackSize < hopper.getInventoryStackLimit()) {
					hopper.getStackInSlot(s).stackSize++;
					return true;
				}
			}
		}
		
		return false;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1F, yCoord, zCoord - 1F, xCoord + 2F, yCoord + 2F, zCoord + 2F);
	}
	
	@Override
	public int getMaxEnergy() {
		return 100000;
	}

	@Override
	public int getMaxReceive() {
		return 10000;
	}

	@Override
	public int getMaxExtract() {
		return 10000;
	}
	
	@Override
	public int getMaxProgress() {
		return 20 * 5;
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
		
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerTurret(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiTurret(player, this);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return dir != ForgeDirection.UP;
	}

	@Override
	public SecurityProfile getSecurityProfile() {
		return profile;
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		profile.readFromNBT(nbt);
		
		angle.pitch = nbt.getDouble("pitch");
		angle.yaw = nbt.getDouble("yaw");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		
		profile.writeToNBT(nbt);
		
		nbt.setDouble("pitch", angle.pitch);
		nbt.setDouble("yaw", angle.yaw);
	}
}
