package tm.fissionwarfare.explosion.type;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityFissionHeart;
import tm.fissionwarfare.init.InitBlocks;
import tm.fissionwarfare.util.math.Vector3d;

public class FissionExplosion implements IExplosionType {

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
		world.spawnEntityInWorld(new EntityFissionHeart(world, vector.x, vector.y, vector.z));
	}

	@Override
	public void doPlayerDamage() {
		
	}

	@Override
	public void doEffects() {
		
	}
}
