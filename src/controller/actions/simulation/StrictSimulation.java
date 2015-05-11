package controller.actions.simulation;

import calculate.Simulation;
import model.State;
import view.Canvas;
import view.panels.TabbedPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
