import edu.kit.informatik.Terminal;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(args[0], args[1], args[2], args[3]);
        graph.output();
    }
}
