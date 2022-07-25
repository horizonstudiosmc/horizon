package org.horizon.plugins.horizon.item;

import org.horizon.plugins.horizon.Horizon;

public class ItemManager {

    private Horizon horizon;

    WaypointStaff waypointStaff;

    public ItemManager(Horizon instance) {
        horizon = instance;
    }

    public void registerItems() {
        waypointStaff = new WaypointStaff(horizon);
    }
}
