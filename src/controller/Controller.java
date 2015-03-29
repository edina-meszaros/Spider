package controller;

import view.MainWindow;
import controller.actions.CreatePlaceButtonAction;
import controller.actions.CreatePlacePopupAction;
import controller.actions.CreateTransitionButtonAction;
import controller.actions.CreateTransitionPopupAction;
import controller.listeners.MousePopupListener;
import model.Graph;

public class Controller {

	private MainWindow mainWindow = null;
	private Graph graph = null;
	private CreatePlaceButtonAction createPlaceButtonAction = null;
	private CreateTransitionButtonAction createTransitionButtonAction = null;
	private MousePopupListener mousePopupListener = null;
	private CreatePlacePopupAction createPlacePopupAction = null;
	private CreateTransitionPopupAction createTransitionPopupAction = null;
	
	public Controller(){
		mainWindow = MainWindow.getInstance();
		graph = Graph.getInstance();
		
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
	}

	public MainWindow getMainWindow() { return mainWindow; }
	public Graph getGraph() { return graph; }	
}
