package controller.actions.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import model.Arch;
import model.Graph;
import model.Transition;
import view.Canvas;

public class CreateTransition extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public CreateTransition() {
		super("Új állapot");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Point point = Canvas.getInstance().getMousePosition();
		Transition transition = new Transition(point, Graph.getInstance().getNextName("T"));

		Graph.getInstance().getGraph().put(transition, new ArrayList<Arch>());
		Canvas.getInstance().repaint();
	}
}