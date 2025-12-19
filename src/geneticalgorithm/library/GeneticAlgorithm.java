package geneticalgorithm.library;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import geneticalgorithm.core.Chromosome;
import geneticalgorithm.core.InfeasibilityCheck;
import geneticalgorithm.operators.Crossover;
import geneticalgorithm.operators.Mutation;
import geneticalgorithm.operators.Replacement;
import geneticalgorithm.operators.Selection;

public abstract class GeneticAlgorithm {

    protected int populationSize;
    protected int chromosomeLength;
    protected float crossoverRate;
    protected float mutationRate;
    protected int generations;
    protected Function<Chromosome, Double> fitnessFunction;
    protected Selection selection;
    protected Crossover crossover;
    protected Mutation mutation;
    protected Replacement replacement;
    protected InfeasibilityCheck infeasibilityCheck;

    public GeneticAlgorithm(int populationSize, int chromosomeLength, float crossoverRate, float mutationRate,
            int generations, Function<Chromosome, Double> fitnessFunction) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.generations = generations;
        this.fitnessFunction = fitnessFunction;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public void setCrossover(Crossover crossover) {
        this.crossover = crossover;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public void setReplacement(Replacement replacement) {
        this.replacement = replacement;
    }

    public void setInfeasibilityCheck(InfeasibilityCheck infeasibilityCheck) {
        this.infeasibilityCheck = infeasibilityCheck;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setChromosomeLength(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
    }

    public void setCrossoverRate(float crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public void setFitnessFunction(Function<Chromosome, Double> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public abstract List<Double> evaluateFitness(List<Chromosome> currentGeneration);

    public abstract List<Chromosome> initializeGenerations();

    public boolean isOptimal(List<Chromosome> currentGeneration) {
        return false;
    }

    public Chromosome run() {
        List<Chromosome> currentGeneration = initializeGenerations();

        if (infeasibilityCheck != null) {
            currentGeneration = removeInfeasible(currentGeneration);
        }

        List<Double> fitnessList = evaluateFitness(currentGeneration);

        Chromosome globalBest = currentGeneration.get(0);
        double globalBestFitness = fitnessList.get(0);

        for (int i = 0; i < generations && !isOptimal(currentGeneration); i++) {
            List<Integer> selectedIndexes = selection.select(fitnessList);
            List<Chromosome> parents = new ArrayList<>();
            for (int index : selectedIndexes) {
                parents.add(currentGeneration.get(index));
            }

            List<Chromosome> children = crossover.cross(parents, chromosomeLength, crossoverRate);

            List<Chromosome> mutatedParents = mutation.mutate(parents);
            List<Chromosome> mutatedChildren = mutation.mutate(children);

            if (infeasibilityCheck != null) {
                mutatedParents = removeInfeasible(mutatedParents);
                mutatedChildren = removeInfeasible(mutatedChildren);
            }

            currentGeneration = replacement.replace(mutatedParents, mutatedChildren);

            while (currentGeneration.size() > populationSize) {
                currentGeneration.remove(currentGeneration.size() - 1);
            }

            fitnessList = evaluateFitness(currentGeneration);

            for (int j = 0; j < fitnessList.size(); j++) {
                if (fitnessList.get(j) > globalBestFitness) {
                    globalBestFitness = fitnessList.get(j);
                    globalBest = currentGeneration.get(j);
                }
            }

            if ((i + 1) % 20 == 0) {
                System.out.println("Generation " + (i + 1) + ": Best Fitness = " +
                        String.format("%.4f", globalBestFitness));
            }
        }

        return globalBest;
    }

    private List<Chromosome> removeInfeasible(List<Chromosome> population) {
        List<Chromosome> feasible = new ArrayList<>();
        for (Chromosome chromosome : population) {
            if (!infeasibilityCheck.isInfeasible(chromosome)) {
                feasible.add(chromosome);
            }
        }

        if (feasible.isEmpty()) {
            return population;
        }

        return feasible;
    }

}
