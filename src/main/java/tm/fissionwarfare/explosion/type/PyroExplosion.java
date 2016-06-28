package tm.fissionwarfare.explosion.type;

import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.explosion.ExplosionUtil;
import tm.fissionwarfare.explosion.PlayerExplosionUtil;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.Vector3d;

public class PyroExplosion implements IExplosionType {

	public static final int SIZE = 15;
	public static final int DAMAGE = 5;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		
		List<Location> locations = ExplosionUtil.getEffectedExplosionBlocks(world, vector, SIZE, 10);
		
		for (Location loc : locations) {
			
			Location loc2 = loc.copy().add(ForgeDirection.DOWN);
			
			if (loc.checkBlock(Blocks.air) && !loc2.checkBlock(Blocks.air)) {
				
				loc.setBlock(Blocks.fire);
			}
		}
	}

	@Override
	public void doPlayerDamage() {
		
		PlayerExplosionUtil.doLivingDamage(world, vector, SIZE * 2, DAMAGE);
	}

	@Override
	public void doEffects() {
		
		double offset = 1.5D;
		double d2 = vector.y + 1.1;
		
		for (int i = 0; i < 8; i++){
			
			world.spawnParticle("hugeexplosion", (vector.x + offset), d2, (vector.z + offset), 0, 0, 0);
			world.spawnParticle("hugeexplosion", (vector.x - offset), d2, (vector.z + offset), 0, 0, 0);
			world.spawnParticle("hugeexplosion", (vector.x - offset), d2, (vector.z - offset), 0, 0, 0);
			world.spawnParticle("hugeexplosion", (vector.x + offset), d2, (vector.z - offset), 0, 0, 0);
		}
		
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, 10, 1);		
	}
}
