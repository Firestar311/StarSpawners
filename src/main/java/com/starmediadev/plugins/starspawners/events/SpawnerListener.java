package com.starmediadev.plugins.starspawners.events;

import com.starmediadev.plugins.starspawners.StarSpawners;
import com.starmediadev.plugins.starspawners.managers.GiveSpawner;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnerListener implements Listener {
    private StarSpawners plugin;
    
    public SpawnerListener(StarSpawners plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!e.getBlock().getType().equals(Material.SPAWNER)) {
            return;
        }
        if (!e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
            return;
        }
        CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlock().getState();
        new GiveSpawner(plugin, player, 1, creatureSpawner.getCreatureTypeName().toUpperCase());
    }
    
    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent e) {
        if (!(e.getBlockPlaced().getType().equals(Material.SPAWNER))) {
            return;
        }
        CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlockPlaced().getState();
        creatureSpawner.setSpawnedType(EntityType.valueOf(plugin.getNMS().getNBTString(e.getItemInHand(), "spawnerType")));
        
        new BukkitRunnable() {
            
            @Override
            public void run() {
                creatureSpawner.update();
            }
        }.runTaskLater(plugin, 1);
    }
}
