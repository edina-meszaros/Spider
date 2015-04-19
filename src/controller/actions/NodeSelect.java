package controller.actions;

import model.*;
import view.Canvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class NodeSelect extends MouseAdapter {

    public static final int SHAPE_SIZE = 40;
    Canvas canvas = null;

    public NodeSelect(Canvas canvas){
        this.canvas = canvas;
    }

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
                    break;

                }
            }

            if(node instanceof Place){
                if (isPlaceContainsPoint(e, node.getPosition())) {
                    node.setSelected(true);
                    break;

                }
            }
        }

        this.canvas.repaint();
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
