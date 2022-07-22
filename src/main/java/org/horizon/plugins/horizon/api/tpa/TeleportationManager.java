package org.horizon.plugins.horizon.api.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizon.Horizon;

import java.util.HashMap;
import java.util.Map;

public class TeleportationManager {

    public Map<Player, Player> playerslist = new HashMap<>();
    private final Horizon instance;

    public TeleportationManager(Horizon instance) {
        this.instance = instance;
    }

    public void request(Player sender, Player target) {
        if (target == sender) {
            target.sendMessage(ChatColor.RED + "You can't teleport to yourself.");
            return;
        }
        if (Bukkit.getOnlinePlayers().toArray().length == 1) {
            target.sendMessage(ChatColor.RED + "There are no other players online");
            return;
        }
        if (playerslist.get(sender) != null) {
            target.sendMessage(ChatColor.RED + "There's already a request");
            return;
        }
        if (playerslist.get(target) == sender) {
            target.sendMessage(ChatColor.RED + "There's a request going the other way");
            return;
        }
        if ((target.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) || target.isInWater()) {
            sender.sendMessage(ChatColor.RED + "They are in a bad situation");
            return;
        }
        if ((sender.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) || sender.isInWater()) {
            sender.sendMessage(ChatColor.RED + "You are in a bad situation");
            return;
        }
        sender.sendMessage(instance.constructPM("&aSent request"));
        target.sendMessage(instance.constructPM("&f" + sender.getName() + " &bhas requested to teleport to you, this will not happen if you are in a bad situation."));
        playerslist.put(sender, target);
        RequestGUI gui = new RequestGUI(sender, target, instance);
    }

}
