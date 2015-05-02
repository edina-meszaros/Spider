package controller.actions.menu;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

import model.Graph;
import view.Canvas;

public class Load extends AbstractAction {

	private static final String icon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsTAAALEwEAmpwYAAABUUlEQVR4nI3TS05UURAG4O9eCDfysDskihPCxB04cMge3IbbgXWwBh2BiTiQiUOdEJXYtk1MRJvjoP9DHzsMrKRSdav+et46MOAIU5T/5Glihi7KSwv6inX8aWSxpC7yUeRxhxvM8QIfcI1t/MAYfZOkD/YpTrAmzs/JeoL9yD28wgXehy/wOom/oHS4TbXnqTLDzkoH7Qjz8Bs87NLBLT5lnHYH85UdVBrSaV8TTPEs1VtqZy/poKTDtxitN8BL/Lyn2n10XZO3822kQk36BKc4iy6+Lth/2pxglO8Oj3FueTjvYqt3MEpMaROM4xya4JtwiW0IZlwTrB5JF56E+3D9rv46+l0H31bnwoHFX5lFb2kjMaXHb2ziMLPtBnTV7OAqtt1gDvEgsY4tj2mCjxZvYRu/wjvYim8SbEns3XP+HuMsS1rL4s6jj+MrwR5h+AvymHgTxhSarwAAAABJRU5ErkJggg==";

	public Load() {
		super("Betöltés", new ImageIcon(DatatypeConverter.parseBase64Binary(icon)));
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
			Canvas.getInstance().repaint();
		} catch (IOException e1) {
			// return
		}
	}

}
