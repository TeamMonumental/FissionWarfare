package tm.fissionwarfare.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityMissileFlameFX extends EntityFlameFX {

	public EntityMissileFlameFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale) {
		super(world, x, y, z, motionX, motionY, motionZ);
		particleScale = 2 + scale;
		noClip = false;
		particleMaxAge += 20;
	}
	
	public EntityMissileFlameFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		this(world, x, y, z, motionX, motionY, motionZ, 0);
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance) {
		return distance < 64;
	}	
}
