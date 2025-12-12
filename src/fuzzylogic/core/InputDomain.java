package fuzzylogic.core;

import fuzzylogic.variables.LinguisticVariable;

import java.util.HashMap;
import java.util.Map;

public class InputDomain {
    private Map<String, LinguisticVariable> variables;

    public InputDomain() {
        this.variables = new HashMap<>();
    }

    public void addVariable(LinguisticVariable variable) {
        variables.put(variable.getName(), variable);
    }

    public void removeVariable(String variableName) {
        variables.remove(variableName);
    }

    public LinguisticVariable getVariable(String variableName) {
        return variables.get(variableName);
    }

    public Map<String, LinguisticVariable> getVariables() {
        return new HashMap<>(variables);
    }

    public boolean validate(Map<LinguisticVariable, Double> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            return false;
        }

        for (Map.Entry<LinguisticVariable, Double> entry : inputs.entrySet()) {
            LinguisticVariable variable = entry.getKey();
            Double value = entry.getValue();

            if (!variables.containsKey(variable.getName())) {
                return false;
            }

            if (value < variable.getMin() || value > variable.getMax()) {
                return false;
            }
        }

        return true;
    }

    public int getVariableCount() {
        return variables.size();
    }

    public void clear() {
        variables.clear();
    }
}
