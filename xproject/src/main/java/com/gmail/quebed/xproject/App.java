package com.gmail.quebed.xproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import com.gmail.quebed.xproject.core.gui.frames.MyFrame;

public class App {
    public static void main_(String[] args) {
        String strPattern = "\\w+(\\.\\w+)?@\\w+(\\.\\w{2,3})$";
        String email = "rick@email.net";

        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(email);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Email Valid: " + matcher.find());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyFrame("X-Project");
            }
        });
    }
}
