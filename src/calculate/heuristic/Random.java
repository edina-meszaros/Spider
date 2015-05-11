package calculate.heuristic;

import calculate.Simulation;
import model.Transition;

import java.util.List;

public class Random {

    public Transition getNextTransition(Simulation simulation) {
        List<Transition> activatableTransitions = simulation.getActivatableTransitions();

        if (activatableTransitions.size() == 0)
            return null;

        java.util.Random randomGenerator = new java.util.Random();
        int randomInt = randomGenerator.nextInt(activatableTransitions.size());

        return activatableTransitions.get(randomInt);
    }

}
