package controller;

import controller.actions.CreatePlaceAction;
import controller.actions.CreateTransitionAction;
import controller.listeners.MousePopupListener;
import layout.MainWindow;
import model.Graph;

public class Controller {

	private MainWindow mainWindow = null;
	private Graph graph = null;
	private CreatePlaceAction createPlaceAction = null;
	private CreateTransitionAction createTransitionAction = null;
	private MousePopupListener mousePopupListener = null;
	
	public Controller(){
		mainWindow = MainWindow.getInstance();
		graph = Graph.getInstance();
		
		createPlaceAction = new CreatePlaceAction("New Place", mainWindow.getCanvas());
		mainWindow.getLeftPanel().getTabbedPanel().getNewPlace().setAction(createPlaceAction);
		
		createTransitionAction = new CreateTransitionAction("New Transition", mainWindow.getCanvas());
		mainWindow.getLeftPanel().getTabbedPanel().getNewTransition().setAction(createTransitionAction);
		
		mousePopupListener = new MousePopupListener(mainWindow.getCanvas().getPopup(), mainWindow.getCanvas());
		mainWindow.getCanvas().addMouseListener(mousePopupListener);
		
		mainWindow.getCanvas().getNewPlace().setAction(createPlaceAction);
		mainWindow.getCanvas().getNewTransition().setAction(createTransitionAction);
	}	
}
