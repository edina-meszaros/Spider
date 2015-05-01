package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Graph;
import model.Place;
import view.Canvas;

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

				if(bound < 1){
					//output error
					return;
				}

				if(selectedNode.getTokens() > bound){
					selectedNode.setTokens(bound);
					//output information
				}

				selectedNode.setBound(bound);

			} catch (NumberFormatException ex){
				//output error
			}
		}

		Canvas.getInstance().repaint();
	}
}
