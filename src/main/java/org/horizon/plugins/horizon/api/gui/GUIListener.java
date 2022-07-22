package org.horizon.plugins.horizon.api.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.horizon.plugins.horizon.Horizon;

public class GUIListener implements Listener {
    private final GUIManager guiManager = Horizon.GUIManager;

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        HorizonGUI gui = guiManager.getGui(event.getInventory());
        gui.handleClose(event);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        try {
            HorizonGUI gui = guiManager.getGui(event.getInventory());
            if(gui != null) {
                if (event.isLeftClick()) gui.handleEventLeftClick(event);
                if (event.isRightClick()) gui.handleEventRightClick(event);
            }
        } catch (NullPointerException e) {
            // Do nothing, its usual
        }

    }
}
