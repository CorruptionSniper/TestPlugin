package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GetPlayerSkullCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            //Creates the skull
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

            if (args.length == 1) {
                if (Bukkit.getPlayerExact(args[0]) != null) { //Checks if the player specified is on the server
                    skullMeta.setOwningPlayer(Bukkit.getPlayerExact(args[0])); //Sets the player skull to the player specified
                } else {
                    player.sendMessage(ChatColor.RED + "The player specified is not online");
                }

            } else if (args.length > 1) {
                player.sendMessage(ChatColor.RED + "Incorrect usage: /getplayerskull | /getplayerskull <player>");
            } else {
                skullMeta.setOwningPlayer(player); //Sets the player skull to self
            }
            skull.setItemMeta(skullMeta);

            player.getInventory().addItem(skull); //Adds the skull to the player's inventory
        }

        return false;
    }
}
