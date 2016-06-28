package tm.fissionwarfare.util;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;

@SideOnly(Side.CLIENT)
public class EffectUtil {
	
	public static void spawnEffect(EntityFX entity) {
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(entity);
	}
}
