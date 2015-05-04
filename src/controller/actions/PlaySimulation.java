package controller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import model.Transition;
import view.Canvas;
import calculate.Simulation;

public class PlaySimulation extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {

		int delay = 1000;
	    ActionListener taskPerformer = new ActionListener() {
	    	@Override
	        public void actionPerformed(ActionEvent evt) {
	    		List<Transition> activatableTransitions = simulation.getActivatableTransitions();

				Random randomGenerator = new Random();
				int randomInt = randomGenerator.nextInt(activatableTransitions.size());

				simulation.fireTransition(activatableTransitions.get(randomInt));

				Canvas.getInstance().repaint();
	        }
	    };
	    new Timer(delay, taskPerformer).start();
	}

}
