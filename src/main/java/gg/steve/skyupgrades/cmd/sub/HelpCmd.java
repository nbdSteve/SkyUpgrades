package gg.steve.skyupgrades.cmd.sub;

import gg.steve.skyupgrades.FileManager;
import gg.steve.skyupgrades.utils.MessageUtil;
import org.bukkit.command.CommandSender;

public class HelpCmd {

    public static void help(CommandSender sender) {
        String node = FileManager.get("config").getString("admin-perms.help");
        if (!sender.hasPermission(node)) {
            MessageUtil.permissionDebug(sender, node);
            return;
        }
        MessageUtil.helpMessage(sender);
    }
}
