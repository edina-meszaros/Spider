package controller;

import controller.actions.*;
import view.Canvas;
import view.MainWindow;
import view.panels.TabbedPanel;

public class Controller {

	private CreatePlaceButtonAction createPlaceButtonAction = null;
	private CreateTransitionButtonAction createTransitionButtonAction = null;
	private NewNodePopupListener newNodePopupListener = null;
	private ChangePopupListener changeNodePopupListener = null;
	private CreatePlacePopupAction createPlacePopupAction = null;
	private CreateTransitionPopupAction createTransitionPopupAction = null;
	private ChangePlaceTokenPopupAction changePlaceTokenPopupAction = null;
	private ChangeNodeNamePopupAction changeNodeNamePopupAction = null;
	private ChangeArchWeightPopupAction changeArchWeightPopupAction = null;
	
	
	private NodeMove mouseMotionHandler = null;
	private NodeSelect nodeSelect = null;
	private ArchCreate createArrowAction = null;
	private ArchSelect archSelect = null;
	private ArchDelete archDelete = null;
	
	public Controller(){
		
		MainWindow.getInstance();
		
		createPlaceButtonAction = new CreatePlaceButtonAction("New Place");
		TabbedPanel.getInstance().getNewPlace().setAction(createPlaceButtonAction);
		
		createTransitionButtonAction = new CreateTransitionButtonAction("New Transition");
		TabbedPanel.getInstance().getNewTransition().setAction(createTransitionButtonAction);
		
		newNodePopupListener = new NewNodePopupListener(Canvas.getInstance());
		Canvas.getInstance().addMouseListener(newNodePopupListener);
		
		createPlacePopupAction = new CreatePlacePopupAction("New Place");
		Canvas.getInstance().getNewPlaceMenu().setAction(createPlacePopupAction);
		
		createTransitionPopupAction = new CreateTransitionPopupAction("New Transition");		
		Canvas.getInstance().getNewTransitionMenu().setAction(createTransitionPopupAction);
		
		changePlaceTokenPopupAction = new ChangePlaceTokenPopupAction("Change token");
		Canvas.getInstance().getNewPlaceTokensMenu().setAction(changePlaceTokenPopupAction);
		
		changeNodeNamePopupAction= new ChangeNodeNamePopupAction("Change node name");
		Canvas.getInstance().getNewNodeNamePlaceMenu().setAction(changeNodeNamePopupAction);
		Canvas.getInstance().getNewNodeNameTransitionMenu().setAction(changeNodeNamePopupAction);
		
		changeArchWeightPopupAction = new ChangeArchWeightPopupAction("Change weight");
		Canvas.getInstance().getNewArchWeightMenu().setAction(changeArchWeightPopupAction);

		mouseMotionHandler = new NodeMove();
		Canvas.getInstance().addMouseListener(mouseMotionHandler);
		Canvas.getInstance().addMouseMotionListener(mouseMotionHandler);

		nodeSelect = new NodeSelect();
		Canvas.getInstance().addMouseListener(nodeSelect);
		
		createArrowAction = new ArchCreate();
		Canvas.getInstance().addMouseListener(createArrowAction);
		Canvas.getInstance().addMouseMotionListener(createArrowAction);
		
		archSelect = new ArchSelect();
		Canvas.getInstance().addMouseListener(archSelect);
		
		archDelete = new ArchDelete();
		Canvas.getInstance().addKeyListener(archDelete);
		
		Canvas.getInstance().addFocusListener(new UnselectOnLostFocus());
		
		Canvas.getInstance().addKeyListener(new NodeDelete());
		
		changeNodePopupListener = new ChangePopupListener(Canvas.getInstance());
		Canvas.getInstance().addMouseListener(changeNodePopupListener);
		
		
	}
}
