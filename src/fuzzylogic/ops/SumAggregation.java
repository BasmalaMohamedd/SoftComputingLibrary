package  fuzzylogic.ops;

import fuzzylogic.variables.FuzzySet;

public class SumAggregation implements Aggregation {
    @Override
    public double aggregate(double a, double b) {
        return a + b - a*b;
    }

    @Override
    public FuzzySet aggregate(FuzzySet a, FuzzySet b) {
        throw new UnsupportedOperationException("SumAggregation does not support FuzzySet aggregation (Mamdani).");
    }
}