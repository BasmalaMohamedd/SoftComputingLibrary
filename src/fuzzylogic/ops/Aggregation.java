package fuzzylogic.ops;

import fuzzylogic.variables.FuzzySet;

public interface Aggregation {

    double aggregate(double a, double b);
    FuzzySet aggregate(FuzzySet a, FuzzySet b);
}