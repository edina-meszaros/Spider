package controller.actions.popup;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import view.Canvas;
import model.Arch;
import model.Graph;

public class ChangeArchWeight extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	public ChangeArchWeight(String name) {
		super(name);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Arch selectedArch = Graph.getInstance().getSelectedArch();
		if(selectedArch == null)
			return;
		
		String weight = JOptionPane.showInputDialog("Új súly:");
		
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
