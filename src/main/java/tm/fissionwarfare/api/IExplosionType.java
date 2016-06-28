package tm.fissionwarfare.api;

import net.minecraft.world.World;
import tm.fissionwarfare.util.math.Vector3d;

public interface IExplosionType {
	
	public void setup(World world, Vector3d vector);
	
	public void doBlockDamage();
	
	public void doPlayerDamage();
	
	public void doEffects();
}
