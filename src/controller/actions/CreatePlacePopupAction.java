package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.Canvas;
import model.Graph;
import model.Place;

public class CreatePlacePopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Point point = null;
	private Place place = null;
	
	public CreatePlacePopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		point = Canvas.getInstance().getMousePosition();
		place = new Place(point);
		place = new Place(point, Graph.getInstance().getNextName("P"));

		Graph.getInstance().addNode(place);		
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}
}