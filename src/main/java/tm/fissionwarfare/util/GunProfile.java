package tm.fissionwarfare.util;

import tm.fissionwarfare.sounds.FWSound;

public class GunProfile {

	public int damage, shotsPerFire, maxAmmo, reloadTime, accuracy, distance;
	public float recoil; 
	public boolean isAuto;
	public FWSound sound;
	
	public GunProfile(int damage, int shotsPerFire, int maxAmmo, int reloadTime, int accuracy, int distance, float recoil, boolean isAuto, FWSound sound) {
		
		this.damage = damage;
		this.shotsPerFire = shotsPerFire;
		this.maxAmmo = maxAmmo;
		this.reloadTime = reloadTime;
		this.accuracy = accuracy;
		this.distance = distance;
		this.recoil = recoil;
		this.isAuto = isAuto;
		this.sound = sound;
	}	
}
