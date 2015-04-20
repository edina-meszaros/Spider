package controller.actions;

import model.*;
import view.Canvas;
import view.style.Style;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class NodeSelect extends MouseAdapter {

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
        return position.x < e.getX() && e.getX() < position.x + Style.SHAPE_SIZE
                && position.y < e.getY() && e.getY() < position.y + Style.SHAPE_SIZE;
    }

    private boolean isPlaceContainsPoint(MouseEvent e, Point position){
        Point center = new Point(position.x + Style.CENTER, position.y + Style.CENTER);
        double distance = Math.sqrt(Math.pow(center.getX() - e.getX(), 2) + Math.pow(center.getY() - e.getY(), 2));

        return distance <= Style.CENTER;
    }
}
