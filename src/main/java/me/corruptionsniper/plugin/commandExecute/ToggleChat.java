package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ToggleChat implements CommandExecutor, Listener {

    //The toggle variable
    static private boolean tcToggle = true;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (tcToggle) { //Flips toggle off
            tcToggle = false;
            if (sender instanceof Player) {sender.sendMessage(ChatColor.RED + "Chat has been disabled");}

        } else { //Flips toggle on
            tcToggle = true;
            if (sender instanceof Player) {sender.sendMessage(ChatColor.DARK_GREEN + "Chat has been enabled");}
        }

        return false;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (!tcToggle) { //If toggle off disabled the sending of messages
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Chat is disabled");
        }
    }
}
