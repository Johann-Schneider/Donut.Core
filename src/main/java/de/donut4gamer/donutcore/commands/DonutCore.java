package de.donut4gamer.donutcore.commands;

import de.donut4gamer.donutcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DonutCore implements CommandExecutor {

    private final Main plugin;

    public DonutCore(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§6DonutCore §8| §7Use /donutcore help for help.");
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§8========== §6DonutCore Help §8==========");
            sender.sendMessage("§6/donutcore help §8- §7Shows this help message");
            sender.sendMessage("§6/donutcore reload §8- §7Reloads the config");
            sender.sendMessage("§6/ip [Player] §8- §7Get the IP from a player");
            sender.sendMessage("§6/ping [Player] §8- §7See your or others ping");
            sender.sendMessage("§6/gmc [Player] §8- §7Set the gamemode to creative (of a player)");
            sender.sendMessage("§6/gma [Player] §8- §7Set the gamemode to adventure (of a player)");
            sender.sendMessage("§6/gms [Player] §8- §7Set the gamemode to survival (of a player)");
            sender.sendMessage("§6/gmsp [Player] §8- §7Set the gamemode to spectator (of a player)");
            sender.sendMessage("§6/gm <0/1/2/3|s/c/a/sp> [Player] §8- §7Set the gamemode (of a player)");
            sender.sendMessage("§8=====================================");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.saveDefaultConfig();
            sender.sendMessage("§6DonutCore §8| §aConfig reloaded!");
            return true;
        }

        sender.sendMessage("§6DonutCore §8| §cUnknown command. Use /donutcore help.");
        return true;
    }
}
