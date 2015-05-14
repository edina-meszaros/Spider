package controller.actions;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.Arch;
import model.Graph;
import model.Place;
import model.Transition;
import view.Canvas;
import view.panels.TabbedPanel;

public class RemoveBoundedPlaces extends MouseAdapter {

	@Override
    public void mouseClicked(MouseEvent e) {

		removeBounds();
	}

	private void removeBounds() {

		if(Graph.getInstance().hasBoundedPlace()) {
			for (Place place : Graph.getInstance().getPlaces()) {
				if (place.getBound() > 0) {
					// Creating new place
					Point position = new Point(place.getPosition().x + 45, place.getPosition().y);
					Place newPlace = new Place(position, place.getLabel() + "'", place.getBound() - place.getTokens());

					// Creating outbound edges
					List<Arch> edges = new ArrayList<Arch>();
					for (Transition transition : Graph.getInstance().getTransitions()) {
						for (Arch arch : Graph.getInstance().getEdges(transition)) {
							if (arch.getTarget().equals(place)) {
								edges.add(new Arch(transition, arch.getWeight()));
							}
						}
					}
					Graph.getInstance().getGraph().put(newPlace, edges);

					// Creating inbound edges
					for (Arch arch : Graph.getInstance().getEdges(place)) {
						Graph.getInstance().getEdges(arch.getTarget()).add(new Arch(newPlace, arch.getWeight()));
					}

					place.setBound(0);
				}
			}

			Canvas.getInstance().repaint();
			TabbedPanel.getInstance().refreshNetAttributes();
		}
	}

}
