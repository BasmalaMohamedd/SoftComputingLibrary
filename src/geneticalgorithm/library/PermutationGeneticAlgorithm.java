package geneticalgorithm.library;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import geneticalgorithm.core.Chromosome;
import geneticalgorithm.core.PermutationChromosome;
import geneticalgorithm.operators.ElitistReplacement;
import geneticalgorithm.operators.OrderCrossover;
import geneticalgorithm.operators.RouletteWheelSelection;
import geneticalgorithm.operators.SwapMutation;

public class PermutationGeneticAlgorithm extends GeneticAlgorithm {

    public PermutationGeneticAlgorithm(int populationSize, int chromosomeLength, 
                                       float crossoverRate, float mutationRate, 
                                       int generations, Function<Chromosome, Double> fitnessFunction) {
        super(populationSize, chromosomeLength, crossoverRate, mutationRate, generations, fitnessFunction);
        
        
        this.selection = new RouletteWheelSelection();
        this.crossover = new OrderCrossover();
        this.mutation = new SwapMutation(mutationRate);
        this.replacement = new ElitistReplacement();
    }

    @Override
    public List<Chromosome> initializeGenerations() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome chromosome = new PermutationChromosome();
            chromosome.randomize(chromosomeLength);
            chromosomes.add(chromosome);
        }
        return chromosomes;
    }

    @Override
    public List<Double> evaluateFitness(List<Chromosome> currentGeneration) {
        List<Double> fitnessList = new ArrayList<>();
        for (Chromosome chromosome : currentGeneration) {
            double fitness = fitnessFunction.apply(chromosome);
            fitnessList.add(fitness);
        }
        return fitnessList;
    }
}
