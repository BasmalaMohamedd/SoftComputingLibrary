package  fuzzylogic.ops;

public class MaxSNorm implements SNorm {

    @Override
    public double execute(double a, double b) {
        return Math.max(a, b);
    }
}