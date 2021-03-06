package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import view.panels.LeftPanel;
import view.style.Theme;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainWindow instance = null;

	private MainWindow() {
		super();

		setVisible(true);
		setTitle("Spider");
		setIconImage(new ImageIcon(Theme.mainWindowIconPath).getImage());
		setSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 900));
		setLocationRelativeTo(null);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		this.setJMenuBar(MainMenu.getInstance());
		add(new JScrollPane(Canvas.getInstance()), BorderLayout.CENTER);
		add(LeftPanel.getInstance(), BorderLayout.LINE_START);
	}

	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}
}
