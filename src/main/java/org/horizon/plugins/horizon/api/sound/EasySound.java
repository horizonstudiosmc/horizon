package org.horizon.plugins.horizon.api.sound;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EasySound {
    public static void playSoundAtPlayer(Player player, Sound sound) {
        player.playSound(player, sound, 1f, 1f);
    }
    public static void playSoundAtLocation(Location location, Player player, Sound sound) {
        player.playSound(location, sound, 1f, 1f);
    }
}
