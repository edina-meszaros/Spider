package view;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.Arch;
import model.Graph;
import model.Node;
import view.panels.TabbedPanel;
import view.style.Theme;
import calculate.Simulation;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Canvas instance = null;

	private Image background;

	private JPopupMenu popupNewNode;
	private JPopupMenu popupChangePlace;
	private JPopupMenu popupChangeTransition;
	private JPopupMenu popupChangeArch;
	private JMenuItem newPlace;
	private JMenuItem newTransition;
	private JMenuItem newPlaceTokens;
	private JMenuItem newArchWeight;
	private JMenuItem newNodeNamePlace;
	private JMenuItem newNodeNameTransition;
	private JMenuItem newPlaceBound;
	private Point mousePosition = null;
	private Point lineStart = null;
	private Point lineEnd = null;


	private Simulation simulation;

	private Canvas() {
		super();
		this.setFocusable(true);
		this.setBackground(Theme.BACKGROUND);

		this.background = new ImageIcon(Theme.backgroundImagePath).getImage();

		this.popupNewNode = new JPopupMenu();
		this.popupNewNode.setFocusable(false);

		this.newPlace = new JMenuItem();
		this.newTransition = new JMenuItem();

		this.popupNewNode.add(newPlace);
		this.popupNewNode.add(newTransition);

		this.popupChangePlace = new JPopupMenu();
		this.popupChangePlace.setFocusable(false);

		this.newNodeNamePlace = new JMenuItem();
		this.newNodeNameTransition = new JMenuItem();
		this.newPlaceTokens = new JMenuItem();
		this.newPlaceBound = new JMenuItem();
		this.newArchWeight = new JMenuItem();

		this.popupChangePlace.add(newNodeNamePlace);
		this.popupChangePlace.add(newPlaceTokens);
		this.popupChangePlace.add(newPlaceBound);

		this.popupChangeTransition = new JPopupMenu();
		this.popupChangeTransition.setFocusable(false);
		this.popupChangeTransition.add(newNodeNameTransition);

		this.popupChangeArch = new JPopupMenu();
		this.popupChangeArch.setFocusable(false);
		this.popupChangeArch.add(newArchWeight);
	}

	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
		g2.drawImage(background, this.getWidth() - background.getWidth(null) - 10, this.getHeight() - background.getHeight(null) - 10, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


		//Editor
		if(TabbedPanel.getInstance().getSelectedIndex() == 0){

			EditorDrawer drawer = new EditorDrawer(g2);
			drawer.setLineStart(lineStart);
			drawer.setLineEnd(lineEnd);
			drawer.draw();

			setPreferredSize(drawer.getScreenSize());
			this.revalidate();


		//Simulation
		}else if(TabbedPanel.getInstance().getSelectedIndex() == 1){

			for(Node node : Graph.getInstance().getGraph().keySet()){
				for(Arch arch : Graph.getInstance().getGraph().get(node)){
					arch.setSelected(false);
				}

				node.setSelected(false);
			}

			SimulationDrawer drawer = new SimulationDrawer(g2, this.simulation);
			drawer.draw();

			setPreferredSize(drawer.getScreenSize());
			this.revalidate();

		//Reachability graph
		}else if(TabbedPanel.getInstance().getSelectedIndex() == 2){

			ReachabilityTreeDrawer drawer = new ReachabilityTreeDrawer(g2);
			drawer.draw();

			setPreferredSize(drawer.getScreenSize());
			this.revalidate();
		}

	}

	public JPopupMenu getPopupNewNode() { return popupNewNode; }
	public JPopupMenu getPopupChangePlace() { return popupChangePlace; }
	public JPopupMenu getPopupChangeTransition() { return popupChangeTransition; }
	public JPopupMenu getPopupChangeArch() { return popupChangeArch; }
	public JMenuItem getNewPlaceMenu() { return newPlace; }
	public JMenuItem getNewTransitionMenu() { return newTransition; }
	public JMenuItem getNewPlaceTokensMenu() { return newPlaceTokens; }
	public JMenuItem getNewArchWeightMenu() { return newArchWeight; }
	public JMenuItem getNewNodeNamePlaceMenu() { return newNodeNamePlace; }
	public JMenuItem getNewNodeNameTransitionMenu() { return newNodeNameTransition; }
	public JMenuItem getNewPlaceBound() { return newPlaceBound; }
	@Override
	public Point getMousePosition() { return mousePosition; }
	public void setMousePosition(Point mousePosition) { this.mousePosition = mousePosition; }
	public void setLineStart(Point lineStart) { this.lineStart = lineStart; }
	public void setLineEnd(Point lineEnd) { this.lineEnd = lineEnd; }
	public void setSimulation(Simulation simulation) { this.simulation = simulation; }

}
