package tm.fissionwarfare.explosion.type;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityFlakey;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.math.Vector3d;

public class FlakeyExplosion implements IExplosionType {

	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}

	@Override
	public void doBlockDamage() {
		System.out.println("YES");
		EntityFlakey flakey = new EntityFlakey(world, vector.x, vector.y, vector.z);
		world.spawnEntityInWorld(flakey);
	}

	@Override
	public void doPlayerDamage() {
		
	}

	@Override
	public void doEffects() {
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, 2, 1);
		
	}

}
