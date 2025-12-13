package core;
import java.util.List;

public interface Chromosome {
    public void randomize(int chromosomeLength);

    public List getGenesList();

    public int getLength();

    public Chromosome clone();

    public void setGene(int index, double value);


}