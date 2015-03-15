package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public Canvas canvas;

	public LeftPanel(Canvas canvas) {
		super();
		this.setSize(200, 300);
		this.setBackground(new Color(100, 100, 100));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.canvas = canvas;
		
		JTabbedPane tabbedPane = new JTabbedPane();
		// ImageIcon icon = createImageIcon("images/middle.gif");
		tabbedPane.setSize(400, 300);

		JComponent panel1 = makeTextPanel("Panel #1");
		// tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
		tabbedPane.addTab("Analyser", panel1);
		// tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeEditorPanel(canvas);
		panel2.setPreferredSize(new Dimension(150, 45));
		tabbedPane.addTab("Editor", panel2);

		tabbedPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.add(tabbedPane);

		JPanel second = new JPanel();
		second.setSize(150, 100);
		second.setBackground(new Color(100, 200, 250));
		JLabel label2 = new JLabel("asdasd");
		second.add(label2);
		second.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		this.add(second);

		JPanel third = new JPanel();
		third.setSize(150, 100);
		third.setBackground(new Color(220, 120, 200));
		JLabel label3 = new JLabel("bsdbsdbsdbsd");
		third.add(label3);

		this.add(third);
	}

	private JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
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
			//popup.add(new JMenuItem(newNode));
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
			
			CircleComponent c2 = new CircleComponent();
	        
			if(canvas != null){
				canvas.add(c2);
		        canvas.revalidate();
		        canvas.repaint();
		        System.out.println("canvas");
	        }
		}
	}
	
	public class CircleComponent extends JComponent
	{
	  Rectangle rect;

	  public CircleComponent()
	  {
	    rect  = new Rectangle(200, 200, 30, 30);
	  }

	  public void paintComponent(Graphics g)
	  {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(Color.RED);
	    g2.draw(rect);
	  } 
	}

}
