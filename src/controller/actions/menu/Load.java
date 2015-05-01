package controller.actions.menu;

import model.Graph;
import view.Canvas;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Load extends AbstractAction {

	public Load() {
		super("Betöltés");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Spider fájlformátum", "spider");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(Canvas.getInstance());
		if(returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
			Graph.deserialize(new BufferedInputStream(fis));
			Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
			Canvas.getInstance().repaint();
		} catch (FileNotFoundException e1) {
			// return
		}
	}

}
