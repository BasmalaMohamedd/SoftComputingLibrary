package fuzzylogic.inference;

import fuzzylogic.ops.*;
import fuzzylogic.rule.Antecedent;
import fuzzylogic.rule.MamdaniConsequent;
import fuzzylogic.rule.Rule;
import fuzzylogic.variables.FuzzySet;
import fuzzylogic.variables.LinguisticVariable;

import java.util.List;
import java.util.Map;

public class MamdaniEngine implements InferenceEngine {
    private TNorm ANDOperator;
    private SNorm OROperator;
    private Implication IMPL;
    private Aggregation Agg;

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
       MamdaniConsequent mamdaniConsequent = (MamdaniConsequent) rule.getConsequents();
       FuzzySet fz = mamdaniConsequent.getValue();
       return fz.convert(alpha);
    }
    @Override
    public Object evaluateRules(List<Rule> rules, Map<LinguisticVariable, Double> ln){
        if(rules.isEmpty()){return null;}
        FuzzySet aggregated = null;
        for (Rule r : rules) {

            if(!r.isFlag()) continue;

            FuzzySet fuzzySet = (FuzzySet) evaluateRule(r,ln);

            if(aggregated == null){
                aggregated = fuzzySet;
            }
            else {
                aggregated = Agg.aggregate(aggregated, fuzzySet);
            }
        }
        return aggregated;
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
        this.Agg = op;
    }
}