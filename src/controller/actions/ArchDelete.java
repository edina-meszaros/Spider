package controller.actions;

import model.*;
import view.Canvas;
import view.style.Style;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchDelete extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {		
		
		if(e.getKeyCode() == KeyEvent.VK_DELETE){
			
			if(Graph.getInstance().getSelectedArch() == null)
				return;
				
			Graph.getInstance().getSelectedArch().setSelected(false);
			Graph.getInstance().getSelectedArch().setTarget(null);		
		}
		
		Canvas.getInstance().updateGraph(Graph.getInstance().getGraph());
        Canvas.getInstance().repaint();		
	}

    

}
