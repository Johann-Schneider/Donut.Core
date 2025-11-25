package de.donut4gamer.donutcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TabList implements Listener {
    private final JavaPlugin plugin;
    private final FileConfiguration config;
    private final int updateInterval;

    public TabList(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.updateInterval = config.getInt("tablist.update-interval", 100);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!config.getBoolean("tablist.enabled", true)) {
                    cancel();
                    return;
                }

                String header = getFormattedLines(config.getStringList("tablist.header"), player);
                String footer = getFormattedLines(config.getStringList("tablist.footer"), player);

                header = ChatColor.translateAlternateColorCodes('&', header);
                footer = ChatColor.translateAlternateColorCodes('&', footer);

                player.setPlayerListHeader(header);
                player.setPlayerListFooter(footer);
            }
        }.runTaskTimer(plugin, 0L, updateInterval);
    }

    private String getFormattedLines(java.util.List<String> lines, Player player) {
        StringBuilder formatted = new StringBuilder();
        for (String line : lines) {
            line = PlaceholderAPI.setPlaceholders(player, line);
            line = ChatColor.translateAlternateColorCodes('&', line);
            formatted.append(line).append("\n");
        }
        return formatted.toString().trim();
    }
}
