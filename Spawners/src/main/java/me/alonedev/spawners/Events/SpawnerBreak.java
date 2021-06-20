package me.alonedev.spawners.Events;

import me.alonedev.spawners.Spawners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerBreak implements Listener {

    private Spawners main;

    public SpawnerBreak(Spawners main) {
        this.main = main;
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        Player p = event.getPlayer();

        if (event.getBlock().getType().equals(Material.SPAWNER) &&
            event.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE) &&
            event.getPlayer().getGameMode() != GameMode.CREATIVE &&
            event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH))
        {

                CreatureSpawner creatureSpawner = (CreatureSpawner) event.getBlock().getState();
                ItemStack spawnerItem = new ItemStack(event.getBlock().getType(), 1);
                ItemMeta spawnerMeta = spawnerItem.getItemMeta();
                spawnerMeta.setDisplayName(ChatColor.RESET + creatureSpawner.getCreatureTypeName() + " Spawner");
                spawnerItem.setItemMeta(spawnerMeta);
                p.getInventory().addItem(spawnerItem);
        }
    }


}
