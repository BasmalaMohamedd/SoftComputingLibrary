package neuralnetwork.library;

import java.util.ArrayList;

import neuralnetwork.activations.ActivationFunction;
import neuralnetwork.core.DenseLayer;
import neuralnetwork.loss.LossFunction;

public class FeedForwardNeuralNetwork extends NeuralNetwork {
    //layers[0] -> input layer 
    //layer[len - 1] -> output layer
    //layer[i] 0 < i < len-1 -> hidden layers
    ArrayList<DenseLayer> layers;
    LossFunction lossFunction;
    ActivationFunction activationFunction;
    //take input layer, hidden layer, output layer and wieghts
    //for each layer:
    //for each node in layer: calculate node in and node out 
    //pass the node out to the next layer
    //
    //return mean square error
    public FeedForwardNeuralNetwork(ArrayList<Double> inputLayerValues, ArrayList<Double> outputLayerValues, int numberOfHiddenLayers, LossFunction lossFunction, ActivationFunction activationFunction){
        layers = new ArrayList<>();
        layers.add(new DenseLayer(inputLayerValues));
        for(int i = 1; i < numberOfHiddenLayers - 1; i++)
        {
            layers.add(new DenseLayer());
        }
        layers.add(new DenseLayer(outputLayerValues));
        this.lossFunction = lossFunction;
        this.activationFunction = activationFunction;
    }

    
}

