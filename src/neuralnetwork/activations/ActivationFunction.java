//hidden layers kolha nfs el output lw msh 3ndna activation

//files will be used later in DenseLayer
package neuralnetwork.activations;

public interface ActivationFunction {

    //forward
    double activate(double x);
    //backward
    double derivative(double x);
}