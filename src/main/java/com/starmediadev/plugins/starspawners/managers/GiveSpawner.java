package com.starmediadev.plugins.starspawners.managers;

import com.starmediadev.plugins.starspawners.StarSpawners;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveSpawner {

    private StarSpawners main;

    public GiveSpawner(StarSpawners main, Player player, int amount, String spawnerName){
        this.main = main;
        ItemStack spawner = new ItemStack(Material.SPAWNER,amount);
        spawner = main.getNMS().addNBTString(spawner, "spawnerType", spawnerName);
        ItemMeta spawnerMeta = spawner.getItemMeta();
        spawnerMeta.setDisplayName(spawnerName + " spawner");
        spawner.setItemMeta(spawnerMeta);
        player.getInventory().addItem(spawner);
    }


}
