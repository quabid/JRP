package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel {
    private ActionListener actionListener;
    private MouseListener mouseListener;
    private final String btnSubmitActionCommand = "login";
    private final JTextField tfUsername = new JTextField(30);
    private final JPasswordField tfPassword = new JPasswordField(30);
    private final JButton btnSubmit = new JButton("Submit");
    private boolean authenticated = false;

    public LoginPanel(ActionListener al, MouseListener ml) {
        this.actionListener = al;
        this.mouseListener = ml;
    }

    public final JPanel createPanel() {
        JPanel pnlLogin = new JPanel(new BorderLayout(0, 0));
        JPanel pnlUsername = new JPanel();
        JPanel pnlPassword = new JPanel();
        JPanel pnlPasswordParent = new JPanel(new BorderLayout());
        JPanel pnlSubmit = new JPanel();
        JPanel pnlUserOptions = new JPanel(new BorderLayout());
        JPanel pnlChbShowPassword = new JPanel();
        JPanel pnlLblRegister = new JPanel();

        pnlLogin.setBorder(BorderFactory.createTitledBorder("Login"));

        // User Options
        JCheckBox chbShowPassword = new JCheckBox("Show Password");
        chbShowPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JCheckBox jcb = (JCheckBox) ae.getSource();
                if (jcb.isSelected()) {
                    tfPassword.setEchoChar((char) 0);
                } else {
                    tfPassword.setEchoChar('*');
                }
            }
        });
        pnlChbShowPassword.add(chbShowPassword);

        JLabel lblRegister = new JLabel("Register");
        lblRegister.setForeground(Color.BLUE);
        Font font = new Font("Courier", Font.BOLD, 12);
        lblRegister.setFont(font);
        lblRegister.addMouseListener(mouseListener);
        pnlLblRegister.add(lblRegister);

        pnlUserOptions.add(pnlChbShowPassword, BorderLayout.WEST);
        pnlUserOptions.add(pnlLblRegister, BorderLayout.EAST);

        // User Login
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
                btnSubmit.setEnabled(validCreds(username, password));
            }
        });

        tfPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String username = tfUsername.getText();
                char[] password = tfPassword.getPassword();
                btnSubmit.setEnabled(validCreds(username, password));
            }
        });

        pnlSubmit.add(btnSubmit);

        pnlUsername.add(lblUsername);
        pnlUsername.add(tfUsername);

        pnlPassword.add(lblPassword);
        pnlPassword.add(tfPassword);
        pnlPasswordParent.add(pnlPassword, BorderLayout.NORTH);
        pnlPasswordParent.add(pnlUserOptions, BorderLayout.CENTER);

        pnlLogin.add(pnlUsername, BorderLayout.NORTH);
        pnlLogin.add(pnlPasswordParent, BorderLayout.CENTER);
        pnlLogin.add(pnlSubmit, BorderLayout.SOUTH);
        return pnlLogin;
    }

    public final String getUsername() {
        return tfUsername.getText();
    }

    public final boolean isAuthenticated() {
        return authenticated;
    }

    public final void clearFields() {
        tfUsername.setText("");
        tfPassword.setText("");
    }

    public final void authenticateUser() {
        authenticated = false;
    }

    private final boolean validCreds(String username, char[] password) {
        for (int i = 0; i < password.length; i++) {
            if (Character.isWhitespace(password[i]) || Character.isSpaceChar(password[i])) {
                return false;
            }
        }
        return !username.isEmpty() && !username.isBlank() && password.length > 0;
    }
}
