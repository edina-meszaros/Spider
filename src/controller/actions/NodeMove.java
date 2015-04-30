package controller.actions;

import model.*;
import view.Canvas;
import view.style.UIStyle;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodeMove extends MouseAdapter {
	
	private int offsetX;
	private int offsetY;
	private Node selectedNode;

	@Override
    public void mousePressed(MouseEvent e) {
		selectedNode = Graph.getInstance().getSelectedNode();
		if(selectedNode == null)
			return;
		
		
		if (selectedNode instanceof Place && !isPlaceContainsPoint(e, selectedNode.getPosition())
				|| selectedNode instanceof Transition && !isTransitionContainsPoint(e, selectedNode.getPosition())){
			selectedNode = null;
			return;
		}           
        
        offsetX = selectedNode.getPosition().x - e.getPoint().x;
        offsetY = selectedNode.getPosition().y - e.getPoint().y;        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(selectedNode == null)
            return;
        
        Point p = new Point(e.getPoint().x + offsetX, e.getPoint().y + offsetY);
        
        selectedNode.setPosition(p);
        Canvas.getInstance().repaint();
    }

    @Override
	public void mouseReleased(MouseEvent e) {
    	selectedNode = null;
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
}
