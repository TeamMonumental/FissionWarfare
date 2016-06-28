package tm.fissionwarfare.explosion.type;

import java.util.Random;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.Vector3d;

public class BasicExplosion implements IExplosionType {

	private final static int SIZE = 5;

	private World world;
	private Vector3d vector;

	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}

	@Override
	public void doBlockDamage() {

		ConcreteUtil.generateShockwave(new Location(world, vector), SIZE, 1);
		ExplosionUtil.generateExplosion(world, vector, SIZE, 1);
	}

	@Override
	public void doPlayerDamage() {

		PlayerExplosionUtil.doLivingDamage(world, vector, SIZE * 2, 20);
	}

	@Override
	public void doEffects() {

		double offset = 0.5D;
		double d2 = vector.y;

		for (int i = 0; i < 8; i++) {

			world.spawnParticle("hugeexplosion", (vector.x + offset), d2, (vector.z + offset), 0, 0, 0);
			world.spawnParticle("hugeexplosion", (vector.x - offset), d2, (vector.z + offset), 0, 0, 0);
			world.spawnParticle("hugeexplosion", (vector.x - offset), d2, (vector.z - offset), 0, 0, 0);
			world.spawnParticle("hugeexplosion", (vector.x + offset), d2, (vector.z - offset), 0, 0, 0);
		}

		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, SIZE * 2, 1);
	}
}
