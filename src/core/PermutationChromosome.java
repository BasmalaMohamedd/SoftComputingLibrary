package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationChromosome implements Chromosome {
    private List<Integer> genesList = new ArrayList<>();

    public PermutationChromosome() {
    }

    public PermutationChromosome(List<Integer> genes) {
        this.genesList = new ArrayList<>(genes);
    }

    @Override
    public void randomize(int chromosomeLength) {
        genesList.clear();
        for (int i = 0; i < chromosomeLength; i++) {
            genesList.add(i);
        }
        Collections.shuffle(genesList);
    }

    @Override
    public List<Integer> getGenesList() {
        return genesList;
    }

    @Override
    public int getLength() {
        return genesList.size();
    }

    public int getGeneAt(int index) {
        return genesList.get(index);
    }

    public void setGene(int index, int value) {
        if (index >= 0 && index < genesList.size()) {
            genesList.set(index, value);
        }
    }

    @Override
    public void setGene(int index, double value) {
        // Convert double to int for permutation chromosome
        setGene(index, (int) value);
    }

    public void setGenesList(List<Integer> genes) {
        this.genesList = new ArrayList<>(genes);
    }

    @Override
    public Chromosome clone() {
        PermutationChromosome cloned = new PermutationChromosome();
        cloned.setGenesList(this.genesList);
        return cloned;
    }
}
