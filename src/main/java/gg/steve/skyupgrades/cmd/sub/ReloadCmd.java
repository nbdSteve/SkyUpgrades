package gg.steve.skyupgrades.cmd.sub;

import gg.steve.skyupgrades.FileManager;
import gg.steve.skyupgrades.utils.MessageUtil;
import org.bukkit.command.CommandSender;

public class ReloadCmd {

    public static void reload(CommandSender sender) {
        String node = FileManager.get("config").getString("admin-perms.reload");
        if (!sender.hasPermission(node)) {
            MessageUtil.permissionDebug(sender, node);
            return;
        }
        FileManager.reload();
        MessageUtil.reloadMessage(sender);
    }
}
