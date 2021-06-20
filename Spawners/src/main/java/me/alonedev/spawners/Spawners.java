package me.alonedev.spawners;

import me.alonedev.spawners.Commands.CommandTab;
import me.alonedev.spawners.Commands.SpawnerGive;
import me.alonedev.spawners.Events.SpawnerBreak;
import me.alonedev.spawners.Utils.Util;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spawners extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new SpawnerGive();
        new CommandTab();
        new SpawnerBreak(this);
        saveDefaultConfig();

        Util.consoleMsg("=============================== \n Spawners have started! \n ===============================");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
