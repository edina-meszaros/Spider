package view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import view.style.Style;
import model.Arch;
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
		this.setBackground(Style.BACKGROUND);

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
			
			if(node instanceof Place){
				drawPlace(node, g2);
			}
			
			if(node instanceof Transition){
				drawTransition(node, g2);
			}			
		
			for (Arch arch : graph.get(node)) {
				drawArch(arch, node, g2);
			}			
		}		
		
		if(lineStart != null && lineEnd != null){		
			g2.setColor(Style.DARK_GREY);
			float[] dash1 = {2f, 0f, 2f};
			BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
	                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
			g2.setStroke(bs1);

			g2.drawLine(lineStart.x + Style.CENTER, lineStart.y + Style.CENTER, lineEnd.x, lineEnd.y);
			g2.fillArc(lineEnd.x-8, lineEnd.y-8, 20, 20, 135, 45);
		}
	}

	public void drawPlace(Node node, Graphics2D g2){
		g2.setColor(Style.LIGHT_GREY);
		g2.fillOval(node.getPosition().x, node.getPosition().y, Style.SHAPE_SIZE, Style.SHAPE_SIZE);

        if(node.isSelected()){
            g2.setColor(Style.PINK);
        }else{
            g2.setColor(Style.DARK_GREY);
        }

		g2.setStroke(new BasicStroke(1.5f));
		g2.drawOval(node.getPosition().x, node.getPosition().y, Style.SHAPE_SIZE, Style.SHAPE_SIZE);
		
		//Place tokens
		g2.setColor(Style.DARK_GREY);
		g2.drawString(((Place)node).getTokens().toString(), node.getPosition().x + 18, node.getPosition().y + 23);
	}

	public void drawTransition(Node node, Graphics2D g2) {
        g2.setColor(Style.LIGHT_GREY);
        g2.fillRect(node.getPosition().x, node.getPosition().y, Style.SHAPE_SIZE, Style.SHAPE_SIZE);

        if(node.isSelected()){
            g2.setColor(Style.PINK);
        }else{
            g2.setColor(Style.DARK_GREY);
        }

        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRect(node.getPosition().x, node.getPosition().y, Style.SHAPE_SIZE, Style.SHAPE_SIZE);
	}
	
	public void drawArch(Arch arch, Node startNode, Graphics2D g2){
		
		Point start = new Point(startNode.getPosition().x + Style.CENTER, startNode.getPosition().y + Style.CENTER);
		Point end = new Point(arch.getTarget().getPosition().x + Style.CENTER, arch.getTarget().getPosition().y + Style.CENTER);
		
		if(arch.isSelected()){
			
			g2.setColor(Style.PINK);
			g2.drawLine(start.x, start.y, end.x, end.y);
			
			//Point arrowHead = calculateArrowHead(start, end);		
			//System.out.println(end + " @@ " + arrowHead);				
			g2.setColor(Style.DARK_GREY);
			g2.fillArc(end.x-22, end.y-22, 20, 20, 135, 45);
		}else{				
			
			g2.setColor(Style.DARK_GREY);
			g2.drawLine(start.x, start.y, end.x, end.y);			
			
			//Point arrowHead = calculateArrowHead(start, end);		
			//System.out.println(end + " @@ " + arrowHead);
			
			g2.fillArc(end.x-22, end.y-22, 20, 20, 135, 45);
		}
		
		//Point arrowHead = calculateArrowHead(start, end);		
		//System.out.println(end + " @@ " + arrowHead);
		
		int lineCenterX = (startNode.getPosition().x + Style.CENTER + arch.getTarget().getPosition().x + Style.CENTER) / 2;
		int lineCenterY = (startNode.getPosition().y + Style.CENTER + arch.getTarget().getPosition().y + Style.CENTER) / 2;
		
		g2.setColor(Style.DARK_GREY);		
		g2.drawString( String.valueOf(arch.getWeight()), lineCenterX - 8, lineCenterY - 8);
	}

	public void updateGraph(Map<Node, List<Arch>> graph){
		this.graph = graph;
	}
	
	public Point calculateArrowHead(Point lineStart, Point lineEnd){
		
		int radius = Style.SHAPE_SIZE/2;
		int a = (int) Math.pow(lineStart.x - lineEnd.x, 2);
		int b = (int) Math.pow(lineStart.y - lineEnd.y, 2);
		
		int X = lineEnd.x + (radius * (lineStart.x - lineEnd.x)) / (int) Math.sqrt(a + b);
		int Y = -1 * ((((lineStart.y - lineEnd.y) / (lineStart.x - lineEnd.x)) * (X - lineEnd.x)) - lineEnd.y);
		
		return new Point(X, Y);
		
	}

	public JPopupMenu getPopup() { return popup; }
	public JMenuItem getNewPlace() { return newPlace; }
	public JMenuItem getNewTransition() { return newTransition; }
	public Point getMousePosition() { return mousePosition; }
	public void setMousePosition(Point mousePosition) { this.mousePosition = mousePosition; }
	public void setLineStart(Point lineStart) { this.lineStart = lineStart; }
	public void setLineEnd(Point lineEnd) { this.lineEnd = lineEnd; }
	
}
