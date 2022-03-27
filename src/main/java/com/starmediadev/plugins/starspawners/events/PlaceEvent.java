package com.starmediadev.plugins.starspawners.events;

import com.starmediadev.plugins.starspawners.StarSpawners;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PlaceEvent implements Listener {

    private StarSpawners main;

    public PlaceEvent(StarSpawners main) {
        this.main = main;
    }

    @EventHandler
    public void onPlaceEvent(BlockPlaceEvent e) {
        if (!(e.getBlockPlaced().getType().equals(Material.SPAWNER))) return;
        CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlockPlaced().getState();
        creatureSpawner.setSpawnedType(EntityType.valueOf(main.getNMS().getNBTString(e.getItemInHand(), "spawnerType")));

        new BukkitRunnable() {

            @Override
            public void run() {
                creatureSpawner.update();
            }
        }.runTaskLater(main, 1);

    }

}
