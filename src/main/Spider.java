package main;

import javax.swing.UIManager;

import layout.MainWindow;

public class Spider {

	public static void main(String[] args) {

		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// handle exception
		}
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
	}

}
