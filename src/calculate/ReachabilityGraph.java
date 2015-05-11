package calculate;

import java.util.ArrayList;
import java.util.List;

import model.Graph;
import model.Place;
import model.ReachabilityNode;
import model.State;
import model.Transition;
import view.panels.Output;

public class ReachabilityGraph {

    private static final int maxNodes = 30;

    private static ReachabilityGraph instance;

    private ReachabilityNode root;
    private List<ReachabilityNode> flatTree;

    public static ReachabilityGraph getInstance() {
        if (instance == null) {
            instance = new ReachabilityGraph();
        }

        return instance;
    }

    private ReachabilityGraph() {

    }

    public void calculate() {
        List<ReachabilityNode> nodesToBeProcessed = new ArrayList<>();
        root = new ReachabilityNode();
        flatTree = new ArrayList<>();
        flatTree.add(root);
        nodesToBeProcessed.add(root);

        while (!nodesToBeProcessed.isEmpty() && flatTree.size() <= maxNodes) {
            ReachabilityNode currentNode = nodesToBeProcessed.get(0);
            nodesToBeProcessed.remove(0);

            Simulation simulation = new Simulation(currentNode.getState());

            for (Transition activatable : simulation.getActivatableTransitions()) {

                simulation = new Simulation(new State(currentNode.getState()));
                simulation.fireTransition(activatable);
                ReachabilityNode newNode = new ReachabilityNode();
                newNode.setState(simulation.getState());
                newNode.setParentNode(currentNode);
                newNode.setParentTransition(activatable);
                newNode.setOld(isNodeOld(simulation.getState()));

                if (!newNode.isOld()) {
                    fillInfiniteTokens(newNode);
                    nodesToBeProcessed.add(newNode);
                }

                flatTree.add(newNode);
                currentNode.addChild(newNode);
            }
        }

        if (flatTree.size() > maxNodes) {
            Output.getInstance().setError("A fedési fa maximális mérete (" + maxNodes + ") elérve!");
        }
    }

    public ReachabilityNode getRootNode() {
        return root;
    }

    public List<ReachabilityNode> getFlatTree() {
        return flatTree;
    }

    private Boolean isNodeOld(State state) {
        List<Place> places = Graph.getInstance().getPlaces();

        for (ReachabilityNode node : flatTree) {
            Boolean isOld = true;
            for (Place place : places) {
                if (!state.getMarking(place).equals(node.getState().getMarking(place))) {
                    isOld = false;
                }
            }

            if (isOld) {
                return true;
            }
        }

        return false;
    }

    private void fillInfiniteTokens(ReachabilityNode newNode) {
        List<Place> places = Graph.getInstance().getPlaces();

        for (ReachabilityNode node : flatTree) {

            // Search for a node which is the same but it has less marking
            Boolean isSubSetOfOldNode = true;
            for (Place place : places) {
                if (newNode.getState().getMarking(place) < node.getState().getMarking(place)) {
                    isSubSetOfOldNode = false;
                }
            }

            // Got it
            if (isSubSetOfOldNode) {
                for (Place place : places) {
                    if (newNode.getState().getMarking(place) > node.getState().getMarking(place)) {
                        newNode.getState().setMarking(place, Double.POSITIVE_INFINITY);
                    }
                }
            }
        }
    }

}
