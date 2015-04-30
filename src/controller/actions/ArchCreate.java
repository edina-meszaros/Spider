package controller.actions;

import model.*;
import view.Canvas;
import view.style.UIStyle;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class ArchCreate extends MouseAdapter {

    private Node startNode = null;
	private Point mouseEndPoint = null;
	private boolean drawingEnabled = false;

    @Override
    public void mousePressed(MouseEvent e) {
        
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

        for(Node node : graph.keySet()){
        	if(!node.isSelected() && isNodeContainsPoint(e, node)){        		
        		drawingEnabled = true;
        		startNode = node;
        		Canvas.getInstance().setLineStart(startNode.getPosition());
        		break;
        	}
        }        
    }    

    @Override
    public void mouseDragged(MouseEvent e) {
    	
    	if(!drawingEnabled)
    		return;
    	   	   		
		mouseEndPoint = e.getPoint();
		Canvas.getInstance().setLineEnd(mouseEndPoint);
		Canvas.getInstance().repaint();    	    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	
    	if(!drawingEnabled)
    		return;
    	
    	Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
    	
        for(Node targetNode : graph.keySet()){
        	if(isNodeContainsPoint(e, targetNode)){        		
        		
        		if(startNode.getClass() != targetNode.getClass()){
        			
        			List<Arch> edges = Graph.getInstance().getEdges(startNode);
        			
        			if(!isContainsNode(targetNode, edges)){
        				edges.add(new Arch(targetNode));
        				refreshCanvas();
        			}        			
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
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}

    private boolean isTransitionContainsPoint(MouseEvent e, Point position) {
        return position.x < e.getX() && e.getX() < position.x + UIStyle.SHAPE_SIZE
                && position.y < e.getY() && e.getY() < position.y + UIStyle.SHAPE_SIZE;
    }

    private boolean isPlaceContainsPoint(MouseEvent e, Point position){
        Point center = new Point(position.x + UIStyle.NODE_CENTER, position.y + UIStyle.NODE_CENTER);
        double distance = Math.sqrt(Math.pow(center.getX() - e.getX(), 2) + Math.pow(center.getY() - e.getY(), 2));

        return distance <= UIStyle.NODE_CENTER;
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
}
