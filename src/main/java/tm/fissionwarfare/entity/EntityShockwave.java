package tm.fissionwarfare.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityShockwave extends EntityHugeExplodeFX{

	public EntityShockwave(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance) {
		return(distance < 64);
	}
}
