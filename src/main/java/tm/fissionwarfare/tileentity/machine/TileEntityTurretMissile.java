package tm.fissionwarfare.tileentity.machine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import tm.fissionwarfare.damage.DamageSourceTeam;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.entity.EntityMissile.MissileState;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.RaytraceUtil;
import tm.fissionwarfare.util.math.RaytraceUtil.HitType;

public class TileEntityTurretMissile extends TileEntityTurretBase {

	public static final int RANGE = 150;
	public static final float DAMAGE = 1.0F;
	
	@Override
	public Entity findTarget() {
				
		for (Object object : worldObj.loadedEntityList) {

			if (object instanceof EntityMissile) {
				
				EntityMissile missile = (EntityMissile)object;
				
				if (missile.getDistance(xCoord, yCoord, zCoord) < RANGE && missile.state == MissileState.GOING_DOWN) {					
					return missile;
				}
			}
		}

		return null;
	}

	@Override
	public void checkTarget() {
		
		EntityMissile missile = (EntityMissile)target;
		
		if (target.getDistance(xCoord, yCoord, zCoord) >= RANGE || missile.isDead) {
			target = null;
		}
	}

	@Override
	public boolean canFire() {
		
		if (target != null && isDone() && RaytraceUtil.raytrace(getAngleFromTarget(), getTurretVector(), worldObj, InitBlocks.sentry_turret, target, RANGE) != HitType.BLOCK) {

			Angle2d angle = getAngleFromTarget();

			if (angle.pitch > -60 && angle.pitch < 60) {

				return true;
			}
		}

		return false;
	}

	@Override
	public void fire() {
		
		if (target != null && target instanceof EntityMissile) {

			EntityMissile missile = (EntityMissile)target;
			missile.health -= DAMAGE;
		}
	}

	@Override
	public int getEnergyCost() {
		return 1000000;
	}
	
	@Override
	public String getName() {
		return "Missile";
	}
	
	@Override
	public int getMaxProgress() {
		return 10;
	}

	@Override
	public int getMaxEnergy() {
		return 10000000;
	}
}
