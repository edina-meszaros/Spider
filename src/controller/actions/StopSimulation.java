package controller.actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.Canvas;
import view.panels.TabbedPanel;
import calculate.Simulation;

public class StopSimulation extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {

		TabbedPanel.getInstance().getPlay().setEnabled(true);
		TabbedPanel.getInstance().getStop().setEnabled(false);

		Canvas.getInstance().repaint();
	}

}
