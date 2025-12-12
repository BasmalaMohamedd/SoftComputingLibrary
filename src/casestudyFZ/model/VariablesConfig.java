package casestudyFZ.model;

import fuzzylogic.variables.FuzzySet;
import fuzzylogic.variables.LinguisticVariable;
import fuzzylogic.mf.TrapezoidalMF;
import fuzzylogic.mf.TriangularMF;

public class VariablesConfig {

    public static LinguisticVariable distance() {
        LinguisticVariable v = new LinguisticVariable("Distance", 0, 100);
        v.getFuzzySets().add(new FuzzySet("Short", new TrapezoidalMF(0, 0, 20, 40)));
        v.getFuzzySets().add(new FuzzySet("Medium", new TrapezoidalMF(20, 40, 60, 80)));
        v.getFuzzySets().add(new FuzzySet("Long", new TrapezoidalMF(60, 80, 100, 100)));
        return v;
    }

    public static LinguisticVariable traffic() {
        LinguisticVariable v = new LinguisticVariable("Traffic", 0, 100);
        v.getFuzzySets().add(new FuzzySet("Light", new TrapezoidalMF(0, 0, 20, 40)));
        v.getFuzzySets().add(new FuzzySet("Moderate", new TrapezoidalMF(20, 40, 60, 80)));
        v.getFuzzySets().add(new FuzzySet("Heavy", new TrapezoidalMF(60, 80, 100, 100)));
        return v;
    }

    public static LinguisticVariable travelTime() {
        LinguisticVariable v = new LinguisticVariable("TravelTime", 0, 60);
        v.getFuzzySets().add(new FuzzySet("VerySmall", new TriangularMF(0, 0, 15)));
        v.getFuzzySets().add(new FuzzySet("Small", new TriangularMF(0, 15, 30)));
        v.getFuzzySets().add(new FuzzySet("Standard", new TriangularMF(15, 30, 45)));
        v.getFuzzySets().add(new FuzzySet("Large", new TriangularMF(30, 45, 60)));
        v.getFuzzySets().add(new FuzzySet("VeryLarge", new TriangularMF(45, 60, 60)));
        return v;
    }
}
