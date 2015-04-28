package controller.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.Canvas;
import model.Graph;
import model.Node;

public class ChangeNodeNamePopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public ChangeNodeNamePopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Node selectedNode = Graph.getInstance().getSelectedNode();
		
		String name = JOptionPane.showInputDialog("K�rlek �rd be az �j cs�cs nev�t:");
		
		if(name != null){
			try{
				selectedNode.setLabel(name);
			} catch (Exception ex){
				//output error
			}
		}		

		Canvas.getInstance().repaint();
	}
}
