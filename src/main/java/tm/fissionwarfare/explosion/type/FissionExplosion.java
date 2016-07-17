package tm.fissionwarfare.explosion.type;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityFissionCore;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.ShapeUtil;
import tm.fissionwarfare.util.math.Vector3d;

public class FissionExplosion implements IExplosionType {

	Random rand = new Random();
	
	private World world;
	private Vector3d vector;

	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}

	@Override
	public void doBlockDamage() {
		
		world.setBlock((int)vector.x, (int)vector.y, (int)vector.z, InitBlocks.fission);
		world.spawnEntityInWorld(new EntityFissionCore(world, vector.x, vector.y, vector.z));
		
		for (Location loc : ShapeUtil.getSphere(new Location(world, vector), 10)) {
			
			if (!loc.checkBlock(Blocks.air)) {
				
				if (rand.nextInt(5) == 1) {
					loc.setBlock(InitBlocks.fission);
				}				
			}
		}
	}

	@Override
	public void doPlayerDamage() {}

	@Override
	public void doEffects() {
		world.playBroadcastSound(1018, (int)this.vector.x, (int)this.vector.y, (int)this.vector.z, 0);
	}
}
