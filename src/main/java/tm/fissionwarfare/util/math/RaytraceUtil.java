package tm.fissionwarfare.util.math;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RaytraceUtil {
	
	public enum HitType {
		BLOCK, ENTITY, NULL;
	}
	
	public static HitType raytrace(Angle2d angle, Vector3d vector, World world, Block src, Entity target, double distance) {
		
		Vector3d velcity = Vector3d.getVectorFromAngle(angle);
		Vector3d raytrace = vector.copy();
		
		while (vector.distance(raytrace) <= distance) {
			
			raytrace.add(velcity);
			
			if (checkBlock(raytrace, world, src)) {
				return HitType.BLOCK;
			}
			
			Vec3 vec1 = Vec3.createVectorHelper(vector.x, vector.y, vector.z);
			Vec3 vec2 = Vec3.createVectorHelper(raytrace.x, raytrace.y, raytrace.z);
			
			if (target.boundingBox.calculateIntercept(vec1, vec2) != null) {
				return HitType.ENTITY;
			}
		}
		
		return HitType.NULL;
	}
	
	public static boolean checkBlock(Vector3d vector, World world, Block src) {
		
		int x = MathHelper.floor_double(vector.x);
		int y = MathHelper.floor_double(vector.y);
		int z = MathHelper.floor_double(vector.z);
		
		int metadata = world.getBlockMetadata(x, y, z);
		Block block = world.getBlock(x, y, z);
		
		if (block != Blocks.air && block != src) {
			return true;
		}
		
		return false;
	}
}
