package fuzzylogic.util;

public class Validator {

	private Validator() {
	}

	public static void requireNonNull(Object obj, String name) {
		if (obj == null) throw new IllegalArgumentException(name + " must not be null");
	}

	public static void requireInRange(double value, double min, double max, String name) {
		if (Double.isNaN(value)) throw new IllegalArgumentException(name + " must not be NaN");
		if (min > max) throw new IllegalArgumentException("Invalid range: min > max");
		if (value < min || value > max) throw new IllegalArgumentException(name + " out of range [" + min + "," + max + "]");
	}

	public static void requireFinite(double value, String name) {
		if (Double.isInfinite(value) || Double.isNaN(value)) throw new IllegalArgumentException(name + " must be finite");
	}
}
