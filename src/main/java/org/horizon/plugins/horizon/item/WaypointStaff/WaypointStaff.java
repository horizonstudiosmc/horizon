package org.horizon.plugins.horizon.item.WaypointStaff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.horizon.plugins.horizon.Horizon;
import org.horizon.plugins.horizon.api.item.LeveledItem;

import java.util.ArrayList;
import java.util.List;

public class WaypointStaff extends LeveledItem {
    List<String> itemLore = new ArrayList<>();
    Horizon horizon;

    public WaypointStaff(Horizon horizon) {
        this.horizon = horizon;
        level = 1;
        material = Material.STICK;
        name = "&eWaypoint Staff";
        itemLore.add(ChatColor.GREEN + "A waypoint staff");
        itemLore.add(ChatColor.GREEN + "Right click to use");
        lore = itemLore;
        itemId = 1;
        init();
    }

    @Override
    public void levelColorChooser() {
        color = "&7";
    }

    @Override
    public void registerRec() {
        NamespacedKey key = new NamespacedKey(horizon, "waypoint_staff");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("I  ", " E ", "  S");
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('E', Material.ENDER_EYE);
        recipe.setIngredient('S', Material.STICK);

        Bukkit.addRecipe(recipe);
    }
}
