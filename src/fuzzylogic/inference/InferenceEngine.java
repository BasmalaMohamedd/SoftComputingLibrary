package fuzzylogic.inference;

import fuzzylogic.ops.Aggregation;
import fuzzylogic.ops.Implication;
import fuzzylogic.ops.SNorm;
import fuzzylogic.ops.TNorm;
import fuzzylogic.rule.Rule;
import fuzzylogic.variables.LinguisticVariable;

import java.util.List;
import java.util.Map;

public interface InferenceEngine {
     Object evaluateRule(Rule rule, Map<LinguisticVariable, Double> ln);
     Object evaluateRules(List<Rule> rules, Map<LinguisticVariable, Double> ln);
     void setAndOperator(TNorm op);
     void setOrOperator(SNorm op);
     void setImplication(Implication op);
     void setAggregation(Aggregation op);
}