package controller.actions.popup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import model.Arch;
import model.Graph;
import model.Place;
import view.Canvas;
import view.panels.TabbedPanel;

public class CreatePlace extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public CreatePlace() {
		super("Új hely");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Point point = Canvas.getInstance().getMousePosition();
		Place place = new Place(point, Graph.getInstance().getNextName("P"));

		Graph.getInstance().getGraph().put(place, new ArrayList<Arch>());
		Canvas.getInstance().repaint();
		TabbedPanel.getInstance().refreshNetAttributes();
	}
}