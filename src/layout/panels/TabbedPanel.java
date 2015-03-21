package layout.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import layout.Canvas;

public class TabbedPanel extends JPanel {

	private Canvas canvas;
	
	public TabbedPanel(Canvas canvas){
		
		this.canvas = canvas;
		
		JTabbedPane tabbedPane = new JTabbedPane();
		// ImageIcon icon = createImageIcon("images/middle.gif");
		//tabbedPane.setSize(600, 300);

		JComponent panel1 = makeTextPanel("Panel #1");
		// tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
		tabbedPane.addTab("Analyser", panel1);
		// tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeEditorPanel(this.canvas);
		panel2.setPreferredSize(new Dimension(150, 45));
		tabbedPane.addTab("Editor", panel2);
		
		tabbedPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	private JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	
	private JComponent makeEditorPanel(Canvas canvas) {
		EditorPanel editor = new EditorPanel(canvas);
		return editor;
	}
	
	class EditorPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private Action newNode;		
		private JButton defaultButton;		
		private JPopupMenu popup = new JPopupMenu();
		private Canvas canvas;

		public EditorPanel(Canvas canvas) {
			this.canvas = canvas;			
			this.setLayout(new FlowLayout(FlowLayout.LEFT));			
			this.newNode = new NewNodeAction("New", this.canvas);
			this.defaultButton = new JButton(newNode);
			this.add(defaultButton);
			popup.add(new JMenuItem(newNode));
		}
		
	}

	private class NewNodeAction extends AbstractAction {

		private static final long serialVersionUID = 1L;		
		private Canvas canvas;
		
		public NewNodeAction(String name, Canvas canvas) {
			super(name);
			this.canvas = canvas;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//Point p = MouseInfo.getPointerInfo().getLocation();
			
			//Ellipse2D circle = new Ellipse2D.Double(200, 200, 30, 30);			
	        
			if(canvas != null){
				Graphics2D g2 = (Graphics2D) canvas.getGraphics();
			    g2.setColor(Color.PINK);
			    g2.fillOval(100, 100, 30, 30);
	        }
		}
	}
	
}
