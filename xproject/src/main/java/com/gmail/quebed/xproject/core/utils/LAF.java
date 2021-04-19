package com.gmail.quebed.xproject.core.utils;

import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LAF {
    private final Map<String, String> lookAndFeelNames = new HashMap<String, String>();
    private final String OS = System.getProperty("os.name").toLowerCase();
    private final String OS_ARCH = System.getProperty("os.arch").toLowerCase();
    private final String OS_VERSION = System.getProperty("os.version").toLowerCase();

    public LAF() {
        retrieveLaf();
    }

    private final void retrieveLaf() {
        if (lookAndFeelNames.size() > 0) {
            lookAndFeelNames.clear();
        }

        for (int i = 0; i < UIManager.getInstalledLookAndFeels().length; i++) {
            LookAndFeelInfo lafi = UIManager.getInstalledLookAndFeels()[i];
            lookAndFeelNames.put(lafi.getName(), lafi.getClassName());
        }
    }

    public final boolean hasLaf() {
        return lookAndFeelNames.size() > 0;
    }

    /**
     * @return the lookAndFeelNames
     */
    public Map<String, String> getLookAndFeelNames() {
        return lookAndFeelNames;
    }

    /**
     * @return the OS
     */
    public String getOS() {
        return OS;
    }

    /**
     * @return the OS_ARCH
     */
    public String getOS_ARCH() {
        return OS_ARCH;
    }

    /**
     * @return the OS_VERSION
     */
    public String getOS_VERSION() {
        return OS_VERSION;
    }
}
