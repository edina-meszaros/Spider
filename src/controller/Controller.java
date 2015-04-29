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
	private SetPlaceBoundPopupAction setPlaceBoundPopupAction = null;
	private NodeMove mouseMotionHandler = null;
	private NodeSelect nodeSelect = null;
	private ArchCreate createArrowAction = null;
	private ArchSelect archSelect = null;
	private ArchDelete archDelete = null;
	
	public Controller(){
		
		MainWindow.getInstance();
		
		//createPlaceButtonAction = new CreatePlaceButtonAction("Új hely");
		//TabbedPanel.getInstance().getNewPlace().setAction(createPlaceButtonAction);
		
		//createTransitionButtonAction = new CreateTransitionButtonAction("Új állapot");
		//TabbedPanel.getInstance().getNewTransition().setAction(createTransitionButtonAction);
		
		newNodePopupListener = new NewNodePopupListener(Canvas.getInstance());
		Canvas.getInstance().addMouseListener(newNodePopupListener);
		
		createPlacePopupAction = new CreatePlacePopupAction("Új hely");
		Canvas.getInstance().getNewPlaceMenu().setAction(createPlacePopupAction);
		
		createTransitionPopupAction = new CreateTransitionPopupAction("Új állapot");		
		Canvas.getInstance().getNewTransitionMenu().setAction(createTransitionPopupAction);
		
		changePlaceTokenPopupAction = new ChangePlaceTokenPopupAction("Új token");
		Canvas.getInstance().getNewPlaceTokensMenu().setAction(changePlaceTokenPopupAction);
		
		changeNodeNamePopupAction= new ChangeNodeNamePopupAction("Új név");
		Canvas.getInstance().getNewNodeNamePlaceMenu().setAction(changeNodeNamePopupAction);
		Canvas.getInstance().getNewNodeNameTransitionMenu().setAction(changeNodeNamePopupAction);
		
		changeArchWeightPopupAction = new ChangeArchWeightPopupAction("Új súly");
		Canvas.getInstance().getNewArchWeightMenu().setAction(changeArchWeightPopupAction);
		
		setPlaceBoundPopupAction = new SetPlaceBoundPopupAction("Új korlát");
		Canvas.getInstance().getNewPlaceBound().setAction(setPlaceBoundPopupAction);

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
