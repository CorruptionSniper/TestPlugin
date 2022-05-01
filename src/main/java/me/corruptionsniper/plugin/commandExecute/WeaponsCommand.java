package me.corruptionsniper.plugin.commandExecute;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WeaponsCommand implements CommandExecutor, Listener {

    private Cache<UUID,Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(150, TimeUnit.MILLISECONDS).build();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("snowball_launcher")) {
                    //Creates the Snowball Launcher
                    ItemStack sl = new ItemStack(Material.DIAMOND_HOE);
                    ItemMeta slMeta = sl.getItemMeta();
                    slMeta.setDisplayName(ChatColor.AQUA + "Snowball Launcher");
                    slMeta.setLore(Collections.singletonList("Fire snowballs"));
                    slMeta.isUnbreakable();
                    sl.setItemMeta(slMeta);

                    //Gives the player the Snowball Launcher
                    player.getInventory().addItem(sl);

                } else if (args[0].equalsIgnoreCase("egg_launcher")) {
                    //Creates the Egg Launcher
                    ItemStack el = new ItemStack(Material.IRON_HOE);
                    ItemMeta elMeta = el.getItemMeta();
                    elMeta.setDisplayName(ChatColor.AQUA + "Egg Launcher");
                    elMeta.setLore(Collections.singletonList("Fire eggs"));
                    elMeta.isUnbreakable();
                    el.setItemMeta(elMeta);

                    //Gives the player the Egg Launcher
                    player.getInventory().addItem(el);

                } /*else if (args[0].equalsIgnoreCase("test_gun")) {
                    //Creates the Egg Launcher
                    ItemStack gun = new ItemStack(Material.GOLDEN_HOE);
                    ItemMeta gunMeta = gun.getItemMeta();
                    gunMeta.setDisplayName(ChatColor.AQUA + "Test Gun");
                    gunMeta.setLore(Collections.singletonList("experimental"));
                    gunMeta.isUnbreakable();
                    gun.setItemMeta(gunMeta);

                    //Gives the player the Test Gun
                    player.getInventory().addItem(gun);
                }*/

            } else {
                player.sendMessage(ChatColor.RED + "incorrect usage: /weapon [snowball_launcher | egg_launcher]");
            }
        }
        return false;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) { //Triggers on click

        Player player = event.getPlayer();

        if (event.hasItem()) { //Checks for the item in hand

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {

                // Checks if Item is Snowball Launcher
                if (event.getItem().getType().equals(Material.DIAMOND_HOE) && event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Snowball Launcher")) {
                    // Shoots the Snowball
                    player.launchProjectile(Snowball.class);
                    player.playSound(player.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F);

                    // Checks if Item is Egg Launcher
                } else if (event.getItem().getType().equals(Material.IRON_HOE) && event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Egg Launcher")) {
                    // Shoots the Egg
                    player.launchProjectile(Egg.class);
                    player.playSound(player.getLocation(), Sound.ENTITY_EGG_THROW, 1.0F, 1.0F);

                } /*else if (event.getItem().getType().equals(Material.GOLDEN_HOE) && event.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Test Gun")) {
                player.launchProjectile()
            }*/
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 150);
            }

        }

    }
}