package gg.steve.skyupgrades.cmd.sub;

import gg.steve.skyupgrades.FileManager;
import gg.steve.skyupgrades.core.UpgradeUtil;
import gg.steve.skyupgrades.nbt.NBTItem;
import gg.steve.skyupgrades.utils.LogUtil;
import gg.steve.skyupgrades.utils.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UpgradeCmd {

    public static void upgrade(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return;
        }
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.getBoolean("sky-upgrades.tool")) {
            MessageUtil.message("lang", "invalid-tool", player);
            return;
        }
        int track = nbtItem.getInteger("sky-upgrades.track");
        int tier = nbtItem.getInteger("sky-upgrades.tier");
        YamlConfiguration config = FileManager.get("track-" + track);
        if (tier >= config.getInt("max-tier")) {
            MessageUtil.message("lang", "max-tier", player);
            return;
        }
        int nextTier = tier + 1;
        String section = "tiers." + nextTier + ".";
        UpgradeUtil.upgrade(config, section, track, nextTier, player);
    }
}
