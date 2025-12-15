package geneticalgorithm.core;

import java.util.ArrayList;
import java.util.List;

public class FloatingPointChromosome implements Chromosome {
    private List<Double> genesList = new ArrayList<>();
    private double lowerBound = 0.0;
    private double upperBound = 1.0;

    public FloatingPointChromosome() {
    }

    public FloatingPointChromosome(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public FloatingPointChromosome(List<Double> genes) {
        this.genesList = new ArrayList<>(genes);
    }

    public FloatingPointChromosome(List<Double> genes, double lowerBound, double upperBound) {
        this.genesList = new ArrayList<>(genes);
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public void randomize(int chromosomeLength) {
        genesList.clear();
        for (int i = 0; i < chromosomeLength; i++) {
            
            double randomValue = lowerBound + (upperBound - lowerBound) * Math.random();
            genesList.add(randomValue);
        }
    }

    @Override
    public List<Double> getGenesList() {
        return genesList;
    }

    @Override
    public int getLength() {
        return genesList.size();
    }

    public double getGeneAt(int index) {
        return genesList.get(index);
    }

    public void addGene(double value) {
        genesList.add(value);
    }

    public void setGene(int index, double value) {
        if (index >= 0 && index < genesList.size()) {
            // Clamp value to bounds
            double clampedValue = Math.max(lowerBound, Math.min(upperBound, value));
            genesList.set(index, clampedValue);
        }
    }

    
    public void setGenesList(List<Double> genes) {
        this.genesList = new ArrayList<>(genes);
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setBounds(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public Chromosome clone() {
        FloatingPointChromosome cloned = new FloatingPointChromosome(lowerBound, upperBound);
        cloned.setGenesList(this.genesList);
        return cloned;
    }
}
