package controller.actions.simulation;

import calculate.Simulation;
import view.Canvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WeakSimulation extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {
		simulation.setStrict(false);
		Canvas.getInstance().repaint();
	}

}
