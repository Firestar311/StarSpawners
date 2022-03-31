package com.starmediadev.plugins.starspawners.managers;

import com.starmediadev.plugins.starmcutils.util.MCUtils;
import com.starmediadev.plugins.starspawners.StarSpawners;
import org.bukkit.*;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerManager {
    private StarSpawners plugin;
    
    public SpawnerManager(StarSpawners plugin) {
        this.plugin = plugin;
    }
    
    public void giveSpawner(Player player, int amount, String spawnerName){
        ItemStack spawner = new ItemStack(Material.SPAWNER,amount);
        spawner = plugin.getNMS().addNBTString(spawner, "spawnerType", spawnerName);
        ItemMeta spawnerMeta = spawner.getItemMeta();
        spawnerMeta.setDisplayName(MCUtils.color("&d" + spawnerName + " spawner"));
        spawner.setItemMeta(spawnerMeta);
        player.getInventory().addItem(spawner);
    }
    
    public void setSpawnerType(CreatureSpawner creatureSpawner, EntityType entityType) {
        creatureSpawner.setSpawnedType(entityType);
        Bukkit.getScheduler().runTaskLater(plugin, () -> creatureSpawner.update(), 1L);
    }
}
