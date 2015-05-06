package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.Controller;

public class Spider {

	public static void main(String[] args) {

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

		    	if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }

		    }
		} catch (Exception e) {
			System.err.println("Couldn't get specified look and feel: Nimbus.");
			System.err.println("Using the default look and feel.");
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
            public void run() {
                Controller.getInstance();
            }
        });
	}

}
