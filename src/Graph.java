public class Graph {
    private String FilePath;
    private int startingCity;
    private int targetCity;
    private int lengthOfPath;
    private int[][] matrixA ;

    public Graph(String theFilePath, String theStartingCity, String theTargetCity, String theLengthOfPath) {
        this.FilePath = theFilePath;
        this.startingCity = Integer.parseInt(theStartingCity);
        this.targetCity = Integer.parseInt(theTargetCity);
        this.lengthOfPath = Integer.parseInt(theLengthOfPath);
    }

}
