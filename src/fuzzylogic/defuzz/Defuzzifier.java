package fuzzylogic.defuzz;

import fuzzylogic.variables.FuzzySet;

public interface Defuzzifier{
    double defuzz(FuzzySet[] fuzzySets, double[] memberships);

}