package controller.actions;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

import model.Arch;
import model.Graph;
import model.Node;
import view.Canvas;

public class DeleteNode extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() != KeyEvent.VK_DELETE)
			return;

		Map<Node, List<Arch>> graph = Graph.getInstance().getGraph();

		Node selectedNode = Graph.getInstance().getSelectedNode();
		for(Node node : graph.keySet()){
			graph.get(node).remove(getRemovableArch(graph.get(node), selectedNode));
		}

		graph.remove(selectedNode);

        Canvas.getInstance().repaint();
	}

	private Arch getRemovableArch(List<Arch> arches, Node selectedNode) {
		for(Arch arch : arches){
			if(arch.getTarget().equals(selectedNode)){
				return arch;
			}
		}

		return null;
	}
}