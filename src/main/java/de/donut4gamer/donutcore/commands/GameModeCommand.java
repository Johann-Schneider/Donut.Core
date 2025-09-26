package de.donut4gamer.donutcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    private final FileConfiguration config;

    public GameModeCommand(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(color(getMessage("gamemode.messages.no-player")));
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(getPermission("gamemode.permissions.gamemode-self"))) {
            player.sendMessage(color(getMessage("gamemode.messages.no-permission")));
            return true;
        }

        GameMode mode = null;
        Player target = player;

        if (label.equalsIgnoreCase("gm")) {
            if (args.length < 1) {
                player.sendMessage(color(getMessage("gamemode.messages.usage")));
                return true;
            }

            mode = parseGamemode(args[0]);
            if (mode == null) {
                player.sendMessage(color(getMessage("gamemode.messages.invalid-gamemode")));
                return true;
            }

            if (args.length >= 2) {
                target = Bukkit.getPlayerExact(args[1]);
                if (target == null) {
                    player.sendMessage(color(getMessage("gamemode.messages.player-not-found")));
                    return true;
                }
            }
        } else {
            switch (label.toLowerCase()) {
                case "gmc":
                    mode = GameMode.CREATIVE;
                    break;
                case "gms":
                    mode = GameMode.SURVIVAL;
                    break;
                case "gmsp":
                    mode = GameMode.SPECTATOR;
                    break;
                case "gma":
                    mode = GameMode.ADVENTURE;
                    break;
                default:
                    return false;
            }

            if (args.length >= 1) {
                target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(color(getMessage("gamemode.messages.player-not-found")));
                    return true;
                }
            }
        }

        if (!player.equals(target) && !player.hasPermission(getPermission("gamemode.permissions.gamemode-others"))) {
            player.sendMessage(color(getMessage("gamemode.messages.no-permission")));
            return true;
        }

        if (mode != null) {
            target.setGameMode(mode);
            String modeName = formatGamemodeName(mode);

            if (player.equals(target)) {
                player.sendMessage(
                        color(getMessage("gamemode.messages.self-change").replace("%gamemode%", modeName))
                );
            } else {
                player.sendMessage(
                        color(getMessage("gamemode.messages.other-change-sender")
                                .replace("%gamemode%", modeName)
                                .replace("%target%", target.getName()))
                );
                target.sendMessage(
                        color(getMessage("gamemode.messages.other-change-target")
                                .replace("%gamemode%", modeName)
                                .replace("%player%", player.getName()))
                );
            }
        }
        return true;
    }

    private GameMode parseGamemode(String input) {
        switch (input.toLowerCase()) {
            case "0":
            case "survival":
            case "s":
                return GameMode.SURVIVAL;
            case "1":
            case "creative":
            case "c":
                return GameMode.CREATIVE;
            case "2":
            case "adventure":
            case "a":
                return GameMode.ADVENTURE;
            case "3":
            case "spectator":
            case "sp":
                return GameMode.SPECTATOR;
            default:
                return null;
        }
    }

    private String formatGamemodeName(GameMode mode) {
        String name = mode.toString().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    private String getMessage(String path) {
        return config.getString(path, "§cMessage not found: " + path);
    }

    private String getPermission(String path) {
        return config.getString(path, "donutcore.default");
    }

    private String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
