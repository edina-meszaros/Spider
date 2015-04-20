package controller;

import controller.actions.*;
import view.Canvas;
import view.MainWindow;
import view.panels.TabbedPanel;

public class Controller {

	private CreatePlaceButtonAction createPlaceButtonAction = null;
	private CreateTransitionButtonAction createTransitionButtonAction = null;
	private MousePopupListener mousePopupListener = null;
	private CreatePlacePopupAction createPlacePopupAction = null;
	private CreateTransitionPopupAction createTransitionPopupAction = null;
	private MouseMotionHandler mouseMotionHandler = null;
	private NodeSelect nodeSelect = null;
	private CreateArrowAction createArrowAction = null;
	
	public Controller(){
		
		MainWindow.getInstance();
		
		createPlaceButtonAction = new CreatePlaceButtonAction("New Place", this);
		TabbedPanel.getInstance().getNewPlace().setAction(createPlaceButtonAction);
		
		createTransitionButtonAction = new CreateTransitionButtonAction("New Transition", this);
		TabbedPanel.getInstance().getNewTransition().setAction(createTransitionButtonAction);
		
		mousePopupListener = new MousePopupListener(Canvas.getInstance().getPopup(), Canvas.getInstance());
		Canvas.getInstance().addMouseListener(mousePopupListener);
		
		createPlacePopupAction = new CreatePlacePopupAction("New Place", this);
		Canvas.getInstance().getNewPlace().setAction(createPlacePopupAction);
		
		createTransitionPopupAction = new CreateTransitionPopupAction("New Transition", this);		
		Canvas.getInstance().getNewTransition().setAction(createTransitionPopupAction);

		mouseMotionHandler = new MouseMotionHandler(Canvas.getInstance());
		Canvas.getInstance().addMouseListener(mouseMotionHandler);
		Canvas.getInstance().addMouseMotionListener(mouseMotionHandler);

		nodeSelect = new NodeSelect(Canvas.getInstance());
		Canvas.getInstance().addMouseListener(nodeSelect);
		
		createArrowAction = new CreateArrowAction(Canvas.getInstance());
		Canvas.getInstance().addMouseListener(createArrowAction);
		Canvas.getInstance().addMouseMotionListener(createArrowAction);				
	}
}
