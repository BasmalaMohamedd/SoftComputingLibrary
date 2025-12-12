package  fuzzylogic.ops;

public class MinTNorm implements TNorm {

    @Override
    public double execute(double a, double b) {
        return Math.min(a, b);
    }
}