package casestudy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import neuralnetwork.activations.Sigmoid;
import neuralnetwork.core.TrainingResult;
import neuralnetwork.initialization.Xavier;
import neuralnetwork.library.FeedForwardNeuralNetwork;
import neuralnetwork.loss.MSE;
import neuralnetwork.optimizers.GradientDescent;
import neuralnetwork.util.DataHandler;


public class HeartDiseaseCaseStudy {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("    HEART DISEASE PREDICTION - Neural Network Case Study");
        System.out.println("=".repeat(60));
        System.out.println();

        // 1. Load dataset from CSV
        System.out.println("1. Loading Dataset from heart.csv...");
        double[][] data = loadCSV("src/casestudy/heart.csv");
        System.out.printf("   Loaded %d samples with %d features each%n", data.length, data[0].length - 1);

        // 2. Separate features and labels
        System.out.println("\n2. Preprocessing Data...");
        int numFeatures = data[0].length - 1; 
        int numSamples = data.length;

        double[][] X = new double[numSamples][numFeatures];
        double[][] Y = new double[numSamples][1];

        for (int i = 0; i < numSamples; i++) {
            for (int j = 0; j < numFeatures; j++) {
                X[i][j] = data[i][j];
            }
            Y[i][0] = data[i][numFeatures]; 
        }

        // 3. Normalize features
        X = DataHandler.normalize(X);
        System.out.println("   Features normalized to [0, 1] range");

        // 4. Shuffle and split data
        DataHandler.shuffle(X, Y, new Random(42));
        double[][][] split = DataHandler.trainTestSplit(X, Y, 0.8);
        double[][] XTrain = split[0];
        double[][] YTrain = split[1];
        double[][] XTest = split[2];
        double[][] YTest = split[3];
        System.out.printf("   Training set: %d samples%n", XTrain.length);
        System.out.printf("   Test set: %d samples%n", XTest.length);

        // 5. Build neural network
        System.out.println("\n3. Building Neural Network...");
        int inputSize = numFeatures; 
        int outputSize = 1; 
        int hiddenSize = 16; 
        int numHiddenLayers = 1;
        double learningRate = 0.05;

        FeedForwardNeuralNetwork nn = new FeedForwardNeuralNetwork(
                inputSize, outputSize, hiddenSize, numHiddenLayers, learningRate);

        // Configure network
        nn.setActivationFunction(new Sigmoid());
        nn.setLossFunction(new MSE());
        nn.setWeightInitializer(new Xavier());
        nn.setOptimizer(new GradientDescent());
        nn.setEpochs(200);
        nn.setBatchSize(32);
        nn.setShuffle(true);
        nn.setVerbose(true);

        // Initialize layers
        nn.initializationLayers();

        System.out.println("   Architecture: " + inputSize + " -> " + hiddenSize + " -> " + outputSize);
        System.out.println("   Activation: Sigmoid");
        System.out.println("   Loss: MSE");
        System.out.println("   Learning Rate: " + learningRate);

        // 6. Train the network
        System.out.println("\n4. Training Neural Network...");
        System.out.println("-".repeat(40));

        long startTime = System.currentTimeMillis();
        TrainingResult result = nn.fit(XTrain, YTrain);
        long endTime = System.currentTimeMillis();

        System.out.println("-".repeat(40));
        System.out.printf("   Training completed in %.2f seconds%n", (endTime - startTime) / 1000.0);
        System.out.printf("   Final Training Loss: %.6f%n", result.getFinalLoss());

        // 7. Evaluate on test set
        System.out.println("\n5. Evaluating on Test Set...");
        double testAccuracy = nn.accuracy(XTest, YTest);
        double trainAccuracy = nn.accuracy(XTrain, YTrain);

        System.out.printf("   Training Accuracy: %.2f%%%n", trainAccuracy * 100);
        System.out.printf("   Test Accuracy: %.2f%%%n", testAccuracy * 100);

        // 8. Show sample predictions
        System.out.println("\n6. Sample Predictions:");
        System.out.println("-".repeat(50));
        System.out.printf("%-10s %-10s %-12s%n", "Actual", "Predicted", "Probability");
        System.out.println("-".repeat(50));

        int samplesToShow = Math.min(10, XTest.length);
        for (int i = 0; i < samplesToShow; i++) {
            double[] pred = nn.predict(XTest[i]);
            int predictedClass = pred[0] >= 0.5 ? 1 : 0;
            int actualClass = (int) YTest[i][0];
            String match = (predictedClass == actualClass) ? "✓" : "✗";
            System.out.printf("%-10d %-10d %.4f %s%n", actualClass, predictedClass, pred[0], match);
        }

        // 9. Display loss curve summary
        System.out.println("\n7. Training Loss Curve (Summary):");
        System.out.println("-".repeat(40));
        List<Double> lossHistory = result.getLossHistory();
        int step = Math.max(1, lossHistory.size() / 10);
        for (int i = 0; i < lossHistory.size(); i += step) {
            int barLength = (int) (40 * (1 - Math.min(lossHistory.get(i) * 4, 1.0)));
            String bar = "█".repeat(Math.max(0, barLength));
            System.out.printf("Epoch %3d: %.4f |%s%n", i + 1, lossHistory.get(i), bar);
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("    Case Study Complete!");
        System.out.println("=".repeat(60));
    }

    /**
     * Load CSV 
     */
    private static double[][] loadCSV(String filePath) {
        List<double[]> dataList = new ArrayList<>();

        
        String[] possiblePaths = {
                filePath,
                "SoftComputingLibrary/" + filePath,
                System.getProperty("user.dir") + "/" + filePath,
                System.getProperty("user.dir") + "/SoftComputingLibrary/" + filePath
        };

        BufferedReader br = null;
        for (String path : possiblePaths) {
            try {
                br = new BufferedReader(new FileReader(path));
                break;
            } catch (IOException e) {
                // Try next path
            }
        }

        if (br == null) {
            throw new RuntimeException("CSV file not found. Tried: " + String.join(", ", possiblePaths));
        }

        try {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                double[] row = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    try {
                        row[i] = Double.parseDouble(values[i].trim());
                    } catch (NumberFormatException e) {
                        row[i] = 0.0;
                    }
                }
                dataList.add(row);
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        return dataList.toArray(new double[0][]);
    }
}
