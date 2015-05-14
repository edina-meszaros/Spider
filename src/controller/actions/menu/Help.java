package controller.actions.menu;

import view.style.Theme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class Help extends AbstractAction {

	public Help() {
		super("Súgó", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.helpIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			File myFile = new File("docs/Documentation.pdf");
			Desktop.getDesktop().open(myFile);
		} catch (IOException ex) {
			// no application registered for PDFs
		}
	}

}
