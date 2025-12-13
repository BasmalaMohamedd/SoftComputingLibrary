package geneticalgorithm.operators;

import java.util.List;

public interface Selection {
    public abstract List<Integer> select(List<Double> fitnessList);

}
