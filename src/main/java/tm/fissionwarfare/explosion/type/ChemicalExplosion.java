package tm.fissionwarfare.explosion.type;

import java.util.Random;

import org.apache.logging.log4j.core.appender.RandomAccessFileManager;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityGasCloud;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.math.Vector3d;

public class ChemicalExplosion implements IExplosionType {

	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {}

	@Override
	public void doPlayerDamage() {
		
		EntityGasCloud gasCloud = new EntityGasCloud(world, vector.x, vector.y, vector.z);
		world.spawnEntityInWorld(gasCloud);
	}

	@Override
	public void doEffects() {
		
		FWSound.gas_cloud.play(world, vector.x, vector.y, vector.z, 2, 1);		
	}
}
