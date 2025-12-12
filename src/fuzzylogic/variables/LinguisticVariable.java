package fuzzylogic.variables;

import java.util.ArrayList;
import java.util.List;

public class LinguisticVariable{
    private String name;
    private double min;
    private double max;
    private List<FuzzySet> fuzzySets;

    public LinguisticVariable(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.fuzzySets = new ArrayList<FuzzySet>();
    }

    public String getName() {
        return name;
    }
    public double getMin() {
        return min;
    }
    public double getMax() {
        return max;
    }
    public List<FuzzySet> getFuzzySets() {
        return fuzzySets;
    }
    public FuzzySet getFuzzySetByName(String name) {
        for (FuzzySet fuzzySet : fuzzySets) {
            if (fuzzySet.getName().equals(name)) {
                return fuzzySet;
            }
        }
        return null;
    }
}