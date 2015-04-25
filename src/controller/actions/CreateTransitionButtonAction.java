package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

import view.Canvas;
import model.Graph;
import model.Transition;

public class CreateTransitionButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Point point = null;
	private Transition transition = null;
	public CreateTransitionButtonAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		point = new Point(100, 100);
		transition = new Transition(point);
		
		Graph.getInstance().addNode(transition);
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}
}
