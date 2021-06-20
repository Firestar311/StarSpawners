package me.alonedev.spawners.Events;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpawnerPlace implements Listener {

    @EventHandler
    public void placeSpawner(BlockPlaceEvent event) {
        if(event.getBlockPlaced().getType().equals(Material.SPAWNER)) {
            CreatureSpawner creatureSpawner = (CreatureSpawner) event.getBlockPlaced().getState();
            try {
                creatureSpawner.setCreatureTypeByName(event.getItemInHand().getItemMeta().getLore().get(0));
            } catch (Exception e) {}
            creatureSpawner.update();

        }
    }
}
