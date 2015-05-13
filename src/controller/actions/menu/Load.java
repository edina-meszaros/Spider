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

	private String file;

	public Load() {
		super("Betöltés", new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.loadIcon)));
	}

	public Load(String name, String file) {
		super(name);
		this.file = file;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String path;
		if (this.file == null)
			path = showOpenDialog();
		else
			path = this.file;

		if (path == null)
			return;

		TabbedPanel.getInstance().setSelectedIndex(0);

		try {
			FileInputStream fis = new FileInputStream(path);
			ZipInputStream zis = new ZipInputStream(fis);
			zis.getNextEntry();
			Graph.deserialize(zis);
			zis.close();
			fis.close();
			Canvas.getInstance().repaint();
			TabbedPanel.getInstance().refreshNetAttributes();
		} catch (Exception e1) {
			Output.getInstance().setError("Hiba a fájl betöltése közben!");
		}
	}

	private String showOpenDialog() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Betöltés");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Spider fájlformátum", "spider");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(Canvas.getInstance());
		if(returnVal != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		String filename = chooser.getSelectedFile().getName();
		if (!filename.substring(filename.length() - 7).equals(".spider")) {
			Output.getInstance().setError("Csak *.spider fájlok megnyitása lehetséges!");
			return null;
		}
		return chooser.getSelectedFile().getAbsolutePath();
	}

}
