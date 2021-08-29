package com.temmy.hammers.listeners;

import com.temmy.hammers.util.hammer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent e){
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getClickedBlock() == null) return;
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        if (hand == null) return;
        if (!hand.hasItemMeta()) return;
        List<Material> mat = new ArrayList<>(Arrays.asList(Material.DIRT,Material.GRASS_BLOCK,Material.COARSE_DIRT,
                Material.ROOTED_DIRT,Material.DIRT_PATH));
        if (mat.contains(e.getClickedBlock().getType())) {
            if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
                PersistentDataContainer data = hand.getItemMeta().getPersistentDataContainer();
                if (data.has(hammer.getTypeKey(), PersistentDataType.STRING)) {
                    if (data.get(hammer.getTypeKey(), PersistentDataType.STRING).equalsIgnoreCase("Scythe")) {
                        hammer.setBlocksToFarmLand(e.getClickedBlock(), e.getBlockFace().getDirection(), 1, 1, hand, e.getPlayer());
                    }
                }
            }
        }
    }
}
