package tm.fissionwarfare.util.math;

public class Vector3d {
	
	public double x, y, z;

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Vector3d vec) {
		x += vec.x;
		y += vec.y;
		z += vec.z;
	}
	
	public Vector3d sub(double nx, double ny, double nz) {
		return new Vector3d(x - nx, y - ny, z - nz);
	}
	
	public Vector3d sub(Vector3d vec) {
		return sub(vec.x, vec.y, vec.z);
	}
	
	public Vector3d mul(double nx, double ny, double nz) {
		return new Vector3d(x * nx, y * ny, z * nz);
	}
	
	public Vector3d mul(Vector3d vec) {
		return mul(vec.x, vec.y, vec.z);
	}
	
	public Vector3d div(double nx, double ny, double nz) {
		return new Vector3d(x / nx, y / ny, z / nz);
	}
	
	public Vector3d div(Vector3d vec) {
		return div(vec.x, vec.y, vec.z);
	}
	
	public double distance(Vector3d vec) {
		
		double dx = x - vec.x;
		double dy = y - vec.y;
		double dz = z - vec.z;
		
		return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
	}
	
	public static Vector3d getVectorFromAngle(Angle2d angle) {
		
		double pitchRad = Math.toRadians(angle.pitch);
		double yawRad = Math.toRadians(angle.yaw);
		
		double x = Math.cos(yawRad) * Math.cos(pitchRad);
		double z = -Math.sin(yawRad) * Math.cos(pitchRad);
		double y = Math.sin(pitchRad);
		
		return new Vector3d(x, y, z);
	}
	
	public Vector3d copy() {
		return new Vector3d(x, y, z);
	}
	
	@Override
	public String toString() {
		return "( " + x + ", " + y + ", " + z + " )";
	}
}
