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

	public Map<Node, List<Arch>> getGraph() {
		return graph;
	}

	public List<Place> getPlaces() {
		List<Place> places = new ArrayList<>();

		for (Node node : Graph.getInstance().getGraph().keySet()) {
			if (node instanceof Place) {
				places.add((Place)node);
			}
		}

		return places;
	}

	public List<Place> getSortedPlaces() {
		List<Place> sortedPlaces = getPlaces();

		sortedPlaces.sort(null);

		return sortedPlaces;
	}

	public List<Transition> getTransitions() {
		List<Transition> transitions = new ArrayList<>();

		for (Node node : Graph.getInstance().getGraph().keySet()) {
			if (node instanceof Transition) {
				transitions.add((Transition) node);
			}
		}

		return transitions;
	}

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
		int number = 0;
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

	public boolean hasInverseArch(Node startNode, Node target) {
		for (Node node : this.graph.keySet()){
			for(Arch arch : this.graph.get(node)){
				if (node.equals(target) && arch.getTarget().equals(startNode)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean hasBoundedPlace(){
		for (Place place : this.getPlaces()){
			return place.getBound() > 0;
		}

		return false;
	}
}
