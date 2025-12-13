package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;

import geneticalgorithm.core.Chromosome;

public class ElitistReplacement implements Replacement {
    private int eliteCount;

    public ElitistReplacement(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    public ElitistReplacement() {
        this.eliteCount = 2; 
    }

    @Override
    public List<Chromosome> replace(List<Chromosome> parents, List<Chromosome> children) {
        List<Chromosome> newGeneration = new ArrayList<>();
        
        
        newGeneration.addAll(parents);
        newGeneration.addAll(children);
        
        return newGeneration;
    }
}
