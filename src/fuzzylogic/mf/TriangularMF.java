package fuzzylogic.mf;

import java.util.ArrayList;

public class TriangularMF implements MembershipFunction{
    double a, b, c;

    public TriangularMF(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double mf(double x){
        if (x <= a) return 0;
        else if(x > a && x <= b) return (x-a)/(b-a);
        else if(x == b) return 1;
        else if(x > b && x <= c) return (c-x)/(c-b);
        else if(x >= c) return 0;
        return 0;
    }

    public ArrayList<Double> getPointsAtLevel(double membership)
    {
        ArrayList<Double> points = new ArrayList<>();
        if(membership == 0)
        {
            points.add(a);
            points.add(c);
        }
        else if(membership == 1)
        {
           points.add(b);
        }
        else{
            double x_left = membership *(b-a) +a;
            double x_right = c - membership *(c-b);
            points.add(x_left);
            points.add(x_right);
        }
        return points;
    }

    @Override
    public double getCentroid()
    {
        return (a + b + c) / 3;
    }

}