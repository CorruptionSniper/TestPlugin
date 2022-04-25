package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class StickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) { //Run if issued by player
            Player player = (Player) sender;

            //Creates the "stick"
            ItemStack stick = new ItemStack(Material.STICK);
            ItemMeta meta = stick.getItemMeta();
            meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
            meta.setDisplayName(ChatColor.AQUA + "Thy Stick");
            meta.setLore(Collections.singletonList("You do not want to get poked by this stick"));
            stick.setItemMeta(meta);

            //Gives the player the "stick"
            player.getInventory().addItem(stick);
        }

        return false;
    }
}
