package layout.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Output extends JComponent {
	
	private Dimension size = new Dimension(150, 100);
	private Color background = new Color(200, 200, 250);
	private JPanel title = new JPanel();
	private JPanel content = new JPanel();
	private Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	public Output(){
		
		this.title.setPreferredSize(new Dimension(150, 25));
		this.title.setBackground(new Color(150, 150, 150));
		//this.title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.title.add(new JLabel("Output / Errors"));

		this.content.setBackground(Color.LIGHT_GRAY);
		
		
		this.setLayout(new BorderLayout());
		//this.setSize(this.size);
		//this.setBackground(this.background);
		this.add(title, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.setBorder(this.border);
	}	
}
