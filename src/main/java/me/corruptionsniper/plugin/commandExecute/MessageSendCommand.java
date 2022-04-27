package me.corruptionsniper.plugin.commandExecute;

import me.corruptionsniper.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageSendCommand implements CommandExecutor {

    private Main main;

    public MessageSendCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {

                if (Bukkit.getPlayerExact(args[0]) != null) { //Checks if the player specified is on the server
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder(); //Creates the variable that stores the message to be sent
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    player.sendMessage(ChatColor.YELLOW + "To " + target.getName() + ": " + ChatColor.WHITE + builder); //Tells the sender that he sent the message
                    target.sendMessage(ChatColor.YELLOW + "From " + player.getName() + ": " + ChatColor.WHITE + builder); //Sends the message to the player receiving the message

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId()); //Adds the players to a hashmap in main that is used by MessageReplyCommand
                } else {
                    player.sendMessage(ChatColor.RED + "The player specified is not online");
                }

            } else {
                player.sendMessage(ChatColor.RED + "incorrect usage: /message <player> <message>");
            }
        }

        return false;
    }
}
