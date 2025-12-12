package fuzzylogic.ops;

public class ProductImplication implements Implication {

    @Override
    public double implicate(double a, double b) {
        return a*b;
    }
}
