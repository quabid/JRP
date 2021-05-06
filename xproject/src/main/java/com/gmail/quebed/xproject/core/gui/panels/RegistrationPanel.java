package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationPanel {
    private ActionListener actionListener;
    private final Map<String, String> formErrors = new HashMap<String, String>();
    private final String btnSubmitActionCommand = "register";
    private final String btnCancelActionCommand = "cancel";
    private final JTextField tfEmail = new JTextField(30);
    private final JPasswordField tfPassword1 = new JPasswordField(30);
    private final JPasswordField tfPassword2 = new JPasswordField(30);
    private final JButton btnRegister = new JButton("Register");
    private final JButton btnCancel = new JButton("Cancel");

    public RegistrationPanel(ActionListener al) {
        this.actionListener = al;
    }

    public final JPanel createPanel() {
        JPanel main = new JPanel();
        JPanel pnlEmail = new JPanel();
        JPanel pnlPwd1 = new JPanel();
        JPanel pnlPwd2 = new JPanel();
        JPanel pnlButtons = new JPanel();

        pnlEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        pnlPwd1.setBorder(BorderFactory.createTitledBorder("Password"));
        pnlPwd2.setBorder(BorderFactory.createTitledBorder("Confirm Password"));

        main.setLayout(new GridLayout(7, 1));
        main.setBorder(BorderFactory.createTitledBorder("Registration"));
        main.add(pnlEmail);
        main.add(pnlPwd1);
        main.add(pnlPwd2);
        main.add(pnlButtons);

        // Config text fields
        tfEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                btnRegister.setEnabled(validEmailField() && validPasswords());
            }
        });
        tfEmail.setPreferredSize(new Dimension(30, 30));

        tfPassword1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                btnRegister.setEnabled(validEmailField() && validPasswords());
            }
        });
        tfPassword1.setPreferredSize(new Dimension(30, 30));

        tfPassword2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                btnRegister.setEnabled(validEmailField() && validPasswords());
            }
        });
        tfPassword2.setPreferredSize(new Dimension(30, 30));

        pnlEmail.add(tfEmail);
        pnlPwd1.add(tfPassword1);
        pnlPwd2.add(tfPassword2);

        // Buttons
        btnRegister.setEnabled(validEmailField() && validPasswords());
        btnRegister.setActionCommand(btnSubmitActionCommand);
        btnRegister.addActionListener(actionListener);

        btnCancel.setActionCommand(btnCancelActionCommand);
        btnCancel.addActionListener(actionListener);

        pnlButtons.add(btnRegister);
        pnlButtons.add(btnCancel);

        JPanel panel = new JPanel();
        panel.add(main);

        return panel;
    }

    // Utils
    public final void clearFields() {
        tfEmail.setText("");
        tfPassword1.setText("");
        tfPassword2.setText("");
    }

    private final boolean validEmailField() {
        final String email = tfEmail.getText();
        final String strPattern = "\\w+(\\.\\w+)?@\\w+(\\.\\w{3})$";
        final Pattern pattern = Pattern.compile(strPattern);
        final Matcher matcher = pattern.matcher(email);
        final boolean validEmail = matcher.find();
        if (email.isEmpty() || email.isBlank()) {
            formErrors.put("empty-email", "Must provide an email");
            return false;
        } else {
            formErrors.remove("empty-email");
        }

        if (!validEmail) {
            formErrors.put("invalid-email", "Must provide a valid email");
        } else {
            formErrors.remove("invalid-email");
        }
        return validEmail;
    }

    private final boolean validPasswords() {
        final char[] password1 = tfPassword1.getPassword();
        final char[] password2 = tfPassword2.getPassword();

        if (password1.length == 0 || password2.length == 0) {
            formErrors.put("empty-password", "Invalid passwords");
            return false;
        } else if (password1.length != password2.length) {
            return false;
        } else {
            formErrors.remove("empty-password");
        }

        for (int i = 0; i < password1.length; i++) {
            if (Character.isWhitespace(password1[i]) || Character.isSpaceChar(password1[i])) {
                formErrors.put("password1", "Passwords are invalid");
                return false;
            } else {
                formErrors.remove("password1");
            }
        }

        for (int i = 0; i < password2.length; i++) {
            if (Character.isWhitespace(password2[i]) || Character.isSpaceChar(password2[i])) {
                formErrors.put("password2", "Passwords are invalid");
                return false;
            } else {
                formErrors.remove("password2");
            }
        }

        for (int i = 0; i < password1.length; i++) {
            if (password1[i] != password2[i]) {
                formErrors.put("passwords", "Passwords don't match");
                return false;
            } else {
                formErrors.remove("passwords");
            }
        }

        return true;
    }

    // Getters
    public final HashMap<String, String> getData() {
        final HashMap<String, String> data = new HashMap<String, String>();
        String password = "";

        for (char c : tfPassword1.getPassword()) {
            password += c;
        }

        data.put("email", tfEmail.getText());
        data.put("password", password);
        return data;

    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = RegistrationPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
