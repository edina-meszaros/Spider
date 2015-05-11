package controller.actions.menu;

import view.style.Theme;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class Exit extends AbstractAction {

	public Exit() {
		super("Kilépés", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.exitIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
