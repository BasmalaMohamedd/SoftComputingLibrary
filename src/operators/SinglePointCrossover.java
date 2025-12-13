package operators;

import java.util.ArrayList;
import java.util.List;

import core.Chromosome;

public class SinglePointCrossover extends Crossover{
    
    public List<Chromosome> crossTwo(Chromosome parent1, Chromosome parent2, int chromosomeLength)
    {
        int crossoverPoint = (int)(Math.random() * chromosomeLength);
        List<Chromosome> children = new ArrayList<>();
        
        // Clone parents to create children
        Chromosome child1 = parent1.clone();
        Chromosome child2 = parent2.clone();

        for(int i = 0; i < chromosomeLength; i++)
        {

            //if(i < crossoverpoint) do nothing
            if(i < crossoverPoint)
            {
                continue;
            }
            else{
            //if(i >= crossoverpoint) swap genes
            double geneFromParent1 = (double)parent1.getGenesList().get(i);
            double geneFromParent2 = (double)parent2.getGenesList().get(i);

            child1.setGene(i, geneFromParent2);
            child2.setGene(i, geneFromParent1);

            }
            
        }
        children.add(child1);
        children.add(child2);

        return children;
    }


}