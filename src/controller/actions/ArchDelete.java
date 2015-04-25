package controller.actions;

import model.*;
import view.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class ArchDelete extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() != KeyEvent.VK_DELETE)
			return;
		
		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();
		
		for(List<Arch> list : graph.values()){
			if (list.remove(Graph.getInstance().getSelectedArch())) {
				break;
			}
		}
		
		Canvas.getInstance().updateGraph(graph);
        Canvas.getInstance().repaint();		
	}

    

}
