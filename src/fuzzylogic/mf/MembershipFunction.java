package fuzzylogic.mf;

import java.util.ArrayList;

public interface MembershipFunction {
    double mf(double x);
    ArrayList<Double> getPointsAtLevel(double membership);
    double getCentroid();

}