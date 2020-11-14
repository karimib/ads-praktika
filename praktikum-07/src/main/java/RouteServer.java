import java.util.*;

public class RouteServer implements CommandExecutor {

	private static final String WHITESPACE = " ";
	private AdjListGraph<DijkstraNode<Edge>, Edge<DijkstraNode>> graph;
	private List<String> pathList = new ArrayList<>();


	@Override
	public String execute(String command) {
		graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
		command.lines().forEach(this::fillGraph);
		dijkstra("Winterthur", "Lugano");
		return String.join("->", pathList);
	}

	private void fillGraph(String line) {
		String[] input = line.split(WHITESPACE);
		try {
			graph.addEdge(input[0], input[1], Double.parseDouble(input[2]));
			graph.addEdge(input[1], input[0], Double.parseDouble(input[2]));
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	private void dijkstra(String from, String to) {
		Queue<DijkstraNode<Edge>> queue = new PriorityQueue<>();
		DijkstraNode<Edge> startNode = graph.findNode(from);
		DijkstraNode<Edge> current;

		graph.getNodes().forEach(node -> {
			node.setDist(Double.MAX_VALUE);
			node.setPrev(null);
			node.setMark(false);
		});

		startNode.setDist(0);
		queue.add(startNode);

		while (!queue.isEmpty()) {
			current = queue.remove();
			if (current.getName().equals(to)) {
				while (current != null) {
					pathList.add(current.getName());
					current = current.getPrev();
				}
				Collections.reverse(pathList);
				return;
			}
			current.setMark(true);
			for (Edge edge : current.getEdges()) {
				DijkstraNode<Edge> neighbour = (DijkstraNode<Edge>) edge.getDest();

				if (!neighbour.getMark()) {
					double dist = current.getDist() + edge.getWeight();

					if (dist < neighbour.getDist()) {
						neighbour.setDist(dist);
						neighbour.setPrev(current);
						queue.remove(neighbour);
						queue.add(neighbour);

					}
				}
			}
		}
	}
}




