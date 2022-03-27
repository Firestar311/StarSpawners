package com.starmediadev.plugins.starspawners;

import com.starmediadev.nmswrapper.NMS;
import com.starmediadev.plugins.starspawners.commands.SpawnerCommand;
import com.starmediadev.plugins.starspawners.events.BreakEvent;
import com.starmediadev.plugins.starspawners.events.PlaceEvent;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class StarSpawners extends JavaPlugin {

    private final NMS nms = NMS.getNMS(NMS.Version.MC_1_18_R2);

    @Override
    public void onEnable() {
        registerCommand("spawner", new SpawnerCommand(this));
        getServer().getPluginManager().registerEvents(new PlaceEvent(this), this);
        getServer().getPluginManager().registerEvents(new BreakEvent(this), this);
    }

    @Override
    public void onDisable() {

    }

    public NMS getNMS() {
        return nms;
    }

    public void registerCommand(String cmd, TabExecutor tabExecutor) {
        PluginCommand command = getCommand(cmd);
        command.setExecutor(tabExecutor);
        command.setTabCompleter(tabExecutor);
    }
}
