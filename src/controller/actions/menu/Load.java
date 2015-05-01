package controller.actions.menu;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Graph;
import view.Canvas;

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
			ZipInputStream zis = new ZipInputStream(fis);
			zis.getNextEntry();
			Graph.deserialize(zis);
			zis.close();
			fis.close();
			Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
			Canvas.getInstance().repaint();
		} catch (IOException e1) {
			// return
		}
	}

}
