package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPopupMenu popup;
	private JMenuItem newPlace;
	private JMenuItem newTransition;

	public Canvas() {
		super();
		this.setBackground(new Color(219, 218, 213));
		
		this.popup = new JPopupMenu();
		
		this.newPlace = new JMenuItem("New Place");
		this.newTransition = new JMenuItem("New Transition");
		
		this.popup.add(newPlace);
		this.popup.add(newTransition);
	}

	public JPopupMenu getPopup() { return popup; }
	public JMenuItem getNewPlace() { return newPlace; }
	public JMenuItem getNewTransition() { return newTransition; }
}
