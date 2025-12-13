package geneticalgorithm.core;

@FunctionalInterface
public interface InfeasibilityCheck {
    boolean isInfeasible(Chromosome chromosome);
}
