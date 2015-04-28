package controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.Canvas;
import model.Graph;
import model.Place;

public class SetPlaceBoundPopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public SetPlaceBoundPopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Place selectedNode = (Place) Graph.getInstance().getSelectedNode();
		
		String bound = JOptionPane.showInputDialog("Kérlek írd be a hely korlátját:");
		
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
