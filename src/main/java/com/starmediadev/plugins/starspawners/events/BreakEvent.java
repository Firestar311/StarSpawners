package com.starmediadev.plugins.starspawners.events;

import com.starmediadev.plugins.starspawners.StarSpawners;
import com.starmediadev.plugins.starspawners.managers.GiveSpawner;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvent implements Listener {

    private StarSpawners main;

    public BreakEvent(StarSpawners main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if(!e.getBlock().getType().equals(Material.SPAWNER)) return;
        if(!e.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) return;
        CreatureSpawner creatureSpawner = (CreatureSpawner) e.getBlock().getState();
        new GiveSpawner(main, player, 1, creatureSpawner.getCreatureTypeName().toUpperCase());
    }

}
