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

	@Override
	public void mousePressed(MouseEvent e) {
		canvas.setMousePosition(e.getPoint());
		checkPopup(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		canvas.setMousePosition(e.getPoint());
		checkPopup(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		canvas.setMousePosition(e.getPoint());
		checkPopup(e);
	}

	private void checkPopup(MouseEvent e) {		
		if (e.isPopupTrigger()) {
			this.popup.show(this.canvas, e.getX(), e.getY());
		}
	}
}
