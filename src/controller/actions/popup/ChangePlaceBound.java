package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Graph;
import model.Place;
import view.Canvas;
import view.panels.Output;

public class ChangePlaceBound extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ChangePlaceBound() {
		super("Új korlát");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Place selectedNode = (Place) Graph.getInstance().getSelectedNode();
		String input = JOptionPane.showInputDialog("Hely korlátja:");

		if(input != null){
			try{
				int bound = Integer.parseInt(input);

				if(bound < 0){

					Output.getInstance().setError("A korlát nem lehet negatív!");
					return;
				}

				int token = selectedNode.getTokens();

				if(token > bound && bound != 0){
					selectedNode.setTokens(bound);

					String error = "Korlátos csúcs tokenszáma nem lehet nagyobb, mint a korlát!\r\n"
        					+ "Az eredeti tokenszám: " + token + " volt!";

					Output.getInstance().setError(error);
				}

				selectedNode.setBound(bound);

			} catch (NumberFormatException ex){
				Output.getInstance().setError("Érvénytelen korlát!");
			}
		}

		Canvas.getInstance().repaint();
	}
}
