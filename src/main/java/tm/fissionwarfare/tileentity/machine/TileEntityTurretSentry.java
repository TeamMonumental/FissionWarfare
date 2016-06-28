package tm.fissionwarfare.tileentity.machine;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.api.ISecurity;
import tm.fissionwarfare.api.SecurityProfile;
import tm.fissionwarfare.damage.DamageSourceTeam;
import tm.fissionwarfare.gui.GuiTurret;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitItems;
import tm.fissionwarfare.inventory.ContainerTurret;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.tileentity.base.TileEntityEnergyBase;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.MathUtil;
import tm.fissionwarfare.util.math.RaytraceUtil;
import tm.fissionwarfare.util.math.Vector3d;
import tm.fissionwarfare.util.math.RaytraceUtil.HitType;

public class TileEntityTurretSentry extends TileEntityTurretBase {

	public static final int RANGE = 20;
	public static final float DAMAGE = 4;

	@Override
	public Entity findTarget() {

		for (Object object : worldObj.loadedEntityList) {

			if (object instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer)object;
				
				if (player.getDistance(xCoord, yCoord, zCoord) < RANGE && !player.capabilities.isCreativeMode && !profile.isSameTeam(player)) {
					
					return player;
				}
			}
		}

		return null;
	}

	@Override
	public void checkTarget() {
		
		EntityPlayer player = (EntityPlayer)target;
		
		if (target.getDistance(xCoord, yCoord, zCoord) >= RANGE || player.isDead || player.capabilities.isCreativeMode || profile.isSameTeam(player)) {
			target = null;
		}
	}

	@Override
	public boolean canFire() {
		
		if (target != null && target.hurtResistantTime <= 0 && isDone() && RaytraceUtil.raytrace(getAngleFromTarget(), getTurretVector(), worldObj, InitBlocks.sentry_turret, target, RANGE) != HitType.BLOCK) {

			Angle2d angle = getAngleFromTarget();

			if (angle.pitch > -60 && angle.pitch < 60) {

				return true;
			}
		}

		return false;
	}

	@Override
	public void fire() {

		if (target != null && target instanceof EntityLivingBase) {

			EntityLivingBase livingBase = (EntityLivingBase) target;
			livingBase.attackEntityFrom(new DamageSourceTeam((EntityPlayer) livingBase, profile.getTeamName() + "'s Sentry Turret"), DAMAGE);
			//livingBase.setFire(3);
		}
	}
	

	@Override
	public int getEnergyCost() {
		return 1000;
	}

	@Override
	public int getMaxProgress() {
		return 20;
	}
}