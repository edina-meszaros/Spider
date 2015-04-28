package controller.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import view.Canvas;
import model.Graph;
import model.Place;

public class ChangePlaceTokenPopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public ChangePlaceTokenPopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Place selectedNode = (Place) Graph.getInstance().getSelectedNode();
		
		String token = JOptionPane.showInputDialog("K�rlek �rd be a cs�cs tokensz�m�t:");
		
		if(token != null){
			try{
				selectedNode.setTokens(Integer.parseInt(token));
			} catch (NumberFormatException ex){
				//output error
			}
		}		

		Canvas.getInstance().repaint();
	}
}
