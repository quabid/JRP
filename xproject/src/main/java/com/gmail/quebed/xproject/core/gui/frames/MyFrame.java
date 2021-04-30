package com.gmail.quebed.xproject.core.gui.frames;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.gmail.quabidlord.core.abstracts.frames.CustomFrame;
import com.gmail.quebed.xproject.core.gui.menus.MyMenu;
import com.gmail.quebed.xproject.core.gui.panels.LoginPanel;
import com.gmail.quebed.xproject.core.gui.panels.PostLoginPanel;
import com.gmail.quebed.xproject.core.gui.panels.RegistrationPanel;

public class MyFrame extends CustomFrame implements ActionListener, MouseListener {
    private final LoginPanel login = new LoginPanel(this, this);
    private final JPanel panel = new JPanel();
    private final Container c = getContentPane();
    private final RegistrationPanel registrationPanel = new RegistrationPanel(this);
    private String username = "";
    CardLayout card;

    public MyFrame(String title) {
        super(title);
        URL imgUrl = getClass().getResource("/air-96.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
        createStartGui();
        pack();
        setResizable(false);
    }

    // Action Handlers

    private final void registerUser() {
        HashMap<String, String> data = registrationPanel.getData();
        printData(data);
    }

    private final void createStartGui() {
        registrationPanel.clearFields();
        card = new CardLayout(50, 100);
        c.removeAll();
        panel.removeAll();
        c.setLayout(card);
        panel.add(login.createPanel());
        c.add(panel);
        c.revalidate();
        panel.revalidate();
        panel.repaint();
        c.repaint();
    }

    private final void showRegistrationForm() {
        JPanel pnlRegister = registrationPanel.createPanel();
        JScrollPane scroll = new JScrollPane(pnlRegister);
        card = new CardLayout(25, 25);
        c.removeAll();
        c.setLayout(card);
        c.add(scroll);
        c.revalidate();
        c.repaint();
    }

    private final void postLogin() {
        login.authenticateUser();
        if (login.isAuthenticated()) {
            username = login.getUsername();
            this.setJMenuBar(new MyMenu(this).getMenu());
            c.removeAll();
            c.add(new PostLoginPanel(this, username, this.getWidth()).createPanel());
            c.revalidate();
            c.repaint();
        } else {
            ImageIcon icon = createImageIcon("/error-50.png");
            alert(icon, "Invalid Credentials", "Oops!");
        }
    }

    private final void logout() {
        login.clearFields();
        createStartGui();
    }

    private final void quitProg() {
        this.exitProg();
    }

    // Action Events
    public void actionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                switch (ae.getActionCommand().toLowerCase().trim()) {
                    case "login":
                        postLogin();
                        break;

                    case "quit":
                        quitProg();
                        break;

                    case "logout":
                        logout();
                        break;

                    case "cancel":
                        createStartGui();
                        break;

                    case "register":
                        registerUser();
                        break;
                }
            }
        });

    }

    // Mouse Events
    @Override
    public void mouseClicked(MouseEvent me) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showRegistrationForm();
                System.out.println("Mouse clicked at " + me.getPoint());
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        JLabel label = (JLabel) me.getSource();
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MyFrame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    // Utils
    private final void printData(HashMap<String, String> data) {
        System.out.println("First Name: " + data.get("fname") + "\nLast Name: " + data.get("lname") + "\nEmail: "
                + data.get("email") + "\nPassword: " + data.get("password"));
    }

    private final void alert(ImageIcon icon, String message, String title) {
        if (null == icon) {
            icon = createImageIcon("/info-48.png");
        }
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE, icon);
    }

}
