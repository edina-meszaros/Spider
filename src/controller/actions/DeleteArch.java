package controller.actions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import model.Arch;
import model.Graph;
import model.Node;
import view.Canvas;
import view.panels.TabbedPanel;

public class DeleteArch extends KeyAdapter {

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

        Canvas.getInstance().repaint();
		TabbedPanel.getInstance().refreshNetAttributes();
	}
}