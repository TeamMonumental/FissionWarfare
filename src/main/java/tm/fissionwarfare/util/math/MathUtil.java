package tm.fissionwarfare.util.math;

public class MathUtil {

	public static int scaleInt(int value, int maxValue, int maxScale) {
		
		float f = value * maxScale / maxValue;
		
		return (int)f;
	}
	
	public static double scaleDouble(double value, double maxValue, double maxScale) {		
		return (maxScale / maxValue) * value;
	}
	
	public static double approach(double value, double target, double speed) {

		double difference = Math.abs(value - target);

		if (difference < speed) {
			return approach(value, target, speed / 2);
		}

		if (value > target) {
			return -speed;
		}

		if (value < target) {
			return speed;
		}

		return 0;
	}

	public static double approachRotation(double value, double target, double speed) {

		double rotationDifference = value - target;

		if (Math.abs(rotationDifference) > 180) {
			rotationDifference += rotationDifference > 0 ? -360 : 360;
		}

		if (Math.abs(rotationDifference) < speed) {
			return approachRotation(value, target, speed / 2);
		}

		if (rotationDifference < 0) {
			return value + speed;
		}

		else if (rotationDifference > 0) {
			return value - speed;
		}

		return value;
	}
}
