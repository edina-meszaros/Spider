package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.Canvas;
import model.Graph;
import model.Place;

public class SetPlaceBound extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public SetPlaceBound(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Place selectedNode = (Place) Graph.getInstance().getSelectedNode();		
		String bound = JOptionPane.showInputDialog("Hely korlátja:");
		
		if(bound != null){
			try{
				selectedNode.setBound(Integer.parseInt(bound));
			} catch (NumberFormatException ex){
				//output error
			}
		}		

		Canvas.getInstance().repaint();
	}
}
