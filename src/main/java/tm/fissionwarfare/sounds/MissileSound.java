package tm.fissionwarfare.sounds;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.ResourceLocation;
import scala.annotation.meta.field;
import tm.fissionwarfare.Reference;
import tm.fissionwarfare.entity.EntityMissile;
import tm.fissionwarfare.entity.EntityMissile.MissileState;
import tm.fissionwarfare.tileentity.machine.TileEntityLaunchPad;

public class MissileSound extends MovingSound {

	private static ResourceLocation location = new ResourceLocation(Reference.MOD_ID + ":missile_start");

	private EntityMissile missile;

	public MissileSound(EntityMissile missile) {
		super(location);
			
		this.missile = missile;
		
		volume = (missile.state == MissileState.GOING_DOWN ? 50 : 4);		
		
		this.repeat = true;
	}

	@Override
	public boolean isDonePlaying() {	
		return missile.isDead;
	}
	
	@Override
	public void update() {
		xPosF = (float)missile.posX;
		yPosF = (float)missile.posY;
		zPosF = (float)missile.posZ;
	}
}
