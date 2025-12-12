package casestudyFZ;

import casestudyFZ.engine.CaseStudyEvaluator;

public class Main {
    public static void main(String[] args) {
        CaseStudyEvaluator eval = new CaseStudyEvaluator();

        double[][] tests = new double[][] {
            {10, 10},
            {30, 30},
            {15, 70},
            {70, 30},
            {90, 10}
        };

        for (double[] t : tests) {
            double dist = t[0];
            double traffic = t[1];
            double output = eval.evaluate(dist, traffic);
            int minutes = (int) Math.round(output);
            System.out.printf("(%d,%d) → %d min\n", (int)dist, (int)traffic, minutes);
        }
    }
}

