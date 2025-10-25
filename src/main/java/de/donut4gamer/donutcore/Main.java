package de.donut4gamer.donutcore;

import de.donut4gamer.donutcore.commands.DonutCore;
import de.donut4gamer.donutcore.commands.GameModeCommand;
import de.donut4gamer.donutcore.commands.IpCommand;
import de.donut4gamer.donutcore.listeners.ChatEmojis;
import de.donut4gamer.donutcore.listeners.ScoreBoard;
import de.donut4gamer.donutcore.listeners.TabList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Loading contents");

        saveDefaultConfig();

        getCommand("gm").setExecutor(new GameModeCommand(getConfig()));
        getCommand("gmc").setExecutor(new GameModeCommand(getConfig()));
        getCommand("gms").setExecutor(new GameModeCommand(getConfig()));
        getCommand("gma").setExecutor(new GameModeCommand(getConfig()));
        getCommand("gmsp").setExecutor(new GameModeCommand(getConfig()));
        getCommand("donutcore").setExecutor(new DonutCore(this));
        getCommand("ip").setExecutor(new IpCommand(getConfig()));

        getServer().getPluginManager().registerEvents(new ScoreBoard(this), this);
        getServer().getPluginManager().registerEvents(new TabList(this), this);
        getServer().getPluginManager().registerEvents(new ChatEmojis(this), this);

        getLogger().info("Plugin enabled - Version: v" + this.getDescription().getVersion());
        getLogger().info("Developed by Donut4GAMER");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
        getLogger().info("Developed by Donut4GAMER");
    }
}
