package geneticalgorithm.library;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import geneticalgorithm.core.Chromosome;
import geneticalgorithm.core.FloatingPointChromosome;
import geneticalgorithm.operators.ElitistReplacement;
import geneticalgorithm.operators.GaussianMutation;
import geneticalgorithm.operators.RouletteWheelSelection;
import geneticalgorithm.operators.SinglePointCrossover;

public class FloatingPointGeneticAlgorithm extends GeneticAlgorithm {
    private double lowerBound;
    private double upperBound;

    public FloatingPointGeneticAlgorithm(int populationSize, int chromosomeLength,
                                         float crossoverRate, float mutationRate,
                                         int generations, Function<Chromosome, Double> fitnessFunction) {
        this(populationSize, chromosomeLength, crossoverRate, mutationRate, generations, fitnessFunction, 0.0, 1.0);
    }

    public FloatingPointGeneticAlgorithm(int populationSize, int chromosomeLength,
                                         float crossoverRate, float mutationRate,
                                         int generations, Function<Chromosome, Double> fitnessFunction,
                                         double lowerBound, double upperBound) {
        super(populationSize, chromosomeLength, crossoverRate, mutationRate, generations, fitnessFunction);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;

       
        this.selection = new RouletteWheelSelection();
        this.crossover = new SinglePointCrossover();
        this.mutation = new GaussianMutation(mutationRate);
        this.replacement = new ElitistReplacement();
    }

    @Override
    public List<Chromosome> initializeGenerations() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            Chromosome chromosome = new FloatingPointChromosome(lowerBound, upperBound);
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

    public void setBounds(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }
}
