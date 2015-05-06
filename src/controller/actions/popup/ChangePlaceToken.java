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

		String input = JOptionPane.showInputDialog("Csúcs tokenszáma:");

		if(input != null){
			try{

				int token = Integer.parseInt(input);
				if(selectedNode.getBound() > 0 && token > selectedNode.getBound()){
					token = selectedNode.getBound();

					String error = "Korlátos csúcs tokenszáma nem lehet nagyobb, mint a korlát!";
					Output.getInstance().setError(error);
				}

				selectedNode.setTokens(token);
			} catch (NumberFormatException ex){

				Output.getInstance().setError("Érvénytelen tokenszám!");
			}
		}

		Canvas.getInstance().repaint();
	}
}
