package geneticalgorithm.util;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel{

    double sum;
    List<Double> commulativeFitnessList;
    public RouletteWheel(List<Double> fitnessList)
    {
        this.commulativeFitnessList = new ArrayList<>();
        this.commulativeFitnessList.add(fitnessList.get(0));
        for(int i = 1; i < fitnessList.size(); i++)
        {
            this.commulativeFitnessList.add(fitnessList.get(i) + this.commulativeFitnessList.get(i - 1));
        }
        this.sum = commulativeFitnessList.get(commulativeFitnessList.size() - 1);
    }

    private double spin()
    {
        return Math.random() * sum;
    }

    public int spin_and_getIndex(){
        double rand = spin();
        for(int i = 0; i < commulativeFitnessList.size(); i++)
        {
            if(rand < commulativeFitnessList.get(i))
            {
                return i;
            }
        }
        return commulativeFitnessList.size() - 1;
  
    }

}
