package layout.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Output extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel title = new JPanel();
	private JPanel content = new JPanel();
	private Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	public Output(){
		
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
}
