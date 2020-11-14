import java.util.List;
import java.util.LinkedList;

public class AdjListGraph<N extends Node, E extends Edge> implements Graph<N, E> {
	private List<N> nodes = new LinkedList<>();
	private final Class nodeClazz;
	private final Class edgeClazz;

	public AdjListGraph(Class nodeClazz, Class edgeClazz) {
		this.nodeClazz = nodeClazz;
		this.edgeClazz = edgeClazz;
	}

	// füge Knoten hinzu, gebe alten zurück falls Knoten schon existiert
	public N addNode(String name) throws Throwable {
		N node = findNode(name);
		if (node == null) {
			node = (N) nodeClazz.getConstructor(new Class[]{}).newInstance();
			node.setName(name);
			nodes.add(node);
		}
		return node;
	}

	// füge gerichtete Kante hinzu
	public void addEdge(String source, String dest, double weight) throws Throwable {
		N src = addNode(source);
		N dst = addNode(dest);
		try {
			E edge = (E) edgeClazz.getConstructor(new Class[]{}).newInstance();
			edge.setWeight(weight);
			edge.setDest(dst);
			src.addEdge(edge);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	// finde den Knoten anhand seines Namens
	public N findNode(String name) {
		for (N node : nodes) {
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}

	// Iterator über alle Knoten
	public Iterable<N> getNodes() {
		return nodes;
	}
}