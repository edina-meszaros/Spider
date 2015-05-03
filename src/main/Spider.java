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

		        } else if("Metal".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;

		        } else if("Metal".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;

		        }

		    }
		} catch (Exception e) {
			// Ex
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
            public void run() {
                new Controller();
            }
        });
	}

}
