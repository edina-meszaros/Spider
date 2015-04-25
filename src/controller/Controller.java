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
	private ArchCreateAction createArrowAction = null;
	private ArchSelect archSelect = null;
	private ArchDelete archDelete = null;
	
	public Controller(){
		
		MainWindow.getInstance();
		
		createPlaceButtonAction = new CreatePlaceButtonAction("New Place");
		TabbedPanel.getInstance().getNewPlace().setAction(createPlaceButtonAction);
		
		createTransitionButtonAction = new CreateTransitionButtonAction("New Transition");
		TabbedPanel.getInstance().getNewTransition().setAction(createTransitionButtonAction);
		
		mousePopupListener = new MousePopupListener(Canvas.getInstance().getPopup(), Canvas.getInstance());
		Canvas.getInstance().addMouseListener(mousePopupListener);
		
		createPlacePopupAction = new CreatePlacePopupAction("New Place");
		Canvas.getInstance().getNewPlace().setAction(createPlacePopupAction);
		
		createTransitionPopupAction = new CreateTransitionPopupAction("New Transition");		
		Canvas.getInstance().getNewTransition().setAction(createTransitionPopupAction);

		mouseMotionHandler = new MouseMotionHandler();
		Canvas.getInstance().addMouseListener(mouseMotionHandler);
		Canvas.getInstance().addMouseMotionListener(mouseMotionHandler);

		nodeSelect = new NodeSelect();
		Canvas.getInstance().addMouseListener(nodeSelect);
		
		createArrowAction = new ArchCreateAction();
		Canvas.getInstance().addMouseListener(createArrowAction);
		Canvas.getInstance().addMouseMotionListener(createArrowAction);
		
		archSelect = new ArchSelect();
		Canvas.getInstance().addMouseListener(archSelect);
		
		archDelete = new ArchDelete();
		Canvas.getInstance().addKeyListener(archDelete);
	}
}
