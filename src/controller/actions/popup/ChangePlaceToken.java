package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Graph;
import model.Place;
import view.Canvas;
import view.panels.Output;

public class ChangePlaceToken extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ChangePlaceToken() {
		super("Új token");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Place selectedNode = (Place) Graph.getInstance().getSelectedNode();

		String token = JOptionPane.showInputDialog("Csúcs tokenszáma:");

		if(token != null){
			try{
				selectedNode.setTokens(Integer.parseInt(token));
			} catch (NumberFormatException ex){

				Output.getInstance().setError("Érvénytelen tokenszám!");
			}
		}

		Canvas.getInstance().repaint();
	}
}
