package com.starmediadev.plugins.starspawners;

import com.starmediadev.plugins.starspawners.commands.CommandTab;
import com.starmediadev.plugins.starspawners.commands.SpawnerGive;
import com.starmediadev.plugins.starspawners.events.SpawnerBreak;
import com.starmediadev.plugins.starspawners.events.SpawnerPlace;
import com.starmediadev.plugins.starspawners.utils.Util;
import org.bukkit.plugin.java.JavaPlugin;

public final class StarSpawners extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new SpawnerGive(this);
        new CommandTab();
        new SpawnerBreak(this);
        new SpawnerPlace(this);
        saveDefaultConfig();

        Util.consoleMsg("=============================== \n Spawners have started! \n ===============================");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
