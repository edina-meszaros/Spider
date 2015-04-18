package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import model.Arch;
import model.Graph;
import model.Place;
import controller.Controller;


public class CreatePlaceButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Controller controller = null;
	private Point point = null;
	private Place place = null;
	private List<Arch> edges = null;
	private Arch arch = null;
	
	public CreatePlaceButtonAction(String name, Controller controller) {
		super(name);
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		point = new Point(100, 100);
		place = new Place(point);
		
		edges = new ArrayList<Arch>();
		arch = new Arch(null, 0);
		edges.add(arch);		

		Graph.getInstance().addNode(place, edges);		
		controller.getMainWindow().getCanvas().updateGraph(Graph.getInstance().getGraph());
		controller.getMainWindow().getCanvas().repaint();
	}
}
