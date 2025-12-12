package fuzzylogic.rule;


import java.util.List;

public class RuleBase {
    private List<Rule> rules;

    public RuleBase(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> getRules() {
        return rules;
    }
    public void addRule(Rule rule) {
        rules.add(rule);
    }
    public void enableRule(Rule rule) {
        rule.setFlag(true);
    }
    public void disableRule(Rule rule) {
        rule.setFlag(false);
    }
    public void deleteRule(Rule rule) {
        rules.remove(rule);
    }
    public void deleteRules() {
        rules.clear();
    }
}
