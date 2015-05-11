package controller.actions.menu;

import view.style.Theme;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class Help extends AbstractAction {

	public Help() {
		super("Súgó", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.helpIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

}
