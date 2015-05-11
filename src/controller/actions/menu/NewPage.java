package controller.actions.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.xml.bind.DatatypeConverter;

import model.Graph;
import view.Canvas;
import view.style.Theme;

public class NewPage extends AbstractAction {

	public NewPage() {
		super("Új lap", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.newIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Graph.reset();
		Canvas.getInstance().repaint();
	}

}
