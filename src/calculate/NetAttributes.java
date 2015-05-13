package calculate;

import model.*;

public class NetAttributes {

    private static NetAttributes instance;

    private NetAttributes() {

    }

    public static NetAttributes getInstance() {
        if (instance == null) {
            instance = new NetAttributes();
        }

        return instance;
    }

    public boolean isTransparent() {
        Graph graph = Graph.getInstance();
        for (Node node : graph.getGraph().keySet()) {
            for (Arch arch : graph.getEdges(node)) {
                for (Arch counterArch : graph.getEdges(arch.getTarget()))
                if (node.equals(counterArch.getTarget())) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isNormal() {
        Graph graph = Graph.getInstance();
        for (Node node : graph.getGraph().keySet()) {
            for (Arch arch : graph.getEdges(node)) {
                if (arch.getWeight() != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isSafe() {
        ReachabilityTree reachabilityGraph = ReachabilityTree.getInstance();
        reachabilityGraph.calculate(false);
        for (ReachabilityNode node : reachabilityGraph.getFlatTree()) {
            for (Place place : Graph.getInstance().getPlaces()) {
                if (node.getState().getMarking(place) > 1) {
                    return false;
                }
            }
        }

        return true;
    }

}
