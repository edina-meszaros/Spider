package controller.actions.menu;

import model.Graph;
import view.Canvas;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class NewPage extends AbstractAction {

	public NewPage() {
		super("Új");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Graph.reset();
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}

}
