package fuzzylogic.core;

import fuzzylogic.variables.LinguisticVariable;

import java.util.HashMap;
import java.util.Map;

public class OutputDomain {
    private LinguisticVariable outputVariable;
    private Map<String, Object> metadata;

    public OutputDomain(LinguisticVariable outputVariable) {
        this.outputVariable = outputVariable;
        this.metadata = new HashMap<>();
    }

    public LinguisticVariable getOutputVariable() {
        return outputVariable;
    }

    public void setOutputVariable(LinguisticVariable outputVariable) {
        this.outputVariable = outputVariable;
    }

    public boolean validate(double value) {
        if (outputVariable == null) {
            return false;
        }

        double min = outputVariable.getMin();
        double max = outputVariable.getMax();

        return value >= min && value <= max;
    }

    public double getMinValue() {
        if (outputVariable == null) {
            throw new IllegalStateException("Output variable not set");
        }
        return outputVariable.getMin();
    }

    public double getMaxValue() {
        if (outputVariable == null) {
            throw new IllegalStateException("Output variable not set");
        }
        return outputVariable.getMax();
    }

    public String getOutputVariableName() {
        if (outputVariable == null) {
            return null;
        }
        return outputVariable.getName();
    }

    public double clamp(double value) {
        if (outputVariable == null) {
            throw new IllegalStateException("Output variable not set");
        }

        double min = outputVariable.getMin();
        double max = outputVariable.getMax();

        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public void setMetadata(String key, Object value) {
        metadata.put(key, value);
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }
}
