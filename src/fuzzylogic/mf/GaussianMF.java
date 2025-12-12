package fuzzylogic.mf;

import java.util.ArrayList;

public class GaussianMF implements  MembershipFunction {
    double c, sigma;

    public GaussianMF(double c, double sigma) {
        this.c = c;
        this.sigma = sigma;
    }

    @Override
    public double mf(double x){
        double first = Math.pow(x - c, 2);
        double second = 2* Math.pow(sigma, 2);
        double divide = first / second;
        double mu = Math.exp(-divide);
        return mu;
    }

    @Override
    public ArrayList<Double> getPointsAtLevel(double membership)
    {
        if(membership <= 0)
        {
            throw new ArithmeticException("Membership must be positive");
        }
        double ln_Y = Math.log(membership) / Math.log(Math.E);
        double res2ln_Y = -2 * ln_Y;
        double sqrt2ln_Y = Math.sqrt(res2ln_Y);
        double sigmaMultSqrt2ln_Y = sigma * sqrt2ln_Y;
        double x1 = sigmaMultSqrt2ln_Y + c;
        double x2 = sigmaMultSqrt2ln_Y * (-1) + c;
        ArrayList<Double> points = new ArrayList<>();
        points.add(x1);
        points.add(x2);
        return points;

    }

    @Override
    public double getCentroid(){
        return c;
    }


}