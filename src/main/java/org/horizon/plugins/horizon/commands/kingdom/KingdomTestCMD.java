package org.horizon.plugins.horizon.commands.kingdom;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.horizon.plugins.horizon.Horizon;
import org.horizon.plugins.horizon.api.kingdoms.Kingdom;

public class KingdomTestCMD implements CommandExecutor {

    Horizon horizon;

    public KingdomTestCMD(Horizon horizon) {
        this.horizon = horizon;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        horizon.kingdomAPI.addKingdom((Player) sender, new Kingdom());
        return false;
    }
}
