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
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final ImageIcon iconProfile = createImageIcon("/male-profile-30.png");
    private final ImageIcon iconSettings = createImageIcon("/tune-24.png");
    private final ImageIcon iconLogout = createImageIcon("/sign-out-30.png");
    private ActionListener actionListener;
    private String username = "";
    private int width;
    private int tabIndex = -1;

    public PostLoginPanel(ActionListener al, String user, int w) {
        this.actionListener = al;
        this.username = user;
        this.width = (w - 9);
    }

    public final JComponent createPanel() {
        tabbedPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        tabbedPane.setSelectedIndex(tabIndex);

        JComponent pnlProfile = makeTextPanel(username);
        pnlProfile.setPreferredSize(new Dimension(width, 50));
        tabbedPane.addTab("Profile", iconProfile, pnlProfile);

        JComponent pnlSettings = makeTextPanel("Settings");
        tabbedPane.addTab("Settings", iconSettings, pnlSettings);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        JComponent pnlLogout = makeLogoutPanel("Logout");
        tabbedPane.addTab("Logout", iconLogout, pnlLogout);

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
        JPanel pnlBtnLogout = new JPanel();
        JButton btnLogout = new JButton(text);
        btnLogout.addActionListener(actionListener);
        btnLogout.setActionCommand("logout");
        pnlBtnLogout.add(btnLogout);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(pnlBtnLogout);
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

    // Getters

    public final int getSelectedTabIndex() {
        return tabbedPane.getSelectedIndex();
    }

    public final void setSelectedTabIndex(int selectTabIndex) {
        this.tabIndex = selectTabIndex;
    }
}
