package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import controller.Controller;
import model.Arch;
import model.Place;

public class CreatePlacePopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Controller controller = null;
	private Point point = null;
	private Place place = null;
	private List<Arch> edges = null;
	private Arch arch = null;

	public CreatePlacePopupAction(String name, Controller controller) {
		super(name);
		this.controller = controller;
		this.place = new Place();
		this.arch = new Arch(null, 0);
		this.edges = new ArrayList<Arch>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		point = controller.getMainWindow().getCanvas().getMousePosition();
		
		Runnable r = new Runnable() {
			public void run() {
				place.setPosition(point);
				edges.add(arch);
				controller.getGraph().addNode(place, edges);
				controller.getMainWindow().getCanvas().updateGraph(controller.getGraph().getGraph());
			}
		};

		Thread t = new Thread(r);
		t.start();
	}
}
