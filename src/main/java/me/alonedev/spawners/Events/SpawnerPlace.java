package me.alonedev.spawners.Events;

import me.alonedev.spawners.Spawners;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnerPlace implements Listener {

    private Spawners main;

    public SpawnerPlace(Spawners main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void placeSpawner(BlockPlaceEvent event) {
        if(event.getBlockPlaced().getType().equals(Material.SPAWNER)) {
            CreatureSpawner creatureSpawner = (CreatureSpawner) event.getBlockPlaced().getState();
            try {
                creatureSpawner.setSpawnedType(EntityType.valueOf(event.getItemInHand().getItemMeta().getLore().get(0)));
            } catch (Exception e) {}
            new BukkitRunnable() {

                @Override
                public void run() {
                    creatureSpawner.update();
                }
            }.runTaskLater(main, 1);

        }
    }
}
