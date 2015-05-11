package controller.actions.menu;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

import model.Graph;
import view.Canvas;
import view.panels.Output;
import view.style.Theme;

public class Save extends AbstractAction {

	public Save() {
		super("Mentés", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.saveIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Mentés");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Spider fájlformátum", "spider");
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
			ZipOutputStream zos = new ZipOutputStream(fos);

			ZipEntry zip = new ZipEntry("graph.xml");
			zos.putNextEntry(zip);
			Graph.serialize(zos);
			zos.close();
			fos.close();

			Canvas.getInstance().repaint();

		} catch (IOException ex) {
			Output.getInstance().setError("Hiba a fájl mentése közben!");
		}
	}

}
