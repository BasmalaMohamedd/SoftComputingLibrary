package casestudyFZ.engine;

import casestudyFZ.model.RulesConfig;
import casestudyFZ.model.VariablesConfig;
import fuzzylogic.core.*;
import fuzzylogic.inference.MamdaniEngine;
import fuzzylogic.defuzz.CentroidDefuzzifier;
import fuzzylogic.variables.LinguisticVariable;
import fuzzylogic.rule.RuleBase;
import fuzzylogic.ops.*;

import java.util.HashMap;
import java.util.Map;

public class CaseStudyEvaluator {

    private final Pipeline pipeline;
    private final LinguisticVariable distance;
    private final LinguisticVariable traffic;

    public CaseStudyEvaluator() {
        distance = VariablesConfig.distance();
        traffic = VariablesConfig.traffic();
        LinguisticVariable travelTime = VariablesConfig.travelTime();

        // Create InputDomain
        InputDomain inputDomain = new InputDomain();
        inputDomain.addVariable(distance);
        inputDomain.addVariable(traffic);

        // Create OutputDomain
        OutputDomain outputDomain = new OutputDomain(travelTime);

        // Create MamdaniEngine and set geneticalgorithm.geneticalgorithm.operators
        MamdaniEngine mamdaniEngine = new MamdaniEngine();
        mamdaniEngine.setAndOperator(new MinTNorm());
        mamdaniEngine.setOrOperator(new MaxSNorm());
        mamdaniEngine.setImplication(new MinImplication());
        mamdaniEngine.setAggregation(new MaxAggregation());

        // Create RuleBase
        RuleBase ruleBase = RulesConfig.create(distance, traffic, travelTime);

        // Create Evaluator
        Evaluator evaluator = new Evaluator(
            mamdaniEngine,
            ruleBase,
            new CentroidDefuzzifier(),
            inputDomain,
            outputDomain
        );

        // Create Pipeline
        pipeline = new Pipeline(
            evaluator,
            inputDomain,
            outputDomain,
            mamdaniEngine,
            ruleBase
        );
    }

    public double evaluate(double distanceValue, double trafficValue) {
        Map<LinguisticVariable, Double> inputs = new HashMap<>();
        inputs.put(distance, distanceValue);
        inputs.put(traffic, trafficValue);

        return pipeline.execute(inputs);
    }
}

