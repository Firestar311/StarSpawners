package me.alonedev.spawners.Commands;

import me.alonedev.spawners.Functions.SpawnerCreator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnerGive implements CommandExecutor {

    private SpawnerCreator creator;

    public SpawnerGive(){
        Bukkit.getPluginCommand("spawner").setExecutor(this);
        this.creator = new SpawnerCreator();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if(args.length == 4) {
                if (args[0].equalsIgnoreCase("give")) {
                    String player = args[1];
                    Player p = Bukkit.getServer().getPlayer(player);
                    String spawner = args[2];
                    int amount = Integer.parseInt(args[3]);
                    creator.giveSpawner(p,spawner,amount);
                    return true;
                }
            }


        }
        return false;
    }
}
