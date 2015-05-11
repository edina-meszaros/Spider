package controller.actions.menu;

import view.AboutWindow;
import view.style.Theme;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class About extends AbstractAction {

	public About() {
		super("Névjegy", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.aboutIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		AboutWindow about = new AboutWindow();
		about.setVisible(true);
	}

}
