package fuzzylogic.defuzz;

import java.util.ArrayList;
import java.util.List;

import fuzzylogic.variables.FuzzySet;

public class MeanOfMaxDefuzzifier implements Defuzzifier{
    public double defuzz(FuzzySet[] fuzzySets, double[] memberships)
    {
        double max = memberships[0];
        for(int i = 1; i < memberships.length; i++)
        {
            if(memberships[i] > max) max = memberships[i];
        }



        List<Double> points = new ArrayList<>();

        for(int i = 0; i < fuzzySets.length; i++)
        {
            points.addAll(fuzzySets[i].getPointsAt(max));
        }

        double average = 0;
        for(int i = 0; i < points.size(); i++)
        {
            average += points.get(i);
        }

        average = average / points.size();

        return average;
    }


}