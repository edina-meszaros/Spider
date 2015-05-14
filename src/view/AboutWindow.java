package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import view.style.Theme;

public class AboutWindow extends JFrame {

    public AboutWindow() {
        super();

        this.setSize(400, 200);

        JLabel icon = new JLabel(new ImageIcon(Theme.aboutImagePath));
        icon.setSize(317, 332);
        this.add(icon, BorderLayout.LINE_START);

        JLabel data = new JLabel();
        data.setText("<html><p style=\"margin:5px;font-size:12px;\"><b>Spider Petri háló elemző és szerkesztő</b></p>" +
                "<p style=\"margin:5px;font-size:10px;\">Készítette: <i>Mészáros Edina</i></p>");
        data.setVerticalAlignment(JLabel.TOP);
        this.add(data, BorderLayout.CENTER);

        JPanel buttonLine = new JPanel();
        buttonLine.setLayout(new BoxLayout(buttonLine, BoxLayout.X_AXIS));

        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(300, 30));
        buttonLine.add(padding);
        JButton close = new JButton();
        close.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.windowForComponent((Component) e.getSource());
                window.dispose();
            }
        });
        close.setText("Bezárás");
        buttonLine.add(close);
        this.add(buttonLine, BorderLayout.PAGE_END);
    }

}
