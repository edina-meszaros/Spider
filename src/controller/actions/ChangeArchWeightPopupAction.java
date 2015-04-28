package controller.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import view.Canvas;
import model.Arch;
import model.Graph;
import model.Node;
import model.Place;

public class ChangeArchWeightPopupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public ChangeArchWeightPopupAction(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Arch selectedArch = Graph.getInstance().getSelectedArch();
		if(selectedArch == null)
			return;
		
		String weight = JOptionPane.showInputDialog("Kérlek írd be az új élsúlyt:");
		
		if(weight != null){
			try{
				selectedArch.setWeight(Integer.parseInt(weight));
			} catch (NumberFormatException ex){
				//output error
			}
		}		

		Canvas.getInstance().repaint();
	}
}
