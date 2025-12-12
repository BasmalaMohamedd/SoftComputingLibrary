package casestudyFZ.model;

import fuzzylogic.rule.*;
import fuzzylogic.ops.OperatorType;
import fuzzylogic.variables.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

public class RulesConfig {

    public static RuleBase create(LinguisticVariable distance, LinguisticVariable traffic, LinguisticVariable travelTime) {

        List<Rule> rules = new ArrayList<>();

        // Rule 1: Short AND Light → VerySmall
        List<Antecedent> r1Antecedents = new ArrayList<>();
        r1Antecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Short"), OperatorType.AND));
        r1Antecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Light"), OperatorType.AND));
        Rule r1 = new Rule(r1Antecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("VerySmall")), 1.0, true);
        rules.add(r1);

        // Rule 2: Medium AND Moderate → Standard
        List<Antecedent> r2Antecedents = new ArrayList<>();
        r2Antecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Medium"), OperatorType.AND));
        r2Antecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Moderate"), OperatorType.AND));
        Rule r2 = new Rule(r2Antecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("Standard")), 1.0, true);
        rules.add(r2);

        // Rule 3A: Short AND Moderate → Small
        List<Antecedent> r3aAntecedents = new ArrayList<>();
        r3aAntecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Short"), OperatorType.AND));
        r3aAntecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Moderate"), OperatorType.AND));
        Rule r3a = new Rule(r3aAntecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("Small")), 1.0, true);
        rules.add(r3a);

        // Rule 3B: Short AND Heavy → Small
        List<Antecedent> r3bAntecedents = new ArrayList<>();
        r3bAntecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Short"), OperatorType.AND));
        r3bAntecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Heavy"), OperatorType.AND));
        Rule r3b = new Rule(r3bAntecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("Small")), 1.0, true);
        rules.add(r3b);

        // Rule 3C: Medium AND Light → Small
        List<Antecedent> r3cAntecedents = new ArrayList<>();
        r3cAntecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Medium"), OperatorType.AND));
        r3cAntecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Light"), OperatorType.AND));
        Rule r3c = new Rule(r3cAntecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("Small")), 1.0, true);
        rules.add(r3c);

        // Rule 4: Medium AND Heavy → Large
        List<Antecedent> r4Antecedents = new ArrayList<>();
        r4Antecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Medium"), OperatorType.AND));
        r4Antecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Heavy"), OperatorType.AND));
        Rule r4 = new Rule(r4Antecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("Large")), 1.0, true);
        rules.add(r4);

        // Rule 5A: Long AND Moderate → VeryLarge
        List<Antecedent> r5aAntecedents = new ArrayList<>();
        r5aAntecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Long"), OperatorType.AND));
        r5aAntecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Moderate"), OperatorType.AND));
        Rule r5a = new Rule(r5aAntecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("VeryLarge")), 1.0, true);
        rules.add(r5a);

        // Rule 5B: Long AND Heavy → VeryLarge
        List<Antecedent> r5bAntecedents = new ArrayList<>();
        r5bAntecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Long"), OperatorType.AND));
        r5bAntecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Heavy"), OperatorType.AND));
        Rule r5b = new Rule(r5bAntecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("VeryLarge")), 1.0, true);
        rules.add(r5b);

        // Rule 6: Long AND Light → Standard
        List<Antecedent> r6Antecedents = new ArrayList<>();
        r6Antecedents.add(new Antecedent(distance, distance.getFuzzySetByName("Long"), OperatorType.AND));
        r6Antecedents.add(new Antecedent(traffic, traffic.getFuzzySetByName("Light"), OperatorType.AND));
        Rule r6 = new Rule(r6Antecedents, new MamdaniConsequent("TravelTime", travelTime.getFuzzySetByName("Standard")), 1.0, true);
        rules.add(r6);

        return new RuleBase(rules);
    }
}

