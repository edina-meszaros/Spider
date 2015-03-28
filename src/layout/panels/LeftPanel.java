package layout.panels;

import javax.swing.*;
import layout.Canvas;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TabbedPanel tabbedPanel = null;
	private Output output = null;
	private Properties properties = null;

	public LeftPanel(Canvas canvas) {
		super();
		this.setSize(200, 300);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.tabbedPanel = new TabbedPanel(canvas);
		this.output = new Output();
		this.properties = new Properties();
		
		this.add(this.tabbedPanel);
		this.add(this.output);
		this.add(this.properties);
	}

	public TabbedPanel getTabbedPanel() {
		return tabbedPanel;
	}

	public Output getOutput() {
		return output;
	}

	public Properties getProperties() {
		return properties;
	}	
}
