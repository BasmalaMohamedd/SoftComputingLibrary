package neuralnetwork.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DataHandler {

    // Load CSV file into 2D array
    public static String[][] loadCSV(String filePath, boolean hasHeader) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine && hasHeader) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                data.add(values);
                firstLine = false;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading CSV file: " + filePath, e);
        }
        return data.toArray(new String[0][]);
    }

    // Convert string array to double array
    public static double[][] toDoubleArray(String[][] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        double[][] result = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                try {
                    result[i][j] = Double.parseDouble(data[i][j].trim());
                } catch (NumberFormatException e) {
                    result[i][j] = 0.0; 
                }
            }
        }
        return result;
    }

    // Normalize data to [0,1] (Min-Max normalization)
    public static double[][] normalize(double[][] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        int rows = data.length;
        int cols = data[0].length;
        double[][] normalized = new double[rows][cols];

        for (int j = 0; j < cols; j++) {
            double min = Double.MAX_VALUE;
            double max = -Double.MAX_VALUE;
            for (int i = 0; i < rows; i++) {
                min = Math.min(min, data[i][j]);
                max = Math.max(max, data[i][j]);
            }
            double range = max - min;
            for (int i = 0; i < rows; i++) {
                if (range > 0) {
                    normalized[i][j] = (data[i][j] - min) / range;
                } else {
                    normalized[i][j] = 0.0;
                }
            }
        }
        return normalized;
    }

    // Standardize data (Z-score normalization)
    public static double[][] standardize(double[][] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        int rows = data.length;
        int cols = data[0].length;
        double[][] standardized = new double[rows][cols];

        for (int j = 0; j < cols; j++) {
            // Calculate mean
            double sum = 0.0;
            for (int i = 0; i < rows; i++) {
                sum += data[i][j];
            }
            double mean = sum / rows;

            // Calculate standard deviation
            double sqSum = 0.0;
            for (int i = 0; i < rows; i++) {
                sqSum += Math.pow(data[i][j] - mean, 2);
            }
            double std = Math.sqrt(sqSum / rows);

            // Standardize
            for (int i = 0; i < rows; i++) {
                if (std > 0) {
                    standardized[i][j] = (data[i][j] - mean) / std;
                } else {
                    standardized[i][j] = 0.0;
                }
            }
        }
        return standardized;
    }

    // One-hot encode categorical labels
    public static double[][] oneHotEncode(int[] labels, int numClasses) {
        if (labels == null || labels.length == 0) {
            throw new IllegalArgumentException("Labels cannot be null or empty");
        }
        double[][] encoded = new double[labels.length][numClasses];
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] >= 0 && labels[i] < numClasses) {
                encoded[i][labels[i]] = 1.0;
            }
        }
        return encoded;
    }

    // Encode categorical string labels to integers
    public static int[] encodeLabels(String[] labels) {
        if (labels == null || labels.length == 0) {
            throw new IllegalArgumentException("Labels cannot be null or empty");
        }
        Map<String, Integer> labelMap = new HashMap<>();
        int[] encoded = new int[labels.length];
        int classIndex = 0;

        for (int i = 0; i < labels.length; i++) {
            String label = labels[i].trim();
            if (!labelMap.containsKey(label)) {
                labelMap.put(label, classIndex++);
            }
            encoded[i] = labelMap.get(label);
        }
        return encoded;
    }

    // Split data into train and test sets
    public static double[][][] trainTestSplit(double[][] X, double[][] Y, double trainRatio) {
        if (X == null || Y == null || X.length == 0 || Y.length == 0) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        if (X.length != Y.length) {
            throw new IllegalArgumentException("X and Y must have the same number of samples");
        }

        int n = X.length;
        int trainSize = (int) (n * trainRatio);
        int testSize = n - trainSize;

        double[][] XTrain = new double[trainSize][X[0].length];
        double[][] YTrain = new double[trainSize][Y[0].length];
        double[][] XTest = new double[testSize][X[0].length];
        double[][] YTest = new double[testSize][Y[0].length];

        for (int i = 0; i < trainSize; i++) {
            XTrain[i] = Arrays.copyOf(X[i], X[i].length);
            YTrain[i] = Arrays.copyOf(Y[i], Y[i].length);
        }
        for (int i = 0; i < testSize; i++) {
            XTest[i] = Arrays.copyOf(X[trainSize + i], X[trainSize + i].length);
            YTest[i] = Arrays.copyOf(Y[trainSize + i], Y[trainSize + i].length);
        }

        return new double[][][] { XTrain, YTrain, XTest, YTest };
    }

    // Shuffle data
    public static void shuffle(double[][] X, double[][] Y, Random rand) {
        if (X == null || Y == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        int n = X.length;
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            double[] tempX = X[i];
            X[i] = X[j];
            X[j] = tempX;
            double[] tempY = Y[i];
            Y[i] = Y[j];
            Y[j] = tempY;
        }
    }

    // Extract column from 2D array
    public static double[] extractColumn(double[][] data, int colIndex) {
        double[] column = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            column[i] = data[i][colIndex];
        }
        return column;
    }

    // Remove column from 2D array
    public static double[][] removeColumn(double[][] data, int colIndex) {
        int rows = data.length;
        int cols = data[0].length - 1;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            int destCol = 0;
            for (int j = 0; j < data[i].length; j++) {
                if (j != colIndex) {
                    result[i][destCol++] = data[i][j];
                }
            }
        }
        return result;
    }
}
