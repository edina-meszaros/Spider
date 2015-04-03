package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import controller.Controller;
import model.Arch;
import model.Graph;
import model.Transition;

public class CreateTransitionButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Controller controller = null;
	
	public CreateTransitionButtonAction(String name, Controller controller) {
		super(name);
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		Point point;
		Transition transition = null;
		List<Arch> edges = new ArrayList<Arch>();

		point = new Point(100, 100); 

//		Runnable r = new Runnable() {
//			public void run() {
				transition = new Transition(point);
				edges.add(new Arch(null, 0));
				Graph.getInstance().addNode(transition, edges);
				controller.getMainWindow().getCanvas().updateGraph(Graph.getInstance().getGraph());
//			}
//		};
//
//		Thread t = new Thread(r);
//		t.start();
		
		controller.getMainWindow().getCanvas().updateGraph(Graph.getInstance().getGraph());
	}
}
