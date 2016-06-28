package tm.fissionwarfare.sounds;

import net.minecraft.world.World;
import tm.fissionwarfare.Reference;

public class FWSound {

	public static final FWSound beep = new FWSound("beep");
	public static final FWSound small_blast = new FWSound("blast");
	public static final FWSound gas_cloud = new FWSound("gas_cloud");
	public static final FWSound nuke = new FWSound("nuke");
	public static final FWSound cave_in = new FWSound("cave_in");
	public static final FWSound rumbling_cave = new FWSound("rumbling_cave");
	public static final FWSound rumbling = new FWSound("rumbling");
	
	public static final FWSound shotgun_fire = new FWSound("shotgun_fire");
	
	public static final FWSound turret_fire = new FWSound("turret_fire");
	
	public static final FWSound missile_start = new FWSound("missile_start");
	public static final FWSound missile_fire = new FWSound("missile_fire");
		
	private String sound;

	public FWSound(String sound) {
		this.sound = sound;
	}
	
	public String getSoundPath() {
		return Reference.MOD_ID + ":" + sound;
	}
	
	public void play(World world, double x, double y, double z, float gain, float pitch) {
		world.playSound(x, y, z, getSoundPath(), gain, pitch, false);
	}
	
	public void broadcast(World world, double x, double y, double z, float gain, float pitch) {
		world.playSound(x, y, z, getSoundPath(), gain, pitch, true);
	}
}
