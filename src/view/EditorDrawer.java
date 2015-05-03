package view;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.Map;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;
import view.style.Theme;

public class EditorDrawer {

	private Point lineStart = null;
	private Point lineEnd = null;
	private static final int CHAR_HEIGHT = 3;
	private Dimension screenSize;
	private Graphics2D g2;

	public EditorDrawer(Graphics2D g2) {
		this.g2 = g2;
	}

	public void draw() {

		screenSize = new Dimension(0, 0);


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

			screenSize.width = Math.max(screenSize.width, node.getNodeCenterPosition().x + 30);
			screenSize.height = Math.max(screenSize.height, node.getNodeCenterPosition().y + 30);
		}

		drawNode(Graph.getInstance().getSelectedNode(), g2);
		drawTempArch(g2);


	}

	public void drawArch(Arch arch, Node startNode, Graphics2D g2){

		int startX = startNode.getNodeCenterPosition().x;
		int endX = arch.getTarget().getNodeCenterPosition().x;
		int startY = startNode.getNodeCenterPosition().y;
		int endY = arch.getTarget().getNodeCenterPosition().y;
		Point start;
		Point end;

		if (hasInverseArch(startNode, arch.getTarget())) {
			double L = Math.sqrt((startX - endX) * (startX - endX) + (startY - endY) * (startY - endY));
			double offsetPixels = 5.0;

			double x1p = startX + offsetPixels * (endY-startY) / L;
			double x2p = endX + offsetPixels * (endY-startY) / L;
			double y1p = startY + offsetPixels * (startX-endX) / L;
			double y2p = endY + offsetPixels * (startX-endX) / L;

			start = new Point((int)x1p, (int)y1p);
			end = new Point((int)x2p, (int)y2p);
		} else {
			start = new Point(startX, startY);
			end = new Point(endX, endY);
		}

		if(arch.isSelected()){
			g2.setColor(Theme.SELECT);
		}else{
			g2.setColor(Theme.DARK_GREY);
		}

		g2.drawLine(start.x, start.y, end.x, end.y);

		if(arch.getTarget() instanceof Place){
			int angle = getAngle(start, end);
			Point arrowHead = calculateArrowHead(start, end, true);
			g2.fillArc(arrowHead.x - 15, arrowHead.y - 15, 30, 30, angle - 20, 40);
		}

		if(arch.getTarget() instanceof Transition){
			int angle = getAngle(start, end);
			Point arrowHead = calculateArrowHead(start, end, false);
			g2.fillArc(arrowHead.x - 15, arrowHead.y - 15, 30, 30, angle - 20, 40);
		}

		if(arch.getWeight() < 2)
			return;

		//Draw arch weight
		int lineCenterX = (startX + endX) / 2;
		int lineCenterY = (startY + endY) / 2;

		g2.setColor(Theme.BACKGROUND);

		FontMetrics metrics=g2.getFontMetrics(new Font("Monospaced", Font.PLAIN, 12));
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

	private boolean hasInverseArch(Node startNode, Node target) {
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		for (Node node : graph.keySet()){
			for(Arch arch : graph.get(node)){
				if (node.equals(target) && arch.getTarget().equals(startNode)) {
					return true;
				}
			}
		}

		return false;
	}



	protected void drawPlace(Place node, Graphics2D g2){

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

		g2.setColor(Theme.DARK_GREY);
		g2.setFont(new Font("Monospaced", Font.PLAIN, 12));

		drawToken(node, g2);

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

	protected void drawToken(Place node, Graphics2D g2) {
		switch (node.getTokens()) {
			case 0:
				break;
			case 1:
				g2.fillOval(node.getNodeCenterPosition().x - Theme.TOKEN_SIZE/2, node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				break;
			case 2:
				g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				break;
			case 3:
				g2.fillOval(node.getNodeCenterPosition().x - Theme.TOKEN_SIZE/2, node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				break;
			case 4:
				g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				break;
			case 5:
				g2.fillOval(node.getNodeCenterPosition().x - Theme.TOKEN_SIZE/2, node.getNodeCenterPosition().y - Theme.TOKEN_SIZE/2, Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y - (int)(Theme.TOKEN_SIZE*1.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x - (int)(Theme.TOKEN_SIZE*1.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				g2.fillOval(node.getNodeCenterPosition().x + (int)(Theme.TOKEN_SIZE*0.3), node.getNodeCenterPosition().y + (int)(Theme.TOKEN_SIZE*0.3), Theme.TOKEN_SIZE, Theme.TOKEN_SIZE);
				break;
			default:
				String token = node.getTokens().toString();
				g2.drawString(token,
						(int) (node.getNodeCenterPosition().x - 3.5*token.length()), node.getNodeCenterPosition().y + CHAR_HEIGHT);
				break;
		}
	}

	protected void drawTransition(Transition node, Graphics2D g2) {

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

		return (360-degree + 180) % 360;
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

	public void setLineStart(Point lineStart) { this.lineStart = lineStart; }
	public void setLineEnd(Point lineEnd) { this.lineEnd = lineEnd; }
	public Dimension getScreenSize() { return screenSize; }
}
