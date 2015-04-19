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

public class Output extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static Output instance = null;
	private JPanel title = new JPanel();
	private JPanel content = new JPanel();
	private Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	private Output(){
		
		this.title.setPreferredSize(new Dimension(150, 25));
		this.title.setBackground(new Color(147, 157, 168));
		//this.title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.title.add(new JLabel("Output / Errors"));

		this.content.setBackground(new Color(219,218,213));		
		
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.setBorder(this.border);
	}
	
	public static Output getInstance() {
		if (instance == null) {
			instance = new Output();
		}
		return instance;		
	}
}
