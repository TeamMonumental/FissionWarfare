package tm.fissionwarfare.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import tm.fissionwarfare.config.FWConfig;
import tm.fissionwarfare.init.InitBlocks;

public class WorldGenOre implements IWorldGenerator {
	
	private OreGenProfile sulfurGen = new OreGenProfile(11, 30, 10, 8, InitBlocks.sulfur_ore);
	private OreGenProfile quartzGen = new OreGenProfile(20, 60, 10, 4, InitBlocks.quartz_ore);
	private OreGenProfile uraniumGen = new OreGenProfileSingle(5, 25, 1, 50, InitBlocks.uranium_ore);
	
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.dimensionId == 0) {
			generateSurface(world, rand, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
				
		if (FWConfig.enableSulfurGen) sulfurGen.generate(world, rand, chunkX, chunkZ);
		if (FWConfig.enableQuartzGen) quartzGen.generate(world, rand, chunkX, chunkZ);
		if (FWConfig.enableUraniumGen) uraniumGen.generate(world, rand, chunkX, chunkZ);		
	}
}
