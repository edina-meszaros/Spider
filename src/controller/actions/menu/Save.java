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

public class Save extends AbstractAction {

	private static final String icon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsTAA"
			+ "ALEwEAmpwYAAABSUlEQVR4nI3SvW4TURAF4G/XllchBIgCilLQ8QYUNEgp6XkNCl7Gfo68AAVQklCRCNHSIEAKGM"
			+ "KPQuyl8LnylbVIHGk0s+ee+blzFzpMMUf/nzZPTtckeGKFzxjjqvK9NZr4O/GzBpdY4DHe4QLX8Q230FZF2mjv4Q"
			+ "gjOfyYqke4G7+PFzjFm9gpXqbwJ/QNlun2IF2+Y2djgvoKi9gr3GgywRLvc516B4uNHRR0mbQtBea4n+4FQ8sr+h2"
			+ "8xs1xJf6AnwPdhnBRGtT3m6TDKPwEz2OTcKNoJiVpXBWof5QltvEwZ9v4Yv2k/VCBTVziRxUPYvMnUX3fDtcm9i9tj"
			+ "3OrpxH/LNxV7DxcZ7WDLlzf4g+u4TAFGuxht5p0N1OUZzzEVnLNrBfzNAn7OK74k3CiKfysjDzFV/zCowgPcIa3ieX"
			+ "sd7RTdH8ByG5sGaY2WU8AAAAASUVORK5CYII=";

	public Save() {
		super("Mentés", new ImageIcon(DatatypeConverter.parseBase64Binary(icon)));
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
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry zip = new ZipEntry("graph.xml");
			zos.putNextEntry(zip);
			Graph.serialize(zos);
			zos.close();
			fos.close();
			Canvas.getInstance().repaint();
		} catch (IOException ex) {
			// return
		}
	}

}
