package id.ac.itats.skripsi.dao.util.parsing.model;

public class Edge {
	private final String id;
	private final Vertex toVertex;
	private final Vertex fromVertex;

	private final Double jarak;

	public Edge(Vertex fromVertex, Vertex toVertex, double jarak, String id) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.jarak = jarak;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Vertex getToVertex() {
		return toVertex;
	}

	public Double getJarak() {
		return jarak;
	}

	public Vertex getFromVertex() {
		return fromVertex;
	}

}
