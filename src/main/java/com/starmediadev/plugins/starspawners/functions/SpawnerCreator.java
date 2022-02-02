package com.starmediadev.plugins.starspawners.functions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpawnerCreator {

    public void giveSpawner(Player player, String spawner, int amount) {
        ItemStack item = new ItemStack(Material.SPAWNER, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',"&c" + spawner + " &eSpawner"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(spawner.toUpperCase());
        meta.setLore(lore);

        item.setItemMeta(meta);
        player.getInventory().addItem(item);




    }

}
