package gg.steve.skyupgrades.utils;

import gg.steve.skyupgrades.SkyUpgrades;

public class LogUtil {

    public static void info(String message) {
        SkyUpgrades.get().getLogger().info(message);
    }

    public static void warning(String message) {
        SkyUpgrades.get().getLogger().warning(message);
    }
}
