package controller.actions;

import model.*;
import view.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;

public class ArchSelect extends MouseAdapter {

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
        
        Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
        Canvas.getInstance().repaint();
        
    }

	private void selectArch(MouseEvent e) {
		
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		
		int boxX = e.getX() - HIT_BOX_SIZE / 2;
        int boxY = e.getY() - HIT_BOX_SIZE / 2;
        
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
}
