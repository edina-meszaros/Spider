package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import controller.Controller;
import model.Arch;
import model.Graph;
import model.Place;

public class CreatePlacePopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Controller controller = null;

	public CreatePlacePopupAction(String name, Controller controller) {
		super(name);
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Point point = new Point(100, 100);
		Place place = new Place();
		List<Arch> edges = new ArrayList<Arch>();
		Arch arch = new Arch(null, 0);

		point = controller.getMainWindow().getCanvas().getMousePosition();
		
//		Runnable r = new Runnable() {
//			public void run() {
				place.setPosition(point);
				edges.add(arch);
				Graph.getInstance().addNode(place, edges);
				
				System.out.println(Graph.getInstance().getGraph().size());
				
				controller.getMainWindow().getCanvas().updateGraph(Graph.getInstance().getGraph());
//			}
//		};

//		Thread t = new Thread(r);
//		t.start();
	}
}
