package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Graph;
import model.Node;
import view.Canvas;

public class ChangeNodeName extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ChangeNodeName() {
		super("Új név");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Node selectedNode = Graph.getInstance().getSelectedNode();

		String name = JOptionPane.showInputDialog("Csúcs új neve:");

		if(name != null){
			try{

				if(name.length() > 20){
					//output error
					return;
				}

				selectedNode.setLabel(name);
			} catch (Exception ex){
				//output error
			}
		}

		Canvas.getInstance().repaint();
	}
}
