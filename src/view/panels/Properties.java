package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Properties extends JComponent {

	private static final long serialVersionUID = 1L;

	private static Properties instance = null;
	private JPanel title = new JPanel();
	private JPanel content = new JPanel();
	private Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	private Properties(){

		this.title.setPreferredSize(new Dimension(150, 25));
		this.title.setBackground(new Color(147, 157, 168));
		this.title.add(new JLabel("Beállítások"));

		this.content.setBackground(new Color(219,218,213));

		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.setBorder(this.border);
	}

	public static Properties getInstance() {
		if (instance == null) {
			instance = new Properties();
		}
		return instance;
	}
}
