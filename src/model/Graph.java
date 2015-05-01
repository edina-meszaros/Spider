package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Node, List<Arch>> graph = new HashMap<Node, List<Arch>>();
	private static Graph instance = null;

	private Graph() {

	}

	public static Graph getInstance() {
		if (instance == null) {
			instance = new Graph();
		}
		return instance;
	}

	public static void reset() {
		instance = new Graph();
	}

	public static void serialize(OutputStream output) {
		XMLEncoder encoder = new XMLEncoder(output);
		encoder.writeObject(instance.graph);
		encoder.close();
	}

	public static void deserialize(InputStream input) {
		XMLDecoder decoder = new XMLDecoder(input);
		Object graphObj = decoder.readObject();
		decoder.close();

		if (graphObj instanceof Map) {
			instance = new Graph();
			instance.graph = (Map) graphObj;
		}
	}

	public void addNode(Node node){
		graph.put(node, new ArrayList<Arch>());
	}

	public void deleteNode(Node node){
		//TODO
	}

	public Set<Node> getNodes(){
		return graph.keySet();
	}

	public Map<Node, List<Arch>> getGraph() { return graph; }

	public Node getSelectedNode(){
		for(Node node : graph.keySet()){
			if(node.isSelected()){
				return node;
			}
		}

		return null;
	}

	public List<Arch> getEdges(Node node){
		return graph.get(node);
	}

	public Arch getSelectedArch(){
		for(Node node : graph.keySet()){
			for(Arch arch : getEdges(node)){
				if(arch.isSelected()){
					return arch;
				}
			}
		}

		return null;
	}

	public String getNextName(String prefix){
		int number = 1;
		String label = prefix + number;

		while (hasLabel(label)) {
			label = prefix + ++number;
		}

		return label;
	}

	private boolean hasLabel(String label) {
		for(Node node : graph.keySet()){
			if (node.getLabel().equals(label)) {
				return true;
			}
		}

		return false;
	}
}
