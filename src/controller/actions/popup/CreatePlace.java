package controller.actions.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.Canvas;
import model.Graph;
import model.Place;

public class CreatePlace extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public CreatePlace(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Point point = Canvas.getInstance().getMousePosition();
		Place place = new Place(point, Graph.getInstance().getNextName("P"));

		Graph.getInstance().addNode(place);		
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}
}