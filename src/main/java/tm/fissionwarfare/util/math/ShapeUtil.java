package tm.fissionwarfare.util.math;

import java.util.ArrayList;
import java.util.List;

public class ShapeUtil {

	public static List<Location> getSphere(Location location, int r) {
		
		List<Location> list = new ArrayList<Location>();
		
		for (int x = -r; x <= r; x++) {
			
			for (int y = -r; y <= r; y++) {
				
				for (int z = -r; z <= r; z++) {
					
					int rx = location.x + x;
					int ry = location.y + y;
					int rz = location.z + z;
					
					Location loc = new Location(location.world, rx, ry, rz);
					
					if (location.getDistance(loc) <= r) {
						
						list.add(loc);
					}
				}
			}
		}
		
		return list;
	}
}
