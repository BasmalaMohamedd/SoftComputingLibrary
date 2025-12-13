package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;

import geneticalgorithm.core.Chromosome;

public abstract class Crossover {

    public abstract List<Chromosome> crossTwo(Chromosome parent1, Chromosome parent2, int chromosomeLength);


    public List<Chromosome> cross(List<Chromosome> parents, int chromosomeLength, float crossoverRate)
    {
        int numberOfParents = parents.size();
        List<Chromosome> children = new ArrayList<>();
        for(int i = 0; i < numberOfParents; i++)
        {
            int parentInd_1 = (int)(Math.random()*numberOfParents);
            int parentInd_2;
            do
            {
                parentInd_2 = (int)(Math.random()*numberOfParents);
            }
            while(parentInd_1 == parentInd_2);
            
            float rand = (float)Math.random();
            if(rand < crossoverRate)
            {
                children.addAll(crossTwo(parents.get(parentInd_1), parents.get(parentInd_2), chromosomeLength));
            }
            else{
                children.add(parents.get(parentInd_1));
                children.add(parents.get(parentInd_2));
            }
            
        }

        return children;
    }

}
