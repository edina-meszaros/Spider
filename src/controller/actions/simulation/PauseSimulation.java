package controller.actions.simulation;

import calculate.Simulation;
import view.Canvas;
import view.panels.TabbedPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseSimulation extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {

		TabbedPanel.getInstance().getPlay().setVisible(true);
		TabbedPanel.getInstance().getPause().setVisible(false);
		TabbedPanel.getInstance().getStop().setEnabled(true);

		//Controller.getInstance().getPlaySimulation().setSimulation(new Simulation(new State()));

		SimulationTimer.getInstance().stop();
		Canvas.getInstance().repaint();
	}

}
