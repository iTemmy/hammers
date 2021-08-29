package com.temmy.hammers.listeners;

import com.temmy.hammers.util.hammer;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class PrepareSmithingListener implements Listener {
    @EventHandler
    public static void onPrepareSmithing(PrepareSmithingEvent e){
        SmithingInventory inv = e.getInventory();

        ItemStack tool = inv.getItem(0);
        ItemStack modifier = inv.getItem(1);

        if (tool == null || modifier == null) return;

        if (tool.getType() != Material.DIAMOND_PICKAXE || modifier.getType() != Material.NETHERITE_INGOT) return;
        if (tool.getItemMeta() != null) return;
        if (!tool.getItemMeta().getPersistentDataContainer().has(hammer.getTypeKey(), PersistentDataType.STRING)) return;

        ItemStack result = new ItemStack(tool);
        result.setType(Material.NETHERITE_PICKAXE);
        ItemMeta meta = tool.getItemMeta();
        meta.displayName(Component.text(ChatColor.WHITE+"Netherite Hammer"));
        tool.setItemMeta(meta);
        e.setResult(result);
        List<HumanEntity> viewers = e.getViewers();
        viewers.forEach(humanEntity -> ((Player)humanEntity).updateInventory());
    }
}
