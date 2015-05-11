package controller.actions.menu;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

import model.Graph;
import view.Canvas;
import view.panels.Output;
import view.panels.TabbedPanel;
import view.style.Theme;

public class Load extends AbstractAction {

	public Load() {
		super("Betöltés", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.loadIcon)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Betöltés");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Spider fájlformátum", "spider");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(Canvas.getInstance());
		if(returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}
		String filename = chooser.getSelectedFile().getName();
		if (!filename.substring(filename.length() - 7).equals(".spider")) {
			Output.getInstance().setError("Csak *.spider fájlok megnyitása lehetséges!");
			return;
		}

		TabbedPanel.getInstance().setSelectedIndex(0);

		try {
			FileInputStream fis = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
			ZipInputStream zis = new ZipInputStream(fis);
			zis.getNextEntry();
			Graph.deserialize(zis);
			zis.close();
			fis.close();
			Canvas.getInstance().repaint();
		} catch (Exception e1) {
			Output.getInstance().setError("Hiba a fájl betöltése közben!");
		}
	}

}
