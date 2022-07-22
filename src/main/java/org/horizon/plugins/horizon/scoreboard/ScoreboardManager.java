package org.horizon.plugins.horizon.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.horizon.plugins.horizon.Horizon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreboardManager implements Listener {

    private Map<Player, FastBoard> boards = new HashMap<>();

    public int runTaskID;
    public boolean taskStarted = false;
    Horizon main;

    public ScoreboardManager(Horizon main) {
        this.main = main;
    }

    public FastBoard getBoardFromPlayer(Player player) {
        return boards.get(player);
    }

    public Map<Player, FastBoard> getBoards() {
        return boards;
    }

    @EventHandler
    public void playerBoardRegister(PlayerJoinEvent event) {
        refresh(event.getPlayer());
    }

    public void refresh(Player player) {
        FastBoard board = new FastBoard(player);
        List<String> updatedLines = new ArrayList<>();
        for (String s : Horizon.configuration.getStringList("scoreboard-lines")) {
            s = s.replaceAll("%player%", player.getName());
            s = s.replaceAll("%player_username%", player.getDisplayName());
            s = s.replaceAll("%stat_deaths%", String.valueOf(player.getStatistic(Statistic.DEATHS)));
            s = s.replaceAll("%stat_kills%", String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS)));
            s = s.replaceAll("%playtime%", String.valueOf(player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 1200));
            updatedLines.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        board.updateLines(updatedLines);
        board.updateTitle(ChatColor.translateAlternateColorCodes('&', Horizon.configuration.getString("scoreboard-title")));
        boards.put(player, board);
    }

    public void registerRefresh() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        taskStarted = true;
        runTaskID = scheduler.scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    refresh(p);
                }
            }
        }, 0, 60);
    }

}
