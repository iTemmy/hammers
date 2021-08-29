package com.temmy.hammers.items;

import com.temmy.hammers.Main;
import com.temmy.hammers.util.hammer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class IronExcavator {
    public IronExcavator(){
        ItemStack item = new ItemStack(Material.IRON_SHOVEL);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.WHITE+"Iron Excavator"));
        meta.getPersistentDataContainer().set(hammer.getMaxDurabilityKey(),
                PersistentDataType.INTEGER,
                (Integer) Main.getConfigValues().get("IronExcavator".toLowerCase()));
        meta.getPersistentDataContainer().set(hammer.getTypeKey(),
                PersistentDataType.STRING,
                "Excavator");
        meta.getPersistentDataContainer().set(hammer.getDurabilityKey(),
                PersistentDataType.INTEGER,
                0);
        meta.getPersistentDataContainer().set(hammer.getItemKey(), PersistentDataType.STRING, "ironexcavator");
        List<Component> lore = new ArrayList<>();
        int i = ((Integer) Main.getConfigValues().get("IronExcavator".toLowerCase())) - meta.getPersistentDataContainer().get(hammer.getDurabilityKey(), PersistentDataType.INTEGER);
        int j = (int) Main.getConfigValues().get("IronExcavator".toLowerCase());
        lore.add(Component.text("Durability: "+i+"/"+j));
        meta.lore(lore);
        item.setItemMeta(meta);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "iron_excavator"), item);
        recipe.shape("DDD","DSD"," S ");
        recipe.setIngredient('D', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(recipe);
        Main.getItems().put("iron_excavator", item);
    }
}
