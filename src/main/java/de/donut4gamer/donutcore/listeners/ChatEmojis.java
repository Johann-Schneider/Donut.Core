package de.donut4gamer.donutcore.listeners;

import de.donut4gamer.donutcore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;

public class ChatEmojis implements Listener {

    private final Main plugin;
    private Map<String, Object> replacements;

    public ChatEmojis(Main plugin) {
        this.plugin = plugin;
        loadReplacements();
    }

    public void loadReplacements() {
        FileConfiguration config = plugin.getConfig();
        replacements = config.getConfigurationSection("chat.replacements").getValues(false);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        for (Map.Entry<String, Object> entry : replacements.entrySet()) {
            msg = msg.replace(entry.getKey(), String.valueOf(entry.getValue()));
        }
        event.setMessage(msg);
    }
}