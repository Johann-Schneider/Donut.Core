package de.donut4gamer.donutcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class IpCommand implements CommandExecutor {

    private final FileConfiguration config;

    public IpCommand(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(color(getMessage("ip.messages.no-player")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(getPermission("ip.permissions.view"))) {
            player.sendMessage(color(getMessage("ip.messages.no-permission")));
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(color(getMessage("ip.messages.usage")));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(color(getMessage("ip.messages.player-not-found")
                    .replace("%target%", args[0])));
            return true;
        }

        String ip = target.getAddress().getAddress().getHostAddress();
        player.sendMessage(color(getMessage("ip.messages.show-ip")
                .replace("%target%", target.getName())
                .replace("%ip%", ip)));

        return true;
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
