package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import view.Canvas;
import view.MainMenu;
import view.MainWindow;
import view.panels.TabbedPanel;
import controller.actions.CreateArch;
import controller.actions.DeleteArch;
import controller.actions.DeleteNode;
import controller.actions.MoveNode;
import controller.actions.SelectArch;
import controller.actions.SelectNode;
import controller.actions.TabChange;
import controller.actions.UnselectOnLostFocus;
import controller.actions.menu.About;
import controller.actions.menu.Exit;
import controller.actions.menu.Help;
import controller.actions.menu.Load;
import controller.actions.menu.NewPage;
import controller.actions.menu.Save;
import controller.actions.popup.ChangeArchWeight;
import controller.actions.popup.AttributeChangeListener;
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

		canvas.getNewPlaceMenu().setAction(new CreatePlace());
		canvas.getNewTransitionMenu().setAction(new CreateTransition());
		canvas.getNewPlaceTokensMenu().setAction(new ChangePlaceToken());

		ChangeNodeName changeNodeName = new ChangeNodeName();
		canvas.getNewNodeNamePlaceMenu().setAction(changeNodeName);
		canvas.getNewNodeNameTransitionMenu().setAction(changeNodeName);

		canvas.getNewArchWeightMenu().setAction(new ChangeArchWeight());
		canvas.getNewPlaceBound().setAction(new ChangePlaceBound());

		canvas.addMouseListener(new AttributeChangeListener());

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
		menu.getNewPage().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

		menu.getSave().setAction(new Save());
		menu.getSave().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		menu.getLoad().setAction(new Load());
		menu.getLoad().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		menu.getExit().setAction(new Exit());
		menu.getExit().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

		menu.getHelp().setAction(new Help());
		menu.getHelp().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

		menu.getAbout().setAction(new About());

		TabbedPanel.getInstance().addChangeListener(new TabChange());
	}
}
