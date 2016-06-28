package tm.fissionwarfare.explosion.type;

import java.util.Random;

import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityShockwave;
import tm.fissionwarfare.explosion.ConcreteUtil;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.EffectUtil;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.Vector3d;

public class AtomicExplosion extends BasicExplosion implements IExplosionType {

	private Random rand = new Random();

	private final static int SIZE = 15;

	private World world;
	private Vector3d vector;

	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}

	@Override
	public void doBlockDamage() {

		ConcreteUtil.generateShockwave(new Location(world, vector), SIZE, 4);
		ExplosionUtil.generateExplosion(world, vector, SIZE, 2);
	}

	@Override
	public void doPlayerDamage() {

		PlayerExplosionUtil.doLivingDamage(world, vector, SIZE * 2, 20);
	}

	@Override
	public void doEffects() {

		FWSound.nuke.play(world, vector.x, vector.y, vector.z, 20, 1);
		FWSound.rumbling.play(world, vector.x, vector.y, vector.z, 20, 1);
		FWSound.cave_in.play(world, vector.x, vector.y, vector.z, 5, 1);

		for (int i = 0; i < 100; i++) {

			double x = vector.x + (rand.nextDouble() * (SIZE * 2)) - SIZE;
			double y = vector.y + (rand.nextDouble() * (SIZE * 2)) - SIZE;
			double z = vector.z + (rand.nextDouble() * (SIZE * 2)) - SIZE;

			world.spawnParticle("hugeexplosion", x, y, z, 0, 0, 0);
		}
	}
}
