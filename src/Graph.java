import edu.kit.informatik.Terminal;

public class Graph {
    private String filePath;
    private int startingCity;
    private int targetCity;
    private int lengthOfPath;
    private String[] serialization;
    private int[][] pathMatrix;
    private int[][] multipliedMatrix;

    /**
     * Constructor
     *
     * @param theFilePath     file path to serialization file
     * @param theStartingCity starting city
     * @param theTargetCity   target city
     * @param theLengthOfPath length of path
     */
    public Graph(String theFilePath, String theStartingCity, String theTargetCity, String theLengthOfPath) {
        this.filePath = theFilePath;
        this.startingCity = Integer.parseInt(theStartingCity);
        this.targetCity = Integer.parseInt(theTargetCity);
        this.lengthOfPath = Integer.parseInt(theLengthOfPath);
        this.serialization = Terminal.readFile(this.filePath);
    }

    /**
     * Checks how many cities are used
     *
     * @return number of cities
     */
    private int determineNumberOfCities() {
        int max = 0;
        for (String value : serialization) {
            int a = Integer.parseInt(String.valueOf(value.charAt(0)));
            int b = Integer.parseInt(String.valueOf(value.charAt(2)));
            if (max < a) {
                max = a;
            }
            if (max < b) {
                max = b;
            }
        }
        return ++max;
    }

    /**
     * Creates an adjacency matrix from the connections between the cities,
     * which are read from the text file
     */
    public void createAdjacencyMatrix() {
        //Initialize adjacency matrix
        this.pathMatrix = new int[determineNumberOfCities()][determineNumberOfCities()];
        //Set all fields to zero
        for (int i = 0; i < determineNumberOfCities(); i++) {
            for (int x = 0; x < determineNumberOfCities(); x++) {
                this.pathMatrix[i][x] = 0;
            }
        }
        for (int i = 0; i < serialization.length; i++) {
            this.pathMatrix[getSourceCity(serialization[i])][getTargetCity(serialization[i])] = 1;
        }
        multipliedMatrix = pathMatrix;
    }

    /**
     * Multiplies a matrix by the path matrix, which is creates in method createAdjacencyMatrix()
     *
     * @param matrix The matrix by which the path matrix is multiplied
     */
    private void multiplyMatrix(int[][] matrix) {
        this.multipliedMatrix = new int[determineNumberOfCities()][determineNumberOfCities()];
        for (int i = 0; i < determineNumberOfCities(); i++) {
            for (int j = 0; j < determineNumberOfCities(); j++) {
                this.multipliedMatrix[i][j] = 0;
                for (int k = 0; k < determineNumberOfCities(); k++) {
                    this.multipliedMatrix[i][j] += matrix[i][k] * pathMatrix[k][j];
                }
            }
        }
    }

    private int getSourceCity(String line) {
        return Integer.parseInt(String.valueOf(line.charAt(0)));
    }

    private int getTargetCity(String line) {
        return Integer.parseInt(String.valueOf(line.charAt(2)));
    }

    /**
     * Calls the responsible methods to create the matrices and outputs the result at the end
     */
    public void output() {
        createAdjacencyMatrix();
        for (int i = 1; i < this.lengthOfPath; i++) {
            multiplyMatrix(this.multipliedMatrix);
        }
        Terminal.printLine(multipliedMatrix[startingCity][targetCity]);

    }

}

