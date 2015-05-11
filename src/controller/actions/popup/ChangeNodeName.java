package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Graph;
import model.Node;
import model.Place;
import view.Canvas;
import view.panels.Output;

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
					Output.getInstance().setError("A csúcs neve max. 20 karakter lehet!");
					return;
				}

				if (name.length() == 0) {
					name = Graph.getInstance().getNextName(selectedNode instanceof Place ? "P" : "T");
				}

				selectedNode.setLabel(name);

			} catch (Exception ex){
				Output.getInstance().setError("Érvénytelen csúcs név!");
			}
		}

		Canvas.getInstance().repaint();
	}
}
