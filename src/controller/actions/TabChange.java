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
        	removeAllEvents(canvas);
        	setAllEvents(canvas);

        	TabbedPanel.getInstance().getStop().addMouseListener(this.controller.getStopSimulation());
        }

        //Simulation
		if(index == 1){
        	removeAllEvents(canvas);
        	Simulation simulation = new Simulation(new State());

        	FireTransition fireTransition = this.controller.getFireTransition();
        	fireTransition.setSimulation(simulation);
        	canvas.addMouseListener(fireTransition);

        	PlaySimulation playSimulation = this.controller.getPlaySimulation();
        	playSimulation.setSimulation(simulation);

        	TabbedPanel.getInstance().getPlay().addMouseListener(this.controller.getPlaySimulation());

        	StopSimulation stopSimulation = this.controller.getStopSimulation();
        	stopSimulation.setSimulation(simulation);

        	TabbedPanel.getInstance().getStop().addMouseListener(this.controller.getStopSimulation());

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

		canvas.getNewPlaceMenu().setAction(this.controller.getCreatePlace());
		canvas.getNewTransitionMenu().setAction(this.controller.getCreateTransition());
		canvas.getNewPlaceTokensMenu().setAction(this.controller.getChangePlaceToken());

		canvas.getNewNodeNamePlaceMenu().setAction(this.controller.getChangeNodeName());
		canvas.getNewNodeNameTransitionMenu().setAction(this.controller.getChangeNodeName());

		canvas.getNewArchWeightMenu().setAction(this.controller.getChangeArchWeight());
		canvas.getNewPlaceBound().setAction(this.controller.getChangePlaceBound());

		canvas.addMouseListener(this.controller.getAttributeChangeListener());

		canvas.addMouseListener(this.controller.getSelectNode());
		canvas.addMouseListener(this.controller.getSelectArch());

		canvas.addMouseListener(this.controller.getMoveNode());
		canvas.addMouseMotionListener(this.controller.getMoveNode());

		canvas.addMouseListener(this.controller.getCreateArch());
		canvas.addMouseMotionListener(this.controller.getCreateArch());

		canvas.addKeyListener(this.controller.getDeleteArch());
		canvas.addKeyListener(this.controller.getDeleteNode());
		canvas.addFocusListener(this.controller.getUnselectOnLostFocus());
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
		canvas.removeMouseListener(this.controller.getFireTransition());
	}
}
