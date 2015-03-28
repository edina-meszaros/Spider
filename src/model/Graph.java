package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	Map<Node, List<Arch>> graph = new HashMap<Node, List<Arch>>();

	private static Graph instance = null;

	private Graph() {

	}

	public static Graph getInstance() {
		if (instance == null) {
			instance = new Graph();
		}
		return instance;
	}
}
