package gg.steve.skyupgrades;

import gg.steve.skyupgrades.utils.LogUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkyUpgrades extends JavaPlugin {
    private static SkyUpgrades instance;
    private static int tracks;
    private static Economy economy;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        tracks = 0;
        SetupManager.setupFiles(new FileManager(instance));
        SetupManager.registerCommands(instance);
        SetupManager.registerEvents(instance);
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
        } else {
            LogUtil.info("Unable to find economy instance, disabling economy features.");
            economy = null;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkyUpgrades get() {
        return instance;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public static int getTracks() {
        return tracks;
    }

    public static void setTracks(int number) {
        tracks = number;
    }
}
