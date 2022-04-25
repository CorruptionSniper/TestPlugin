package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    private List<UUID> vanished = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {//Run if issued by player
            Player player = (Player) sender;

            if (vanished.contains(player.getUniqueId())) { //To un-vanish the player
                vanished.remove(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()) {
                    target.showPlayer(player);
                }
                player.sendMessage("You have been un-vanished");

            } else { //To vanish the player
                vanished.add(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()) {
                    target.hidePlayer(player);
                }
                player.sendMessage("You are now vanished");
            }
        }

        return false;
    }
}
