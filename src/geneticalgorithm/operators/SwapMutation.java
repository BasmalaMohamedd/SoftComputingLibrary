package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;

import geneticalgorithm.core.Chromosome;
import geneticalgorithm.core.PermutationChromosome;

public class SwapMutation implements Mutation {
    private float mutationRate;

    public SwapMutation(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public List<Chromosome> mutate(List<Chromosome> currentGeneration) {
        List<Chromosome> mutatedGeneration = new ArrayList<>();

        for (Chromosome chromosome : currentGeneration) {
            if (Math.random() < mutationRate) {
                PermutationChromosome permChrom = (PermutationChromosome) chromosome;
                List<Integer> genes = new ArrayList<>(permChrom.getGenesList());
                
              
                int pos1 = (int) (Math.random() * genes.size());
                int pos2 = (int) (Math.random() * genes.size());
                
             
                int temp = genes.get(pos1);
                genes.set(pos1, genes.get(pos2));
                genes.set(pos2, temp);
                
                PermutationChromosome mutatedChrom = new PermutationChromosome(genes);
                mutatedGeneration.add(mutatedChrom);
            } else {
                
                PermutationChromosome permChrom = (PermutationChromosome) chromosome;
                mutatedGeneration.add(new PermutationChromosome(permChrom.getGenesList()));
            }
        }

        return mutatedGeneration;
    }
}
