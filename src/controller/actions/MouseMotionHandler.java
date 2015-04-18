package controller.actions;

import model.*;
import view.Canvas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMotionHandler extends MouseAdapter {

    private final int SHAPE_SIZE = 40;
    private Canvas canvas = null;
	private Point mouseStartPoint = null;

    public MouseMotionHandler(Canvas canvas){
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseStartPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Node selectedNode = Graph.getInstance().getSelectedNode();
        if(selectedNode == null)
            return;

        if(selectedNode instanceof Place) {

            if (isPlaceContainsPoint(e, selectedNode.getPosition())) {
                int deltaX = mouseStartPoint.x - e.getPoint().x;
                int deltaY = mouseStartPoint.y - e.getPoint().y;

                selectedNode.setPosition(new Point(selectedNode.getPosition().x - deltaX, selectedNode.getPosition().y - deltaY));
                canvas.repaint();
            }
        }

        if(selectedNode instanceof Transition) {

            if (isTransitionContainsPoint(e, selectedNode.getPosition())) {
                int deltaX = mouseStartPoint.x - e.getPoint().x;
                int deltaY = mouseStartPoint.y - e.getPoint().y;

                selectedNode.setPosition(new Point(selectedNode.getPosition().x - deltaX, selectedNode.getPosition().y - deltaY));
                canvas.repaint();
            }
        }

        mouseStartPoint = e.getPoint();
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
}
