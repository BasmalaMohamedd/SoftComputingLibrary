package fuzzylogic.core;

import fuzzylogic.inference.InferenceEngine;
import fuzzylogic.rule.RuleBase;
import fuzzylogic.variables.LinguisticVariable;
import fuzzylogic.defuzz.Defuzzifier;

import java.util.Map;

public class Evaluator {
    private InferenceEngine inferenceEngine;
    private RuleBase ruleBase;
    private Defuzzifier defuzzifier;
    private InputDomain inputDomain;
    private OutputDomain outputDomain;

    public Evaluator(InferenceEngine inferenceEngine, RuleBase ruleBase, 
                     Defuzzifier defuzzifier, InputDomain inputDomain, 
                     OutputDomain outputDomain) {
        this.inferenceEngine = inferenceEngine;
        this.ruleBase = ruleBase;
        this.defuzzifier = defuzzifier;
        this.inputDomain = inputDomain;
        this.outputDomain = outputDomain;
    }

    public double evaluate(Map<LinguisticVariable, Double> inputs) {
        if (!inputDomain.validate(inputs)) {
            throw new IllegalArgumentException("Invalid inputs provided");
        }

        Object inferenceResult = inferenceEngine.evaluateRules(ruleBase.getRules(), inputs);

        double crispOutput = 0.0;
        if (inferenceResult != null) {
            if (inferenceResult instanceof double[]) {
                double[] fuzzyOutput = (double[]) inferenceResult;
                crispOutput = defuzzifier.defuzz(new fuzzylogic.variables.FuzzySet[0], fuzzyOutput);
            } else if (inferenceResult instanceof Number) {
                crispOutput = ((Number) inferenceResult).doubleValue();
            } else if (inferenceResult instanceof fuzzylogic.variables.FuzzySet) {
                fuzzylogic.variables.FuzzySet agg = (fuzzylogic.variables.FuzzySet) inferenceResult;
                // Call defuzzifier with single aggregated fuzzy set
                crispOutput = defuzzifier.defuzz(new fuzzylogic.variables.FuzzySet[]{agg}, new double[]{1.0});
            }
        }
        
        if (!outputDomain.validate(crispOutput)) {
            throw new IllegalStateException("Output outside valid domain range");
        }
        
        return crispOutput;
    }

    public InferenceEngine getInferenceEngine() {
        return inferenceEngine;
    }

    public RuleBase getRuleBase() {
        return ruleBase;
    }

    public Defuzzifier getDefuzzifier() {
        return defuzzifier;
    }

    public InputDomain getInputDomain() {
        return inputDomain;
    }

    public OutputDomain getOutputDomain() {
        return outputDomain;
    }
}