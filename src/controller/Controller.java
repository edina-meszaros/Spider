package controller;

import controller.actions.*;
import view.MainWindow;
import controller.listeners.MousePopupListener;

public class Controller {

	private MainWindow mainWindow = null;
	private CreatePlaceButtonAction createPlaceButtonAction = null;
	private CreateTransitionButtonAction createTransitionButtonAction = null;
	private MousePopupListener mousePopupListener = null;
	private CreatePlacePopupAction createPlacePopupAction = null;
	private CreateTransitionPopupAction createTransitionPopupAction = null;
	private MouseMotionHandler mouseMotionHandler = null;
	private NodeSelect nodeSelect = null;
	
	public Controller(){
		mainWindow = MainWindow.getInstance();
		
		createPlaceButtonAction = new CreatePlaceButtonAction("New Place", this);
		mainWindow.getLeftPanel().getTabbedPanel().getNewPlace().setAction(createPlaceButtonAction);
		
		createTransitionButtonAction = new CreateTransitionButtonAction("New Transition", this);
		mainWindow.getLeftPanel().getTabbedPanel().getNewTransition().setAction(createTransitionButtonAction);
		
		mousePopupListener = new MousePopupListener(mainWindow.getCanvas().getPopup(), mainWindow.getCanvas());
		mainWindow.getCanvas().addMouseListener(mousePopupListener);
		
		createPlacePopupAction = new CreatePlacePopupAction("New Place", this);
		createTransitionPopupAction = new CreateTransitionPopupAction("New Transition", this);
		
		mainWindow.getCanvas().getNewPlace().setAction(createPlacePopupAction);
		mainWindow.getCanvas().getNewTransition().setAction(createTransitionPopupAction);

		mouseMotionHandler = new MouseMotionHandler(mainWindow.getCanvas());
		mainWindow.getCanvas().addMouseListener(mouseMotionHandler);
		mainWindow.getCanvas().addMouseMotionListener(mouseMotionHandler);

		nodeSelect = new NodeSelect(mainWindow.getCanvas());
		mainWindow.getCanvas().addMouseListener(nodeSelect);
	}

	public MainWindow getMainWindow() { return mainWindow; }
}
