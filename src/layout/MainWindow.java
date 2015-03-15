package layout;

import java.awt.BorderLayout;

import javax.swing.*;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private MainMenu menuBar;
	private ToolBar toolBar;
	private LeftPanel leftPanel;
	private Canvas canvas;

	public MainWindow() {
		super();
		setTitle("Spider");
		setLocation(100, 100);
		setSize(800, 600);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());		
		
		menuBar = new MainMenu();
		this.setJMenuBar(menuBar);
		
		canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		
		toolBar = new ToolBar();
		add(toolBar, BorderLayout.PAGE_START);
		
		leftPanel = new LeftPanel(canvas);
		add(leftPanel, BorderLayout.LINE_START);
		
	}
}
