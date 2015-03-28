package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.Controller;

public class Spider {

	public static void main(String[] args) {

		try {			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// handle exception
		}
		
		SwingUtilities.invokeLater(new Runnable() {
            
			@Override
            public void run() {
                new Controller();                
            }
        });
	}

}
