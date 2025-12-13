package casestudy;

import java.util.List;
import geneticalgorithm.core.*;
import geneticalgorithm.library.GeneticAlgorithm;
import geneticalgorithm.library.PermutationGeneticAlgorithm;

public class TSPCaseStudy {
    
    
    private static double[][] distanceMatrix;
    private static int numberOfCities;

    public static void main(String[] args) {
        
        numberOfCities = 5;
        distanceMatrix = new double[][] {
            {0, 10, 15, 20, 25},
            {10, 0, 35, 25, 30},
            {15, 35, 0, 30, 20},
            {20, 25, 30, 0, 15},
            {25, 30, 20, 15, 0}
        };

        System.out.println("=== Traveling Salesman Problem - GA Solution ===");
        System.out.println("Number of cities: " + numberOfCities);
        System.out.println();

        
        FitnessFunction fitnessFunction = new FitnessFunction() {
            @Override
            public double evaluate(Chromosome chromosome) {
                PermutationChromosome tour = (PermutationChromosome) chromosome;
                double totalDistance = 0.0;
                
                
                for (int i = 0; i < tour.getLength() - 1; i++) {
                    int city1 = tour.getGeneAt(i);
                    int city2 = tour.getGeneAt(i + 1);
                    totalDistance += distanceMatrix[city1][city2];
                }
                
                
                int lastCity = tour.getGeneAt(tour.getLength() - 1);
                int firstCity = tour.getGeneAt(0);
                totalDistance += distanceMatrix[lastCity][firstCity];
                
                
                return 1000.0 / totalDistance;
            }
        };

        
        InfeasibilityCheck infeasibilityCheck = new InfeasibilityCheck() {
            @Override
            public boolean isInfeasible(Chromosome chromosome) {
                PermutationChromosome tour = (PermutationChromosome) chromosome;
                List<Integer> genes = tour.getGenesList();
                
              
                for (int i = 0; i < genes.size(); i++) {
                    for (int j = i + 1; j < genes.size(); j++) {
                        if (genes.get(i).equals(genes.get(j))) {
                            return true; 
                        }
                    }
                }
                
              
                for (int i = 0; i < numberOfCities; i++) {
                    if (!genes.contains(i)) {
                        return true; 
                    }
                }
                
                return false; 
            }
        };

        
        GeneticAlgorithm ga_engine = new PermutationGeneticAlgorithm(
            100,             
            numberOfCities,  
            0.8f,           
            0.1f,           
            200,           
            (chromosome) -> fitnessFunction.evaluate(chromosome)
        );

        ga_engine.setInfeasibilityCheck(infeasibilityCheck);

        System.out.println("GA Configuration:");
        System.out.println("  Population Size: 100");
        System.out.println("  Chromosome Length: " + numberOfCities);
        System.out.println("  Crossover Rate: 0.8");
        System.out.println("  Mutation Rate: 0.1");
        System.out.println("  Generations: 200");
        System.out.println();
        System.out.println("Running Genetic Algorithm...");
        System.out.println();

       
        Chromosome bestSolution = ga_engine.run();

      
        System.out.println("\n=== Best Solution Found ===");
        PermutationChromosome bestTour = (PermutationChromosome) bestSolution;
        System.out.print("Best Tour: ");
        for (int i = 0; i < bestTour.getLength(); i++) {
            System.out.print(bestTour.getGeneAt(i));
            if (i < bestTour.getLength() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println(" -> " + bestTour.getGeneAt(0));
        
        
        double totalDistance = 0.0;
        for (int i = 0; i < bestTour.getLength() - 1; i++) {
            int city1 = bestTour.getGeneAt(i);
            int city2 = bestTour.getGeneAt(i + 1);
            totalDistance += distanceMatrix[city1][city2];
        }
        totalDistance += distanceMatrix[bestTour.getGeneAt(bestTour.getLength() - 1)][bestTour.getGeneAt(0)];
        
        System.out.println("Total Distance: " + totalDistance);
        System.out.println("Fitness Value: " + fitnessFunction.evaluate(bestTour));
    }
}
