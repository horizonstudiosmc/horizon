package org.horizon.plugins.horizon.api.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface HorizonGUI {
    void handleEventLeftClick(InventoryClickEvent event);
    void handleEventRightClick(InventoryClickEvent event);
    void handleClose(InventoryCloseEvent event);




}
