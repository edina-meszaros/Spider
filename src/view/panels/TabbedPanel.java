package view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.xml.bind.DatatypeConverter;

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
	private JRadioButton strictSimulation;
	private JRadioButton weakSimulation;
	private JRadioButton randomHeuristic;
	private JRadioButton balancedHeuristic;
	private static final String playIcon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RjgwRURDNDMzQTI4MTFFMjlCODZGQzk3RDhCRTBDRDIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RjgwRURDNDQzQTI4MTFFMjlCODZGQzk3RDhCRTBDRDIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpGODBFREM0MTNBMjgxMUUyOUI4NkZDOTdEOEJFMENEMiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpGODBFREM0MjNBMjgxMUUyOUI4NkZDOTdEOEJFMENEMiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PgYmT1QAAABbSURBVHjaYvz//z8DJYCRagYwMjKSpBFuMYiB5IrDQHwbiBOAmJmQAWB9aAb8R8J4DSLGALwGkWIAVoPIMQCGD1BqwEm6e4HsQCQrGteRmpAoTsrUM4BcABBgAHpTzzf9rmArAAAAAElFTkSuQmCC";
	private static final String stopIcon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RjgwRURDM0YzQTI4MTFFMjlCODZGQzk3RDhCRTBDRDIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RjgwRURDNDAzQTI4MTFFMjlCODZGQzk3RDhCRTBDRDIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpGODBFREMzRDNBMjgxMUUyOUI4NkZDOTdEOEJFMENEMiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpGODBFREMzRTNBMjgxMUUyOUI4NkZDOTdEOEJFMENEMiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PnnrepYAAABNSURBVHjaYvz//z8DJYCJgULAAmMwMjKSpBHmcnQX7AXJEcB7kTUwwkyCuoDYAGHE5QL6B+KoATQwYAcRenbgS0gUJ2XyMxO52RogwACgzyD40N7fJAAAAABJRU5ErkJggg==";

	private TabbedPanel(Canvas canvas){

		//Editor
		this.firstTab = new JPanel();
		this.firstTab.setBackground(new Color(219,218,213));
		this.firstTab.setPreferredSize(new Dimension(150, 300));
		this.firstTab.setLayout(new GridLayout(0,1));

		this.firstTab.add(new JCheckBox("Átlátszóság"));
		this.firstTab.add(new JCheckBox("Normalitás"));
		this.firstTab.add(new JCheckBox("Biztonságosság"));
		this.firstTab.add(new JCheckBox("Visszatérési képesség"));
		this.firstTab.add(new JCheckBox("Otthonállapot"));

		this.removeBounds = new JButton("Korlátok kiküszöbölése");

		    this.firstTab.add(removeBounds);

		//Simulation
		this.secondTab = new JPanel();
		this.secondTab.setBackground(new Color(219,218,213));
		this.secondTab.setPreferredSize(new Dimension(150, 300));

		this.play = new JButton(new ImageIcon(DatatypeConverter.parseBase64Binary(playIcon)));
		this.stop = new JButton(new ImageIcon(DatatypeConverter.parseBase64Binary(stopIcon)));

		JPanel row = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		JPanel row6 = new JPanel();

		BoxLayout layout = new BoxLayout(this.secondTab, BoxLayout.Y_AXIS);
		BoxLayout layout2 = new BoxLayout(row, BoxLayout.X_AXIS);
		BoxLayout layout3 = new BoxLayout(row2, BoxLayout.X_AXIS);
		BoxLayout layout4 = new BoxLayout(row3, BoxLayout.X_AXIS);
		BoxLayout layout5 = new BoxLayout(row4, BoxLayout.X_AXIS);
		BoxLayout layout6 = new BoxLayout(row5, BoxLayout.X_AXIS);

		BoxLayout layout7 = new BoxLayout(row6, BoxLayout.X_AXIS);


		secondTab.setLayout(layout);

		row.add(this.play);
		row.add(this.stop);
		row.setLayout(layout2);
		secondTab.add(row);

		this.normalSpeed = new JRadioButton("1 sec", true);
		this.doubleSpeed = new JRadioButton("0.5 sec", false);
		this.halfSpeed = new JRadioButton("2 sec", false);

		ButtonGroup group = new ButtonGroup();
		group.add(normalSpeed);
		group.add(doubleSpeed);
		group.add(halfSpeed);

		row2.add(this.normalSpeed);
		row2.add(this.doubleSpeed);
		row2.add(this.halfSpeed);

		//row2.add(Box.createRigidArea(new Dimension(10,30)));

		row2.setLayout(layout3);

		secondTab.add(row2);

		this.strictSimulation = new JRadioButton("Szigorú működési szabály", true);
		this.weakSimulation = new JRadioButton("Gyenge működési szabály", false);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(strictSimulation);
		group2.add(weakSimulation);

		row3.add(this.strictSimulation);
		row3.setLayout(layout4);
		secondTab.add(row3);

		row4.add(this.weakSimulation);
		//row4.add(Box.createRigidArea(new Dimension(10,30)));
		row4.setLayout(layout5);
		secondTab.add(row4);

		this.randomHeuristic = new JRadioButton("Random heurisztika", true);
		this.balancedHeuristic = new JRadioButton("Kiegyensúlyozott heurisztika", false);

		ButtonGroup group3 = new ButtonGroup();
		group3.add(this.randomHeuristic);
		group3.add(this.balancedHeuristic);

		row5.add(this.randomHeuristic);
		row5.setLayout(layout6);
		secondTab.add(row5);

		row6.add(this.balancedHeuristic);
		row6.setLayout(layout7);
		secondTab.add(row6);


		//Reachability graph
		this.thirdTab = new JPanel();
		this.thirdTab.setBackground(new Color(219,218,213));
		this.thirdTab.setPreferredSize(new Dimension(150, 300));

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
