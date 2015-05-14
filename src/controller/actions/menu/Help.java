package controller.actions.menu;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.xml.bind.DatatypeConverter;

import view.panels.Output;
import view.style.Theme;

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
			Output.getInstance().setError("A felhasználói dokumentáció megnyitása sikertelen!");
		}
	}

}
