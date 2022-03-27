package com.starmediadev.plugins.starspawners.commands;

import com.starmediadev.plugins.starspawners.StarSpawners;
import com.starmediadev.plugins.starspawners.managers.GiveSpawner;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.StringUtil;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpawnerCommand implements TabExecutor {

    private StarSpawners main;

    public SpawnerCommand(StarSpawners main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("give")) {
                if (!player.hasPermission("StarSpawners.admin")) return false;
                if (args.length != 4) return false;
                Player p = Bukkit.getPlayer(args[1]);
                String spawner = args[2];
                int amount = Integer.parseInt(args[3]);
                new GiveSpawner(main, player, amount, spawner.toUpperCase());
                return true;
            }
            if (args[0].equalsIgnoreCase("set")) {
                if (args.length != 2) return false;
                if (!player.hasPermission("StarSpawners.admin")) return false;
                Block block = player.getTargetBlock(null, 4);
                if (!block.getType().equals(Material.SPAWNER)) return false;
                CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
                creatureSpawner.setSpawnedType(EntityType.valueOf(args[1].toUpperCase()));

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        creatureSpawner.update();
                    }
                }.runTaskLater(main, 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> cmdList = new ArrayList<>();
        if(!sender.hasPermission("StarSpawner.admin")) return completions;
        if(args.length == 1) {
            cmdList.add("give");
            cmdList.add("set");
            StringUtil.copyPartialMatches(args[0], cmdList, completions);
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("give")) {
                cmdList.add("<player>");
            } else {
                cmdList.add("<entity>");
            }
            StringUtil.copyPartialMatches(args[1], cmdList, completions);
        }
        if(args.length == 3) {
            if(args[0].equalsIgnoreCase("give")) {
                cmdList.add("<entity>");
                StringUtil.copyPartialMatches(args[2], cmdList, completions);
            }
        }
        if(args.length == 4) {
            if(args[0].equalsIgnoreCase("give")) {
                cmdList.add("<amount>");
                StringUtil.copyPartialMatches(args[3], cmdList, completions);
            }
        }
        Collections.sort(completions);
        return completions;
    }
}