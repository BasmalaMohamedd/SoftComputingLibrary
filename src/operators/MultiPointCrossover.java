package operators;

import java.util.ArrayList;
import java.util.List;

import core.BinaryChromosome;
import core.Chromosome;

public class MultiPointCrossover extends Crossover {
    private int numberOfPoints;
    
    public MultiPointCrossover(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }
    
    public MultiPointCrossover() {
        this.numberOfPoints = 3; 
    }
    
    @Override
    public List<Chromosome> crossTwo(Chromosome parent1, Chromosome parent2, int chromosomeLength) {
        List<Chromosome> children = new ArrayList<>();
        
       
        if (parent1 instanceof BinaryChromosome && parent2 instanceof BinaryChromosome) {
            BinaryChromosome p1 = (BinaryChromosome) parent1;
            BinaryChromosome p2 = (BinaryChromosome) parent2;
            
            BinaryChromosome child1 = new BinaryChromosome();
            BinaryChromosome child2 = new BinaryChromosome();
            
    
            boolean useParent1 = true;
            int pointsUsed = 0;
            
            for (int i = 0; i < chromosomeLength; i++) {
              
                if (pointsUsed < numberOfPoints && Math.random() < 0.3) {
                    useParent1 = !useParent1; 
                    pointsUsed++;
                }
                
               
                if (useParent1) {
                    child1.addGene(p1.getGeneAt(i));
                    child2.addGene(p2.getGeneAt(i));
                } else {
                    child1.addGene(p2.getGeneAt(i));
                    child2.addGene(p1.getGeneAt(i));
                }
            }
            
            children.add(child1);
            children.add(child2);
        } else {
            throw new UnsupportedOperationException("MultiPointCrossover currently only supports BinaryChromosome");
        }
        
        return children;
    }
}
