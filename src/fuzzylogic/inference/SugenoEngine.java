package fuzzylogic.inference;

import fuzzylogic.ops.*;
import fuzzylogic.rule.Antecedent;
import fuzzylogic.rule.Rule;
import fuzzylogic.rule.SugenoConsequent;
import fuzzylogic.variables.LinguisticVariable;

import java.util.List;
import java.util.Map;

public class SugenoEngine implements InferenceEngine {

    private TNorm ANDOperator;
    private SNorm OROperator;
    private Implication IMPL;
    

    @Override
    public Object evaluateRule(Rule rule, Map<LinguisticVariable, Double> ln){

        List<Antecedent> antecedents = rule.getAntecedents();
        double result = 1.0;
        for (Antecedent a : antecedents) {
            LinguisticVariable linVar = a.getLingVar();
            Double value = ln.get(linVar);
            double m = a.getFuzzySet().evaluate(value);
            if(a.getOp() == OperatorType.AND){
                result = ANDOperator.execute(result, m);
            }
            else if (a.getOp() == OperatorType.OR){
                result = OROperator.execute(result, m);
            }
        }

        double alpha = IMPL.implicate(result, rule.getWeight());
        return alpha;
    }

    @Override
    public Object evaluateRules(List<Rule> rules, Map<LinguisticVariable, Double> ln){
        double num = 0.0;
        double den = 0.0;
        for (Rule r : rules) {
            if(!r.isFlag()) continue;
            double fuzzySet = (double) evaluateRule(r,ln);
            double z = ((SugenoConsequent) r.getConsequents()).getValue();
            num+= fuzzySet * z;
            den += fuzzySet;
        }
        if(den == 0.0) return 0.0;
        double result = num/den;
        return result;
    }

    @Override
    public void setAndOperator(TNorm op){
        this.ANDOperator = op;
    }
    @Override
    public void setOrOperator(SNorm op){
        this.OROperator = op;
    }
    @Override
    public void setImplication(Implication op){
        this.IMPL = op;
    }
    @Override
    public void setAggregation(Aggregation op){
    }
}