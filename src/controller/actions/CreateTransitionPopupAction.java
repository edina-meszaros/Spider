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

public class CreateTransitionPopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Controller controller = null;
	private Point point = null;
	private Transition transition = null;
	private List<Arch> edges = null;
	private Arch arch = null;
	
	public CreateTransitionPopupAction(String name, Controller controller) {
		super(name);
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		point = controller.getMainWindow().getCanvas().getMousePosition();
		transition = new Transition(point);
		
		edges = new ArrayList<Arch>();
		arch = new Arch(null, 0);
		edges.add(arch);
		
		Graph.getInstance().addNode(transition, edges);
		controller.getMainWindow().getCanvas().updateGraph(Graph.getInstance().getGraph());
	}
}
