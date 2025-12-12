package fuzzylogic.ops;

import fuzzylogic.mf.MembershipFunction;
import fuzzylogic.variables.FuzzySet;

import java.util.ArrayList;

public class MaxAggregation implements Aggregation {
    @Override
    public double aggregate(double a, double b) {
        return Math.max(a, b);
    }

    @Override
    public FuzzySet aggregate(FuzzySet a, FuzzySet b) {
        return new FuzzySet(
                "agg(" + a.getName() + "," + b.getName() + ")",
                new MembershipFunction() {
                    @Override
                    public double mf(double x) {
                        return Math.max(a.evaluate(x), b.evaluate(x));
                    }

                    @Override
                    public ArrayList<Double> getPointsAtLevel(double membership) {
                        ArrayList<Double> p = new ArrayList<>();
                        p.addAll(a.getPointsAt(membership));
                        p.addAll(b.getPointsAt(membership));
                        return p;
                    }

                    @Override
                    public double getCentroid(){
                        return (a.getCentroid()+b.getCentroid())/2;
                    }
                }
        );
    }
}