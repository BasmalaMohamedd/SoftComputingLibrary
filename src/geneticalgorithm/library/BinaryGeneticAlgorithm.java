package geneticalgorithm.library;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import geneticalgorithm.core.Chromosome;
import geneticalgorithm.operators.BinaryGenerationIntialization;
import geneticalgorithm.operators.GenerationIntializater;

public class BinaryGeneticAlgorithm extends GeneticAlgorithm{

    public BinaryGeneticAlgorithm(int populationSize, int chromosomeLength, float crossoverRate, float mutationRate, int generations, Function<Chromosome, Double> fitnessFunction){
        super(populationSize, chromosomeLength, crossoverRate, mutationRate,generations , fitnessFunction);
    }
    
    public List<Chromosome> initializeGenerations(){
        GenerationIntializater generationInitializor = new BinaryGenerationIntialization();
        return generationInitializor.initializeGenerations(populationSize, chromosomeLength);
        
    }

    public List<Double> evaluateFitness(List<Chromosome> currentGeneration)
    {
        List<Double> fitnessList = new ArrayList<>();
        for (Chromosome chromosome : currentGeneration) {
            double fitness = fitnessFunction.apply(chromosome);
            fitnessList.add(fitness);
        }
        return fitnessList;
    }

    

    
}
