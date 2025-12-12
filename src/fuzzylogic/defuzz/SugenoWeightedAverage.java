package  fuzzylogic.defuzz;
import fuzzylogic.variables.FuzzySet;

import java.util.ArrayList;

public class SugenoWeightedAverage implements Defuzzifier{
    public double defuzz(FuzzySet[] fuzzySets, double[] memberships)
    {
        ArrayList<Double> centroids = new ArrayList<>();

        for(int i = 0; i < fuzzySets.length; i++)
        {
            centroids.add(fuzzySets[i].getCentroid());
        }

        double num = 0 ,den = 0;
        for(int i = 0; i < fuzzySets.length; i++)
        {
            num += centroids.get(i) * memberships[i];
            den += memberships[i];
        }

        return den==0?0: num /den;
    }
}