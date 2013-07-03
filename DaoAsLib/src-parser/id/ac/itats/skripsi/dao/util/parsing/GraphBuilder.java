package id.ac.itats.skripsi.dao.util.parsing;

import id.ac.itats.skripsi.dao.util.parsing.engine.OSMParser;
import id.ac.itats.skripsi.dao.util.parsing.model.Graph;
import id.ac.itats.skripsi.dao.util.parsing.osm.OSM;
import id.ac.itats.skripsi.dao.util.parsing.osm.OSMNode;
import id.ac.itats.skripsi.dao.util.parsing.osm.Way;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



//TODO parser data osm lalu build directed graph
public class GraphBuilder {

	private OSM osmData;
	private Set<OSMNode> nodeAwalAkhir = new HashSet<OSMNode>();
	private Graph graph = new Graph();

	public GraphBuilder(String file) throws Exception {
		osmData = OSMParser.parse(file);
		nodeAwalAkhir = getNodeAwalAkhir();
		initiateGraph();
	}

	public Graph getGraph() {
		return graph;
	}

	public Set<OSMNode> getNodesInGraph() {
		return nodeAwalAkhir;
	}

	private Set<OSMNode> getNodeAwalAkhir() {
		Set<OSMNode> result = new HashSet<OSMNode>();
		for (Way way : osmData.getWays()) {
			if (((way.getVisible() == null) || (way.getVisible().equals("true")))
					&& (way.isAccessibleByCar())) {
				List<OSMNode> nodes = way.getNodes();
				if (nodes.size() > 0) {
					result.add(nodes.get(0));
					result.add(nodes.get(nodes.size() - 1));
				}
			}
		}
		return result;
	}

	/*
	 * GRAPH = (V,E)
	 */
	private void initiateGraph() {
		for (Way way : this.osmData.getWays()) {
			if (((way.getVisible() == null) || (way.getVisible().equals("true")))
					&& (way.isAccessibleByCar())) {

				OSMNode previousNode = null;
				int previousPosition = -1;
				int position = 0;

				for (OSMNode currentNode : way.getNodes()) {

					if (nodeAwalAkhir.contains(currentNode)) {
						if (previousNode != null) {
							// jarak antar node
							double jarak = way.getWayPartLength(
									previousPosition, position + 1);

							graph.addEdge(previousNode, currentNode, jarak,
									way.getId());

						}
						previousNode = currentNode;
						previousPosition = position;
					}
					position++;
				}
			}
		}

	}

}
