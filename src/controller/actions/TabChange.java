package controller.actions;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.State;
import view.Canvas;
import view.panels.TabbedPanel;
import calculate.ReachabilityGraph;
import calculate.Simulation;
import controller.Controller;

public class TabChange implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent changeEvent) {

        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
        int index = sourceTabbedPane.getSelectedIndex();

        Canvas canvas = Canvas.getInstance();

        //Editor
        if(index == 0){
        	removeAllEvents(canvas);
        	setAllEvents(canvas);

        	//TabbedPanel.getInstance().getStop().addMouseListener(this.controller.getStopSimulation());
        }

        //Simulation
		if(index == 1){
        	removeAllEvents(canvas);
        	Simulation simulation = new Simulation(new State());

        	FireTransition fireTransition = Controller.getInstance().getFireTransition();
        	fireTransition.setSimulation(simulation);
        	canvas.addMouseListener(fireTransition);

        	PlaySimulation playSimulation = Controller.getInstance().getPlaySimulation();
        	playSimulation.setSimulation(simulation);

        	TabbedPanel.getInstance().getPlay().addMouseListener(Controller.getInstance().getPlaySimulation());

        	StopSimulation stopSimulation = Controller.getInstance().getStopSimulation();
        	stopSimulation.setSimulation(simulation);

        	TabbedPanel.getInstance().getStop().addMouseListener(Controller.getInstance().getStopSimulation());

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
		Controller controller = Controller.getInstance();

		canvas.addMouseListener(controller.getNewNodeListener());

		canvas.getNewPlaceMenu().setAction(controller.getCreatePlace());
		canvas.getNewTransitionMenu().setAction(controller.getCreateTransition());
		canvas.getNewPlaceTokensMenu().setAction(controller.getChangePlaceToken());

		canvas.getNewNodeNamePlaceMenu().setAction(controller.getChangeNodeName());
		canvas.getNewNodeNameTransitionMenu().setAction(controller.getChangeNodeName());

		canvas.getNewArchWeightMenu().setAction(controller.getChangeArchWeight());
		canvas.getNewPlaceBound().setAction(controller.getChangePlaceBound());

		canvas.addMouseListener(controller.getAttributeChangeListener());

		canvas.addMouseListener(controller.getSelectNode());
		canvas.addMouseListener(controller.getSelectArch());

		canvas.addMouseListener(controller.getMoveNode());
		canvas.addMouseMotionListener(controller.getMoveNode());

		canvas.addMouseListener(controller.getCreateArch());
		canvas.addMouseMotionListener(controller.getCreateArch());

		canvas.addKeyListener(controller.getDeleteArch());
		canvas.addKeyListener(controller.getDeleteNode());
		canvas.addFocusListener(controller.getUnselectOnLostFocus());
	}

	private void removeAllEvents(Canvas canvas) {
		Controller controller = Controller.getInstance();

		canvas.removeMouseListener(controller.getNewNodeListener());
		canvas.getNewPlaceMenu().removeActionListener(controller.getCreatePlace());
		canvas.getNewTransitionMenu().removeActionListener(controller.getCreateTransition());
		canvas.getNewPlaceTokensMenu().removeActionListener(controller.getChangePlaceToken());

		canvas.getNewNodeNamePlaceMenu().removeActionListener(controller.getChangeNodeName());
		canvas.getNewNodeNameTransitionMenu().removeActionListener(controller.getChangeNodeName());

		canvas.getNewArchWeightMenu().removeActionListener(controller.getChangeArchWeight());
		canvas.getNewPlaceBound().removeActionListener(controller.getChangePlaceBound());

		canvas.removeMouseListener(controller.getAttributeChangeListener());
		canvas.removeMouseListener(controller.getSelectNode());
		canvas.removeMouseListener(controller.getSelectArch());

		canvas.removeMouseMotionListener(controller.getMoveNode());
		canvas.removeMouseListener(controller.getMoveNode());

		canvas.removeMouseMotionListener(controller.getCreateArch());
		canvas.removeMouseListener(controller.getCreateArch());

		canvas.removeKeyListener(controller.getDeleteArch());
		canvas.removeKeyListener(controller.getDeleteNode());

		canvas.removeFocusListener(controller.getUnselectOnLostFocus());
		canvas.removeMouseListener(controller.getFireTransition());
	}
}
