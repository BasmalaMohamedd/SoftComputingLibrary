package operators;

import java.util.List;

import core.Chromosome;

public class GenerationalReplacement implements Replacement {
    
    @Override
    public List<Chromosome> replace(List<Chromosome> parents, List<Chromosome> children) {
        // Pure generational replacement: children completely replace parents
        // Maintain original population size (parents came from selection, so size = populationSize)
        int targetSize = parents.size() * 2;
        
        if (children.size() > targetSize) {
            return children.subList(0, targetSize);
        }
        
        return children;
    }
}
