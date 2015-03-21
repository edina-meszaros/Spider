package layout.panels;

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
import java.awt.geom.Ellipse2D;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import layout.Canvas;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public int ctr = 0;

	public LeftPanel(Canvas canvas) {
		super();
		this.setSize(200, 300);
		this.setBackground(new Color(100, 100, 100));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(new TabbedPanel(canvas));
		this.add(new Output());
		this.add(new Properties());

	}
}
