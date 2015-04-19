package controller.actions;

import model.*;
import view.Canvas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class CreateArrowAction extends MouseAdapter {

    private final int SHAPE_SIZE = 40;
    private Canvas canvas = null;
	private Point mouseStartPoint = null;
	private Node startNode = null;
	private Point mouseEndPoint = null;
	private boolean drawingEnabled = false;

    public CreateArrowAction(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseStartPoint = e.getPoint();
        
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
    	Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
    	boolean created = false;
    	
        for(Node targetNode : graph.keySet()){
        	if(!targetNode.isSelected() && isNodeContainsPoint(e, targetNode)){        		
        		
        		if((startNode instanceof Place && targetNode instanceof Transition) || 
        				(startNode instanceof Transition && targetNode instanceof Place)){
        			List<Arch> edges = Graph.getInstance().getEdges(startNode);
        			
        			for(Arch arch : edges){
        				if(arch.getTarget() != null && arch.getTarget().equals(targetNode)){
        					created = true;
        					continue;
        				}else if(arch.getTarget() == null){
        					arch.setTarget(targetNode);
        					created = true;
        					refreshCanvas();
        				}
        			}
        			
        			if(!created){
        				Graph.getInstance().getEdges(startNode).add(new Arch(targetNode));
	        			refreshCanvas();
        			}
        		}        		
        	}
        	
        	Canvas.getInstance().setLineStart(null);
			Canvas.getInstance().setLineEnd(null);
			Canvas.getInstance().repaint();
        }   
    }

	private void refreshCanvas() {
		Canvas.getInstance().setLineStart(null);
		Canvas.getInstance().setLineEnd(null);
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
		Canvas.getInstance().repaint();
	}

    private boolean isTransitionContainsPoint(MouseEvent e, Point position) {
        return position.x < e.getX() && e.getX() < position.x + SHAPE_SIZE
                && position.y < e.getY() && e.getY() < position.y + SHAPE_SIZE;
    }

    private boolean isPlaceContainsPoint(MouseEvent e, Point position){
        Point center = new Point(position.x + SHAPE_SIZE/2, position.y + SHAPE_SIZE/2);
        double distance = Math.sqrt(Math.pow(center.getX() - e.getX(), 2) + Math.pow(center.getY() - e.getY(), 2));

        return distance <= SHAPE_SIZE/2;
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
