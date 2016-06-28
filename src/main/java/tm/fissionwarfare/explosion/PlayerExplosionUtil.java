package tm.fissionwarfare.explosion;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tm.fissionwarfare.util.math.Vector3d;

public class PlayerExplosionUtil {

	private static List<EntityLivingBase> getLivingEntities(World world) {

		List<EntityLivingBase> list = new ArrayList<EntityLivingBase>();

		for (Object object : world.loadedEntityList) {

			if (object instanceof EntityLivingBase) {

				list.add((EntityLivingBase) object);
			}
		}

		return list;
	}

	public static void doLivingDamage(World world, Vector3d vector, double range, float maxDamage) {

		List<EntityLivingBase> list = getLivingEntities(world);
		
		for (EntityLivingBase living : list) {

			double distance = living.getDistance(vector.x, vector.y, vector.z);

			if (distance <= range) {
				
				living.attackEntityFrom(DamageSource.generic, maxDamage);
			}
		}
	}
}
