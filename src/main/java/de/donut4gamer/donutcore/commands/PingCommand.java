package de.donut4gamer.donutcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    private final FileConfiguration config;

    public PingCommand(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(getMessage("ping.messages.no-player")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.hasPermission(getPermission("ping.permissions.self"))) {
                player.sendMessage(color(getMessage("ping.messages.no-permission")));
                return true;
            }

            int ping = getPing(player);
            player.sendMessage(color(getMessage("ping.messages.self-ping")
                    .replace("%ping%", String.valueOf(ping))));
            return true;
        }

        if (args.length == 1) {
            if (!player.hasPermission(getPermission("ping.permissions.others"))) {
                player.sendMessage(color(getMessage("ping.messages.no-permission")));
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(color(getMessage("ping.messages.player-not-found")
                        .replace("%target%", args[0])));
                return true;
            }

            int ping = getPing(target);

            player.sendMessage(color(getMessage("ping.messages.other-ping-sender")
                    .replace("%target%", target.getName())
                    .replace("%ping%", String.valueOf(ping))));

            return true;
        }

        player.sendMessage(color(getMessage("ping.messages.usage")));
        return true;
    }

    private int getPing(Player player) {
        try {
            return player.getPing();
        } catch (Exception e) {
            return -1;
        }
    }

    private String getMessage(String path) {
        return config.getString(path, "§cMessage not found: " + path);
    }

    private String getPermission(String path) {
        return config.getString(path, "donutcore.default");
    }

    private String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
