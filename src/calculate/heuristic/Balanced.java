package calculate.heuristic;

import calculate.Simulation;
import model.Graph;
import model.Transition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Balanced {

    private Map<Transition, Integer> activations = new HashMap<Transition, Integer>();

    public Balanced() {
        for (Transition transition : Graph.getInstance().getTransitions()) {
            activations.put(transition, 0);
        }
    }

    public Transition getNextTransition(Simulation simulation) {
        List<Transition> activatableTransitions = simulation.getActivatableTransitions();

        if (activatableTransitions.size() == 0)
            return null;

        Transition minTransition = activatableTransitions.get(0);
        Integer min = activations.get(minTransition);

        for (Transition transition : activatableTransitions) {
            if (min > activations.get(transition)) {
                min = activations.get(transition);
                minTransition = transition;
            }
        }

        activations.put(minTransition, min + 1);

        return minTransition;
    }

}
