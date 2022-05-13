package me.corruptionsniper.plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerHealthScoreboard implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("health","dummy");
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective.setDisplayName("Health");

        Team playerHealth = scoreboard.registerNewTeam("playerHealth");
        playerHealth.addEntry(ChatColor.RED.toString());
        playerHealth.setPrefix("Health: ");
        playerHealth.setSuffix(String.valueOf(player.getHealth()));
        objective.getScore(ChatColor.RED.toString()).setScore(1);

        player.setScoreboard(scoreboard);
    }
}
