package geneticalgorithm.operators;

import java.util.ArrayList;
import java.util.List;
import geneticalgorithm.util.RouletteWheel;

public class RouletteWheelSelection implements Selection{
    
    public List<Integer> select(List<Double> fitnessList)
    {
        List<Integer> selectedChromosomesIndexes = new ArrayList<>();
        
        RouletteWheel rouletteWheel = new RouletteWheel(fitnessList);

        for(int i = 0; i < fitnessList.size() / 2; i++)
        {
            selectedChromosomesIndexes.add(rouletteWheel.spin_and_getIndex());
        }

        return selectedChromosomesIndexes;
    }

}
