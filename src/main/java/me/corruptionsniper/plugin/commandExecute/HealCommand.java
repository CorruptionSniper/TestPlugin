package me.corruptionsniper.plugin.commandExecute;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) { //Run if issued by player
            Player player = (Player) sender;
            player.sendMessage("Your have been healed");

            //Restores health
            int health = (int) player.getMaxHealth();
            player.setHealth(health);

            //Restores hunger
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,1,10,false,false,false));
        }

        return false;
    }
}
