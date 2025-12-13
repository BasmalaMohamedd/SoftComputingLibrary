// tanh= (e^x - e^-x)/(e^x + e^-x)
//output[-1,1]
//center 0
package neuralnetwork.activations;

public class Tanh implements ActivationFunction {

    @Override
    public double activate(double x){
        return  (Math.exp(x) - Math.exp(-x)) / (Math.exp(x) + Math.exp(-x));
    }

    @Override
    public double derivative(double x){
        return 1.0 - (activate(x) * activate(x));
    }
}