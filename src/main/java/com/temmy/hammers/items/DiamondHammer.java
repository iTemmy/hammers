package com.temmy.hammers.items;

import com.temmy.hammers.Main;
import com.temmy.hammers.util.hammer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class DiamondHammer {

    public DiamondHammer(){
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.WHITE+"Diamond Hammer"));
        meta.getPersistentDataContainer().set(hammer.getMaxDurabilityKey(),
                PersistentDataType.INTEGER,
                (Integer) Main.getConfigValues().get("DiamondHammer".toLowerCase()));
        meta.getPersistentDataContainer().set(hammer.getTypeKey(),
                PersistentDataType.STRING,
                "Hammer");
        meta.getPersistentDataContainer().set(hammer.getDurabilityKey(),
                PersistentDataType.INTEGER,
                0);
        meta.getPersistentDataContainer().set(hammer.getItemKey(), PersistentDataType.STRING, "diamondhammer");
        List<Component> lore = new ArrayList<>();
        int i = ((Integer) Main.getConfigValues().get("DiamondHammer".toLowerCase())) - meta.getPersistentDataContainer().get(hammer.getDurabilityKey(), PersistentDataType.INTEGER);
        int j = (int) Main.getConfigValues().get("DiamondHammer".toLowerCase());
        lore.add(Component.text("Durability: "+i+"/"+j));
        meta.lore(lore);
        item.setItemMeta(meta);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "diamond_hammer"), item);
        recipe.shape("DSD","DSD"," S ");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(recipe);
        Main.getItems().put("diamond_hammer", item);
    }

    public static void smithingRecipe(){
        ItemStack item = new ItemStack(Main.getItems().get("diamond_hammer"));
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(ChatColor.WHITE+"Netherite Hammer"));
        item.setItemMeta(meta);
        RecipeChoice c = new RecipeChoice.ExactChoice(Bukkit.getRecipe(new NamespacedKey(Main.getPlugin(), "diamond_hammer")).getResult());
        RecipeChoice d = new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_INGOT));
        SmithingRecipe recipe = new SmithingRecipe(new NamespacedKey(Main.getPlugin(), "netherite_hammer"), item, c, d);
        Bukkit.addRecipe(recipe);
    }
}
