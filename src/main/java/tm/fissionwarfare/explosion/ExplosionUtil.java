package tm.fissionwarfare.explosion;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IReinforcedBlock;
import tm.fissionwarfare.util.math.Angle2d;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.Vector3d;

public class ExplosionUtil {
	
	private static List<Location> effectedBlocks = new ArrayList<Location>();
	
	public static List<Location> getEffectedExplosionBlocks(World world, Vector3d vector, double size, int step) {
		
		effectedBlocks.clear();
		
		for (int yaw = 0; yaw < 360; yaw += step) {

			for (int pitch = -90; pitch <= 90; pitch += step) {

				Angle2d angle = new Angle2d(pitch, yaw);
				effectedBlocks.addAll(generateExplosionRay(vector, angle, world, size));
			}
		}
		
		return effectedBlocks;
	}
	
	public static void generateExplosion(World world, Vector3d vector, double size, int step) {
		
		for (int yaw = 0; yaw < 360; yaw += step) {

			for (int pitch = -90; pitch <= 90; pitch += step) {

				Angle2d angle = new Angle2d(pitch, yaw);
				List<Location> list = generateExplosionRay(vector, angle, world, size);
				
				for (Location location : list) {
					processBlock(location);
				}
			}
		}
	}
	
	private static void processBlock(Location location) {	
		
		location.setBlockToAir();		
	}

	private static List<Location> generateExplosionRay(Vector3d vector, Angle2d angle, World world, double distance) {
		
		List<Location> list = new ArrayList<Location>();
		
		Vector3d velcity = vector.getVectorFromAngle(angle);
		Vector3d raytrace = vector.copy();
				
		while (vector.distance(raytrace) <= distance) {
			
			raytrace.add(velcity);
			Location loc = new Location(world, raytrace);
			
			if (loc.getBlock().getBlockHardness(world, loc.x, loc.y, loc.z) < 0) {
				break;
			}
			
			if (loc.getBlock() instanceof IReinforcedBlock) {
				break;
			}
			
			list.add(loc);
		}
		
		return list;
	}
}
