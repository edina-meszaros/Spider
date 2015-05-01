package controller;

import controller.actions.*;
import controller.actions.popup.*;
import view.Canvas;
import view.MainWindow;

public class Controller {

	public Controller(){
		
		MainWindow.getInstance();		
		Canvas canvas = Canvas.getInstance();
		
		canvas.addMouseListener(new NewNodeListener());
		
		canvas.getNewPlaceMenu().setAction(new CreatePlace("Új hely"));
		canvas.getNewTransitionMenu().setAction(new CreateTransition("Új állapot"));
		canvas.getNewPlaceTokensMenu().setAction(new ChangePlaceToken("Új token"));

		ChangeNodeName changeNodeName = new ChangeNodeName("Új név");
		canvas.getNewNodeNamePlaceMenu().setAction(changeNodeName);
		canvas.getNewNodeNameTransitionMenu().setAction(changeNodeName);
		
		canvas.getNewArchWeightMenu().setAction(new ChangeArchWeight("Új súly"));
		canvas.getNewPlaceBound().setAction(new SetPlaceBound("Új korlát"));

		canvas.addMouseListener(new ChangeListener());
		
		canvas.addMouseListener(new SelectNode());
		canvas.addMouseListener(new SelectArch());

		MoveNode mouseMotionHandler = new MoveNode();
		canvas.addMouseListener(mouseMotionHandler);
		canvas.addMouseMotionListener(mouseMotionHandler);

		CreateArch createArrowAction = new CreateArch();
		canvas.addMouseListener(createArrowAction);
		canvas.addMouseMotionListener(createArrowAction);

		canvas.addKeyListener(new DeleteArch());
		canvas.addKeyListener(new DeleteNode());
		canvas.addFocusListener(new UnselectOnLostFocus());		
	}
}
