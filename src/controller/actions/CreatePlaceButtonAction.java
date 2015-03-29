package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import model.Arch;
import model.Place;
import controller.Controller;


public class CreatePlaceButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Controller controller = null;
	private Point point;
	private Place place = null;
	private List<Arch> edges = new ArrayList<Arch>();
	
	public CreatePlaceButtonAction(String name, Controller controller) {
		super(name);
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		point = new Point(100, 100);
		
		Runnable r = new Runnable() {
			public void run() {
				place = new Place(point);
				edges.add(new Arch(null, 0));
				controller.getGraph().addNode(place, edges);
			}
		};
			 
		Thread t = new Thread(r);
		t.start();
		
		controller.getMainWindow().getCanvas().updateGraph(controller.getGraph().getGraph());
	}
}
