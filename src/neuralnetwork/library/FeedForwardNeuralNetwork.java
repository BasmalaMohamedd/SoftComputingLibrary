package neuralnetwork.library;

import java.util.ArrayList;

import neuralnetwork.activations.ActivationFunction;
import neuralnetwork.core.DenseLayer;
import neuralnetwork.initialization.WeightInitializer;
import neuralnetwork.loss.LossFunction;
import neuralnetwork.optimizers.Optimizer;

public class FeedForwardNeuralNetwork extends NeuralNetwork {
    int inputSize;
    int hiddenSize;
    int outputSize;
    double learningRate;
    int numberOfLayers;
    double acceptedErrorRate = 0.7;
    int maxIterations = 100;

    ArrayList<DenseLayer> layers = new ArrayList<>();


    WeightInitializer weightInitializer;
    ActivationFunction activationFunction;
    LossFunction lossFunction;
    Optimizer optimizer;

    
    public FeedForwardNeuralNetwork(int inputSize, int outputSize, int hiddenSize,int numberOfHiddenLayers, double learningRate){
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.hiddenSize = hiddenSize;
        this.learningRate = learningRate;
        this.numberOfLayers = numberOfHiddenLayers + 2;
        

    }

    public void setAcceptedErrorRate(double acceptedErrorRate) {
        this.acceptedErrorRate = acceptedErrorRate;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public void setWeightInitializer(WeightInitializer weightInitializer) {
        this.weightInitializer = weightInitializer;
    }

    public void setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void setLossFunction(LossFunction lossFunction) {
        this.lossFunction = lossFunction;
    }
    public void setOptimizer(Optimizer optimizer) {
        this.optimizer = optimizer;
    }

    public void initializationLayers(){
        layers.add(
            new DenseLayer(inputSize, hiddenSize, activationFunction, weightInitializer, optimizer)
        );
        for(int i = 1; i < numberOfLayers - 1; i++)
        {
            layers.add(
                new DenseLayer(hiddenSize, hiddenSize, activationFunction, weightInitializer, optimizer)
            );
        }
        layers.add(
            new DenseLayer(hiddenSize, outputSize, activationFunction, weightInitializer, optimizer)
        );

    }

    private double[] performForwardPass(double[] x){
        double [] output = layers.get(0).forward(x);
        for(int i = 1; i < numberOfLayers; i++)
        {
            output = layers.get(i).forward(output);
        }
        return output;

    }

    private void performBackwardPass(double[] outputGradient)
    {
        double[] inputGradient = layers.get(numberOfLayers - 1).backward(outputGradient, learningRate);
            for(int j = numberOfLayers - 2; j >= 0; j--)
            {
                inputGradient = layers.get(j).backward(inputGradient, learningRate);
            }
    }
    
    private double calculateLoss(double[] expected, double[] predicted)
    {
        double loss = 0.00;
        for(int j = 0; j < outputSize; j++)
            {
                loss += lossFunction.calculateLoss(expected[j], predicted[j]);
            }
            loss = loss / outputSize;
            return loss;
    }

    public void fit(double[] x_train, double[] y_train){
        //forward pass
        double[] output = performForwardPass(x_train);
        double loss = calculateLoss(y_train, output); 
        for(int i = 0; i < maxIterations && loss > acceptedErrorRate ; i++)
        {
            //loss derivative
            double[] outputGradient = new double[outputSize];
            for(int j = 0; j < outputSize; j++)
            {
                outputGradient[j] = lossFunction.derivative(y_train[j], output[j]);
            }
            //backward pass
            performBackwardPass(outputGradient);
            //forward pass
            output = performForwardPass(x_train);
            loss = calculateLoss(y_train, output);
        }

        

    }

    public double[] predict(double[] x_predict){
        return performForwardPass(x_predict);
    }

    public Double score(double[] x_test, double[] y_test)
    {
        double[] y_predicted = predict(x_test);
        double loss = calculateLoss(y_test, y_predicted); 
        return 1 - loss;
    }
}

