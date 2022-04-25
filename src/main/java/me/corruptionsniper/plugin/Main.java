package me.corruptionsniper.plugin;


import me.corruptionsniper.plugin.commandExecute.*;
import me.corruptionsniper.plugin.tabCompleter.MessageSendCommandTabCompleter;
import me.corruptionsniper.plugin.tabCompleter.WeaponsCommandTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages; //The hashmap used to store the sender and receiver of the MessageSendCommand

    @Override
    public void onEnable() {
        System.out.println("Plugin: Enabled");

        Bukkit.getPluginManager().registerEvents(new ToggleChat(),this);
        Bukkit.getPluginManager().registerEvents(new WeaponsCommand(),this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);

        getCommand("stick").setExecutor(new StickCommand());
        getCommand("test").setExecutor(new TestCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("togglechat").setExecutor(new ToggleChat());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("weapon").setExecutor(new WeaponsCommand());
        getCommand("message").setExecutor(new MessageSendCommand(this));
        getCommand("reply").setExecutor(new MessageReplyCommand(this));
        getCommand("menu").setExecutor(new MenuCommand());

        getCommand("weapon").setTabCompleter(new WeaponsCommandTabCompleter());
        getCommand("message").setTabCompleter(new MessageSendCommandTabCompleter());

        recentMessages = new HashMap<>();
    }

    public HashMap<UUID, UUID> getRecentMessages() {return recentMessages;}

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        recentMessages.remove(event.getPlayer().getUniqueId());
    } //Removes players that go offline from the hashmap

}