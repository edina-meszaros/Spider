package view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;
import view.panels.TabbedPanel;
import view.style.Theme;

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
	private JMenuItem newPlaceBound;
	private Point mousePosition = null;
	private Point lineStart = null;
	private Point lineEnd = null;
	private static final int CHAR_HEIGHT = 3;

	private Canvas() {
		super();
		this.setFocusable(true);
		this.setBackground(Theme.BACKGROUND);

		this.popupNewNode = new JPopupMenu();
		this.popupNewNode.setFocusable(false);

		this.newPlace = new JMenuItem();
		this.newTransition = new JMenuItem();

		this.popupNewNode.add(newPlace);
		this.popupNewNode.add(newTransition);

		this.popupChangePlace = new JPopupMenu();
		this.popupChangePlace.setFocusable(false);

		this.newNodeNamePlace = new JMenuItem();
		this.newNodeNameTransition = new JMenuItem();
		this.newPlaceTokens = new JMenuItem();
		this.newPlaceBound = new JMenuItem();
		this.newArchWeight = new JMenuItem();

		this.popupChangePlace.add(newNodeNamePlace);
		this.popupChangePlace.add(newPlaceTokens);
		this.popupChangePlace.add(newPlaceBound);

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
		Graphics2D g2 = (Graphics2D) g.create();

		if(TabbedPanel.getInstance().getSelectedIndex() == 0){
			drawEditor(g2);
		}

	}

	private void drawEditor(Graphics2D g2) {
		int width = 0, height = 0;

		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

		if(graph == null) return;

		for (Node node : graph.keySet()){
			for(Arch arch : graph.get(node)){
				drawArch(arch, node, g2);
			}
		}

		for (Node node : graph.keySet()) {
			if(!node.isSelected()){
				drawNode(node, g2);
			}

			width = Math.max(width, node.getNodeCenterPosition().x + 30);
			height = Math.max(height, node.getNodeCenterPosition().y + 30);
		}

		drawNode(Graph.getInstance().getSelectedNode(), g2);
		drawTempArch(g2);

		setPreferredSize(new Dimension(width, height));
		this.revalidate();
	}

	public void drawArch(Arch arch, Node startNode, Graphics2D g2){

		int startX = startNode.getNodeCenterPosition().x;
		int endX = arch.getTarget().getNodeCenterPosition().x;
		int startY = startNode.getNodeCenterPosition().y;
		int endY = arch.getTarget().getNodeCenterPosition().y;
		Point start = new Point(startX, startY);
		Point end = new Point(endX, endY);

		if(arch.isSelected()){

			g2.setColor(Theme.SELECT);
			g2.drawLine(start.x, start.y, end.x, end.y);

			if(arch.getTarget() instanceof Place){
				int angle = getAngle(start, end);
				Point arrowHead = calculateArrowHead(start, end, true);
				g2.fillArc(arrowHead.x - 15, arrowHead.y - 15, 30, 30, angle - 20, 40);
			}

			if(arch.getTarget() instanceof Transition){
				Point arrowHead = calculateArrowHead(start, end, false);
				int angle = getAngle(start, end);

				g2.fillArc(arrowHead.x - 15, arrowHead.y - 15, 30, 30, angle - 20, 40);
			}

		}else{

			g2.setColor(Theme.DARK_GREY);
			g2.drawLine(start.x, start.y, end.x, end.y);

//			QuadCurve2D quadCurve = new QuadCurve2D.Double(start.x, start.y, (start.x + end.x) / 2, (start.y + end.y) / 2 - 40, end.x, end.y);
//		    g2.draw(quadCurve);

			if(arch.getTarget() instanceof Place){
				int angle = getAngle(start, end);
				Point arrowHead = calculateArrowHead(start, end, true);
				g2.fillArc(arrowHead.x - 15, arrowHead.y - 15, 30, 30, angle - 20, 40);
			}

			if(arch.getTarget() instanceof Transition){
				Point arrowHead = calculateArrowHead(start, end, false);
				int angle = getAngle(start, end);

				g2.fillArc(arrowHead.x - 15, arrowHead.y - 15, 30, 30, angle - 20, 40);
			}
		}

		if(arch.getWeight() < 2)
			return;

		//Draw arch weight
		int lineCenterX = (startX + endX) / 2;
		int lineCenterY = (startY + endY) / 2;

		g2.setColor(Theme.BACKGROUND);

		FontMetrics metrics=getFontMetrics(new Font("Monospaced", Font.PLAIN, 12));
		int width=metrics.stringWidth(String.valueOf(arch.getWeight()));
		int height=metrics.getHeight();

		g2.fillRect(lineCenterX - (width + 10) / 2, lineCenterY - (height / 2), width + 10, height);

		g2.setColor(Theme.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		g2.drawString(String.valueOf(arch.getWeight()),
				(int) (lineCenterX - 3.5 * String.valueOf(arch.getWeight()).length()),
				lineCenterY + 4);
	}

	public void drawNode(Node node, Graphics2D g2) {
		if(node instanceof Place){
			drawPlace((Place) node, g2);
		}

		if(node instanceof Transition){
			drawTransition((Transition) node, g2);
		}
	}

	public void drawPlace(Place node, Graphics2D g2){

		//Draw node
		g2.setColor(Theme.LIGHT_GREY);
		g2.fillOval(node.getPosition().x, node.getPosition().y, Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

        if(node.isSelected()){
            g2.setColor(Theme.SELECT);
        }else{
            g2.setColor(Theme.DARK_GREY);
        }

		g2.setStroke(new BasicStroke(1.5f));
		g2.drawOval(node.getPosition().x, node.getPosition().y, Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

		//Draw token
		g2.setColor(Theme.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		String token = (node).getTokens().toString();

		if(Integer.parseInt(token) != 0)
			g2.drawString(token,
				(int) (node.getNodeCenterPosition().x - 3.5*token.length()), node.getNodeCenterPosition().y + CHAR_HEIGHT);

		//Draw label
		String label = node.getLabel();

		g2.drawString(label, (int) (node.getNodeCenterPosition().x - 3.5*label.length()), node.getPosition().y - 5);

		//Draw bound
		String bound = "";

		if((node).getBound() != 0){
			bound = "K: ".concat(String.valueOf((node).getBound()));
		}

		g2.drawString(bound, (int) (node.getNodeCenterPosition().x - 3.5*bound.length()), node.getPosition().y + 55);

	}

	public void drawTransition(Transition node, Graphics2D g2) {

		//Draw node
		g2.setColor(Theme.LIGHT_GREY);
		g2.fillRect(node.getPosition().x, node.getPosition().y, Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

		if(node.isSelected()){
			g2.setColor(Theme.SELECT);
		}else{
			g2.setColor(Theme.DARK_GREY);
		}

		g2.setStroke(new BasicStroke(1.5f));
		g2.drawRect(node.getPosition().x, node.getPosition().y, Theme.SHAPE_SIZE, Theme.SHAPE_SIZE);

		g2.setColor(Theme.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));

		//Draw label
		String label = node.getLabel();

		g2.drawString(label, (int) (node.getNodeCenterPosition().x - 3.5*label.length()), node.getPosition().y - 5);
	}

	private void drawTempArch(Graphics2D g2) {
		if(lineStart != null && lineEnd != null){
			g2.setColor(Theme.DARK_GREY);
			float[] dash1 = {2f, 0f, 2f};
			BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
			g2.setStroke(bs1);

			g2.drawLine(lineStart.x + Theme.NODE_CENTER, lineStart.y + Theme.NODE_CENTER, lineEnd.x, lineEnd.y);
			g2.fillArc(lineEnd.x-8, lineEnd.y-8, 20, 20, 135, 45);
		}
	}

	public Point calculateArrowHead(Point lineStart, Point lineEnd, boolean Place){

		int angle = getAngle(lineStart, lineEnd);

		if(Place){
			return new Point(lineEnd.x + (int)(Math.cos(Math.toRadians(angle)) * 20), lineEnd.y - (int)(Math.sin(Math.toRadians(angle)) * 20));
		}

		if(angle > 45 && angle <= 135){

			int y = lineEnd.y - 20;
			int x = (int) (lineEnd.x + ((double) (lineStart.x - lineEnd.x) / (double) (lineStart.y - lineEnd.y)) * -20);

			return new Point(x, y);

		}else if(angle > 135 && angle <= 225){

			int x = lineEnd.x - 20;
			int y = (int) (lineEnd.y + ((double) (lineStart.y - lineEnd.y) / (double) (lineStart.x - lineEnd.x)) * -20);

			return new Point(x, y);

		}else if(angle > 225 && angle <= 315){

			int y = lineEnd.y + 20;
			int x = (int) (lineEnd.x + ((double) (lineStart.x - lineEnd.x) / (double) (lineStart.y - lineEnd.y)) * 20);

			return new Point(x, y);

		}else{

			int x = lineEnd.x + 20;
			int y = (int) (lineEnd.y + ((double) (lineStart.y - lineEnd.y) / (double) (lineStart.x - lineEnd.x)) * 20);

			return new Point(x, y);
		}


	}

	private int getAngle(Point lineStart, Point lineEnd) {
		if(lineEnd.x == lineStart.x){
			if(lineEnd.y > lineStart.y){
				return 90;
			}else{
				return 270;
			}
		}

		double slope = (double) (lineEnd.y - lineStart.y) / (double) (lineEnd.x - lineStart.x);
		int degree = (int) Math.toDegrees(Math.atan(slope));

		if (lineStart.x > lineEnd.x)
			degree += 180;
		else if (lineStart.y > lineEnd.y)
			degree += 360;

		int a = (360-degree + 180) % 360;

		return a;
	}

	public JPopupMenu getPopupNewNode() { return popupNewNode; }
	public JPopupMenu getPopupChangePlace() { return popupChangePlace; }
	public JPopupMenu getPopupChangeTransition() { return popupChangeTransition; }
	public JPopupMenu getPopupChangeArch() { return popupChangeArch; }
	public JMenuItem getNewPlaceMenu() { return newPlace; }
	public JMenuItem getNewTransitionMenu() { return newTransition; }
	public JMenuItem getNewPlaceTokensMenu() { return newPlaceTokens; }
	public JMenuItem getNewArchWeightMenu() { return newArchWeight; }
	public JMenuItem getNewNodeNamePlaceMenu() { return newNodeNamePlace; }
	public JMenuItem getNewNodeNameTransitionMenu() { return newNodeNameTransition; }
	public JMenuItem getNewPlaceBound() { return newPlaceBound; }
	@Override
	public Point getMousePosition() { return mousePosition; }
	public void setMousePosition(Point mousePosition) { this.mousePosition = mousePosition; }
	public void setLineStart(Point lineStart) { this.lineStart = lineStart; }
	public void setLineEnd(Point lineEnd) { this.lineEnd = lineEnd; }
}
