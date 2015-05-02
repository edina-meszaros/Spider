package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private static MainMenu instance = null;
	private JMenu fileMenu;
	private JMenu exampleSubMenu;
	private JMenu helpMenu;
	private JMenuItem newPage;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem exit;
	private JMenuItem help;
	private JMenuItem about;
	private JMenuItem ex1;

	private MainMenu() {
		super();

		//A menu example:

		fileMenu = new JMenu("Fájl");
		//menu.setMnemonic(KeyEvent.VK_A);
		fileMenu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");

		this.add(fileMenu);

		newPage = new JMenuItem();
		fileMenu.add(newPage);

		fileMenu.addSeparator();

		//a group of JMenuItems
		save = new JMenuItem();
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		fileMenu.add(save);

		load = new JMenuItem();
		fileMenu.add(load);

		exampleSubMenu = new JMenu("Példák");
		exampleSubMenu.setMnemonic(KeyEvent.VK_S);

		ex1 = new JMenuItem("Példa 1");
		ex1.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		exampleSubMenu.add(ex1);

		fileMenu.add(exampleSubMenu);

		fileMenu.addSeparator();
		exit = new JMenuItem();
		fileMenu.add(exit);

		this.add(fileMenu);

		helpMenu = new JMenu("Súgó");

		help = new JMenuItem();
		helpMenu.add(help);

		about = new JMenuItem();
		helpMenu.add(about);

		this.add(helpMenu);


	}

	public static MainMenu getInstance() {
		if (instance == null) {
			instance = new MainMenu();
		}
		return instance;
	}

	public JMenuItem getNewPage() { return newPage; }
	public JMenuItem getSave() { return save; }
	public JMenuItem getLoad() { return load; }
	public JMenuItem getExit() { return exit; }
	public JMenuItem getHelp() { return help; }
	public JMenuItem getAbout() { return about; }
}
