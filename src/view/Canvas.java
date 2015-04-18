package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPopupMenu popup;
	private JMenuItem newPlace;
	private JMenuItem newTransition;
	private Point mousePosition = null;
	private Map<Node, List<Arch>> graph = null;

	public Canvas() {
		super();
		this.setBackground(new Color(219, 218, 213));
		
		this.popup = new JPopupMenu();
		
		this.newPlace = new JMenuItem("New Place");
		this.newTransition = new JMenuItem("New Transition");
		
		this.popup.add(newPlace);
		this.popup.add(newTransition);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;		
		
		if(graph == null) return;
		
		for (Node node : graph.keySet()) {
			
			if(node instanceof Place){
				drawPlace(node.getPosition(), g2);				
			}
			
			if(node instanceof Transition){
				drawTransition(node.getPosition(), g2);
			}
		}
	}
	
	public void drawPlace(Point point, Graphics2D g2){
		g2.setColor(new Color(172, 174, 185));			    
		g2.fillOval(point.x, point.y, 40, 40);
		g2.setColor(new Color(118, 126, 135));
		g2.setStroke(new BasicStroke(1.5f));
		g2.drawOval(point.x, point.y, 40, 40);		
	}
	
	public void drawTransition(Point point, Graphics2D g2){
		g2.setColor(new Color(172, 174, 185));			    
		g2.fillRect(point.x, point.y, 40, 40);
		g2.setColor(new Color(118, 126, 135));
		g2.setStroke(new BasicStroke(1.5f));
		g2.drawRect(point.x, point.y, 40, 40);		
	}
	
	public void updateGraph(Map<Node, List<Arch>> graph){
		this.graph = graph;
	}

	public JPopupMenu getPopup() { return popup; }
	public JMenuItem getNewPlace() { return newPlace; }
	public JMenuItem getNewTransition() { return newTransition; }
	public Point getMousePosition() { return mousePosition; }
	public void setMousePosition(Point mousePosition) { this.mousePosition = mousePosition; }	
}
