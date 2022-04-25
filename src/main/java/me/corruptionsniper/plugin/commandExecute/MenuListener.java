package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (ChatColor.translateAlternateColorCodes('&', event.getView().getTitle()).equals(ChatColor.DARK_GREEN + "Menu") && event.getCurrentItem() != null) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();

            switch (event.getRawSlot()) {
                case 0:
                    break;
                case 20: //Random Teleport
                    Random random = new Random();
                    Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                    player.teleport(target);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You have been teleported to " + target.getName());
                    break;
                case 22: //Kill Self
                    player.setHealth(0);
                    player.sendMessage(ChatColor.RED + "You killed yourself");
                    break;
                case 24: //Clear Inventory
                    player.closeInventory();
                    player.getInventory().clear();
                    player.sendMessage(ChatColor.YELLOW + "You cleared your inventory");
                    return;
                default:

                    return;
            }
            player.closeInventory();
        }
    }

}
