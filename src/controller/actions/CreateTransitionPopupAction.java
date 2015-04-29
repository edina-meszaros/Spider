package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.Canvas;
import model.Graph;
import model.Transition;

public class CreateTransitionPopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Point point = null;
	private Transition transition = null;
	
	public CreateTransitionPopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		point = Canvas.getInstance().getMousePosition();
		transition = new Transition(point);
		transition = new Transition(point, Graph.getInstance().getNextName("T"));
		
		Graph.getInstance().addNode(transition);
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}
}
