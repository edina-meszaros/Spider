package model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import view.style.Theme;
import calculate.ReachabilityGraph;

public class ReachabilityGraphDrawer {

	private Graphics2D g2;
	private int counter = 0;
	private Map<ReachabilityNode, Point> positions = new HashMap<ReachabilityNode, Point>();
	private int x;
	private int y;
	private Dimension screenSize = new Dimension();

	public ReachabilityGraphDrawer(Graphics2D g2) {
		this.g2 = g2;
	}

	public void draw() {

		this.g2.setColor(Theme.DARK_GREY);
		this.g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
		this.x = 500;
		this.y = 500;

		ReachabilityGraph reachabilityGraph = ReachabilityGraph.getInstance();
		drawNode(reachabilityGraph.getRootNode(), 0);
	}

	private void drawNode(ReachabilityNode node, int childNumber) {
		String markingSet = getMarkingSet(node);
		this.counter++;

		int x;
		int y;

		if(node.getParentNode() == null){
			x = this.x;
			y = this.y;
		}else{

			if(childNumber == 0){
				x = positions.get(node.getParentNode()).x;
				y = positions.get(node.getParentNode()).y + 70;
			}else{

				FontMetrics metrics=g2.getFontMetrics(new Font("Monospaced", Font.PLAIN, 12));
				int nodeWidth=metrics.stringWidth(markingSet);

				x = this.x + 20 + nodeWidth;
				y = positions.get(node.getParentNode()).y + 70;

				this.x = x;

			}
		}

		screenSize.width = this.x + 150;
		screenSize.height = Math.max(y + 50, screenSize.height);


		positions.put(node, new Point(x, y));
		g2.drawString(markingSet, x, y);

		if(node.isOld()){
			FontMetrics metrics=g2.getFontMetrics(new Font("Monospaced", Font.PLAIN, 12));
			int nodeWidth=metrics.stringWidth(markingSet);

			g2.drawString("old", x + nodeWidth / 2, y + 15);
		}

		if(node.getParentNode() != null){
			FontMetrics metrics=g2.getFontMetrics(new Font("Monospaced", Font.PLAIN, 12));
			int nodeWidth=metrics.stringWidth(markingSet);
			int nodeHeight=metrics.getHeight();

			int parentWidth=metrics.stringWidth(getMarkingSet(node.getParentNode()));
			int parentHeight=metrics.getHeight();

			Point parentPosition = positions.get(node.getParentNode());

			g2.drawLine(x + nodeWidth / 2, y - nodeHeight / 2 - 15,
					parentPosition.x + parentWidth / 2, parentPosition.y + parentHeight / 2 + 5);

			int lineCenterX = ((x + nodeWidth / 2) + (parentPosition.x + parentWidth / 2)) / 2;
			int lineCenterY = ((y - nodeHeight / 2 - 10) + (parentPosition.y + parentHeight / 2)) / 2;
			g2.drawString(node.getParentTransition().getLabel(), lineCenterX + 3, lineCenterY);
		}

		for(ReachabilityNode child : node.getChildren()){
			drawNode(child, node.getChildren().indexOf(child));
		}
	}

	private String getMarkingSet(ReachabilityNode node) {
		State state = node.getState();
		String markingSet = "M" + this.counter + " = (";

		for(Place place : Graph.getInstance().getSortedPlaces()){

			if(state.getMarking(place) == Double.POSITIVE_INFINITY){
				markingSet += "oo" + ", ";
			}else{
				markingSet += state.getMarking(place).intValue() + ", ";
			}
		}

		markingSet = markingSet.substring(0, markingSet.length()-2) + ")";
		return markingSet;
	}

	public Dimension getScreenSize() { return screenSize; }
}
