package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ProfilePanel {
    private ActionListener actionListener;
    private final Map<String, String> formErrors = new HashMap<String, String>();
    private final String btnSubmitActionCommand = "register";
    private final String btnCancelActionCommand = "cancel";
    private final String jrMaleActionCommand = "male";
    private final String jrFemaleActionCommand = "female";
    private final String jrOtherActionCommand = "other";
    private final JTextField tfFirstName = new JTextField(30);
    private final JTextField tfLastName = new JTextField(30);
    private final JRadioButton jrMale = new JRadioButton("Male");
    private final JRadioButton jrFemale = new JRadioButton("Female");
    private final JRadioButton jrOther = new JRadioButton("Other");
    private final JButton btnRegister = new JButton("Register");
    private final JButton btnCancel = new JButton("Cancel");

    public ProfilePanel(ActionListener al) {
        this.actionListener = al;
    }

    public final JPanel createPanel() {
        JPanel main = new JPanel();
        JPanel pnlFirstname = new JPanel();
        JPanel pnlLastname = new JPanel();
        JPanel pnlRadio = new JPanel();
        JPanel pnlButtons = new JPanel();

        pnlFirstname.setBorder(BorderFactory.createTitledBorder("First Name"));
        pnlLastname.setBorder(BorderFactory.createTitledBorder("Last Name"));
        pnlRadio.setBorder(BorderFactory.createTitledBorder("Choose Gender"));

        main.setLayout(new GridLayout(7, 1));
        main.setBorder(BorderFactory.createTitledBorder("Profile"));
        main.add(pnlFirstname);
        main.add(pnlLastname);
        main.add(pnlRadio);
        main.add(pnlButtons);

        // Config radio buttons
        jrMale.setMnemonic(KeyEvent.VK_M);
        jrMale.setName(jrMaleActionCommand);

        jrFemale.setMnemonic(KeyEvent.VK_F);
        jrFemale.setName(jrFemaleActionCommand);

        jrOther.setMnemonic(KeyEvent.VK_O);
        jrOther.setName(jrOtherActionCommand);

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        pnlRadio.setLayout(new GridLayout(3, 1));
        pnlRadio.add(jrMale);
        pnlRadio.add(jrFemale);
        pnlRadio.add(jrOther);
        group.add(jrMale);
        group.add(jrFemale);
        group.add(jrOther);

        // Config text fields
        tfFirstName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                btnRegister.setEnabled(validNameFields() && validEmailField() && validPasswords());
            }
        });
        tfFirstName.setPreferredSize(new Dimension(30, 30));

        tfLastName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                btnRegister.setEnabled(validNameFields() && validEmailField() && validPasswords());
            }
        });
        tfLastName.setPreferredSize(new Dimension(30, 30));

        pnlFirstname.add(tfFirstName);
        pnlLastname.add(tfLastName);

        // Buttons
        btnRegister.setEnabled(validNameFields() && validEmailField() && validPasswords());
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
        tfFirstName.setText("");
        tfLastName.setText("");
    }

    private final boolean validNameFields() {
        final String firstName = tfFirstName.getText();
        final String lastName = tfLastName.getText();
        if (firstName.isEmpty() || firstName.isBlank()) {
            formErrors.put("empty-first-name", "Must provide a first name");
            return false;
        } else {
            formErrors.remove("empty-first-name");
        }

        if (lastName.isEmpty() || lastName.isBlank()) {
            formErrors.put("empty-last-name", "Must provide a first name");
            return false;
        } else {
            formErrors.remove("empty-last-name");
        }
        return true;
    }

    // Getters
    public final HashMap<String, String> getData() {
        final HashMap<String, String> data = new HashMap<String, String>();
        String gender = "";

        if (jrMale.isSelected()) {
            gender = "male";
        } else if (jrFemale.isSelected()) {
            gender = "female";
        } else {
            gender = "other";
        }

        data.put("fname", tfFirstName.getText());
        data.put("lname", tfLastName.getText());
        data.put("gender", gender);
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
