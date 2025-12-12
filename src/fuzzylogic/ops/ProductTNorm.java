package  fuzzylogic.ops;

public class ProductTNorm implements TNorm {

    @Override
    public double execute(double a, double b) {
        return a * b;
    }
}