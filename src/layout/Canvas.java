package layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = 1L;
	private Point point = null;

	public Canvas() {
		super();
		this.setBackground(new Color(40, 60, 80));		
	}

	@Override
	public void paint(Graphics g) {
		
//		g.setColor(Color.BLACK);
//		g.drawOval(200, 200, 30, 30);
		

	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}
