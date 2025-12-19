package neuralnetwork.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import neuralnetwork.activations.ActivationFunction;
import neuralnetwork.core.DenseLayer;
import neuralnetwork.core.TrainingResult;
import neuralnetwork.initialization.WeightInitializer;
import neuralnetwork.loss.LossFunction;
import neuralnetwork.optimizers.Optimizer;

public class FeedForwardNeuralNetwork extends NeuralNetwork {
    private int inputSize;
    private int hiddenSize;
    private int outputSize;
    private double learningRate;
    private int numberOfLayers;
    private int epochs = 100;
    private int batchSize = 32;
    private boolean shuffle = true;
    private boolean verbose = true;

    private ArrayList<DenseLayer> layers = new ArrayList<>();

    private WeightInitializer weightInitializer;
    private ActivationFunction activationFunction;
    private LossFunction lossFunction;
    private Optimizer optimizer;

    
    public FeedForwardNeuralNetwork(int inputSize, int outputSize, int hiddenSize, int numberOfHiddenLayers,
            double learningRate) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.hiddenSize = hiddenSize;
        this.learningRate = learningRate;
        this.numberOfLayers = numberOfHiddenLayers + 2;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
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

    public void initializationLayers() {
        layers.clear();
        layers.add(new DenseLayer(inputSize, hiddenSize, activationFunction, weightInitializer, optimizer));
        for (int i = 1; i < numberOfLayers - 1; i++) {
            layers.add(new DenseLayer(hiddenSize, hiddenSize, activationFunction, weightInitializer, optimizer));
        }
        layers.add(new DenseLayer(hiddenSize, outputSize, activationFunction, weightInitializer, optimizer));
    }

    private double[] performForwardPass(double[] x) {
        if (x == null || x.length != inputSize) {
            throw new IllegalArgumentException("Input size mismatch. Expected: " + inputSize);
        }
        double[] output = layers.get(0).forward(x);
        for (int i = 1; i < numberOfLayers; i++) {
            output = layers.get(i).forward(output);
        }
        return output;
    }

    private void performBackwardPass(double[] outputGradient) {
        double[] inputGradient = layers.get(numberOfLayers - 1).backward(outputGradient, learningRate);
        for (int j = numberOfLayers - 2; j >= 0; j--) {
            inputGradient = layers.get(j).backward(inputGradient, learningRate);
        }
    }

    private double calculateLoss(double[] expected, double[] predicted) {
        double loss = 0.0;
        for (int j = 0; j < outputSize; j++) {
            loss += lossFunction.calculateLoss(expected[j], predicted[j]);
        }
        return loss / outputSize;
    }

    // Single sample training
    public void fit(double[] x_train, double[] y_train) {
        double[] output = performForwardPass(x_train);
        double[] outputGradient = new double[outputSize];
        for (int j = 0; j < outputSize; j++) {
            outputGradient[j] = lossFunction.derivative(y_train[j], output[j]);
        }
        performBackwardPass(outputGradient);
    }

    // Batch training with loss history tracking
    public TrainingResult fit(double[][] X, double[][] Y) {
        if (X == null || Y == null || X.length == 0 || Y.length == 0) {
            throw new IllegalArgumentException("Training data cannot be null or empty");
        }
        if (X.length != Y.length) {
            throw new IllegalArgumentException("X and Y must have the same number of samples");
        }

        List<Double> lossHistory = new ArrayList<>();
        int n = X.length;
        int[] indices = new int[n];
        for (int i = 0; i < n; i++)
            indices[i] = i;

        Random rand = new Random(42);

        for (int epoch = 0; epoch < epochs; epoch++) {
            // Shuffle data at start of each epoch
            if (shuffle) {
                for (int i = n - 1; i > 0; i--) {
                    int j = rand.nextInt(i + 1);
                    int temp = indices[i];
                    indices[i] = indices[j];
                    indices[j] = temp;
                }
            }

            double epochLoss = 0.0;
            int batchCount = 0;

            // Mini-batch training
            for (int start = 0; start < n; start += batchSize) {
                int end = Math.min(start + batchSize, n);
                double batchLoss = 0.0;

                for (int b = start; b < end; b++) {
                    int idx = indices[b];
                    double[] output = performForwardPass(X[idx]);
                    batchLoss += calculateLoss(Y[idx], output);

                    // Compute gradients
                    double[] outputGradient = new double[outputSize];
                    for (int j = 0; j < outputSize; j++) {
                        outputGradient[j] = lossFunction.derivative(Y[idx][j], output[j]);
                    }
                    performBackwardPass(outputGradient);
                }

                epochLoss += batchLoss / (end - start);
                batchCount++;
            }

            double avgLoss = epochLoss / batchCount;
            lossHistory.add(avgLoss);

            if (verbose && (epoch + 1) % 10 == 0) {
                System.out.printf("Epoch %d/%d - Loss: %.6f%n", epoch + 1, epochs, avgLoss);
            }
        }

        double finalLoss = lossHistory.get(lossHistory.size() - 1);
        return new TrainingResult(finalLoss, lossHistory);
    }

    // Single prediction
    public double[] predict(double[] x_predict) {
        return performForwardPass(x_predict);
    }

    // Batch prediction
    public double[][] predict(double[][] X) {
        if (X == null || X.length == 0) {
            throw new IllegalArgumentException("Input data cannot be null or empty");
        }
        double[][] predictions = new double[X.length][outputSize];
        for (int i = 0; i < X.length; i++) {
            predictions[i] = performForwardPass(X[i]);
        }
        return predictions;
    }

    // Calculate accuracy for binary classification
    public double accuracy(double[][] X, double[][] Y) {
        int correct = 0;
        for (int i = 0; i < X.length; i++) {
            double[] pred = predict(X[i]);
            int predictedClass = pred[0] >= 0.5 ? 1 : 0;
            int actualClass = Y[i][0] >= 0.5 ? 1 : 0;
            if (predictedClass == actualClass) {
                correct++;
            }
        }
        return (double) correct / X.length;
    }

    // Get intermediate layer outputs for debugging
    public List<double[]> getLayerOutputs(double[] input) {
        List<double[]> outputs = new ArrayList<>();
        double[] current = input.clone();
        outputs.add(current);
        for (int i = 0; i < numberOfLayers; i++) {
            current = layers.get(i).forward(current);
            outputs.add(current.clone());
        }
        return outputs;
    }
}
