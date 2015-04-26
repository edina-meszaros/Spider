package controller.actions;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;

import javax.swing.JPopupMenu;

import model.Arch;
import model.Graph;
import model.Node;
import model.Place;
import model.Transition;
import view.Canvas;
import view.style.Style;

public class NewNodePopupListener extends MouseAdapter {
	
	private JPopupMenu popup;
	private Canvas canvas;
	
	private static final int HIT_BOX_SIZE = 6;
	
	public NewNodePopupListener(Canvas canvas){
		this.canvas = canvas;
		this.popup = Canvas.getInstance().getPopupNewNode();
	}	

	@Override
	public void mouseReleased(MouseEvent e) {
		canvas.setMousePosition(e.getPoint());
		checkPopup(e);
	}

	private void checkPopup(MouseEvent e) {
		Node selectedNode = Graph.getInstance().getSelectedNode();
		Arch selectedArch = Graph.getInstance().getSelectedArch();
		
		if (e.isPopupTrigger() && !(selectedNode instanceof Place && isPlaceContainsPoint(e, selectedNode.getPosition())) 
				&&!(selectedNode instanceof Transition && isTransitionContainsPoint(e, selectedNode.getPosition()))
				&& !(selectedArch != null && isArchContainsPoint(e))) {
			this.popup.show(this.canvas, e.getX(), e.getY());
		}
		
	}
	
	private boolean isTransitionContainsPoint(MouseEvent e, Point position) {
        return position.x < e.getX() && e.getX() < position.x + Style.SHAPE_SIZE
                && position.y < e.getY() && e.getY() < position.y + Style.SHAPE_SIZE;
    }

    private boolean isPlaceContainsPoint(MouseEvent e, Point position){
        Point center = new Point(position.x + Style.CENTER, position.y + Style.CENTER);
        double distance = Math.sqrt(Math.pow(center.getX() - e.getX(), 2) + Math.pow(center.getY() - e.getY(), 2));

        return distance <= Style.CENTER;
    }
    
	private boolean isArchContainsPoint(MouseEvent e) {
			
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		
		int boxX = e.getX() - HIT_BOX_SIZE / 2;
        int boxY = e.getY() - HIT_BOX_SIZE / 2;
        
        for(Node node : graph.keySet()) {        	
        	for(Arch a : Graph.getInstance().getEdges(node)){        		
        		
        		Line2D line = new Line2D.Double(node.getPosition().getX()+20, node.getPosition().getY()+20,
        				a.getTarget().getPosition().getX()+20, a.getTarget().getPosition().getY()+20);
        	     		
	        	if (line.intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE)) {	        		
	        		return true;	        		
	        	}
        	}        	
        }
        
        return false;
	}
}
