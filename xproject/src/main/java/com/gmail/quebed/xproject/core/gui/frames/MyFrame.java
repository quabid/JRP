package com.gmail.quebed.xproject.core.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.gmail.quabidlord.core.abstracts.frames.CustomFrame;
import com.gmail.quebed.xproject.core.gui.menus.MyMenu;
import com.gmail.quebed.xproject.core.gui.panels.LoginPanel;
import com.gmail.quebed.xproject.core.gui.panels.PostLoginPanel;

public class MyFrame extends CustomFrame implements ActionListener {
    private final JPanel main = new JPanel();
    private final LoginPanel login = new LoginPanel(this);
    private String username = "";

    public MyFrame(String title) {
        super(title);
        URL imgUrl = getClass().getResource("/air-96.png");
        setIconImage(new ImageIcon(imgUrl).getImage());
        getContentPane().setLayout(new BorderLayout());
        add(main, BorderLayout.CENTER);
        createStartGui();
        setResizable(false);
        pack();
    }

    private final void createStartGui() {
        username = null;
        main.removeAll();
        main.add(login.createPanel());
        main.revalidate();
        main.repaint();
    }

    // Action Handlers

    private final void postLogin() {
        login.authenticateUser();
        if (login.isAuthenticated()) {
            username = login.getUsername();
            this.setJMenuBar(new MyMenu(this).getMenu());
            main.removeAll();
            main.add(new PostLoginPanel(this, username, this.getWidth()).createPanel());
            main.revalidate();
            main.repaint();
        } else {
            ImageIcon icon = createImageIcon("/error-50.png");
            JOptionPane.showMessageDialog(this, "Invalid Credentials", "Oops!", JOptionPane.ERROR_MESSAGE, icon);
        }
    }

    private final void logout() {
        login.clearFields();
        createStartGui();
    }

    private final void quitProg() {
        this.exitProg();
    }

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
                }
            }
        });

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

}
