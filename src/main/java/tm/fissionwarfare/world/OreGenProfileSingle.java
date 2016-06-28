package tm.fissionwarfare.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class OreGenProfileSingle extends OreGenProfile {

	private int chance;	
	
	public OreGenProfileSingle(int minY, int maxY, int amount, int chance, Block ore) {
		super(minY, maxY, amount, 1, ore);
		this.chance = chance;
	}
	
	public OreGenProfileSingle(int minY, int maxY, int amount, Block ore) {
		this(minY, maxY, amount, 100, ore);
	}

	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ) {
				
		for (int i = 0; i < amount; i++) {
			
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(maxY - minY) + minY;
			int randPosZ = chunkZ + rand.nextInt(16);
			
			if (rand.nextInt(100 - chance) == 0) {
				
				if (world.getBlock(randPosX, randPosY, randPosZ) == Blocks.stone) world.setBlock(randPosX, randPosY, randPosZ, ore);
			}		
		}		
	}
}
