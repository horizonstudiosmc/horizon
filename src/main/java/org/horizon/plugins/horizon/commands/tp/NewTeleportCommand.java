package org.horizon.plugins.horizon.commands.tp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizon.Horizon;
import org.horizon.plugins.horizon.api.sound.EasySound;

public class NewTeleportCommand implements CommandExecutor {

    Horizon instance;

    public NewTeleportCommand(Horizon instance) {
        this.instance = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                if (!player.hasPermission("horizonessentials.tp")) {
                    player.sendMessage(instance.constructPM("&cYou do not have permission!"));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(instance.constructPM("&cThat player is not online"));
                    return true;
                }
                if (target == player) {
                    target.sendMessage(ChatColor.RED + "You can't teleport to yourself.");
                    return true;
                }
                EasySound.playSoundAtPlayer(player, Sound.ENTITY_VILLAGER_YES);
                player.teleport(target);
                player.sendMessage(instance.constructPM("&aTeleported to &f" + target.getName()));
                return true;

            } else if (args.length == 2) {
                if (!player.hasPermission("horizonessentials.tp")) {
                    player.sendMessage(instance.constructPM("&cYou do not have permission!"));
                    return true;
                }
                Player player1 = Bukkit.getPlayer(args[0]);
                Player player2 = Bukkit.getPlayer(args[1]);
                if (player1 == null || player2 == null) {
                    player.sendMessage(instance.constructPM("&cThose players are not online"));
                    return true;
                }
                if (player1 == player2) {
                    player.sendMessage(instance.constructPM("&cYou cannot teleport yourself to yourself."));
                    return true;
                }
                player1.teleport(player2);
                player.sendMessage(instance.constructPM("&aTeleported &f" + player1 + " &bto &f" + player2));
                return true;
            }

            player.sendMessage(instance.constructPM("&fOpened teleportation GUI, if you don't want to do this, use an argument"));
            NewTeleportGUI teleportGUI = new NewTeleportGUI(player, instance);
        }
        return false;
    }


}
