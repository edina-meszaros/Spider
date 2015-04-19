package view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import controller.Controller;
import controller.actions.CreateArrowAction;
import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Canvas instance = null;
	private JPopupMenu popup;
	private JMenuItem newPlace;
	private JMenuItem newTransition;
	private Point mousePosition = null;
	private Map<Node, List<Arch>> graph = null;
	private Point lineStart = null;
	private Point lineEnd = null;

	private Canvas() {
		super();
		this.setBackground(new Color(219, 218, 213));

		this.popup = new JPopupMenu();

		this.newPlace = new JMenuItem("New Place");
		this.newTransition = new JMenuItem("New Transition");

		this.popup.add(newPlace);
		this.popup.add(newTransition);
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
		Graphics2D g2 = (Graphics2D) g;

		if(graph == null) return;

		for (Node node : graph.keySet()) {
		
			for(Arch arch : graph.get(node)){
				
				if(node instanceof Place){
					drawPlace(node, g2);					
					if(arch.getTarget() != null)
						drawArch(node, arch.getTarget(), g2);
				}

				if(node instanceof Transition){
					drawTransition(node, g2);
					if(arch.getTarget() != null)
						drawArch(node, arch.getTarget(), g2);
				}
			}
			
		}		
		
		if(lineStart != null && lineEnd != null){		
			g2.setColor(new Color(118, 126, 135));
			float[] dash1 = {2f, 0f, 2f};
			BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
	                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
			g2.setStroke(bs1);

			g2.drawLine(lineStart.x + 20, lineStart.y + 20, lineEnd.x, lineEnd.y);
			g2.fillArc(lineEnd.x-8, lineEnd.y-8, 20, 20, 135, 45);
		}
	}

	public void drawPlace(Node node, Graphics2D g2){
		g2.setColor(new Color(172, 174, 185));
		g2.fillOval(node.getPosition().x, node.getPosition().y, 40, 40);

        if(node.isSelected()){
            g2.setColor(Color.MAGENTA);
        }else{
            g2.setColor(new Color(118, 126, 135));
        }

		g2.setStroke(new BasicStroke(1.5f));
		g2.drawOval(node.getPosition().x, node.getPosition().y, 40, 40);
	}

	public void drawTransition(Node node, Graphics2D g2) {
        g2.setColor(new Color(172, 174, 185));
        g2.fillRect(node.getPosition().x, node.getPosition().y, 40, 40);

        if(node.isSelected()){
            g2.setColor(Color.MAGENTA);
        }else{
            g2.setColor(new Color(118, 126, 135));
        }

        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(node.getPosition().x, node.getPosition().y, 40, 40);
	}
	
	public void drawArch(Node startNode, Node endNode, Graphics2D g2){
		g2.setColor(new Color(118, 126, 135));
		g2.drawLine(startNode.getPosition().x + 20, startNode.getPosition().y + 20, endNode.getPosition().x + 20, endNode.getPosition().y + 20);
		g2.fillArc(endNode.getPosition().x-3, endNode.getPosition().y-3, 20, 20, 135, 45);
	}

	public void updateGraph(Map<Node, List<Arch>> graph){
		this.graph = graph;
	}

	public JPopupMenu getPopup() { return popup; }
	public JMenuItem getNewPlace() { return newPlace; }
	public JMenuItem getNewTransition() { return newTransition; }
	public Point getMousePosition() { return mousePosition; }
	public void setMousePosition(Point mousePosition) { this.mousePosition = mousePosition; }
	public void setLineStart(Point lineStart) { this.lineStart = lineStart; }
	public void setLineEnd(Point lineEnd) { this.lineEnd = lineEnd; }
	
}
