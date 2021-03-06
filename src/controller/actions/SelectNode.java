package controller.actions;

import model.*;
import view.Canvas;
import view.style.Theme;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class SelectNode extends MouseAdapter {
	
    @Override
    public void mouseClicked(MouseEvent e) {
        Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

        for(Node node : graph.keySet()){
            node.setSelected(false);
        }

        for(Node node : graph.keySet()) {

            if(node instanceof Transition) {
                if (isTransitionContainsPoint(e, node.getPosition())) {
                    node.setSelected(true);
                    Canvas.getInstance().grabFocus();
                    break;
                }
            }

            if(node instanceof Place){
                if (isPlaceContainsPoint(e, node.getPosition())) {
                    node.setSelected(true);                    
                    Canvas.getInstance().grabFocus();
                    break;
                }
            }
        }

        Canvas.getInstance().repaint();
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
}
