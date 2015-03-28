package controller.actions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import layout.Canvas;

public class CreateTransitionAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	
	public CreateTransitionAction(String name, Canvas canvas) {
		super(name);
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(canvas != null){
			Graphics2D g2 = (Graphics2D) canvas.getGraphics();
		    g2.setColor(Color.blue);
		    g2.fillRect(200, 200, 30, 30);
        }
	}
}
