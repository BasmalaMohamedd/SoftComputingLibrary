package fuzzylogic.util;

public class Clamping {
	private Clamping() {
	}

	public static double clamp(double value, double min, double max) {
		if (Double.isNaN(value)) return value;
		if (min > max) {
			double tmp = min; min = max; max = tmp;
		}
		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

	public static float clamp(float value, float min, float max) {
		if (Float.isNaN(value)) return value;
		if (min > max) {
			float tmp = min; min = max; max = tmp;
		}
		if (value < min) return min;
		if (value > max) return max;
		return value;
	}

	public static int clamp(int value, int min, int max) {
		if (min > max) {
			int tmp = min; min = max; max = tmp;
		}
		if (value < min) return min;
		if (value > max) return max;
		return value;
	}
}
