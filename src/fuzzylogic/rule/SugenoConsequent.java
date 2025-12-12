package fuzzylogic.rule;

public class SugenoConsequent implements Consequent {
    private String name;
    private double value;

    public SugenoConsequent(String name, double value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public Double getValue() {
        return value;
    }
}
