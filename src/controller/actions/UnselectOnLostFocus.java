package controller.actions;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Map;

import view.Canvas;
import model.Arch;
import model.Graph;
import model.Node;

public class UnselectOnLostFocus implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		
		for(Node node : graph.keySet()){
			node.setSelected(false);
			
			for(Arch arch : graph.get(node)){
				arch.setSelected(false);
			}
		}
		
		Canvas.getInstance().repaint();
	}

}
