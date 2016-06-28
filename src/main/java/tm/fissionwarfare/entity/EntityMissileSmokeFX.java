package tm.fissionwarfare.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tm.fissionwarfare.Reference;

@SideOnly(Side.CLIENT)
public class EntityMissileSmokeFX extends EntitySmokeFX {

	public EntityMissileSmokeFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ, 1.5F);
		particleMaxAge += 20;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance) {
		return distance < 64;
	}
}