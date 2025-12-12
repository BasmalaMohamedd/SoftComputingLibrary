package fuzzylogic.rule;
import fuzzylogic.ops.OperatorType;
import fuzzylogic.variables.FuzzySet;
import fuzzylogic.variables.LinguisticVariable;

public class Antecedent {
    private LinguisticVariable lingVar;
    private FuzzySet fuzzySet;
    private OperatorType op;

    public Antecedent(LinguisticVariable lingVar, FuzzySet fuzzySet, OperatorType op) {
        this.lingVar = lingVar;
        this.fuzzySet = fuzzySet;
        this.op = op;
    }

    public LinguisticVariable getLingVar() {
        return lingVar;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public OperatorType getOp() {
        return op;
    }
    public double evaluate(double x) {
        return fuzzySet.evaluate(x);
    }
}
