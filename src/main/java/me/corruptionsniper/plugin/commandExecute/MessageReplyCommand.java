package me.corruptionsniper.plugin.commandExecute;

import me.corruptionsniper.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MessageReplyCommand implements CommandExecutor {

    private Main main;

    public MessageReplyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId()); //Uses the hashmap from main to get the player to send the message to
                    if (Bukkit.getPlayer(uuid) != null) { //Checks if the player is online
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder(); //Creates the variable that stores the message to be sent
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage(ChatColor.YELLOW + "To " + target.getName() + ": " + ChatColor.WHITE + builder); //Tells the sender that he sent the message
                        target.sendMessage(ChatColor.YELLOW + "From " + player.getName() + ": " + ChatColor.WHITE + builder); //Sends the message to the player receiving the message

                    } else {
                        player.sendMessage(ChatColor.RED + "That player is not online at the moment");
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone recently");
                }


            } else {
                player.sendMessage(ChatColor.RED + "incorrect usage: /reply <message>");
            }
        }

        return false;
    }
}
