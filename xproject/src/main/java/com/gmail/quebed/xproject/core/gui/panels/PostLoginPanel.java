package com.gmail.quebed.xproject.core.gui.panels;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PostLoginPanel {
    private final String btnActionCommand = "okay";
    private ActionListener actionListener;
    private String username = "";
    private int width;

    public PostLoginPanel(ActionListener al, String user, int w) {
        this.actionListener = al;
        this.username = user;
        this.width = (w - 5);
    }

    public final JComponent createPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon iconProfile = createImageIcon("/male-profile-30.png");
        ImageIcon iconSettings = createImageIcon("/tune-24.png");
        ImageIcon iconLogout = createImageIcon("/sign-out-30.png");

        tabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JComponent pnlProfile = makeTextPanel(username);
        pnlProfile.setPreferredSize(new Dimension(width, 50));
        tabbedPane.addTab("Profile", iconProfile, pnlProfile);

        JComponent pnlSettings = makeTextPanel("Settings");
        tabbedPane.addTab("Settings", iconSettings, pnlSettings);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        JComponent pnlQuit = makeLogoutPanel("Quit");
        tabbedPane.addTab("Logout", iconLogout, pnlQuit);

        JPanel main = new JPanel();
        main.add(tabbedPane);

        return main;
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 2));
        panel.add(filler);
        return panel;
    }

    protected JComponent makeLogoutPanel(String text) {
        JPanel panel = new JPanel(false);
        JPanel pnlBtnQuit = new JPanel();
        JButton btnQuit = new JButton(text);
        btnQuit.addActionListener(actionListener);
        btnQuit.setActionCommand("quit");
        pnlBtnQuit.add(btnQuit);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(pnlBtnQuit);
        return panel;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PostLoginPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
