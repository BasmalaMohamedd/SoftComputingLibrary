package geneticalgorithm.operators;

import java.util.List;

import geneticalgorithm.core.Chromosome;

public interface GenerationIntializater {
        public List<Chromosome> initializeGenerations(int populationSize, int chromosomeLength);


}
