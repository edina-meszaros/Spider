package controller.actions.simulation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.State;
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

		TabbedPanel.getInstance().getPlay().setVisible(true);
		TabbedPanel.getInstance().getPause().setVisible(false);
		TabbedPanel.getInstance().getStop().setEnabled(false);
		TabbedPanel.getInstance().getStrictSimulation().setEnabled(true);
		TabbedPanel.getInstance().getWeakSimulation().setEnabled(true);
		TabbedPanel.getInstance().getBalancedHeuristic().setEnabled(true);
		TabbedPanel.getInstance().getRandomHeuristic().setEnabled(true);

		SimulationTimer.getInstance().stop();
		simulation.setState(new State());
		Canvas.getInstance().repaint();
	}

}
