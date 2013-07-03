package id.ac.itats.skripsi.dao.util.parsing.model;

import id.ac.itats.skripsi.dao.util.parsing.osm.OSMNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private HashMap<Key, Vertex> vertices = new HashMap<Key, Vertex>();

	public void addEdge(OSMNode fromNode, OSMNode toNode, double jarak,
			String edgeId) {
		Vertex fromVertex = vertices.get(new Key(fromNode.lat, fromNode.lon));
		if (fromVertex == null) {
			fromVertex = new Vertex(fromNode);

			putLatLon(fromNode.lat, fromNode.lon, fromVertex);

			// System.out.println(vertices.keySet());

		}
		Vertex toVertex = vertices.get(new Key(toNode.lat, toNode.lon));
		if (toVertex == null) {
			toVertex = new Vertex(toNode);
			putLatLon(toNode.lat, toNode.lon, toVertex);
		}

		fromVertex.adjacencies
				.add(new Edge(fromVertex, toVertex, jarak, edgeId));
	}

	public int getSize() {
		return vertices.size();
	}

	public Collection<Vertex> getVertexs() {
		return vertices.values();
	}

	public Collection<Edge> getEdges() {
		List<Edge> edges = new LinkedList<Edge>();
		for (Vertex vertex : vertices.values()) {
			for (Edge edge : vertex.adjacencies) {
				edges.add(edge);
			}
		}
		return edges;
	}

	public Vertex fromVertex(Key id) {

		return vertices.get(id);
	}

	public Vertex toVertex(Key id) {
		return vertices.get(id);
	}

	private void putLatLon(String lat, String lon, Vertex value) {

		Key key = new Key(lat, lon);
		vertices.put(key, value);
	}

}
