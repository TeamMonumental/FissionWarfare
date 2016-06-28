package tm.fissionwarfare.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.Vector3d;

public class EntityBullet extends EntityThrowable {

	public Vector3d velcity;
	public double speed;
	
	public float damage;
	
	public EntityBullet(World world) {
		super(world);
		velcity = new Vector3d(0, 0, 0);
	}
	
	public void shoot(Vector3d vector, Angle2d angle, double speed, float damage) {
		setPosition(vector.x, vector.y, vector.z);
		this.velcity = Vector3d.getVectorFromAngle(angle);
		this.speed = speed;
		this.damage = damage;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (!isDead && ticksExisted > 20 * 30) {
			setDead();
		}
		
		motionX = velcity.x * speed;
		motionY = velcity.y * speed;
		motionZ = velcity.z * speed;
		
		moveEntity(motionX, motionY, motionZ);
	}

	@Override
	public void onImpact(MovingObjectPosition pos) {
		
		Entity entity = pos.entityHit;
		
		if (!isDead && entity != null && entity instanceof EntityLivingBase) {
			
			EntityLivingBase living = (EntityLivingBase) entity;
			
			living.attackEntityFrom(DamageSource.generic, damage);
			
			setDead();
		}
	}
}
