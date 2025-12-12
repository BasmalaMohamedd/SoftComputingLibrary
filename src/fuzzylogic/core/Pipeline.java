package fuzzylogic.core;

import fuzzylogic.inference.InferenceEngine;
import fuzzylogic.rule.RuleBase;
import fuzzylogic.variables.LinguisticVariable;
import fuzzylogic.ops.TNorm;
import fuzzylogic.ops.SNorm;
import fuzzylogic.ops.Implication;
import fuzzylogic.ops.Aggregation;

import java.util.Map;

public class Pipeline {
    private Evaluator evaluator;
    private InputDomain inputDomain;
    private OutputDomain outputDomain;
    private InferenceEngine inferenceEngine;
    private RuleBase ruleBase;

    public Pipeline(Evaluator evaluator, InputDomain inputDomain, 
                   OutputDomain outputDomain, InferenceEngine inferenceEngine, 
                   RuleBase ruleBase) {
        this.evaluator = evaluator;
        this.inputDomain = inputDomain;
        this.outputDomain = outputDomain;
        this.inferenceEngine = inferenceEngine;
        this.ruleBase = ruleBase;
    }

    public double execute(Map<LinguisticVariable, Double> inputs) {
        if (!inputDomain.validate(inputs)) {
            throw new IllegalArgumentException("Invalid inputs: values outside domain or missing required variables");
        }

        double output = evaluator.evaluate(inputs);

        return outputDomain.clamp(output);
    }

    public void addRule(Object rule) {
    }

    public void setAndOperator(TNorm tnorm) {
        inferenceEngine.setAndOperator(tnorm);
    }

    public void setOrOperator(SNorm snorm) {
        inferenceEngine.setOrOperator(snorm);
    }

    public void setImplication(Implication implication) {
        inferenceEngine.setImplication(implication);
    }

    public void setAggregation(Aggregation aggregation) {
        inferenceEngine.setAggregation(aggregation);
    }

    public Evaluator getEvaluator() {
        return evaluator;
    }

    public InputDomain getInputDomain() {
        return inputDomain;
    }

    public OutputDomain getOutputDomain() {
        return outputDomain;
    }

    public InferenceEngine getInferenceEngine() {
        return inferenceEngine;
    }

    public RuleBase getRuleBase() {
        return ruleBase;
    }
}

