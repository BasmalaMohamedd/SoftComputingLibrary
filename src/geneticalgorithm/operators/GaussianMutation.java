package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geneticalgorithm.core.Chromosome;
import geneticalgorithm.core.FloatingPointChromosome;

public class GaussianMutation implements Mutation {
    private float mutationRate;
    private double standardDeviation;
    private Random random;

    public GaussianMutation(float mutationRate) {
        this(mutationRate, 0.1);
    }

    public GaussianMutation(float mutationRate, double standardDeviation) {
        this.mutationRate = mutationRate;
        this.standardDeviation = standardDeviation;
        this.random = new Random();
    }

    @Override
    public List<Chromosome> mutate(List<Chromosome> currentGeneration) {
        List<Chromosome> mutatedGeneration = new ArrayList<>();

        for (Chromosome chromosome : currentGeneration) {
            if (chromosome instanceof FloatingPointChromosome) {
                FloatingPointChromosome fpChrom = (FloatingPointChromosome) chromosome;
                List<Double> genes = new ArrayList<>(fpChrom.getGenesList());
                double lowerBound = fpChrom.getLowerBound();
                double upperBound = fpChrom.getUpperBound();

                for (int i = 0; i < genes.size(); i++) {
                    if (Math.random() < mutationRate) {
                        double originalValue = genes.get(i);
                        double noise = random.nextGaussian() * standardDeviation * (upperBound - lowerBound);
                        double mutatedValue = originalValue + noise;

                        mutatedValue = Math.max(lowerBound, Math.min(upperBound, mutatedValue));
                        genes.set(i, mutatedValue);
                    }
                }

                FloatingPointChromosome mutatedChrom = new FloatingPointChromosome(genes, lowerBound, upperBound);
                mutatedGeneration.add(mutatedChrom);
            } else {

                mutatedGeneration.add(chromosome.clone());
            }
        }

        return mutatedGeneration;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
