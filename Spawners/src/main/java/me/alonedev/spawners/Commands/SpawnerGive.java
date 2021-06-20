package me.alonedev.spawners.Commands;

import me.alonedev.spawners.Functions.SpawnerCreator;
import me.alonedev.spawners.Spawners;
import me.alonedev.spawners.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnerGive implements CommandExecutor {

    private SpawnerCreator creator;
    private Spawners main;

    public SpawnerGive(Spawners main){
        this.main = main;
        Bukkit.getPluginCommand("spawner").setExecutor(this);
        this.creator = new SpawnerCreator();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    Block b = pl.getTargetBlock(null, 4);
                    Util.sendMsg("The block you looked at is: " + b, pl);
                    if (b.getType() == Material.SPAWNER) {
                        CreatureSpawner creatureSpawner = (CreatureSpawner) b;
                        if (args[1].equalsIgnoreCase("Entity")) {
                            creatureSpawner.setSpawnedType(EntityType.valueOf(args[2].toUpperCase()));
                        }
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                creatureSpawner.update();
                            }
                        }.runTaskLater(main, 1);
                    }
                    return true;
                }
            }
            else if(args.length == 4) {
                if (args[0].equalsIgnoreCase("give")) {
                    String player = args[1];
                    Player p = Bukkit.getServer().getPlayer(player);
                    String spawner = args[2];
                    int amount = Integer.parseInt(args[3]);
                    creator.giveSpawner(p, spawner, amount);
                    return true;
                }
            }


        }
        return false;
    }
}
