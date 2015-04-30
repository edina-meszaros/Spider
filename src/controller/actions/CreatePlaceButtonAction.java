package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.Canvas;
import model.Graph;
import model.Place;

public class CreatePlaceButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Point point = null;
	private Place place = null;
	
	public CreatePlaceButtonAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		point = new Point(100, 100);
		place = new Place(point);		

		Graph.getInstance().addNode(place);		
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}
}