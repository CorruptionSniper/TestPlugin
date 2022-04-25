package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            //Menu
            Inventory menu = Bukkit.createInventory(player, 45, ChatColor.DARK_GREEN + "Menu");

            //Exit Item
            ItemStack exit = new ItemStack(Material.BARRIER);
            ItemMeta exitMeta = exit.getItemMeta();
            exitMeta.setDisplayName(ChatColor.RED + "Exit");
            exit.setItemMeta(exitMeta);

            //Border Item
            ItemStack border = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

            //Random Teleport Item
            ItemStack randomTp = new ItemStack(Material.ENDER_PEARL);
            ItemMeta randomTpMeta = randomTp.getItemMeta();
            randomTpMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Random teleport");
            randomTpMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Teleports to a random player"));
            randomTp.setItemMeta(randomTpMeta);

            //Kill Self Item
            ItemStack killSelf = new ItemStack(Material.IRON_SWORD);
            ItemMeta killSelfMeta = killSelf.getItemMeta();
            killSelfMeta.setDisplayName(ChatColor.RED + "Kill Self");
            killSelfMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Kills yourself"));
            killSelf.setItemMeta(killSelfMeta);

            //Clear Inventory Item
            ItemStack clearInv = new ItemStack(Material.BUCKET);
            ItemMeta clearInvMeta = clearInv.getItemMeta();
            clearInvMeta.setDisplayName(ChatColor.YELLOW + "Clear Inventory");
            clearInvMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Clears the Inventory"));
            clearInv.setItemMeta(clearInvMeta);

            //Positions in Menu
            menu.setItem(0,exit);
            menu.setItem(20,randomTp);
            menu.setItem(22,killSelf);
            menu.setItem(24,clearInv);
            for (int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}) {
                menu.setItem(i,border);
            }

            player.openInventory(menu);
        }

        return false;
    }
}
