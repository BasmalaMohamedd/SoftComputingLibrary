package geneticalgorithm.util;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private List<Double> fitnessList;
    public Tournament(List<Double> fitnessList)
    {
        this.fitnessList = new ArrayList<>();
        this.fitnessList.addAll(fitnessList);
    }

    public int getFittest()
    {
        int populationSize = fitnessList.size();
        int bestInd = 0;
        for(int i = 0; i < fitnessList.size(); i++)
        {
            int rand = (int)(Math.random()*populationSize);
            if(fitnessList.get(rand) > fitnessList.get(bestInd))
            {
                bestInd = rand;
            }
        }
        return bestInd;
    }


}