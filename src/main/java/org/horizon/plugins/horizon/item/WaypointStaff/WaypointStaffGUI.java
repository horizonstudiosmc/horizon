package org.horizon.plugins.horizon.item.WaypointStaff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.horizon.plugins.horizon.Horizon;
import org.horizon.plugins.horizon.api.gui.HorizonGUI;

public class WaypointStaffGUI implements HorizonGUI {

    Player player;
    Horizon horizon;
    Inventory inv;
    private boolean bedNotSet = false;

    public WaypointStaffGUI(Player player, Horizon instance) {
        inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&dWarp Core"));
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemStack home = new ItemStack(Material.RED_BED);
        ItemMeta homeMeta = home.getItemMeta();
        if (player.getBedSpawnLocation() == null) {
            bedNotSet = false;
            homeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cTeleport to your bed &7(You do not have a bed set)"));
        } else {
            bedNotSet = true;
            homeMeta.setDisplayName(ChatColor.GREEN + "Teleport to home");
        }
        home.setItemMeta(homeMeta);
        inv.addItem(glass, home);
        Horizon.GUIManager.setGui(inv, this);
        player.openInventory(inv);
    }
    @Override
    public void handleEventLeftClick(InventoryClickEvent event) {
        Bukkit.getLogger().info(String.valueOf(event.getSlot()));
        Bukkit.getLogger().info(String.valueOf(bedNotSet));
        if (bedNotSet) {
            Bukkit.getLogger().info(String.valueOf(event.getSlot()));
            if (event.getSlot() == 1) {
                player.closeInventory();
                player.teleport(player.getBedSpawnLocation());
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }
        event.setCancelled(true);
    }

    @Override
    public void handleEventRightClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void handleClose(InventoryCloseEvent event) {
        Horizon.GUIManager.removeGui(event.getInventory());
        return;
    }
}
