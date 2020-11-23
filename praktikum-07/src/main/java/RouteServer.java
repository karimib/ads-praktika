import java.util.*;

public class RouteServer implements CommandExecutor {

	private static final String DELIMITER = "\\s";
	private AdjListGraph<DijkstraNode, Edge> graph;
	private List<String> pathList;


	@Override
	public String execute(String command) {
		graph = new AdjListGraph<>(DijkstraNode.class, Edge.class);
		pathList = new ArrayList<>();

		command.lines().forEach(this::addEdge);
		dijkstra("Winterthur", "Lugano");

		return String.join("->", pathList);
	}

	private void addEdge(String line) {
		validateInput(line);
		String[] input = line.split(DELIMITER);
		try {
			graph.addEdge(input[0], input[1], Double.parseDouble(input[2]));
			graph.addEdge(input[1], input[0], Double.parseDouble(input[2]));
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	private void validateInput(String line) {
		if (line == null || line.isBlank() || line.isEmpty()) throw new RuntimeException();
		String[] input = line.split(DELIMITER);
		if (input.length != 3) throw new RuntimeException();
		if (input[0] == input[1]) throw new RuntimeException();
	}

	private void dijkstra(String from, String to) {
		//init
		Queue<DijkstraNode<Edge>> queue = new PriorityQueue<>();
		DijkstraNode<Edge> startNode = graph.findNode(from);
		DijkstraNode<Edge> current;

		graph.getNodes().forEach(node -> { //upgrade graph with DijkstraNodes
			node.setDist(Double.MAX_VALUE);
			node.setPrev(null);
			node.setMark(false);
		});

		startNode.setDist(0); //root has dist 0 to itself
		queue.add(startNode); //add root

		while (!queue.isEmpty()) {
			current = queue.remove();

			if (current.getName().equals(to)) { //found a path
				while (current != null) { //traverse from endNode to startNode
					pathList.add(current.getName());
					current = current.getPrev();
				}
				Collections.reverse(pathList); //reverse the result to get correct path
				return;
			}
			current.setMark(true); //mark as visited
			for (Edge edge : current.getEdges()) { //for every Edge get its destination
				DijkstraNode<Edge> neighbour = (DijkstraNode<Edge>) edge.getDest();

				if (!neighbour.getMark()) { //only if not marked yet
					double dist = current.getDist() + edge.getWeight(); //calc new distance

					if (dist < neighbour.getDist()) {
						neighbour.setDist(dist); //set new distance
						neighbour.setPrev(current); //set new previous => shortest path
						queue.remove(neighbour); //Remove outdated neighbour
						queue.add(neighbour); //Add updated neighbour

					}
				}
			}
		}
	}
}




