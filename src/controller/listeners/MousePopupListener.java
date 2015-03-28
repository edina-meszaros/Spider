package controller.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import view.Canvas;

public class MousePopupListener extends MouseAdapter {
	
	private JPopupMenu popup;
	private Canvas canvas;
	
	public MousePopupListener(JPopupMenu popup, Canvas canvas){
		this.popup = popup;
		this.canvas = canvas;
	}	
	
	public void mousePressed(MouseEvent e) {
		checkPopup(e);
	}

	public void mouseClicked(MouseEvent e) {
		checkPopup(e);
	}

	public void mouseReleased(MouseEvent e) {
		checkPopup(e);
	}

	private void checkPopup(MouseEvent e) {
		
		if (e.isPopupTrigger()) {
			this.popup.show(this.canvas, e.getX(), e.getY());
		}
	}
}
