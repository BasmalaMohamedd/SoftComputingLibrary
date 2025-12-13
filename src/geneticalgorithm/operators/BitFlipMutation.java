package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;

import geneticalgorithm.core.BinaryChromosome;
import geneticalgorithm.core.Chromosome;

public class BitFlipMutation implements Mutation {
    private float mutationRate;

    public BitFlipMutation(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public List<Chromosome> mutate(List<Chromosome> currentGeneration) {
        List<Chromosome> mutatedGeneration = new ArrayList<>();
        for (Chromosome chromosome : currentGeneration) {
            mutatedGeneration.add(mutateChromosome(chromosome));
        }
        return mutatedGeneration;
    }

    private Chromosome mutateChromosome(Chromosome chromosome) {
        if (!(chromosome instanceof BinaryChromosome)) {
            throw new IllegalArgumentException("BitFlipMutation only supports BinaryChromosome");
        }
        
        BinaryChromosome binaryChromosome = (BinaryChromosome) chromosome;
        BinaryChromosome mutated = new BinaryChromosome();
        
        for (int i = 0; i < binaryChromosome.getLength(); i++) {
            int gene = binaryChromosome.getGeneAt(i);
            if (Math.random() < mutationRate) {
                gene = 1 - gene;
            }
            mutated.addGene(gene);
        }
        
        return mutated;
    }
}
