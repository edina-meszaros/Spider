package model;

import java.util.HashMap;
import java.util.Map;

public class State {

    private Map<Place, Double> markings = new HashMap<>();

    public State() {
        for (Place place : Graph.getInstance().getPlaces()) {
            markings.put(place, place.getTokens().doubleValue());
        }
    }

    public State(State state) {
        for (Place place : state.markings.keySet()) {
            markings.put(place, state.markings.get(place));
        }
    }

    public Double getMarking(Place place) {
        return markings.get(place);
    }

    public void setMarking(Place place, Double marking) {
        markings.put(place, marking);
    }

    public void decreaseMarking(Place place, Double value) {
        Double marking = getMarking(place);
        setMarking(place, marking - value);
    }

    public void increaseMarking(Place place, Double value) {
        Double marking = getMarking(place);
        setMarking(place, marking + value);
    }

}
