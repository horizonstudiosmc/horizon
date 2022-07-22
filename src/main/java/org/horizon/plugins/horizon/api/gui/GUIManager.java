package org.horizon.plugins.horizon.api.gui;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GUIManager {
    private final Map<Inventory, HorizonGUI> guiMap = new HashMap<>();

    public void setGui(Inventory inventory, HorizonGUI gui) {
        guiMap.put(inventory, gui);
    }

    public HorizonGUI getGui(Inventory inventory) {
        return guiMap.get(inventory);
    }

    public void removeGui(Inventory inventory) {
        guiMap.remove(inventory);
    }
}
