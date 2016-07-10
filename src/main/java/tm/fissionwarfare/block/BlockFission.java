package tm.fissionwarfare.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.util.math.Location;

public class BlockFission extends BlockBase {

	Random rand = new Random();
	
	public BlockFission() {
		super("fission", Material.rock, 0, 0, Float.MAX_VALUE, Block.soundTypeStone);
		setBlockUnbreakable();
		setTickRandomly(true);
	}
	
	@Override
	public int tickRate(World world) {
		return 10;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
				
		for (int offX = -1; offX <= 1; offX++) {
			
			for (int offY = -1; offY <= 1; offY++) {
				
				for (int offZ = -1; offZ <= 1; offZ++) {
					
					Location loc = new Location(world, x + offX, y + offY, z + offZ);
									
					if (rand.nextInt(2) == 1 && loc.getBlock() != this && loc.getBlock() != Blocks.air) {
					
						loc.setBlock(this);						
					}
				}
			}
		}
	}
}
