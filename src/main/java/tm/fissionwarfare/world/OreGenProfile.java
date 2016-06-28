package tm.fissionwarfare.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class OreGenProfile {

	public int minY, maxY, amount, size;
	public Block ore;

	public OreGenProfile(int minY, int maxY, int amount, int size, Block ore) {
		this.minY = minY;
		this.maxY = maxY;
		this.amount = amount;
		this.size = size;
		this.ore = ore;
	}

	public void generate(World world, Random rand, int chunkX, int chunkZ) {

		WorldGenMinable minable = new WorldGenMinable(ore, size);
		
		for (int i = 0; i < amount; i++) {

			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(maxY - minY) + minY;
			int randPosZ = chunkZ + rand.nextInt(16);

			minable.generate(world, rand, randPosX, randPosY, randPosZ);
		}
	}
}
