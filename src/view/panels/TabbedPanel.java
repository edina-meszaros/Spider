package view.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.Canvas;

public class TabbedPanel extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	private static TabbedPanel instance = null;
	private JPanel firstTab;
	private JPanel secondTab;
	private JPanel thirdTab;

	private TabbedPanel(Canvas canvas){

		//Create first tab
		this.firstTab = new JPanel();
		this.firstTab.setBackground(new Color(219,218,213));
		this.firstTab.setPreferredSize(new Dimension(150, 300));

		//Create second tab
		this.secondTab = new JPanel();
		this.secondTab.setBackground(new Color(219,218,213));
		this.secondTab.setPreferredSize(new Dimension(150, 300));

		this.thirdTab = new JPanel();
		this.thirdTab.setBackground(new Color(219,218,213));
		this.thirdTab.setPreferredSize(new Dimension(150, 300));

		//Add tabs to the panel
		this.addTab("  Elemző  ", this.firstTab);
		this.addTab("Szimuláció", this.secondTab);
		this.addTab(" Fedési fa ", this.thirdTab);
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
}
