package org.horizon.plugins.horizon;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Horizon extends JavaPlugin {

    public static FileConfiguration configuration;

    @Override
    public void onEnable() {
        // Config
        saveDefaultConfig();
        configuration = getConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
