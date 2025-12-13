package operators;

import java.util.ArrayList;
import java.util.List;
import util.Tournament;

public class TournamentSelection implements Selection{
    public List<Integer> select(List<Double> fitnessList)
    {
        List<Integer> selectedChromosomesIndexes = new ArrayList<>();
        
        Tournament tournament = new Tournament(fitnessList);

        for(int i = 0; i < fitnessList.size() / 2; i++)
        {
            selectedChromosomesIndexes.add(tournament.getFittest());
        }

        return selectedChromosomesIndexes;
    }

}