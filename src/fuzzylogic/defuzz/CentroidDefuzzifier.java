package fuzzylogic.defuzz;

import fuzzylogic.variables.FuzzySet;

public class CentroidDefuzzifier implements Defuzzifier{

    @Override
    public double defuzz(FuzzySet[] fuzzySets, double[] memberships) {
        if (fuzzySets == null || fuzzySets.length == 0) return 0.0;

        // If single aggregated fuzzy set provided with membership 1, compute numeric centroid
        if (fuzzySets.length == 1 && memberships != null && memberships.length == 1 && memberships[0] == 1.0) {
            fuzzylogic.variables.FuzzySet f = fuzzySets[0];
            java.util.ArrayList<Double> pts = f.getPointsAt(0.0);
            if (pts == null || pts.isEmpty()) {
                return f.getCentroid();
            }
            double min = pts.get(0);
            double max = pts.get(pts.size()-1);
            int steps = 601;
            double step = (max - min) / (steps - 1);
            double sumMu = 0.0;
            double sumXM = 0.0;
            for (int i = 0; i < steps; i++) {
                double x = min + i * step;
                double mu = f.evaluate(x);
                sumMu += mu;
                sumXM += x * mu;
            }
            if (sumMu == 0) return 0.0;
            return sumXM / sumMu;
        }

        // Otherwise compute weighted average of centroids by memberships
        double num = 0.0;
        double den = 0.0;
        int n = Math.min(fuzzySets.length, memberships == null ? 0 : memberships.length);
        for (int i = 0; i < n; i++) {
            num += fuzzySets[i].getCentroid() * memberships[i];
            den += memberships[i];
        }
        if (den == 0.0) return 0.0;
        return num / den;
    }
}
