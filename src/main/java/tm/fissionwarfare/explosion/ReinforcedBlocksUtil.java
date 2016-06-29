package tm.fissionwarfare.explosion;

import java.util.List;

import net.minecraft.block.Block;
import tm.fissionwarfare.block.BlockReinforced;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.ShapeUtil;

public class ReinforcedBlocksUtil {

	public static void generateShockwave(Location location, int r, int damage) {
		
		List<Location> list = ShapeUtil.getSphere(location, r);
		
		for (Location loc : list) {
			damageReinforcedBlocks(loc, damage);
		}
	}
	
	public static void damageReinforcedBlocks(Location location, int damage) {
		
		Block block = location.getBlock();
		int currentMeta = location.getMetadata();
		int newMeta = currentMeta - damage;
		
		if (block instanceof BlockReinforced) {
			
			if (newMeta < 1) {
				
				location.setBlockToAir();
			}
			
			location.setBlockMetadata(newMeta);
		}
	}
}
