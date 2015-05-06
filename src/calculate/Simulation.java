package calculate;

import java.util.List;
import java.util.Map;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.State;
import model.Transition;

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

        // Select Transitions which may be activated
        List<Transition> activatables = Graph.getInstance().getTransitions();

        // Remove Transitions which have at least one bad arch
        for (Place place : places) {
            for (Arch arch : graph.get(place)) {
                if (state.getMarking(place) < arch.getWeight()) {
                    activatables.remove(arch.getTarget());
                }
            }
        }

        return activatables;
    }

    public void fireTransition(Transition transition) {
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
        List<Place> places = Graph.getInstance().getPlaces();

        // Subtract tokens from source nodes
        for (Place place : places) {
            for (Arch arch : graph.get(place)) {
                if (arch.getTarget().equals(transition)) {
                    state.decreaseMarking(place, (double) arch.getWeight());
                }
            }
        }

        // Add tokens to target nodes
        for (Arch arch : graph.get(transition)) {
            state.increaseMarking((Place) arch.getTarget(), (double) arch.getWeight());
        }
    }

}
