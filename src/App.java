import geneticalgorithm.library.*;

public class App {
    public static void main(String[] args) throws Exception {
        GeneticAlgorithm ga_engine = new BinaryGeneticAlgorithm(10, 5, 0, 0, 5, null);
        ga_engine.initializeGenerations();
        
    }
}
