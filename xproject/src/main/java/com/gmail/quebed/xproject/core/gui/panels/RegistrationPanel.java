package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegistrationPanel {
    private ActionListener actionListener;
    private final String btnSubmitActionCommand = "register";
    private final JTextField tfFirstName = new JTextField(30);
    private final JTextField tfLastName = new JTextField(30);
    private final JTextField tfEmail = new JTextField(30);
    private final JRadioButton jrMale = new JRadioButton("Male");
    private final JRadioButton jrFemale = new JRadioButton("Female");
    private final JRadioButton jrOther = new JRadioButton("Other");
    private final String jrMaleActionCommand = "male";
    private final String jrFemaleActionCommand = "female";
    private final String jrOtherActionCommand = "other";
    private final JPasswordField tfPassword1 = new JPasswordField(30);
    private final JPasswordField tfPassword2 = new JPasswordField(30);
    private final JButton btnRegister = new JButton("Register");
    private boolean registered = false;

    public RegistrationPanel(ActionListener al) {
        this.actionListener = al;
    }

    public final JPanel createPanel() {
        JPanel main = new JPanel();

        // Config radio buttons
        jrMale.setMnemonic(KeyEvent.VK_M);
        jrMale.setActionCommand(jrMaleActionCommand);

        jrFemale.setMnemonic(KeyEvent.VK_F);
        jrFemale.setActionCommand(jrFemaleActionCommand);

        jrOther.setMnemonic(KeyEvent.VK_O);
        jrOther.setActionCommand(jrOtherActionCommand);

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(jrMale);
        group.add(jrFemale);
        group.add(jrOther);

        // Register a listener for the radio buttons.
        jrMale.addActionListener(actionListener);
        jrFemale.addActionListener(actionListener);
        jrOther.addActionListener(actionListener);

        return main;
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
