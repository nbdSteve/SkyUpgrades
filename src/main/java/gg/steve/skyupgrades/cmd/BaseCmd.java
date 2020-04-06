package gg.steve.skyupgrades.cmd;

import gg.steve.skyupgrades.cmd.sub.GiveCmd;
import gg.steve.skyupgrades.cmd.sub.HelpCmd;
import gg.steve.skyupgrades.cmd.sub.ReloadCmd;
import gg.steve.skyupgrades.cmd.sub.UpgradeCmd;
import gg.steve.skyupgrades.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("upgrade")) {
            if (args.length == 0) {
                UpgradeCmd.upgrade(sender);
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")) {
                    HelpCmd.help(sender);
                } else if (args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload")) {
                    ReloadCmd.reload(sender);
                } else if (args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("give")) {
                    GiveCmd.give(sender, args);
                } else {
                    MessageUtil.commandDebug(sender, "The base argument is invalid, try: help, reload or give");
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("give")) {
                    GiveCmd.give(sender, args);
                } else {
                    MessageUtil.commandDebug(sender, "The base argument is invalid, try: help, reload or give");
                }
            } else {
                MessageUtil.commandDebug(sender, "The arguments you entered are invalid");
            }
        }
        return true;
    }
}
