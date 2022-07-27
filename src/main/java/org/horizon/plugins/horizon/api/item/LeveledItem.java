package org.horizon.plugins.horizon.api.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.horizon.plugins.horizon.Horizon;

import java.util.ArrayList;
import java.util.List;

public abstract class LeveledItem {
    public int level;
    public Material material;
    public ItemStack item;
    public String name;
    public String color;
    Horizon horizon;
    public String itemId;
    public NamespacedKey key;
    public List<String> lore = new ArrayList<>();


    public void init() {
        key = new NamespacedKey(horizon, itemId);
        levelColorChooser();
        generateItem();
        registerRec();
    }

    public abstract void levelColorChooser();

    private void generateItem() {
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&7(" + color + String.valueOf(level) + "&7)&r " + name));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LOYALTY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, itemId);
        item.setItemMeta(meta);

    }


    public abstract void registerRec();


}
