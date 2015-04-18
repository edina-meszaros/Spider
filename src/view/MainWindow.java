package view;

import java.awt.BorderLayout;

import javax.swing.*;

import view.panels.LeftPanel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private MainMenu mainMenu;
	private ToolBar toolBar;
	private LeftPanel leftPanel;
	private Canvas canvas;
	
	private static MainWindow instance = null;

	private MainWindow() {
		super();

		setVisible(true);
		setResizable(false);
		setTitle("Spider");
		setLocation(100, 100);
		setSize(800, 600);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		mainMenu = new MainMenu();
		this.setJMenuBar(mainMenu);

		canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);

		toolBar = new ToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		leftPanel = new LeftPanel(canvas);
		add(leftPanel, BorderLayout.LINE_START);
	}
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}

	public MainMenu getMainMenu() { return this.mainMenu; }
	public ToolBar getToolBar() { return toolBar; }
	public LeftPanel getLeftPanel() { return leftPanel; }
	public Canvas getCanvas() { return canvas; }	
}
