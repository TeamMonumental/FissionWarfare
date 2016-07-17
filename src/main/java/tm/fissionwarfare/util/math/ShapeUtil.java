package tm.fissionwarfare.util.math;

import java.util.ArrayList;
import java.util.List;

public class ShapeUtil {

	public static List<Location> getSphere(Location location, int r) {
		
		return getHSphere(location, 0, r);
	}
	
	public static List<Location> getHSphere(Location location, int minR, int maxR) {
		
		List<Location> list = new ArrayList<Location>();
		
		for (int x = -maxR; x <= maxR; x++) {
			
			for (int y = -maxR; y <= maxR; y++) {
				
				for (int z = -maxR; z <= maxR; z++) {
					
					int rx = location.x + x;
					int ry = location.y + y;
					int rz = location.z + z;
					
					Location loc = new Location(location.world, rx, ry, rz);
					
					if (location.getDistance(loc) <= maxR && location.getDistance(loc) >= minR) {
						
						list.add(loc);
					}
				}
			}
		}
		
		return list;
	}
}
