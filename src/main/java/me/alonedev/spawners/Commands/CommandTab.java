package me.alonedev.spawners.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandTab implements TabCompleter {

    public CommandTab() {
        Bukkit.getPluginCommand("spawner").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> cmdList = new ArrayList<>();

        if (!sender.hasPermission("Generators.admin")) {
            return completions;
        }

        if (args.length == 1) {
            cmdList.add("give");
            cmdList.add("set");
            StringUtil.copyPartialMatches(args[0], cmdList, completions);
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("set")) {
                cmdList.add("Entity");
            }
            else {
                cmdList.add("<Player>");
            }
            StringUtil.copyPartialMatches(args[1], cmdList, completions);
        }
        if (args.length == 3) {
            cmdList.add("<Spawner>");
            StringUtil.copyPartialMatches(args[2], cmdList, completions);
        }
        if(args.length == 4) {
            if (args[0].equalsIgnoreCase("give")) {
                cmdList.add("<Amount>");
            }
            StringUtil.copyPartialMatches(args[3], cmdList, completions);
        }

        Collections.sort(completions);
        return completions;
    }
}
