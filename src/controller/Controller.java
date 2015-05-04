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
import controller.actions.FireTransition;
import controller.actions.MoveNode;
import controller.actions.PlaySimulation;
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
import controller.actions.popup.AttributeChangeListener;
import controller.actions.popup.ChangeArchWeight;
import controller.actions.popup.ChangeNodeName;
import controller.actions.popup.ChangePlaceBound;
import controller.actions.popup.ChangePlaceToken;
import controller.actions.popup.CreatePlace;
import controller.actions.popup.CreateTransition;
import controller.actions.popup.NewNodeListener;

public class Controller {

	private NewNodeListener newNodeListener = new NewNodeListener();
	private CreatePlace createPlace = new CreatePlace();
	private CreateTransition createTransition = new CreateTransition();
	private ChangePlaceToken changePlaceToken = new ChangePlaceToken();
	private ChangeNodeName changeNodeName = new ChangeNodeName();
	private ChangeArchWeight changeArchWeight = new ChangeArchWeight();
	private ChangePlaceBound changePlaceBound = new ChangePlaceBound();
	private SelectNode selectNode = new SelectNode();
	private SelectArch selectArch = new SelectArch();
	private AttributeChangeListener attributeChangeListener = new AttributeChangeListener();
	private MoveNode moveNode = new MoveNode();
	private CreateArch createArch = new CreateArch();
	private DeleteArch deleteArch = new DeleteArch();
	private DeleteNode deleteNode = new DeleteNode();
	private UnselectOnLostFocus unselectOnLostFocus = new UnselectOnLostFocus();
	private FireTransition fireTransition = new FireTransition();
	private PlaySimulation playSimulation = new PlaySimulation();

	public Controller(){

		MainWindow.getInstance();
		Canvas canvas = Canvas.getInstance();
		MainMenu menu = MainMenu.getInstance();

		canvas.addMouseListener(newNodeListener);

		canvas.getNewPlaceMenu().setAction(createPlace);
		canvas.getNewTransitionMenu().setAction(createTransition);
		canvas.getNewPlaceTokensMenu().setAction(changePlaceToken);

		canvas.getNewNodeNamePlaceMenu().setAction(changeNodeName);
		canvas.getNewNodeNameTransitionMenu().setAction(changeNodeName);

		canvas.getNewArchWeightMenu().setAction(changeArchWeight);
		canvas.getNewPlaceBound().setAction(changePlaceBound);

		canvas.addMouseListener(attributeChangeListener);

		canvas.addMouseListener(selectNode);
		canvas.addMouseListener(selectArch);

		canvas.addMouseListener(moveNode);
		canvas.addMouseMotionListener(moveNode);

		canvas.addMouseListener(createArch);
		canvas.addMouseMotionListener(createArch);

		canvas.addKeyListener(deleteArch);
		canvas.addKeyListener(deleteNode);
		canvas.addFocusListener(unselectOnLostFocus);

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

		TabbedPanel.getInstance().addChangeListener(new TabChange(this));
	}

	public NewNodeListener getNewNodeListener() { return newNodeListener; }
	public CreatePlace getCreatePlace() { return createPlace; }
	public CreateTransition getCreateTransition() { return createTransition; }
	public ChangePlaceToken getChangePlaceToken() { return changePlaceToken; }
	public ChangeNodeName getChangeNodeName() { return changeNodeName; }
	public ChangeArchWeight getChangeArchWeight() { return changeArchWeight; }
	public ChangePlaceBound getChangePlaceBound() { return changePlaceBound; }
	public SelectNode getSelectNode() { return selectNode; }
	public SelectArch getSelectArch() { return selectArch; }
	public AttributeChangeListener getAttributeChangeListener() { return attributeChangeListener; }
	public MoveNode getMoveNode() { return moveNode; }
	public CreateArch getCreateArch() { return createArch; }
	public DeleteArch getDeleteArch() { return deleteArch; }
	public DeleteNode getDeleteNode() { return deleteNode; }
	public UnselectOnLostFocus getUnselectOnLostFocus() { return unselectOnLostFocus; }
	public FireTransition getFireTransition() { return fireTransition; }
	public PlaySimulation getPlaySimulation() { return playSimulation; }

}
