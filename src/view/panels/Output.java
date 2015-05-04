package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import view.style.Theme;

public class Output extends JComponent {

	private static final long serialVersionUID = 1L;

	private static Output instance = null;
	private JPanel title = new JPanel();
	private JPanel content = new JPanel();
	private JTextArea textEditor = new JTextArea();
	private Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

	private Output(){

		this.title.setPreferredSize(new Dimension(150, 25));
		this.title.setBackground(new Color(147, 157, 168));
		this.title.add(new JLabel("Kimenet / hibák"));

		this.content.setBackground(new Color(219,218,213));
		this.textEditor.setBackground(new Color(219,218,213));
		this.textEditor.setPreferredSize(new Dimension(200, 300));
		this.textEditor.setEditable(false);
		this.textEditor.setText("");
		this.content.add(textEditor);

		JScrollPane editorScrollPane = new JScrollPane(textEditor);
		editorScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setMinimumSize(new Dimension(200, 300));

		this.content.add(editorScrollPane, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.setBorder(this.border);
	}

	public static Output getInstance() {
		if (instance == null) {
			instance = new Output();
		}
		return instance;
	}

	public JTextArea getTextEditor() {
		return textEditor;
	}

	public void setError(String error){
		String history = this.textEditor.getText();
		this.textEditor.setWrapStyleWord(true);

		this.textEditor.setForeground(Theme.DARK_GREY);
		this.textEditor.setText(history + "\r\n");
//		this.textEditor.setForeground(Color.RED);
//		this.textEditor.append(error);
	}
}
