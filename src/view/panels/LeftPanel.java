package view.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static LeftPanel instance = null;

	private LeftPanel() {
		super();
		//this.setSize(200, 300);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(TabbedPanel.getInstance());
		this.add(Output.getInstance());
	}

	public static LeftPanel getInstance() {
		if (instance == null) {
			instance = new LeftPanel();
		}
		return instance;
	}
}
