package org.horizon.plugins.horizon.item;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.horizon.plugins.horizon.Horizon;
import org.horizon.plugins.horizon.item.WaypointStaff.WaypointStaffGUI;

public class ItemEvent implements Listener {
    Horizon horizon;

    public ItemEvent(Horizon horizon) {
        this.horizon = horizon;
    }

    @EventHandler
    public void onItemRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (!event.getItem().getItemMeta().hasCustomModelData()) return;
            switch (event.getItem().getItemMeta().getCustomModelData()) {
                case 1:
                    WaypointStaffGUI staffGUI = new WaypointStaffGUI(player, horizon);
                    break;
            }
        }
    }
}
