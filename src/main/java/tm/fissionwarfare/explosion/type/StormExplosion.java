package tm.fissionwarfare.explosion.type;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityCloud;
import tm.fissionwarfare.entity.EntityFlakey;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.math.Vector3d;

public class StormExplosion implements IExplosionType {

	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}

	@Override
	public void doBlockDamage() {
		EntityFlakey flakey = new EntityFlakey(world, vector.x, vector.y, vector.z);
		EntityCloud cloud = new EntityCloud(world, vector.x, vector.y + 28, vector.z);
		world.spawnEntityInWorld(flakey);
		world.spawnEntityInWorld(cloud);
	}

	@Override
	public void doPlayerDamage() {
		
	}

	@Override
	public void doEffects() {
		FWSound.thunder.play(world, vector.x, vector.y, vector.z, 5, 1);
		
	}

}
