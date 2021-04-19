package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PostLoginPanel {
    private final String btnActionCommand = "okay";
    private ActionListener actionListener;

    public PostLoginPanel(ActionListener al) {
        this.actionListener = al;
    }

    public final JPanel createPanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel pnlLabel = new JPanel();
        JPanel pnlButton = new JPanel();

        JButton btn = new JButton("Okay");
        btn.setActionCommand(btnActionCommand);
        btn.addActionListener(actionListener);

        JLabel label = new JLabel("Well Done!");

        pnlLabel.add(label);
        pnlButton.add(btn);

        main.add(pnlLabel, BorderLayout.CENTER);
        main.add(pnlButton, BorderLayout.SOUTH);

        return main;
    }
}
