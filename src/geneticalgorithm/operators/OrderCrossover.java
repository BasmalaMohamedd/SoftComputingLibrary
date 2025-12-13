package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;

import geneticalgorithm.core.Chromosome;
import geneticalgorithm.core.PermutationChromosome;

public class OrderCrossover extends Crossover {

    @Override
    public List<Chromosome> crossTwo(Chromosome parent1, Chromosome parent2, int chromosomeLength) {
        PermutationChromosome p1 = (PermutationChromosome) parent1;
        PermutationChromosome p2 = (PermutationChromosome) parent2;

        List<Chromosome> children = new ArrayList<>();

        
        int point1 = (int) (Math.random() * chromosomeLength);
        int point2 = (int) (Math.random() * chromosomeLength);

        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        
        children.add(createChild(p1, p2, point1, point2, chromosomeLength));
        
        
        children.add(createChild(p2, p1, point1, point2, chromosomeLength));

        return children;
    }

    private Chromosome createChild(PermutationChromosome p1, PermutationChromosome p2, 
                                   int point1, int point2, int chromosomeLength) {
        List<Integer> childGenes = new ArrayList<>();
        for (int i = 0; i < chromosomeLength; i++) {
            childGenes.add(-1);
        }

        
        for (int i = point1; i <= point2; i++) {
            childGenes.set(i, p1.getGeneAt(i));
        }

        
        int currentIndex = (point2 + 1) % chromosomeLength;
        for (int i = 0; i < chromosomeLength; i++) {
            int p2Index = (point2 + 1 + i) % chromosomeLength;
            int gene = p2.getGeneAt(p2Index);

            if (!childGenes.contains(gene)) {
                childGenes.set(currentIndex, gene);
                currentIndex = (currentIndex + 1) % chromosomeLength;
            }
        }

        return new PermutationChromosome(childGenes);
    }
}
