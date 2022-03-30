package com.starmediadev.plugins.starspawners.events;

import com.starmediadev.plugins.starspawners.StarSpawners;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.block.*;

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
        plugin.getSpawnerManager().giveSpawner(player, 1, creatureSpawner.getSpawnedType().name());
    }
    
    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent e) {
        if (!(e.getBlockPlaced().getType().equals(Material.SPAWNER))) {
            return;
        }
        CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlockPlaced().getState();
        plugin.getSpawnerManager().setSpawnerType(creatureSpawner, creatureSpawner.getSpawnedType());
    }
}
