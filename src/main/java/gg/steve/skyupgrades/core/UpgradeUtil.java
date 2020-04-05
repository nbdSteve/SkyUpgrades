package gg.steve.skyupgrades.core;

import gg.steve.skyupgrades.FileManager;
import gg.steve.skyupgrades.SkyUpgrades;
import gg.steve.skyupgrades.utils.ColorUtil;
import gg.steve.skyupgrades.utils.ItemBuilderUtil;
import gg.steve.skyupgrades.utils.MessageUtil;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class UpgradeUtil {

    public static void upgrade(YamlConfiguration config, String section, int track, int tier, Player target) {
        double cost = config.getDouble("tiers." + (tier - 1) + ".cost");
        if (SkyUpgrades.getEconomy().getBalance(target) >= cost) {
            SkyUpgrades.getEconomy().withdrawPlayer(target, cost);
            target.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            give(config, section, track, tier, target);
            List<String> msg = FileManager.get("lang").getStringList("upgrade-success");
            for (int i = 0; i < msg.size() - 1; i++) {
                target.sendMessage(ColorUtil.colorize(msg.get(i)));
            }
            for (String line : config.getStringList(section + "lore")) {
                if (line.length() < 1) continue;
                String string = msg.get(msg.size() - 1).replace("{tier_item_lore}", ColorUtil.colorize(line));
                target.sendMessage(ColorUtil.colorize(string));
            }
        } else {
            MessageUtil.message("lang", "insufficient", target);
        }
    }

    public static void give(YamlConfiguration config, String section, int track, int tier, Player target) {
        ItemBuilderUtil itemStack = new ItemBuilderUtil(config.getString(section + "item").toUpperCase(),
                config.getString(section + "data-value"));
        itemStack.addName(config.getString(section + "name"));
        itemStack.addLore(config.getStringList(section + "lore"));
        itemStack.addEnchantments(config.getStringList(section + "enchantments"));
        itemStack.addItemFlags(config.getStringList(section + "item-flags"));
        itemStack.addData(track, tier, config.getBoolean(section + "unbreakable"));
        itemStack.give(target);
    }
}
