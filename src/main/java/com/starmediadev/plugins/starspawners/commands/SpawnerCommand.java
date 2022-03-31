package com.starmediadev.plugins.starspawners.commands;

import com.starmediadev.plugins.starmcutils.util.MCUtils;
import com.starmediadev.plugins.starspawners.StarSpawners;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.util.StringUtil;

import java.util.*;

public class SpawnerCommand implements TabExecutor {
    
    private StarSpawners plugin;
    
    public SpawnerCommand(StarSpawners plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MCUtils.color("&cOnly players can use that command"));
            return true;
        }
        
        if (!(args.length > 0)) {
            sender.sendMessage(MCUtils.color("&cYou must provide a sub command."));
            return true;
        }
    
        if (!player.hasPermission("starspawners.admin")) {
            player.sendMessage(MCUtils.color("&cYou do not have permission to use that command"));
            return true;
        }
        
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length != 4) {
                player.sendMessage(MCUtils.color("/" + label + " give <player> <entityType> <amount>"));
                return true;
            }
            Player p = Bukkit.getPlayer(args[1]);
            String spawner = args[2];
            int amount = Integer.parseInt(args[3]);
            plugin.getSpawnerManager().giveSpawner(p, amount, spawner);
            return true;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (args.length != 2) {
                player.sendMessage(MCUtils.color("/" + label + " set <entityType>"));
                return true;
            }
            Block block = player.getTargetBlock(null, 4);
            if (!block.getType().equals(Material.SPAWNER)) {
                player.sendMessage(MCUtils.color("&cThe block you are looking at is not a spawner."));
                return true;
            }
            
            CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
            plugin.getSpawnerManager().setSpawnerType(creatureSpawner, EntityType.valueOf(args[1].toUpperCase()));
            return true;
        }
        return false;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> cmdList = new ArrayList<>();
        if (!sender.hasPermission("starspawner.admin")) {
            return completions;
        }
        if (args.length == 1) {
            cmdList.add("give");
            cmdList.add("set");
            StringUtil.copyPartialMatches(args[0], cmdList, completions);
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("give")) {
                cmdList.add("<player>");
            } else {
                cmdList.add("<entity>");
            }
            StringUtil.copyPartialMatches(args[1], cmdList, completions);
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                cmdList.add("<entity>");
                StringUtil.copyPartialMatches(args[2], cmdList, completions);
            }
        }
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("give")) {
                cmdList.add("<amount>");
                StringUtil.copyPartialMatches(args[3], cmdList, completions);
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
