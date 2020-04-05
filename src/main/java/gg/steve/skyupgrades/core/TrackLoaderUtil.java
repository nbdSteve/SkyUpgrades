package gg.steve.skyupgrades.core;

import gg.steve.skyupgrades.FileManager;
import gg.steve.skyupgrades.SkyUpgrades;
import gg.steve.skyupgrades.utils.LogUtil;

import java.io.File;

public class TrackLoaderUtil {

    public static void loadTracks(FileManager fileManager) {
        int tracks = FileManager.get("config").getInt("number-of-tracks");
        SkyUpgrades.setTracks(tracks);
        for (int i = 1; i <= tracks; i++) {
            fileManager.add("track-" + i, "tracks" + File.separator + i + ".yml");
            LogUtil.info("Successfully loaded the configuration file for track " + i);
        }
    }
}
