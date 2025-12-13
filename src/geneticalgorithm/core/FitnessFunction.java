package geneticalgorithm.core;

@FunctionalInterface
public interface FitnessFunction {
    double evaluate(Chromosome chromosome);
}
