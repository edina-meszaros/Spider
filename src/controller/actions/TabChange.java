package controller.actions;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.Canvas;
import calculate.ReachabilityGraph;
import controller.Controller;

public class TabChange implements ChangeListener {

	private Controller controller;

	public TabChange(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent) {

        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();

        Canvas canvas = Canvas.getInstance();

        //Simulation
		if(index == 1){
        	removeAllEvents(canvas);
        }

        //Reachability graph
        if (index == 2) {
            ReachabilityGraph.getInstance().calculate();
        }

        canvas.repaint();
    }

	private void removeAllEvents(Canvas canvas) {
		canvas.removeMouseListener(this.controller.getNewNodeListener());
		canvas.getNewPlaceMenu().removeActionListener(this.controller.getCreatePlace());
		canvas.getNewTransitionMenu().removeActionListener(this.controller.getCreateTransition());
		canvas.getNewPlaceTokensMenu().removeActionListener(this.controller.getChangePlaceToken());

		canvas.getNewNodeNamePlaceMenu().removeActionListener(this.controller.getChangeNodeName());
		canvas.getNewNodeNameTransitionMenu().removeActionListener(this.controller.getChangeNodeName());

		canvas.getNewArchWeightMenu().removeActionListener(this.controller.getChangeArchWeight());
		canvas.getNewPlaceBound().removeActionListener(this.controller.getChangePlaceBound());

		canvas.removeMouseListener(this.controller.getAttributeChangeListener());
		canvas.removeMouseListener(this.controller.getSelectNode());
		canvas.removeMouseListener(this.controller.getSelectArch());

		canvas.removeMouseMotionListener(this.controller.getMoveNode());
		canvas.removeMouseListener(this.controller.getMoveNode());

		canvas.removeMouseMotionListener(this.controller.getCreateArch());
		canvas.removeMouseListener(this.controller.getCreateArch());

		canvas.removeKeyListener(this.controller.getDeleteArch());
		canvas.removeKeyListener(this.controller.getDeleteNode());

		canvas.removeFocusListener(this.controller.getUnselectOnLostFocus());
	}
}
