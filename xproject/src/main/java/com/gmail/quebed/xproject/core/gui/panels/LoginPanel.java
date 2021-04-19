package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel {
    private ActionListener actionListener;
    private final String btnSubmitActionCommand = "submit";
    private final JTextField tfUsername = new JTextField(30);
    private final JPasswordField tfPassword = new JPasswordField(30);

    public LoginPanel(ActionListener al) {
        this.actionListener = al;
    }

    public final JPanel createPanel() {
        JPanel pnlLogin = new JPanel(new BorderLayout());
        JPanel pnlUsername = new JPanel();
        JPanel pnlPassword = new JPanel();
        JPanel pnlSubmit = new JPanel();
        JPanel panel = new JPanel();

        JLabel lblUsername = new JLabel("Username");
        JLabel lblPassword = new JLabel("Password");

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setActionCommand(btnSubmitActionCommand);
        btnSubmit.addActionListener(actionListener);

        tfUsername.setPreferredSize(new Dimension(30, 30));
        tfPassword.setPreferredSize(new Dimension(30, 30));

        pnlSubmit.add(btnSubmit);

        pnlUsername.add(lblUsername);
        pnlUsername.add(tfUsername);

        pnlPassword.add(lblPassword);
        pnlPassword.add(tfPassword);

        pnlLogin.add(pnlUsername, BorderLayout.NORTH);
        pnlLogin.add(pnlPassword, BorderLayout.CENTER);
        pnlLogin.add(pnlSubmit, BorderLayout.SOUTH);
        panel.add(pnlLogin);
        return panel;
    }
}
