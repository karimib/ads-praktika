import java.util.function.Function;

import static java.awt.Color.*;

public class LabyrinthServer implements CommandExecutor {
    private AdjListGraph<LabyrinthNode, Edge> graph;
    private ServerGraphics labyrinth;
    private LabyrinthNode<Edge> start;
    private LabyrinthNode<Edge> goal;
    private final double SCALE = 10;

    @Override
    public String execute(String command) throws Exception {
        buildGraph(command);
        buildLabyrinth(command);
        setBoundaries(command);
        search(start);
        drawShortestpath();
        return labyrinth.getTrace();
    }


    private void buildGraph(String command) {
        graph = new AdjListGraph<>(LabyrinthNode.class, Edge.class);
        command.lines().forEach(this::addEdge);
    }

    private void buildLabyrinth(String command) {
        labyrinth = new ServerGraphics();
        labyrinth.setColor(BLACK);
        labyrinth.drawRect(0, 0, 1, 1);
        labyrinth.fillRect(0, 0, 1, 1);
        labyrinth.setColor(WHITE);
        command.lines().forEach(this::drawEdge);
    }

    private void setBoundaries(String command) {
        String firstLine = command.lines().reduce((first, second) -> first).orElse(null);
        String lastLine = command.lines().reduce((first, second) -> second).orElse(null);
        if (lastLine == null || lastLine.isBlank() || lastLine.isEmpty()) throw new IllegalArgumentException();
        String[] firstParts = firstLine.split("\\s");
        String[] lastParts = lastLine.split("\\s");
        try {
            start = graph.findNode(firstParts[0]);
            goal = graph.findNode(lastParts[1]);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void drawShortestpath() {
        labyrinth.setColor(RED);
        LabyrinthNode<Edge> node = goal;
        while (node.getPrev() != null) {
            drawPath(labyrinth, node.getName(), node.getPrev().getName(), true);
            node = node.getPrev();
        }
    }

    private boolean search(LabyrinthNode<Edge> current) {
        current.setMark(true);
        if (current.getName() == goal.getName()) return true;
        else {
            for (Edge edge : current.getEdges()) {
                LabyrinthNode<Edge> neighbour = (LabyrinthNode<Edge>) edge.getDest();
                if (!neighbour.getMark()) {
                    if (search(neighbour)) {
                        neighbour.setPrev(current);
                        return true;
                    }
                }
            }
        }
        current.setMark(false);
        return false;
    }

    private void drawPath(ServerGraphics labyrinth, String from, String to, boolean mouse) {
        double scale = 10;
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';
        double x0 = Math.min(xh0, xh1) / SCALE;
        double y0 = Math.min(yh0, yh1) / SCALE;
        double x1 = Math.max(xh0, xh1) / SCALE;
        double y1 = Math.max(yh0, yh1) / SCALE;
        double w = 1 / SCALE;
        if (mouse) labyrinth.drawLine(x0 + w / 2, y0 + w / 2, x1 + w / 2, y1 + w / 2);
        else {
            if (y0 == y1)
                labyrinth.fillRect(x0, y0, x1 - x0 + w, w);
            else
                labyrinth.fillRect(x0, y0, w, y1 - y0 + w);
        }
    }

    private void addEdge(String line) {
        if (line == null || line.isBlank() || line.isEmpty()) throw new IllegalArgumentException();
        String[] parts = line.split("\\s");
        try {
            graph.addEdge(parts[0], parts[1], 0);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void drawEdge(String line) {
        if (line == null || line.isBlank() || line.isEmpty()) throw new IllegalArgumentException();
        String[] parts = line.split("\\s");
        try {
            drawPath(labyrinth, parts[0], parts[1], false);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
