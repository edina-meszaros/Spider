package controller.actions;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionHandler extends MouseMotionAdapter {
	
	private Rectangle mouseRect = new Rectangle();
	private Point mousePt = new Point();

	Point delta = new Point();

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getX());
        
//            mouseRect.setBounds(
//                Math.min(mousePt.x, e.getX()),
//                Math.min(mousePt.y, e.getY()),
//                Math.abs(mousePt.x - e.getX()),
//                Math.abs(mousePt.y - e.getY()));
//            
//        
//            delta.setLocation(
//                e.getX() - mousePt.x,
//                e.getY() - mousePt.y);
//            Node.updatePosition(nodes, delta);
//            mousePt = e.getPoint();
//        
//        e.getComponent().repaint();
    }
}
