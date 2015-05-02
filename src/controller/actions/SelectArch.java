package controller.actions;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;
import view.Canvas;
import view.style.Theme;

public class SelectArch extends MouseAdapter {

    private static final int HIT_BOX_SIZE = 6;

    @Override
    public void mouseClicked(MouseEvent e) {
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

        for(Node node : graph.keySet()) {
        	for(Arch a : graph.get(node)){
        		a.setSelected(false);
        	}
        }

        selectArch(e);
        Canvas.getInstance().grabFocus();
        Canvas.getInstance().repaint();

    }

	private void selectArch(MouseEvent e) {

		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

		int boxX = e.getX() - HIT_BOX_SIZE / 2;
        int boxY = e.getY() - HIT_BOX_SIZE / 2;

        for(Node node : graph.keySet()){
        	if(isNodeContainsPoint(e, node))
        		return;
        }

        for(Node node : graph.keySet()) {

        	for(Arch a : Graph.getInstance().getEdges(node)){

        		Line2D line = new Line2D.Double(node.getPosition().getX()+20, node.getPosition().getY()+20,
        				a.getTarget().getPosition().getX()+20, a.getTarget().getPosition().getY()+20);

	        	if (line.intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE)) {
	        		a.setSelected(true);
	        		return;
	        	}
        	}
        }
	}

	private boolean isNodeContainsPoint(MouseEvent e, Node node) {
        if(node instanceof Place){
        	return isPlaceContainsPoint(e, node.getPosition());
        }

        if(node instanceof Transition){
        	return isTransitionContainsPoint(e, node.getPosition());
        }

        return false;
    }

	private boolean isPlaceContainsPoint(MouseEvent e, Point position){
    	Point center = new Point(position.x + Theme.NODE_CENTER, position.y + Theme.NODE_CENTER);
    	double distance = Math.sqrt(Math.pow(center.getX() - e.getX(), 2) + Math.pow(center.getY() - e.getY(), 2));

    	return distance <= Theme.NODE_CENTER;
    }

    private boolean isTransitionContainsPoint(MouseEvent e, Point position) {
    	return position.x < e.getX() && e.getX() < position.x + Theme.SHAPE_SIZE
    			&& position.y < e.getY() && e.getY() < position.y + Theme.SHAPE_SIZE;
    }
}
