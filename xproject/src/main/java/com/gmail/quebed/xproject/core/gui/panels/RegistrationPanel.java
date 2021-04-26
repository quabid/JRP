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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegistrationPanel {
    private ActionListener actionListener;
    private final String btnSubmitActionCommand = "register";
    private final String btnCancelActionCommand = "cancel";
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
    private boolean formFurnished = false;
    private Map<String, String> formErrors = new HashMap<String, String>();
    private int width = 0;
    private int height = 0;

    public RegistrationPanel(ActionListener al) {
        this.actionListener = al;
    }

    public final JPanel createPanel() {
        JPanel main = new JPanel();
        JPanel pnlFirstname = new JPanel();
        JPanel pnlLastname = new JPanel();
        JPanel pnlEmail = new JPanel();
        JPanel pnlPwd1 = new JPanel();
        JPanel pnlPwd2 = new JPanel();
        JPanel pnlRadio = new JPanel();
        JPanel pnlButtons = new JPanel();

        pnlFirstname.setBorder(BorderFactory.createTitledBorder("First Name"));
        pnlLastname.setBorder(BorderFactory.createTitledBorder("Last Name"));
        pnlEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        pnlPwd1.setBorder(BorderFactory.createTitledBorder("Password"));
        pnlPwd2.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        pnlRadio.setBorder(BorderFactory.createTitledBorder("Choose Gender"));

        main.setLayout(new GridLayout(7, 1));
        main.add(pnlFirstname);
        main.add(pnlLastname);
        main.add(pnlEmail);
        main.add(pnlRadio);
        main.add(pnlPwd1);
        main.add(pnlPwd2);
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

        // Config names and email fields
        tfFirstName.addKeyListener(new KeyAdapter() {
            public void keyRelease(KeyEvent Ke) {
                if ((tfFirstName.getText().isBlank() || tfFirstName.getText().isEmpty())
                        || (tfLastName.getText().isBlank() || tfLastName.getText().isEmpty())
                        || (tfEmail.getText().isBlank() || tfEmail.getText().isEmpty())) {
                    formErrors.put("fname", "Must provide a first name");
                    formFurnished = false;
                } else {
                    formErrors.remove("fname");
                    formFurnished = true;
                }
                btnRegister.setEnabled(formFurnished);
            }
        });
        tfFirstName.setPreferredSize(new Dimension(30, 30));

        tfLastName.addKeyListener(new KeyAdapter() {
            public void keyRelease(KeyEvent Ke) {
                if ((tfFirstName.getText().isBlank() || tfFirstName.getText().isEmpty())
                        || (tfLastName.getText().isBlank() || tfLastName.getText().isEmpty())
                        || (tfEmail.getText().isBlank() || tfEmail.getText().isEmpty())) {
                    formErrors.put("lname", "Must provide a last name");
                    formFurnished = false;
                } else {
                    formErrors.remove("lname");
                    formFurnished = true;
                }
                btnRegister.setEnabled(formFurnished);
            }
        });
        tfLastName.setPreferredSize(new Dimension(30, 30));

        tfEmail.addKeyListener(new KeyAdapter() {
            public void keyRelease(KeyEvent Ke) {
                if ((tfFirstName.getText().isBlank() || tfFirstName.getText().isEmpty())
                        || (tfLastName.getText().isBlank() || tfLastName.getText().isEmpty())
                        || (tfEmail.getText().isBlank() || tfEmail.getText().isEmpty())) {
                    formErrors.put("email", "Must provide an email");
                    formFurnished = false;
                } else {
                    formErrors.remove("email");
                    formFurnished = true;
                }
                btnRegister.setEnabled(formFurnished);
            }
        });
        tfEmail.setPreferredSize(new Dimension(30, 30));

        tfPassword1.addKeyListener(new KeyAdapter() {
            public void keyRelease(KeyEvent ke) {
                formFurnished = validPasswords(tfPassword1.getPassword(), tfPassword2.getPassword());
            }
        });
        tfPassword1.setPreferredSize(new Dimension(30, 30));

        tfPassword2.addKeyListener(new KeyAdapter() {
            public void keyRelease(KeyEvent ke) {
                formFurnished = validPasswords(tfPassword1.getPassword(), tfPassword2.getPassword());
                btnRegister.setEnabled(formFurnished);
            }
        });
        tfPassword2.setPreferredSize(new Dimension(30, 30));

        pnlFirstname.add(tfFirstName);
        pnlLastname.add(tfLastName);
        pnlEmail.add(tfEmail);
        pnlPwd1.add(tfPassword1);
        pnlPwd2.add(tfPassword2);

        // Buttons
        JButton btnSubmit = new JButton("Register");
        btnSubmit.setActionCommand(btnSubmitActionCommand);
        btnSubmit.addActionListener(actionListener);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setActionCommand(btnCancelActionCommand);
        btnCancel.addActionListener(actionListener);

        pnlButtons.add(btnSubmit);
        pnlButtons.add(btnCancel);

        JPanel panel = new JPanel();
        panel.add(main);

        return panel;
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

    private final boolean validPasswords(char[] password1, char[] password2) {
        if (password1.length == 0 || password2.length == 0) {
            formErrors.put("empty-password", "Invalid passwords");
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
}
