package geneticalgorithm.operators;

import java.util.List;

import geneticalgorithm.core.Chromosome;

public interface Mutation {
    public abstract List<Chromosome> mutate(List<Chromosome> currentGeneration);
}
