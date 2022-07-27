package org.horizon.plugins.horizon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.horizon.plugins.horizon.api.expansion.ExpansionAPI;
import org.horizon.plugins.horizon.api.gui.GUIListener;
import org.horizon.plugins.horizon.api.gui.GUIManager;
import org.horizon.plugins.horizon.api.kingdoms.KingdomAPI;
import org.horizon.plugins.horizon.commands.tp.NewTeleportCommand;
import org.horizon.plugins.horizon.commands.tpa.TPACommand;
import org.horizon.plugins.horizon.commands.tpa.TeleportationManager;
import org.horizon.plugins.horizon.commands.util.HorizonMainCommand;
import org.horizon.plugins.horizon.scoreboard.ScoreboardManager;

import java.io.File;
import java.io.IOException;

public final class Horizon extends JavaPlugin {


    // Addon/expansion path
    public File af;


    // Configuration
    public static FileConfiguration configuration;

    // Scoreboard Manager
    public static ScoreboardManager scoreboardManager;

    // Teleportation Manager
    public static TeleportationManager teleportationManager;

    // GUI Manager
    public static GUIManager GUIManager;

    // Expansions
    public ExpansionAPI expansionAPI;

    // Kingdoms
    KingdomAPI kingdomAPI;

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
        kingdomAPI = new KingdomAPI(this);

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

        // Save kingdoms if not exist
        if (!kingdomAPI.kingdomsSave.exists()) {
            try {
                kingdomAPI.saveKingdoms();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Load kingdoms
        try {
            kingdomAPI.loadKingdoms();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            kingdomAPI.saveKingdoms();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
