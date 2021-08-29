package com.temmy.hammers.util;

import com.temmy.hammers.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomDurability {

    public static void IncrementCustomDurability(ItemStack item, int dmg, Player player, String type){
        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.lore() == null) return;
        int maxDurability = (int) Main.getConfigValues().get(type);
        int remainingDurability = maxDurability - meta.getPersistentDataContainer().get(hammer.durabilityKey, PersistentDataType.INTEGER);
        for (int i =0; i<= meta.lore().size()-1; i++) {
            if (item.lore().get(i).toString().contains("Durability")) {
                List<Component> lore = meta.lore();
                meta.getPersistentDataContainer().set(hammer.getDurabilityKey(),
                        PersistentDataType.INTEGER,
                        meta.getPersistentDataContainer().get(hammer.getDurabilityKey(), PersistentDataType.INTEGER)+1);
                lore.set(i, Component.text("Durability: "+remainingDurability+"/"+maxDurability));
                meta.lore(lore);
            }
        }
        if (meta.getPersistentDataContainer().get(hammer.getDurabilityKey(), PersistentDataType.INTEGER) > (Integer) Main.getConfigValues().get(type)) {
            item.setAmount(0);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
        }else if (remainingDurability < 50)
            player.sendActionBar(Component.text("Your " + meta.getPersistentDataContainer().get(hammer.itemKey, PersistentDataType.STRING) + " will break soon " + remainingDurability + "/" + maxDurability));
        item.setItemMeta(meta);
    }

    public static void incrementDurability(ItemStack item, int dmg, Player player){
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable)
            ((Damageable) meta).setDamage(((Damageable) meta).getDamage()+dmg);
        if (((Damageable) meta).getDamage() >= item.getType().getMaxDurability()) {
            item.setAmount(0);
            player.playSound(player.getLocation(), item.getType().createBlockData().getSoundGroup().getBreakSound(), 1,1);
        }
        item.setItemMeta(meta);
    }

    static Logger log = Bukkit.getLogger();

    public static void createCustomDurability(ItemStack tool){
        double vanilla = tool.getType().getMaxDurability();
        double custom = 0;
        if (!tool.hasItemMeta()) return;
        if (!tool.getItemMeta().getPersistentDataContainer().has(hammer.maxDurabilityKey, PersistentDataType.INTEGER)) return;
        custom = tool.getItemMeta().getPersistentDataContainer().get(hammer.maxDurabilityKey, PersistentDataType.INTEGER);
        double ratio = custom/vanilla;
    }
}
