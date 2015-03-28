package controller.actions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import layout.Canvas;

public class CreatePlaceAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Canvas canvas = null;
	
	public CreatePlaceAction(String name, Canvas canvas) {
		super(name);
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.canvas != null){
			Graphics2D g2 = (Graphics2D) this.canvas.getGraphics();
		    g2.setColor(Color.PINK);			    
		    g2.fillOval(100, 100, 30, 30);
        }
	}
}
