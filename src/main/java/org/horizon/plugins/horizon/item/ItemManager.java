package org.horizon.plugins.horizon.item;

import org.horizon.plugins.horizon.Horizon;
import org.horizon.plugins.horizon.item.WaypointStaff.WaypointStaff;

public class ItemManager {

    private Horizon horizon;

    WaypointStaff waypointStaff;

    public ItemManager(Horizon horizon) {
        this.horizon = horizon;
    }

    public void registerItems() {
        waypointStaff = new WaypointStaff(horizon);
    }
}
