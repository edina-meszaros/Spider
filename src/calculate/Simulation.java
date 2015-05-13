package calculate;

import java.util.ArrayList;
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
    private boolean strict = false;

    public Simulation(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
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

        // Remove transitions which would overflow the bounds
        if (strict) {
            List<Transition> removeList = new ArrayList<Transition>();
            for (Transition transition : activatables) {
                for (Arch arch : Graph.getInstance().getEdges(transition)) {
                    Place place = (Place)arch.getTarget();
                    if (place.getBound() != 0 && place.getBound() < place.getTokens() + arch.getWeight()) {
                        removeList.add(transition);
                        break;
                    }
                }
            }

            for (Transition transition : removeList) {
                activatables.remove(transition);
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
            Place target = (Place) arch.getTarget();
            state.increaseMarking(target, (double) arch.getWeight());
            if (target.getBound() != 0 && state.getMarking(target) > target.getBound())
                state.setMarking(target, (double) target.getBound());
        }
    }

}
