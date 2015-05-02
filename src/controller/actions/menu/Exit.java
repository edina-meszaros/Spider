package controller.actions.menu;

import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

public class Exit extends AbstractAction {

	private static final String icon = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsTAAALEwEAmpwYAAABOUlEQVR4nKXTz0pbURAG8N9NUiNBTRTElbu+QRc+gS+gfQDX7euYvobP0IKCgqu66ba7KthGC9VQc7vId/CQirQ4MMwwf775Zu650MchJmj/USfp6Tdx3pvLFXr4XdnWozSxm7HjBlM8YA9f8BMruMEInQqkk9rXOEJXkt+CeoTt2C18xAU+Ry/wKcCXaBvMMm0nU26xusCgXuEheoa1Jgxm+Jp1eino436BepF+mHYKwARvMl2Kb7C2wKqX+lWcY1hu8B2DasIr7GM5up9YkUF62hpglB0bHCR+Gm0TK/nRUwDDat9NHHt8OCeJlYMOC0B94eekfS45C9r6f6ywnp5ZQb/GUgW6hLfmxxrEX8xfF2ZT3GE3u22Yfyaxtb+Rml38wrTBGO9S9KOa0i0U/f2QRrEfeOHv/AcHJ3QurUDmegAAAABJRU5ErkJggg==";

	public Exit() {
		super("Kilépés", new ImageIcon(DatatypeConverter.parseBase64Binary(icon)));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
