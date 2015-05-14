package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.xml.bind.DatatypeConverter;

import view.style.Theme;
import calculate.NetAttributes;

public class TabbedPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private static TabbedPanel instance = null;
	private JPanel firstTab;
	private JPanel secondTab;
	private JPanel thirdTab;
	private JButton removeBounds;
	private JButton play;
	private JButton pause;
	private JButton stop;
	private JRadioButton normalSpeed;
	private JRadioButton doubleSpeed;
	private JRadioButton halfSpeed;
	private JRadioButton strictSimulation;
	private JRadioButton weakSimulation;
	private JRadioButton randomHeuristic;
	private JRadioButton balancedHeuristic;
	private JLabel transparency;
	private JLabel normalization;
	private JLabel safetyness;

	private TabbedPanel(){

		//Editor
		this.firstTab = new JPanel();
		this.firstTab.setBackground(new Color(219, 218, 213));
		this.firstTab.setPreferredSize(new Dimension(150, 300));
		this.firstTab.setLayout(new GridLayout(0, 1));

		transparency = new JLabel("Átlátszóság", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_X)), LEFT);
		normalization = new JLabel("Normalitás", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_X)), LEFT);
		safetyness = new JLabel("Biztonságosság", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_X)), LEFT);


		Border paddingBorder = BorderFactory.createEmptyBorder(0,20,0,0);
		Border border = BorderFactory.createLineBorder(Theme.BACKGROUND);

		transparency.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		normalization.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		safetyness.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));

		this.firstTab.add(transparency);
		this.firstTab.add(normalization);
		this.firstTab.add(safetyness);
		JLabel padding = new JLabel();
		padding.setPreferredSize(new Dimension(20, 100));
		this.firstTab.add(padding);

		this.removeBounds = new JButton("Korlátok kiküszöbölése");
		this.firstTab.add(removeBounds);

		//Simulation
		this.secondTab = new JPanel();
		this.secondTab.setBackground(new Color(219,218,213));
		this.secondTab.setPreferredSize(new Dimension(150, 300));

		this.play = new JButton(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.playIcon)));
		this.pause = new JButton(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.pauseIcon)));
		this.pause.setVisible(false);
		this.stop = new JButton(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.stopIcon)));

		JPanel row = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel pad = new JPanel();
		row.setBackground(Theme.BACKGROUND);
		row2.setBackground(Theme.BACKGROUND);
		row3.setBackground(Theme.BACKGROUND);
		pad.setBackground(Theme.BACKGROUND);
		//pad.setPreferredSize(new Dimension(0, 10));

		GridLayout layout4 = new GridLayout(0, 1);

		row.add(this.play);
		row.add(this.pause);
		row.add(this.stop);
		secondTab.add(row);

		this.normalSpeed = new JRadioButton("1 sec", true);
		this.doubleSpeed = new JRadioButton("0.5 sec", false);
		this.halfSpeed = new JRadioButton("2 sec", false);

		ButtonGroup group = new ButtonGroup();
		group.add(normalSpeed);
		group.add(doubleSpeed);
		group.add(halfSpeed);

		row2.add(this.doubleSpeed);
		row2.add(this.normalSpeed);
		row2.add(this.halfSpeed);

		secondTab.add(row2);

		this.weakSimulation = new JRadioButton("Gyenge működési szabály  ", true);
		this.strictSimulation = new JRadioButton("Szigorú működési szabály", false);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(weakSimulation);
		group2.add(strictSimulation);

		this.randomHeuristic = new JRadioButton("Random heurisztika", true);
		this.balancedHeuristic = new JRadioButton("Egyenletes heurisztika", false);

		ButtonGroup group3 = new ButtonGroup();
		group3.add(this.randomHeuristic);
		group3.add(this.balancedHeuristic);

		row3.setLayout(layout4);
		row3.add(this.strictSimulation);
		row3.add(this.weakSimulation);
		//row3.add(pad);
		row3.add(this.randomHeuristic);
		row3.add(this.balancedHeuristic);
		secondTab.add(row3);


		//Reachability tree
		this.thirdTab = new JPanel();
		this.thirdTab.setBackground(new Color(219,218,213));
		this.thirdTab.setPreferredSize(new Dimension(150, 300));

		this.addTab("Elemző", this.firstTab);
		this.addTab("Szimuláció", this.secondTab);
		this.addTab("Fedési fa", this.thirdTab);

		refreshNetAttributes();
	}

	public static TabbedPanel getInstance() {
		if (instance == null) {
			instance = new TabbedPanel();
		}
		return instance;
	}

	public void refreshNetAttributes() {
		NetAttributes attributes = NetAttributes.getInstance();

		if (attributes.isTransparent())
			this.transparency.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_TICK)));
		else
			this.transparency.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_X)));
		if (attributes.isNormal())
			this.normalization.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_TICK)));
		else
			this.normalization.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_X)));
		if (attributes.isSafe())
			this.safetyness.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_TICK)));
		else
			this.safetyness.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.ICON_X)));
	}

	public JPanel getFirstTab() { return firstTab; }
	public JPanel getSecondTab() { return secondTab; }
	public JPanel getThirdTab() { return thirdTab; }
	public JButton getPlay() { return play; }
	public JButton getPause() { return pause; }
	public JButton getStop() { return stop; }
	public JRadioButton getDoubleSpeed() { return doubleSpeed; }
	public JRadioButton getHalfSpeed() { return halfSpeed; }
	public JRadioButton getNormalSpeed() { return normalSpeed; }
	public JRadioButton getStrictSimulation() { return strictSimulation; }
	public JRadioButton getWeakSimulation() { return weakSimulation; }
	public JRadioButton getRandomHeuristic() { return randomHeuristic; }
	public JRadioButton getBalancedHeuristic() { return balancedHeuristic; }
	public JButton getRemoveBounds() { return removeBounds; }

}
