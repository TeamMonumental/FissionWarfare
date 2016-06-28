package tm.fissionwarfare.util.math;

public class Angle2d {

	public double pitch, yaw;

	public Angle2d(double pitch, double yaw) {
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	@Override
	public String toString() {
		return "( Pitch: " + pitch + ", Yaw: " + yaw + " )";
	}
	
	public static Angle2d getAngleFromVectors(Vector3d vec1, Vector3d vec2) {
		
		double dx = vec1.x - vec2.x;
		double dy = vec1.y - vec2.y;
		double dz = vec1.z - vec2.z;
		
		double dis = vec1.distance(vec2);
		
		double yawRad = Math.atan2(dz, -dx);
		double pitchRad = -Math.atan2(dy, dis);
		
		return new Angle2d(Math.toDegrees(pitchRad), Math.toDegrees(yawRad));
	}
}
