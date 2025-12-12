package  fuzzylogic.variables;

import fuzzylogic.mf.MembershipFunction;

import java.util.ArrayList;

public class FuzzySet {
    private String name;
    private MembershipFunction func;
    public FuzzySet(String name, MembershipFunction func) {
        this.name = name;
        this.func = func;
    }

    public MembershipFunction getFunc() {
        return func;
    }

    public String getName() {
        return name;
    }

    public double evaluate(double x) {
        return func.mf(x);
    }

    public ArrayList<Double> getPointsAt(double membership)
    {
        return func.getPointsAtLevel(membership);
    }
    public double getCentroid(){return func.getCentroid();}

    public  FuzzySet convert(double alpha) {
        return new FuzzySet(name, new MembershipFunction() {
            @Override
            public double mf(double x) {
                return Math.min(alpha, FuzzySet.this.evaluate(x));
            }

            @Override
            public ArrayList<Double> getPointsAtLevel(double membership) {
                return FuzzySet.this.getPointsAt(membership);
            }

            @Override
            public double getCentroid() {
                return FuzzySet.this.getCentroid();
            }
        });
    }

}