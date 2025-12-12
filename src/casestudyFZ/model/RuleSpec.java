package casestudyFZ.model;

import java.util.ArrayList;
import java.util.List;

public class RuleSpec {
    public String distSet;
    public boolean distNot;
    public String trafficSet;
    public boolean trafficNot;
    public String outputSet;

    public RuleSpec(String distSet, boolean distNot, String trafficSet, boolean trafficNot, String outputSet) {
        this.distSet = distSet;
        this.distNot = distNot;
        this.trafficSet = trafficSet;
        this.trafficNot = trafficNot;
        this.outputSet = outputSet;
    }

    public static List<RuleSpec> getAll() {
        List<RuleSpec> rules = new ArrayList<>();
        rules.add(new RuleSpec("Short", false, "Light", false, "VerySmall"));
        rules.add(new RuleSpec("Medium", false, "Moderate", false, "Standard"));
        rules.add(new RuleSpec("Short", false, "Light", true, "Small"));
        rules.add(new RuleSpec("Medium", false, "Light", false, "Small"));
        rules.add(new RuleSpec("Medium", false, "Heavy", false, "Large"));
        rules.add(new RuleSpec("Long", false, "Light", true, "VeryLarge"));
        rules.add(new RuleSpec("Long", false, "Light", false, "Standard"));
        return rules;
    }
}
