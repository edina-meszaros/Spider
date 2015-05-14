package controller.actions.simulation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.Canvas;
import calculate.Simulation;

public class StrictSimulation extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {
		simulation.setStrict(true);
		Canvas.getInstance().repaint();
	}

}
