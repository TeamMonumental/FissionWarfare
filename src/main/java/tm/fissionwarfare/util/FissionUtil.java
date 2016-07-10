package tm.fissionwarfare.util;

import tm.fissionwarfare.block.BlockFission;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.ShapeUtil;

public class FissionUtil {

	public static void removeFission(Location loc, int size) {
		
		for (Location newLoc : ShapeUtil.getSphere(loc, size)) {
			
			if (newLoc.getBlock() instanceof BlockFission) {
				
				newLoc.setBlockToAir();
			}
		}
	}
}
