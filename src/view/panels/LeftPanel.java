package view.panels;

import javax.swing.*;
import view.Canvas;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static LeftPanel instance = null;	

	private LeftPanel(Canvas canvas) {
		super();
		this.setSize(200, 300);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(TabbedPanel.getInstance());
		this.add(Output.getInstance());
		this.add(Properties.getInstance());
	}
	
	public static LeftPanel getInstance() {
		if (instance == null) {
			instance = new LeftPanel(Canvas.getInstance());
		}
		return instance;		
	}	
}
