package org.horizon.plugins.horizon.api.kingdoms;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizon.Horizon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Kingdom's system main API
 */
public class KingdomAPI {


    /**
     * Even though this map says it is using Object, and Object.
     * All use cases should be as if it is Player, Kingdom
     */
    public Map<Object, Object> kingdomMap;
    public File kingdomsSave;
    Properties properties;
    private final Horizon instance;

    public KingdomAPI(Horizon instance) {
        this.instance = instance;
        properties = new Properties();
        kingdomMap = new HashMap<>();
        kingdomsSave = new File(instance.af.getAbsolutePath() + "/data.properties");
        try {
            if (!kingdomsSave.exists()) {
                boolean t = kingdomsSave.createNewFile();
                Bukkit.getLogger().info(String.valueOf(t));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addKingdom(Player ruler, Kingdom kingdom) {
        kingdomMap.put(ruler, kingdom);
    }

    /**
     * Save kingdoms to data.properties
     * @throws IOException
     */
    public void saveKingdoms() throws IOException {
        properties.putAll(kingdomMap);
        kingdomsSave = new File(instance.af.getAbsolutePath() + "/data.properties");
        properties.store(new FileOutputStream("data.properties"), null);
    }

    /**
     * Load kingdoms from data.properties file
     * @throws IOException
     */
    public void loadKingdoms() throws IOException {
        kingdomsSave = new File(instance.af.getAbsolutePath() + "/data.properties");
        properties.load(new FileInputStream(kingdomsSave));
        kingdomMap = new HashMap<>(properties);
    }
}
