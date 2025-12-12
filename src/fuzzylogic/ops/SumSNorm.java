package  fuzzylogic.ops;

public class SumSNorm implements SNorm {

    @Override
    public double execute(double a, double b) {
        return a+b - a*b ;
    }
}