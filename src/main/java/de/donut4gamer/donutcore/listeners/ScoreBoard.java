package de.donut4gamer.donutcore.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreBoard implements Listener {
    private final JavaPlugin plugin;
    private final FileConfiguration config;
    private final int updateInterval;

    public ScoreBoard(JavaPlugin plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.updateInterval = config.getInt("scoreboard.update-interval", 100);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!config.getBoolean("scoreboard.enabled", true)) {
                    cancel();
                    player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                    return;
                }

                Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
                String title = getFormattedLine(config.getString("scoreboard.title", "&6DonutCore"), player);

                Objective objective = board.registerNewObjective("sidebar", "dummy", title);
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                List<String> lines = config.getStringList("scoreboard.lines");
                int score = lines.size();

                for (String line : lines) {
                    String formatted = getFormattedLine(line, player);
                    Score s = objective.getScore(formatted);
                    s.setScore(score);
                    score--;
                }

                player.setScoreboard(board);
            }
        }.runTaskTimer(plugin, 0L, updateInterval);
    }

    private String getFormattedLine(String line, Player player) {
        line = PlaceholderAPI.setPlaceholders(player, line);
        return ChatColor.translateAlternateColorCodes('&', line);
    }
}
