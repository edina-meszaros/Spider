package controller.actions;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.State;
import view.Canvas;
import calculate.ReachabilityGraph;
import calculate.Simulation;
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

        //Editor
        if(index == 0){
        	setAllEvents(canvas);
        }

        //Simulation
		if(index == 1){
        	removeAllEvents(canvas);
        	Simulation simulation = new Simulation(new State());
        	FireTransition fireTransition = this.controller.getFireTransition();
        	fireTransition.setSimulation(simulation);
        	canvas.addMouseListener(fireTransition);
			canvas.setSimulation(simulation);
        }

        //Reachability graph
        if (index == 2) {
            ReachabilityGraph.getInstance().calculate();
            removeAllEvents(canvas);
        }


        canvas.repaint();
    }

	private void setAllEvents(Canvas canvas){
		canvas.addMouseListener(this.controller.getNewNodeListener());

//		canvas.getNewPlaceMenu().setAction(createPlace);
//		canvas.getNewTransitionMenu().setAction(createTransition);
//		canvas.getNewPlaceTokensMenu().setAction(changePlaceToken);
//
//		canvas.getNewNodeNamePlaceMenu().setAction(changeNodeName);
//		canvas.getNewNodeNameTransitionMenu().setAction(changeNodeName);
//
//		canvas.getNewArchWeightMenu().setAction(changeArchWeight);
//		canvas.getNewPlaceBound().setAction(changePlaceBound);
//
//		canvas.addMouseListener(attributeChangeListener);
//
//		canvas.addMouseListener(selectNode);
//		canvas.addMouseListener(selectArch);
//
//		canvas.addMouseListener(moveNode);
//		canvas.addMouseMotionListener(moveNode);
//
//		canvas.addMouseListener(createArch);
//		canvas.addMouseMotionListener(createArch);
//
//		canvas.addKeyListener(deleteArch);
//		canvas.addKeyListener(deleteNode);
//		canvas.addFocusListener(unselectOnLostFocus);
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
