package fuzzylogic.rule;


import java.util.List;

public class Rule {
    private List<Antecedent> antecedents;
    private Consequent consequents;

    private double weight;
    private boolean flag;

    public Rule(List<Antecedent> antecedents, Consequent consequents, double weight, boolean flag) {
        this.antecedents = antecedents;
        this.consequents = consequents;
        this.weight = weight;
        this.flag = flag;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Antecedent> getAntecedents() {
        return antecedents;
    }
    public Consequent getConsequents() {
        return consequents;
    }
    public double getWeight() {
        return weight;
    }
    public boolean isFlag() {
        return flag;
    }
}