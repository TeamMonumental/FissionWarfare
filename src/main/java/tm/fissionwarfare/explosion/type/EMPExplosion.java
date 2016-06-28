package tm.fissionwarfare.explosion.type;

import java.util.List;
import java.util.Random;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tm.fissionwarfare.api.IExplosionType;
import tm.fissionwarfare.entity.EntityMissileSmokeFX;
import tm.fissionwarfare.sounds.FWSound;
import tm.fissionwarfare.util.EffectUtil;
import tm.fissionwarfare.util.math.Location;
import tm.fissionwarfare.util.math.ShapeUtil;
import tm.fissionwarfare.util.math.Vector3d;

public class EMPExplosion implements IExplosionType {

	private Random rand = new Random();
	
	public static final int SIZE = 15;
	
	private World world;
	private Vector3d vector;
	
	@Override
	public void setup(World world, Vector3d vector) {
		this.world = world;
		this.vector = vector;
	}
	
	@Override
	public void doBlockDamage() {
		
		List<Location> locations = ShapeUtil.getSphere(new Location(world, vector), SIZE);
				
		for (Location loc : locations) {
			
			if (loc.hasTileEntity() && loc.getTileEntity() instanceof IEnergyHandler) {
				
				IEnergyHandler energy = (IEnergyHandler) loc.getTileEntity();
				
				while (energy.getEnergyStored(ForgeDirection.UNKNOWN) > 0) {
					
					energy.extractEnergy(ForgeDirection.UNKNOWN, energy.getEnergyStored(ForgeDirection.UNKNOWN), false);
				}			
			}
		}
	}

	@Override
	public void doPlayerDamage() {}

	@Override
	public void doEffects() {
		
		List<Location> locations = ShapeUtil.getSphere(new Location(world, vector), SIZE);
		
		for (Location loc : locations) {
			
			if (loc.hasTileEntity() && loc.getTileEntity() instanceof IEnergyHandler) {
			
				for (int i = 0; i < 8; i++) {
					
					double randX = MathHelper.getRandomDoubleInRange(rand, 0, 1);
					double randZ = MathHelper.getRandomDoubleInRange(rand, 0, 1);
					
					world.spawnParticle("smoke", loc.x + randX, loc.y + 1D, loc.z + randZ, 0, 0, 0);
				}
				
				world.playSound(loc.x, loc.y, loc.z, "random.fizz", 1, 0.5F, false);
			}
		}
		
		FWSound.small_blast.play(world, vector.x, vector.y, vector.z, SIZE * 2, 1);
	}
}
