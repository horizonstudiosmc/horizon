package org.horizon.plugins.horizon.api.tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizon.Horizon;

public class TPACommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!(target == null)) {
                    Horizon.teleportationManager.request(player, target);
                }
            }
        }
        return false;
    }
}
