package gg.steve.skyupgrades.cmd.sub;

import gg.steve.skyupgrades.FileManager;
import gg.steve.skyupgrades.SkyUpgrades;
import gg.steve.skyupgrades.core.UpgradeUtil;
import gg.steve.skyupgrades.utils.ItemBuilderUtil;
import gg.steve.skyupgrades.utils.LogUtil;
import gg.steve.skyupgrades.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCmd {

    public static void give(CommandSender sender, String[] args) {
        String node = FileManager.get("config").getString("admin-perms.give");
        if (!sender.hasPermission(node)) {
            MessageUtil.permissionDebug(sender, node);
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (!Bukkit.getOnlinePlayers().contains(target)) {
            MessageUtil.commandDebug(sender, "The target player must be online");
            return;
        }
        int track = Integer.parseInt(args[2]);
        if (track > SkyUpgrades.getTracks() || track <= 0) {
            MessageUtil.commandDebug(sender, "The track number you entered does not exist");
            return;
        }
        YamlConfiguration config = FileManager.get("track-" + track);
        int tier = Integer.parseInt(args[3]);
        if (tier > SkyUpgrades.getTracks() || tier <= 0) {
            MessageUtil.commandDebug(sender, "The tier number you entered does not exist");
            return;
        }
        String section = "tiers." + tier + ".";
        UpgradeUtil.give(config, section, track, tier, target);
//        ItemBuilderUtil itemStack = new ItemBuilderUtil(config.getString(section + "item").toUpperCase(),
//                config.getString(section + "data-value"));
//        if (config.getBoolean(section + "unbreakable")) {
//            LogUtil.info("working");
//            itemStack.setUnbreakable();
//        }
//        itemStack.addName(config.getString(section + "name"));
//        itemStack.addLore(config.getStringList(section + "lore"));
//        itemStack.addEnchantments(config.getStringList(section + "enchantments"));
//        itemStack.addItemFlags(config.getStringList(section + "item-flags"));
//        itemStack.addData(track, tier);
//        itemStack.give(target);
        MessageUtil.message("lang", "give-tool-receiver", target,
                "{tier}", String.valueOf(tier),
                "{track}", String.valueOf(track));
        if (sender instanceof Player && !sender.getName().equalsIgnoreCase(target.getName())) {
            MessageUtil.message("lang", "give-tool-giver", (Player) sender,
                    "{player}", target.getName(),
                    "{tier}", String.valueOf(tier),
                    "{track}", String.valueOf(track));
        } else {
            LogUtil.info("Console has given " + target.getName() + " a tier " + tier + " tool from track " + track + ".");
        }
    }
}
