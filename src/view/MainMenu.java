package view;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.bind.DatatypeConverter;

import view.style.Theme;

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
	private JMenuItem ex2;
	private JMenuItem ex3;
	private JMenuItem ex4;
	private JMenuItem ex5;

	private MainMenu() {
		super();

		fileMenu = new JMenu("Fájl");
		this.add(fileMenu);

		newPage = new JMenuItem();
		fileMenu.add(newPage);

		fileMenu.addSeparator();

		save = new JMenuItem();
		fileMenu.add(save);

		load = new JMenuItem();
		fileMenu.add(load);

		exampleSubMenu = new JMenu("Példák");
		exampleSubMenu.setIcon(new ImageIcon(DatatypeConverter.parseBase64Binary(Theme.iconSamples)));
		exampleSubMenu.setMnemonic(KeyEvent.VK_S);

		ex1 = new JMenuItem();
		exampleSubMenu.add(ex1);

		ex2 = new JMenuItem();
		exampleSubMenu.add(ex2);

		ex3 = new JMenuItem();
		exampleSubMenu.add(ex3);

		ex4 = new JMenuItem();
		exampleSubMenu.add(ex4);

		ex5 = new JMenuItem();
		exampleSubMenu.add(ex5);

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
	public JMenuItem getEx1() { return ex1; }
	public JMenuItem getEx2() { return ex2; }
	public JMenuItem getEx3() { return ex3; }
	public JMenuItem getEx4() { return ex4; }
	public JMenuItem getEx5() { return ex5; }
}
