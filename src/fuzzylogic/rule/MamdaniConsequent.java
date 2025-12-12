package fuzzylogic.rule;

import fuzzylogic.variables.FuzzySet;

public class MamdaniConsequent implements Consequent {

    private String name;
    private FuzzySet value;

    public MamdaniConsequent(String name, FuzzySet value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public FuzzySet getValue() {
        return value;
    }
}
