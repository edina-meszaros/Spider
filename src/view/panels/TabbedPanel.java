package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import view.Canvas;

public class TabbedPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private static TabbedPanel instance = null;
	private JPanel firstTab;
	private JPanel secondTab;
	private JPanel thirdTab;
	private JButton removeBounds;
	private JButton play;
	private JButton stop;
	private JRadioButton normalSpeed;
	private JRadioButton doubleSpeed;
	private JRadioButton halfSpeed;

	private TabbedPanel(Canvas canvas){

		//Create first tab
		this.firstTab = new JPanel();
		this.firstTab.setBackground(new Color(219,218,213));
		this.firstTab.setPreferredSize(new Dimension(150, 300));

		this.removeBounds = new JButton("Korlátok");
		this.firstTab.add(removeBounds);

		//Create second tab
		this.secondTab = new JPanel();
		this.secondTab.setBackground(new Color(219,218,213));
		this.secondTab.setPreferredSize(new Dimension(150, 300));

		this.play = new JButton("Play");
		this.secondTab.add(play);

		this.stop = new JButton("Stop");
		this.secondTab.add(stop);

		this.normalSpeed = new JRadioButton("1 sec", true);
		this.doubleSpeed = new JRadioButton("0.5 sec", false);
		this.halfSpeed = new JRadioButton("2 sec", false);

		ButtonGroup group = new ButtonGroup();
		group.add(normalSpeed);
		group.add(doubleSpeed);
		group.add(halfSpeed);

		this.secondTab.add(normalSpeed);
		this.secondTab.add(doubleSpeed);
		this.secondTab.add(halfSpeed);

		this.thirdTab = new JPanel();
		this.thirdTab.setBackground(new Color(219,218,213));
		this.thirdTab.setPreferredSize(new Dimension(150, 300));



		//Add tabs to the panel
		this.addTab("Elemző", this.firstTab);
		this.addTab("Szimuláció", this.secondTab);
		this.addTab("Fedési fa", this.thirdTab);
	}

	public static TabbedPanel getInstance() {
		if (instance == null) {
			instance = new TabbedPanel(Canvas.getInstance());
		}
		return instance;
	}

	public JPanel getFirstTab() { return firstTab; }
	public JPanel getSecondTab() { return secondTab; }
	public JPanel getThirdTab() { return thirdTab; }
	public JButton getPlay() { return play; }
	public JButton getStop() { return stop; }
	public JRadioButton getDoubleSpeed() { return doubleSpeed; }
	public JRadioButton getHalfSpeed() { return halfSpeed; }
	public JRadioButton getNormalSpeed() { return normalSpeed; }
	public JButton getRemoveBounds() { return removeBounds; }

}
