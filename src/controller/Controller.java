package controller;

import controller.actions.*;
import view.Canvas;
import view.MainWindow;

public class Controller {

	private ChangeNodeNamePopupAction changeNodeNamePopupAction = null;
	private NodeMove mouseMotionHandler = null;
	private ArchCreate createArrowAction = null;
	
	public Controller(){
		
		MainWindow.getInstance();		
		Canvas canvas = Canvas.getInstance();
		
		canvas.addMouseListener(new NewNodePopupListener(canvas));
		
		canvas.getNewPlaceMenu().setAction(new CreatePlacePopupAction("�j hely"));		
		canvas.getNewTransitionMenu().setAction(new CreateTransitionPopupAction("�j �llapot"));		
		canvas.getNewPlaceTokensMenu().setAction(new ChangePlaceTokenPopupAction("�j token"));
		
		changeNodeNamePopupAction= new ChangeNodeNamePopupAction("�j n�v");
		canvas.getNewNodeNamePlaceMenu().setAction(changeNodeNamePopupAction);
		canvas.getNewNodeNameTransitionMenu().setAction(changeNodeNamePopupAction);
		
		canvas.getNewArchWeightMenu().setAction(new ChangeArchWeightPopupAction("�j s�ly"));
		canvas.getNewPlaceBound().setAction(new SetPlaceBoundPopupAction("�j korl�t"));

		canvas.addMouseListener(new ChangePopupListener(canvas));
		
		canvas.addMouseListener(new NodeSelect());
		canvas.addMouseListener(new ArchSelect());
		
		mouseMotionHandler = new NodeMove();
		canvas.addMouseListener(mouseMotionHandler);
		canvas.addMouseMotionListener(mouseMotionHandler);
		
		createArrowAction = new ArchCreate();
		canvas.addMouseListener(createArrowAction);
		canvas.addMouseMotionListener(createArrowAction);

		canvas.addKeyListener(new ArchDelete());		
		canvas.addKeyListener(new NodeDelete());
		canvas.addFocusListener(new UnselectOnLostFocus());		
	}
}
