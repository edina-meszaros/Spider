package controller.actions.simulation;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Transition;
import view.Canvas;
import view.style.Theme;
import calculate.Simulation;

public class FireTransition extends MouseAdapter {

	private Simulation simulation;

    public void setSimulation(Simulation simulation) {
    	this.simulation = simulation;
    }

	@Override
    public void mouseClicked(MouseEvent e) {

		for (Transition transition : this.simulation.getActivatableTransitions()) {

			if (isTransitionContainsPoint(e, transition.getPosition())) {
				this.simulation.fireTransition(transition);
				break;
			}

		}

        Canvas.getInstance().repaint();
    }

	private boolean isTransitionContainsPoint(MouseEvent e, Point position) {
        return position.x < e.getX() && e.getX() < position.x + Theme.SHAPE_SIZE
                && position.y < e.getY() && e.getY() < position.y + Theme.SHAPE_SIZE;
    }

}
