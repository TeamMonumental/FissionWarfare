package tm.fissionwarfare.init;

import cpw.mods.fml.common.registry.EntityRegistry;
import tm.fissionwarfare.FissionWarfare;
import tm.fissionwarfare.entity.EntityBullet;
import tm.fissionwarfare.entity.EntityExplosive;
import tm.fissionwarfare.entity.EntityGasCloud;
import tm.fissionwarfare.entity.EntityMissile;

public class InitEntities {

	private static int nextEntityId = 0;
	
	public static void init() {
		
		EntityRegistry.registerModEntity(EntityExplosive.class, "explosive", nextEntityId++, FissionWarfare.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityMissile.class, "missile", nextEntityId++, FissionWarfare.instance, 128, 1, true);
		EntityRegistry.registerModEntity(EntityBullet.class, "bullet", nextEntityId++, FissionWarfare.instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityGasCloud.class, "gas", nextEntityId++, FissionWarfare.instance, 64, 10, true);
	}
}
