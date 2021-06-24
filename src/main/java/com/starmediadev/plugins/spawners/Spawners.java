package com.starmediadev.plugins.spawners;

import com.starmediadev.plugins.spawners.commands.CommandTab;
import com.starmediadev.plugins.spawners.commands.SpawnerGive;
import com.starmediadev.plugins.spawners.events.SpawnerBreak;
import com.starmediadev.plugins.spawners.events.SpawnerPlace;
import com.starmediadev.plugins.spawners.utils.Util;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spawners extends JavaPlugin {

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
