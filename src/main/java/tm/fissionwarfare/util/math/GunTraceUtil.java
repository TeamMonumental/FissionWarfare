package tm.fissionwarfare.util.math;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.damage.DamageSourceCustom;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.packet.ClientPacketHandler;
import tm.fissionwarfare.util.math.RaytraceUtil.HitType;

public class GunTraceUtil {

	public static void doGunTrace(EntityPlayer shooter, Vector3d origin, Angle2d angle, World world, int damage, double life) {
		
		List<EntityLivingBase> list = getAllLivingEntities(world);
		
		Vector3d velcity = Vector3d.getVectorFromAngle(angle);
				
		Vector3d raytrace = origin.copy();
				
		while (raytrace.distance(origin) <= life) {
			
			raytrace.add(velcity);
			
			FissionWarfare.network.sendToAll(new ClientPacketHandler("spawnparticle%smoke%" + raytrace.x + "%" + raytrace.y + "%" + raytrace.z + "%" + 0 + "%" + 0 + "%" + 0));
			
			for (EntityLivingBase living : list) {
				
				AxisAlignedBB box = living.boundingBox;
				
				Vec3 vec1 = Vec3.createVectorHelper(origin.x, origin.y, origin.z);
				Vec3 vec2 = Vec3.createVectorHelper(raytrace.x, raytrace.y, raytrace.z);
							
				if (living != shooter && box.calculateIntercept(vec2, vec1) != null) {
					
					if (living instanceof EntityPlayer) {
						
						EntityPlayer player = (EntityPlayer)living;
						
						player.attackEntityFrom(new DamageSourceCustom(player.getDisplayName() + " was shot by " + shooter.getDisplayName() + "'s gun."), damage);						
					}
					
					else living.attackEntityFrom(DamageSource.generic, damage);
					
					return;
				}
			}
		}
	}
	
	public static List<EntityLivingBase> getAllLivingEntities(World world) {
		
		List<EntityLivingBase> enttiyList = new ArrayList<EntityLivingBase>();
		
		for (Object obj : world.loadedEntityList) {
			
			if (obj instanceof EntityLivingBase) {
				
				enttiyList.add((EntityLivingBase) obj);
			}
		}
		
		return enttiyList;
	}
}
