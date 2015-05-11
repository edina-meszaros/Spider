package controller.actions.simulation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.panels.TabbedPanel;
import calculate.Simulation;

public class PlaySimulation extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {

		TabbedPanel.getInstance().getPlay().setVisible(false);
		TabbedPanel.getInstance().getPause().setVisible(true);
		TabbedPanel.getInstance().getStop().setEnabled(true);
		TabbedPanel.getInstance().getStrictSimulation().setEnabled(false);
		TabbedPanel.getInstance().getWeakSimulation().setEnabled(false);
		TabbedPanel.getInstance().getBalancedHeuristic().setEnabled(false);
		TabbedPanel.getInstance().getRandomHeuristic().setEnabled(false);

		int delay = 1000;

		if(TabbedPanel.getInstance().getNormalSpeed().isSelected()){
			delay = 1000;
		}else if(TabbedPanel.getInstance().getDoubleSpeed().isSelected()){
			delay = 500;
		}else if(TabbedPanel.getInstance().getHalfSpeed().isSelected()){
			delay = 2000;
		}

	    SimulationTimer.getInstance().start(delay);
	}

}
