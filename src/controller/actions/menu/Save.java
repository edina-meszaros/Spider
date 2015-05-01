package controller.actions.menu;

import model.Graph;
import view.Canvas;

import java.awt.event.ActionEvent;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Save extends AbstractAction {

	public Save() {
		super("Mentés");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Spider fájlformátum", "spider");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(Canvas.getInstance());
		if(returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}

		try {
			String filename = chooser.getSelectedFile().getAbsolutePath();
			if (!filename.substring(filename.length() - 7).equals(".spider")) {
				filename += ".spider";
			}
			FileOutputStream fos = new FileOutputStream(filename);
			Graph.serialize(new BufferedOutputStream(fos));
			Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
			Canvas.getInstance().repaint();
		} catch (FileNotFoundException e1) {
			// return
		}
	}

}
