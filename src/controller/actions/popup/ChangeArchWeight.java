package controller.actions.popup;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import model.Arch;
import model.Graph;
import view.Canvas;
import view.panels.Output;
import view.panels.TabbedPanel;

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
					Output.getInstance().setError("Az élsúly nem lehet kisebb, mint 1!");
					return;
				}

				if(weight >= 10000){
					Output.getInstance().setError("Az élsúly nem lehet nagyobb, mint 9999!");
					return;
				}

				selectedArch.setWeight(Integer.parseInt(input));
				TabbedPanel.getInstance().refreshNetAttributes();

			} catch (NumberFormatException ex){
				Output.getInstance().setError("Érvénytelen élsúly!");
			}
		}

		Canvas.getInstance().repaint();
	}
}
