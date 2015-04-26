package view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import view.style.Style;
import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Canvas instance = null;
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
	private Point mousePosition = null;
	private Map<Node, List<Arch>> graph = null;
	private Point lineStart = null;
	private Point lineEnd = null;
	private static final int CHAR_HEIGHT = 3;

	private Canvas() {
		super();
		this.setFocusable(true);
		this.setBackground(Style.BACKGROUND);		

		this.popupNewNode = new JPopupMenu();
		this.popupNewNode.setFocusable(false);

		this.newPlace = new JMenuItem("New Place");
		this.newTransition = new JMenuItem("New Transition");

		this.popupNewNode.add(newPlace);
		this.popupNewNode.add(newTransition);
		
		this.popupChangePlace = new JPopupMenu();
		this.popupChangePlace.setFocusable(false);
		
		this.newPlaceTokens = new JMenuItem("Change token");
		this.newArchWeight = new JMenuItem("Change weight");
		this.newNodeNamePlace = new JMenuItem("Change node name");
		this.newNodeNameTransition = new JMenuItem("Change node name");

		this.popupChangePlace.add(newPlaceTokens);
		this.popupChangePlace.add(newNodeNamePlace);
		
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
		Graphics2D g2 = (Graphics2D) g;

		if(graph == null) return;
		
		for (Node node : graph.keySet()){			
			for(Arch arch : graph.get(node)){
				drawArch(arch, node, g2);
			}			
		}		
		
		for (Node node : graph.keySet()) {
			if(!node.isSelected()){
				if(node instanceof Place){
					drawPlace(node, g2);
				}else{
					drawTransition(node, g2);
				}	
			}
		}
		
		if(Graph.getInstance().getSelectedNode() instanceof Place){
			drawPlace(Graph.getInstance().getSelectedNode(), g2);
		}
		
		if(Graph.getInstance().getSelectedNode() instanceof Transition){
			drawTransition(Graph.getInstance().getSelectedNode(), g2);
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
		
		//Draw token
		g2.setColor(Style.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		String token = ((Place)node).getTokens().toString();
		g2.drawString(token, 
				(int) (node.getNodeCenterPosition().x - 3.5*token.length()), node.getNodeCenterPosition().y + CHAR_HEIGHT);
		
		//Draw label
		g2.drawString("P1", node.getPosition().x-8, node.getPosition().y-8);
		
		System.out.println(node.getPosition());
		
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

        //Draw label
		g2.drawString("T1", node.getPosition().x-8, node.getPosition().y-8);
	}
	
	public void drawArch(Arch arch, Node startNode, Graphics2D g2){
		
		int startX = startNode.getNodeCenterPosition().x;
		int endX = arch.getTarget().getNodeCenterPosition().x;
		int startY = startNode.getNodeCenterPosition().y;
		Point start = new Point(startX, startY);
		int endY = arch.getTarget().getNodeCenterPosition().y;
		Point end = new Point(endX, endY);
		
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
		
		if(arch.getWeight() < 2)
			return;
		
		int lineCenterX = (startX + endX) / 2;
		int lineCenterY = (startY + endY) / 2;
		
		if(startX == endX)
			startX += 1;
		
		if(startY == endY)
			startY += 1;
		
		g2.setColor(Style.BACKGROUND);
		//g2.setColor(Color.yellow);
		g2.fillRect((int) (lineCenterX - 3.5 * String.valueOf(arch.getWeight()).length() - 3), 
				lineCenterY - 10, (int) (13 * String.valueOf(arch.getWeight()).length()), 20);
		
//		g2.setColor(Color.black);
//		g2.setStroke(new BasicStroke(1.0f));
//		g2.drawRect((int) (lineCenterX - 3.5 * String.valueOf(arch.getWeight()).length()), 
//				lineCenterY - 20, (int) (15 * String.valueOf(arch.getWeight()).length()), 20);
		
		g2.setColor(Style.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));		
		g2.drawString(String.valueOf(arch.getWeight()), 
				(int) (lineCenterX - 3.5 * String.valueOf(arch.getWeight()).length()), 
				lineCenterY + 4);
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

	public JPopupMenu getPopupNewNode() { return popupNewNode; }
	public JPopupMenu getPopupChangePlace() { return popupChangePlace; }
	public JPopupMenu getPopupChangeTransition() { return popupChangeTransition; }
	public JPopupMenu getPopupChangeArch() { return popupChangeArch; }
	public JMenuItem getNewPlaceMenu() { return newPlace; }
	public JMenuItem getNewTransitionMenu() { return newTransition; }	
	public JMenuItem getNewPlaceTokensMenu() { 		return newPlaceTokens; 	}
	public JMenuItem getNewArchWeightMenu() { 		return newArchWeight; 	}
	public JMenuItem getNewNodeNamePlaceMenu() { 		return newNodeNamePlace; 	}
	public JMenuItem getNewNodeNameTransitionMenu() { 		return newNodeNameTransition; 	}
	public Point getMousePosition() { return mousePosition; }
	public void setMousePosition(Point mousePosition) { this.mousePosition = mousePosition; }
	public void setLineStart(Point lineStart) { this.lineStart = lineStart; }
	public void setLineEnd(Point lineEnd) { this.lineEnd = lineEnd; }	
}
