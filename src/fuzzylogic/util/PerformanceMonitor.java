package fuzzylogic.util;

public class PerformanceMonitor {

	private long startTime = -1;
	private long totalNanos = 0;
	private long runs = 0;

	public void start() {
		startTime = System.nanoTime();
	}

	public void stop() {
		if (startTime < 0) return;
		long delta = System.nanoTime() - startTime;
		totalNanos += delta;
		runs++;
		startTime = -1;
	}

	public void reset() {
		startTime = -1;
		totalNanos = 0;
		runs = 0;
	}

	public long getRuns() {
		return runs;
	}

	public long getTotalNanos() {
		return totalNanos;
	}

	public double getAverageMillis() {
		if (runs == 0) return 0.0;
		return (totalNanos / (double) runs) / 1_000_000.0;
	}
}
