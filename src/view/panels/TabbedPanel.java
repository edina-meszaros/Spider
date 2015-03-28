package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.Canvas;

public class TabbedPanel extends JTabbedPane {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel firstTab;
	private JPanel secondTab;
	private JButton newPlace;
	private JButton newTransition;
	
	public TabbedPanel(Canvas canvas){
		
		//Create first tab
		this.firstTab = new JPanel();
		this.firstTab.setBackground(new Color(219,218,213));
		
		//Create second tab
		this.secondTab = new JPanel();
		this.secondTab.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.secondTab.setBackground(new Color(219,218,213));
		this.secondTab.setPreferredSize(new Dimension(150, 150));
		
		this.newPlace = new JButton();
		this.secondTab.add(newPlace);
		this.newTransition = new JButton();
		this.secondTab.add(newTransition);
		
		//Add tabs to the panel
		this.addTab("Analyser", this.firstTab);
		this.addTab("Editor", this.secondTab);		
	}

	public JPanel getFirstTab() { return firstTab; }
	public JPanel getSecondTab() { return secondTab; }
	public JButton getNewPlace() { return newPlace; }
	public JButton getNewTransition() { return newTransition; }	
}
