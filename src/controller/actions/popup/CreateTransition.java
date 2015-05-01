package controller.actions.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.Canvas;
import model.Graph;
import model.Transition;

public class CreateTransition extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public CreateTransition(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Point point = Canvas.getInstance().getMousePosition();
		Transition transition = new Transition(point, Graph.getInstance().getNextName("T"));
		
		Graph.getInstance().addNode(transition);
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}
}