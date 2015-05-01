package view;

import java.awt.BorderLayout;
import javax.swing.*;
import view.panels.LeftPanel;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;	
	private static MainWindow instance = null;

	private MainWindow() {
		super();

		setVisible(true);
		setResizable(false);
		setTitle("Spider");
		setSize(1280, 750);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		this.setJMenuBar(MainMenu.getInstance());		
		add(Canvas.getInstance(), BorderLayout.CENTER);		
		add(ToolBar.getInstance(), BorderLayout.PAGE_START);		
		add(LeftPanel.getInstance(), BorderLayout.LINE_START);
	}
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
}
