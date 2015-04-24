package controller.actions;

import model.*;
import view.Canvas;
import view.style.Style;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchSelect extends MouseAdapter {

    private static final int HIT_BOX_SIZE = 2;

    @Override
    public void mouseClicked(MouseEvent e) {
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
        
        for(Node node : graph.keySet()) {        	
        	for(Arch a : graph.get(node)){        		
        		a.setSelected(false);
        		
        		System.out.println(a.isSelected() + ", " + a);
        	}
        }       
        
        selectArch(e);        
        
        Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
        Canvas.getInstance().repaint();
        
        System.out.println("repaint ");
        
    }

	private void selectArch(MouseEvent e) {
		
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		
		int boxX = e.getX() - HIT_BOX_SIZE / 2;
        int boxY = e.getY() - HIT_BOX_SIZE / 2;
        
        int width = HIT_BOX_SIZE;
        int height = HIT_BOX_SIZE;
		
		for(Node node : graph.keySet()) {        	
        	for(Arch a : Graph.getInstance().getEdges(node)){
        		
        		if(a.getTarget() == null){
        			continue;
        		}
        		
        		Line2D line = new Line2D.Double(node.getPosition().getX()+20, node.getPosition().getY()+20,
        				a.getTarget().getPosition().getX()+20, a.getTarget().getPosition().getY()+20);
        	     		
        		System.out.println(boxX +  " " + boxY + " " + width  + " " + height);
        		System.out.println(line.getX1() +  " " + line.getX2() + " " + line.getY1()  + " " + line.getY2());
        		
	        	if (line.intersects(boxX, boxY, width, height)) {	        		
	        		a.setSelected(true);
	        		return;
	        		
	        	}
        	}        	
        }
	}

}
