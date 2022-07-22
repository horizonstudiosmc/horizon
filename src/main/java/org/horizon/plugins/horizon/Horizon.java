package org.horizon.plugins.horizon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.horizon.api.gui.GUIListener;
import org.horizon.plugins.horizon.api.gui.GUIManager;
import org.horizon.plugins.horizon.api.tp.NewTeleportCommand;
import org.horizon.plugins.horizon.api.tpa.TPACommand;
import org.horizon.plugins.horizon.api.tpa.TeleportationManager;
import org.horizon.plugins.horizon.commands.util.HorizonMainCommand;
import org.horizon.plugins.horizon.scoreboard.ScoreboardManager;

public final class Horizon extends JavaPlugin {

    public static FileConfiguration configuration;
    public static ScoreboardManager scoreboardManager;
    public static TeleportationManager teleportationManager;
    public static GUIManager GUIManager;

    public String prefix;

    @Override
    public void onEnable() {
        // Config
        saveDefaultConfig();
        reloadConfig();
        configuration = getConfig();

        // Init
        GUIManager = new GUIManager();
        teleportationManager = new TeleportationManager(this);
        scoreboardManager = new ScoreboardManager(this);

        // Prefix
        prefix = getConfig().getString("prefix");

        // Commands
        getCommand("horizon").setExecutor(new HorizonMainCommand(this));
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tp").setExecutor(new NewTeleportCommand(this));

        // Listeners
        getServer().getPluginManager().registerEvents(new GUIListener(), this);


        // Scoreboard
        if (!getConfig().getBoolean("scoreboard-refresh") && scoreboardManager.taskStarted) {
            Bukkit.getScheduler().cancelTask(scoreboardManager.runTaskID);
            scoreboardManager.taskStarted = false;
        } else {
            scoreboardManager.registerRefresh();
        }
        getServer().getPluginManager().registerEvents(new ScoreboardManager(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public String constructPM(String msg) {
        prefix = getConfig().getString("prefix");
        return ChatColor.translateAlternateColorCodes('&', prefix + msg);
    }


}
