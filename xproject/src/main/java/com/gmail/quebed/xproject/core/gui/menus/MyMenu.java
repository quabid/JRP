package com.gmail.quebed.xproject.core.gui.menus;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenu {
    private ActionListener actionListener;

    public MyMenu(ActionListener aListener) {
        this.actionListener = aListener;
    }

    public final JMenuBar getMenu() {
        // Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        // Create the menu bar.
        menuBar = new JMenuBar();

        // Build the first menu.
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        menuItem = new JMenuItem("Quit", new ImageIcon("/close-64.png"));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(actionListener);
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menu.add(menuItem);

        return menuBar;
    }

}
