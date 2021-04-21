package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private final JButton btnSubmit = new JButton("Submit");

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

        btnSubmit.setActionCommand(btnSubmitActionCommand);
        btnSubmit.addActionListener(actionListener);
        btnSubmit.setEnabled(false);

        tfUsername.setPreferredSize(new Dimension(30, 30));
        tfPassword.setPreferredSize(new Dimension(30, 30));

        tfUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String username = tfUsername.getText();
                char[] password = tfPassword.getPassword();
                boolean validPassword = false;

                if (password.length > 0) {
                    for (char c : password) {
                        if (!Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
                            validPassword = true;
                        }
                    }
                }

                if ((!username.isBlank() && !username.isEmpty()) && (validPassword)) {
                    btnSubmit.setEnabled(true);
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });

        tfPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String username = tfUsername.getText();
                char[] password = tfPassword.getPassword();
                boolean validPassword = false;

                if (password.length > 0) {
                    for (char c : password) {
                        if (!Character.isWhitespace(c) && !Character.isSpaceChar(c)) {
                            validPassword = true;
                        }
                    }
                }

                if ((!username.isBlank() && !username.isEmpty()) && (validPassword)) {
                    btnSubmit.setEnabled(true);
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });

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

    public final String getUsername() {
        return tfUsername.getText();
    }

    public final void clearFields() {
        tfUsername.setText("");
        tfPassword.setText("");
    }
}
