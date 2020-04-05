package gg.steve.skyupgrades;

import gg.steve.skyupgrades.cmd.BaseCmd;
import gg.steve.skyupgrades.core.TrackLoaderUtil;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {

    private SetupManager() throws IllegalAccessException {
        throw new IllegalAccessException("Manager class cannot be instantiated.");
    }

    /**
     * Loads the files into the file manager
     *
     * @param fileManager FileManager, the plugins file manager
     */
    public static void setupFiles(FileManager fileManager) {
        // general files
        fileManager.add("config", "sky-upgrades.yml");
        fileManager.add("lang", "lang.yml");
        // load all of the track files
        TrackLoaderUtil.loadTracks(fileManager);
    }

    public static void registerCommands(SkyUpgrades instance) {
        instance.getCommand("upgrade").setExecutor(new BaseCmd());
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(Plugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
    }
}
