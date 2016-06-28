package tm.fissionwarfare.itemblock;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.init.InitTabs;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;
import tm.fissionwarfare.util.math.Location;

public class ItemControlPanel extends ItemBlockLaunchPadAttachment {

	public ItemControlPanel() {
		super("control_panel");
		setCreativeTab(InitTabs.tabWarfare);
	}

	@Override
	public boolean placeAttachment(World world, Location hitLoc) {
		Block block = InitBlocks.control_panel;
		
		Location loc = hitLoc.add(hitLoc.getMetadata(), false);
		
		if (block.canPlaceBlockAt(loc.world, loc.x, loc.y, loc.z)) {
				
			loc.setBlock(block);
			loc.setBlockMetadata(hitLoc.getMetadata());
			playSound(world, loc.x, loc.y, loc.z, block);
			return true;						
		}
		
		return false;
	}
}
