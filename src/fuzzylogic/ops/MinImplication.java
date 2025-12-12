package fuzzylogic.ops;

public class MinImplication implements Implication {
    @Override
    public double implicate(double a, double b) {return Math.min(a,b);}
}