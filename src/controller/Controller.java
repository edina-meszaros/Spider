package controller;

import view.Canvas;
import view.MainMenu;
import view.MainWindow;
import controller.actions.CreateArch;
import controller.actions.DeleteArch;
import controller.actions.DeleteNode;
import controller.actions.MoveNode;
import controller.actions.SelectArch;
import controller.actions.SelectNode;
import controller.actions.UnselectOnLostFocus;
import controller.actions.menu.Exit;
import controller.actions.menu.Load;
import controller.actions.menu.NewPage;
import controller.actions.menu.Save;
import controller.actions.popup.ChangeArchWeight;
import controller.actions.popup.ChangeListener;
import controller.actions.popup.ChangeNodeName;
import controller.actions.popup.ChangePlaceBound;
import controller.actions.popup.ChangePlaceToken;
import controller.actions.popup.CreatePlace;
import controller.actions.popup.CreateTransition;
import controller.actions.popup.NewNodeListener;

public class Controller {

	public Controller(){

		MainWindow.getInstance();
		Canvas canvas = Canvas.getInstance();
		MainMenu menu = MainMenu.getInstance();

		canvas.addMouseListener(new NewNodeListener());

		canvas.getNewPlaceMenu().setAction(new CreatePlace("Új hely"));
		canvas.getNewTransitionMenu().setAction(new CreateTransition("Új állapot"));
		canvas.getNewPlaceTokensMenu().setAction(new ChangePlaceToken("Új token"));

		ChangeNodeName changeNodeName = new ChangeNodeName("Új név");
		canvas.getNewNodeNamePlaceMenu().setAction(changeNodeName);
		canvas.getNewNodeNameTransitionMenu().setAction(changeNodeName);

		canvas.getNewArchWeightMenu().setAction(new ChangeArchWeight("Új súly"));
		canvas.getNewPlaceBound().setAction(new ChangePlaceBound("Új korlát"));

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

		menu.getNewPage().setAction(new NewPage());
		menu.getSave().setAction(new Save());
		menu.getLoad().setAction(new Load());
		menu.getExit().setAction(new Exit());
	}
}
