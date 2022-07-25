package org.horizon.plugins.horizon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.horizon.api.expansion.ExpansionAPI;
import org.horizon.plugins.horizon.api.gui.GUIListener;
import org.horizon.plugins.horizon.api.gui.GUIManager;
import org.horizon.plugins.horizon.api.tp.NewTeleportCommand;
import org.horizon.plugins.horizon.api.tpa.TPACommand;
import org.horizon.plugins.horizon.api.tpa.TeleportationManager;
import org.horizon.plugins.horizon.commands.util.HorizonMainCommand;
import org.horizon.plugins.horizon.item.ItemManager;
import org.horizon.plugins.horizon.scoreboard.ScoreboardManager;

import java.io.File;

public final class Horizon extends JavaPlugin {

    public File af;

    public static FileConfiguration configuration;
    public static ScoreboardManager scoreboardManager;
    public static TeleportationManager teleportationManager;
    public static GUIManager GUIManager;
    public ExpansionAPI expansionAPI;

    // Item
    ItemManager itemManager;

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
        itemManager = new ItemManager(this);

        // Prefix
        prefix = getConfig().getString("prefix");

        // Commands
        getCommand("horizon").setExecutor(new HorizonMainCommand(this));
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tp").setExecutor(new NewTeleportCommand(this));

        // Listeners
        getServer().getPluginManager().registerEvents(new GUIListener(), this);

        // Items
        itemManager.registerItems();


        // Scoreboard
        if (!getConfig().getBoolean("scoreboard-refresh") && scoreboardManager.taskStarted) {
            Bukkit.getScheduler().cancelTask(scoreboardManager.runTaskID);
            scoreboardManager.taskStarted = false;
        } else {
            scoreboardManager.registerRefresh();
        }
        getServer().getPluginManager().registerEvents(new ScoreboardManager(this), this);


        // Instantiate expansionManager
        expansionAPI = new ExpansionAPI(this);

        // Save addons folder if It's not there.
        String configPath = getDataFolder().getAbsolutePath();
        Bukkit.getLogger().info(configPath);
        File expansionFolder = new File(configPath + "/expansions");
        af = expansionFolder;
        if (!expansionFolder.exists()) expansionFolder.mkdir();

        // Start searching for expansions
        expansionAPI.sfer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public void registerEvent(Object cls, String name) {
        getServer().getPluginManager().registerEvents((Listener) cls, this);
        getLogger().info("Registered event " + name);
    }


    public String constructPM(String msg) {
        prefix = getConfig().getString("prefix");
        return ChatColor.translateAlternateColorCodes('&', prefix + msg);
    }


}
