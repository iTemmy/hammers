package com.temmy.hammers.listeners;

import com.temmy.hammers.util.CustomDurability;
import com.temmy.hammers.util.hammer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;

public class BlockBreakListener implements Listener {
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();
        if (hand.getItemMeta() == null || hand == null) return;
        CustomDurability.createCustomDurability(hand);
        PersistentDataContainer data = hand.getItemMeta().getPersistentDataContainer();
        if (hand.getType() != Material.AIR){
            if (data.has(hammer.getTypeKey(), PersistentDataType.STRING)){
                if (data.get(hammer.getTypeKey(), PersistentDataType.STRING).equalsIgnoreCase("hammer")){
                    RayTraceResult result = player.rayTraceBlocks(6);
                    hammer.breakBlocksNaturallyIndirection(e.getBlock(), result.getHitBlockFace().getDirection(), 1, 1, hand, player);
                }else if (data.get(hammer.getTypeKey(), PersistentDataType.STRING).equalsIgnoreCase("excavator")){
                    RayTraceResult result = player.rayTraceBlocks(6);
                    hammer.breakBlocksNaturallyIndirection(e.getBlock(), result.getHitBlockFace().getDirection(), 1,1, hand, player);
                }else if (data.get(hammer.getTypeKey(), PersistentDataType.STRING).equalsIgnoreCase("Scythe")){
                    RayTraceResult result = player.rayTraceBlocks(6);
                    hammer.breakBlocksNaturallyIndirection(e.getBlock(), result.getHitBlockFace().getDirection(), 1,1, hand, player);
                }
            }
        }
    }
}
