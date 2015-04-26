package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.Canvas;
import model.Graph;
import model.Node;
import model.Place;

public class ChangePlaceTokenPopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	private Point point = null;
	private Place place = null;
	
	public ChangePlaceTokenPopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Place selectedNode = (Place) Graph.getInstance().getSelectedNode();
		
		String token = JOptionPane.showInputDialog("Please insert new token number:");
		
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
