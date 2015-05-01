package controller.actions.popup;

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
import view.style.Theme;

public class NewNodeListener extends MouseAdapter {
	
	private JPopupMenu popup;

	private static final int HIT_BOX_SIZE = 6;
	
	public NewNodeListener(){
		this.popup = Canvas.getInstance().getPopupNewNode();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Canvas.getInstance().setMousePosition(e.getPoint());
		checkPopup(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Canvas.getInstance().setMousePosition(e.getPoint());
		checkPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Canvas.getInstance().setMousePosition(e.getPoint());
		checkPopup(e);
	}

	private void checkPopup(MouseEvent e) {
		Node selectedNode = Graph.getInstance().getSelectedNode();
		Arch selectedArch = Graph.getInstance().getSelectedArch();
		
		if (e.isPopupTrigger() && !(selectedNode instanceof Place && isPlaceContainsPoint(e, selectedNode.getPosition())) 
				&&!(selectedNode instanceof Transition && isTransitionContainsPoint(e, selectedNode.getPosition()))
				&& !(selectedArch != null && isArchContainsPoint(e))) {
			this.popup.show(Canvas.getInstance(), e.getX(), e.getY());
		}		
	}
	
	private boolean isTransitionContainsPoint(MouseEvent e, Point position) {
        return position.x < e.getX() && e.getX() < position.x + Theme.SHAPE_SIZE
                && position.y < e.getY() && e.getY() < position.y + Theme.SHAPE_SIZE;
    }

    private boolean isPlaceContainsPoint(MouseEvent e, Point position){
        Point center = new Point(position.x + Theme.NODE_CENTER, position.y + Theme.NODE_CENTER);
        double distance = Math.sqrt(Math.pow(center.getX() - e.getX(), 2) + Math.pow(center.getY() - e.getY(), 2));

        return distance <= Theme.NODE_CENTER;
    }
    
	private boolean isArchContainsPoint(MouseEvent e) {
			
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		
		int boxX = e.getX() - HIT_BOX_SIZE / 2;
        int boxY = e.getY() - HIT_BOX_SIZE / 2;
        
        for(Node node : graph.keySet()) {        	
        	for(Arch a : Graph.getInstance().getEdges(node)){        		
        		
        		Line2D line = new Line2D.Double(node.getNodeCenterPosition().x, node.getNodeCenterPosition().y,
        				a.getTarget().getNodeCenterPosition().x, a.getTarget().getNodeCenterPosition().y);
        	     		
	        	if (line.intersects(boxX, boxY, HIT_BOX_SIZE, HIT_BOX_SIZE)) {	        		
	        		return true;	        		
	        	}
        	}        	
        }
        
        return false;
	}
}