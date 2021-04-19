package com.gmail.quebed.xproject;

import javax.swing.SwingUtilities;

import com.gmail.quebed.xproject.core.gui.frames.MyFrame;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyFrame("X-Project");
            }
        });
    }
}
