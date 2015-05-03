package calculate;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Simulation {

    private State state;

    public Simulation(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public List<Transition> getActivatableTransitions() {
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
        List<Place> places = Graph.getInstance().getPlaces();

        List<Transition> activatables = new ArrayList<>();

        // Select Transitions which may be activated
        for (Place place : places) {
            for (Arch arch : graph.get(place)) {
                if (state.getMarking(place) >= arch.getWeight() && !activatables.contains((Transition) arch.getTarget())) {
                    activatables.add((Transition) arch.getTarget());
                }
            }
        }

        // Remove Transitions which have at least one bad arch
        for (Place place : places) {
            for (Arch arch : graph.get(place)) {
                if (state.getMarking(place) < arch.getWeight()) {
                    activatables.remove((Transition) arch.getTarget());
                }
            }
        }

        return activatables;
    }

    public void fireTransition(Transition transition) {
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
        List<Place> places = Graph.getInstance().getPlaces();

        // Subtract weights from source nodes
        for (Place place : places) {
            for (Arch arch : graph.get(place)) {
                if (arch.getTarget().equals(transition)) {
                    state.decreaseMarking(place, (double) arch.getWeight());
                }
            }
        }

        // Add weights to target nodes
        for (Arch arch : graph.get(transition)) {
            state.increaseMarking((Place) arch.getTarget(), (double) arch.getWeight());
        }
    }

}
