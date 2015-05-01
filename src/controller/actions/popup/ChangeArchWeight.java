package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Arch;
import model.Graph;
import view.Canvas;

public class ChangeArchWeight extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public ChangeArchWeight() {
		super("Új súly");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Arch selectedArch = Graph.getInstance().getSelectedArch();
		if(selectedArch == null)
			return;

		String input = JOptionPane.showInputDialog("Új súly:");

		if(input != null){
			try{

				int weight = Integer.parseInt(input);

				if(weight < 1){
					//output error
					return;
				}

				if(weight >= 10000){
					//output error
					return;
				}

				selectedArch.setWeight(Integer.parseInt(input));
			} catch (NumberFormatException ex){
				//output error
			}
		}

		Canvas.getInstance().repaint();
	}
}
