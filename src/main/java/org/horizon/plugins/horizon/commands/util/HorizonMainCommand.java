package org.horizon.plugins.horizon.commands.util;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizon.Horizon;

public class HorizonMainCommand implements CommandExecutor {

    Horizon main;

    public HorizonMainCommand(Horizon main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(main.constructPM("&cYou need to use an argument! Do /horizon help for help"));
                return true;
            }
            switch (args[0]) {
                case "reload":
                    if (!player.hasPermission("horizon.reload")) {
                        player.sendMessage(main.constructPM("&cNo permission to reload"));
                        return true;
                    }
                    main.reloadConfig();
                    player.sendMessage(main.constructPM("&aReloaded config!"));
                    return true;
            }

        }
        return false;
    }
}
