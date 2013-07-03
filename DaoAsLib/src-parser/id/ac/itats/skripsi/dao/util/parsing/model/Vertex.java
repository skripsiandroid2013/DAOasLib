package id.ac.itats.skripsi.dao.util.parsing.model;

import id.ac.itats.skripsi.dao.util.parsing.osm.OSMNode;

import java.util.LinkedList;


//TODO add latlon
public class Vertex implements Comparable<Vertex> {

	public LinkedList<Edge> adjacencies = new LinkedList<Edge>();
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	private OSMNode node;

	public Vertex(OSMNode node) {
		this.setNode(node);
	}

	@Override
	public String toString() {
		return node.id;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}

	public OSMNode getNode(String id) {
		if (this.node.id.equals(id)) {
			return this.node;
		}
		return null;
	}

	public OSMNode getNode() {
		return node;
	}

	public void setNode(OSMNode node) {
		this.node = node;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}

}
