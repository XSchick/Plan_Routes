import com.sun.jdi.ArrayReference;
import edu.kit.informatik.Terminal;

import java.util.Arrays;

public class Graph {
    private String FilePath;
    private int startingCity;
    private int targetCity;
    private int lengthOfPath;
    private int[][] matrixA;
    private String[] serialization;

    /**
     * Constructor
     *
     * @param theFilePath     file path to serialization file
     * @param theStartingCity starting city
     * @param theTargetCity   target city
     * @param theLengthOfPath length of path
     */
    public Graph(String theFilePath, String theStartingCity, String theTargetCity, String theLengthOfPath) {
        this.FilePath = theFilePath;
        this.startingCity = Integer.parseInt(theStartingCity);
        this.targetCity = Integer.parseInt(theTargetCity);
        this.lengthOfPath = Integer.parseInt(theLengthOfPath);
        this.serialization = Terminal.readFile(this.FilePath);
    }

    private int determineNumberOfCities() {
        int max = 0;
        for (String value : serialization) {
            int a = Integer.parseInt(String.valueOf(value.charAt(0)));
            if (max < a) {
                max = a;
            }
        }
        for (String value : serialization) {
            int b = Integer.parseInt(String.valueOf(value.charAt(2)));
            if (max < b) {
                max = b;
            }
        }
        return max + 1;
    }

    public void initializeMatrix() {
        this.matrixA = new int[determineNumberOfCities()][determineNumberOfCities()];

        for (int i = 0; i < determineNumberOfCities(); i++) {
            for (int x = 0; x < determineNumberOfCities(); x++) {
                this.matrixA[i][x] = 0;
            }
        }
    }

    private void writeMatrix() {
        initializeMatrix();
        for (int i = 0; i < serialization.length; i++) {
            this.matrixA[getSourceCity(serialization[i])][getTargetCity(serialization[i])] = 1;
        }
    }

    private int getSourceCity(String line) {
        return Integer.parseInt(String.valueOf(line.charAt(0)));
    }

    private int getTargetCity(String line) {
        return Integer.parseInt(String.valueOf(line.charAt(2)));
    }

    public void output() {
        writeMatrix();
        System.out.println();
        //System.out.println(this.matrixA[1][1]);
    }

}

