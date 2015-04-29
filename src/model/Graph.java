package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	
	private static final long serialVersionUID = 1L;
	
	private static Map<Node, List<Arch>> graph = new HashMap<Node, List<Arch>>();
	private static Graph instance = null;

	private Graph() {

	}

	public static Graph getInstance() {
		if (instance == null) {
			instance = new Graph();
		}
		return instance;		
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
	
	public String getNextName(){
		int placeCounter = 1;
		int transitionCounter = 1;
		String label = "";
		
		for(Node node : graph.keySet()){
			if(node instanceof Place && ((Place)node).getLabel().equals("P".concat(String.valueOf(placeCounter)))){
				
				System.out.println("if P".concat(String.valueOf(placeCounter)));
				placeCounter++;
			}else{
				System.out.println("P".concat(String.valueOf(placeCounter)));
				node.setLabel("P".concat(String.valueOf(placeCounter)));
				
				label = node.getLabel();				
			}
			
			if(node instanceof Transition && ((Transition)node).getLabel().equals("T".concat(String.valueOf(transitionCounter)))){				
				transitionCounter++;
			}else{
				node.setLabel("T".concat(String.valueOf(transitionCounter)));				
				label =  node.getLabel();	
			}
		}
		
		return label;
	}
}
