package com.gmail.quebed.xproject.core.gui.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.gmail.quabidlord.core.abstracts.frames.CustomFrame;
import com.gmail.quebed.xproject.core.gui.panels.LoginPanel;
import com.gmail.quebed.xproject.core.gui.panels.PostLoginPanel;

public class MyFrame extends CustomFrame implements ActionListener {
    private final JPanel main = new JPanel();

    public MyFrame(String title) {
        super(title);
        URL imgUrl = getClass().getResource("/air-96.png");
        this.setIconImage(new ImageIcon(imgUrl).getImage());
        this.add(main);
        createStartGui();
        this.setResizable(false);
        this.pack();
    }

    private final void createStartGui() {
        main.add(new LoginPanel(this).createPanel());
    }

    private final void postLogin() {
        main.removeAll();
        main.add(new PostLoginPanel(this).createPanel());
        main.revalidate();
        main.repaint();
    }

    private final void successfulLogin() {
        ImageIcon icon = createImageIcon("/element-64.png");
        JOptionPane.showMessageDialog(this, "You've successfully logged in", "Congratulations!",
                JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public void actionPerformed(ActionEvent ae) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                switch (ae.getActionCommand().toLowerCase().trim()) {
                case "okay":
                    successfulLogin();
                    break;

                case "submit":
                    postLogin();
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
