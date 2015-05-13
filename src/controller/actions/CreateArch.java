package controller.actions;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;
import view.Canvas;
import view.panels.Output;
import view.panels.TabbedPanel;
import view.style.Theme;

public class CreateArch extends MouseAdapter {

    private Node startNode = null;
	private boolean drawingEnabled = false;

    @Override
    public void mousePressed(MouseEvent e) {

        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
        int overlaps = 0;

        for(Node node : graph.keySet()){

        	if(isNodeContainsPoint(e, node))
        		overlaps++;

        	if(!node.isSelected() && isNodeContainsPoint(e, node)){
        		drawingEnabled = true;
        		startNode = node;
        	}
        }

        if(overlaps != 1){
        	drawingEnabled = false;
    		startNode = null;
        }

        if(startNode != null)
        	Canvas.getInstance().setLineStart(startNode.getPosition());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    	if(!drawingEnabled)
    		return;

		Point mouseEndPoint = e.getPoint();
		Canvas.getInstance().setLineEnd(mouseEndPoint);
		Canvas.getInstance().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    	if(!drawingEnabled)
    		return;

    	Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

        for(Node targetNode : graph.keySet()){
        	if(isNodeContainsPoint(e, targetNode) && !startNode.equals(targetNode)){

        		if(startNode.getClass() != targetNode.getClass()){

        			List<Arch> edges = Graph.getInstance().getEdges(startNode);

        			if(!isContainsNode(targetNode, edges)){
        				edges.add(new Arch(targetNode));
        				refreshCanvas();
						TabbedPanel.getInstance().refreshNetAttributes();
        			}

        		}else{
        			Output.getInstance().setError("Azonos típusú csúcsok nem köthetők össze éllel!");
        		}
        	}

        	Canvas.getInstance().setLineStart(null);
			Canvas.getInstance().setLineEnd(null);
			Canvas.getInstance().repaint();
        }

        drawingEnabled = false;
    }

	private boolean isContainsNode(Node targetNode, List<Arch> edges) {

		for(Arch arch : edges){
			if(arch.getTarget().equals(targetNode)){
				return true;
			}
		}

		return false;
	}

	private void refreshCanvas() {
		Canvas.getInstance().setLineStart(null);
		Canvas.getInstance().setLineEnd(null);
		Canvas.getInstance().repaint();
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
