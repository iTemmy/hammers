package com.temmy.hammers;

import com.temmy.hammers.items.*;
import com.temmy.hammers.listeners.BlockBreakListener;
import com.temmy.hammers.listeners.PlayerInteractListener;
import com.temmy.hammers.listeners.PrepareSmithingListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {
    static JavaPlugin plugin = null;

    static Map<String, ItemStack> items = new HashMap<String, ItemStack>();
    static Map<String, Object> configValues = new HashMap<String, Object>();

    //TODO: Implement check on player item damage event to cancel hammers be damaged

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        configValues.put("DiamondHammer".toLowerCase(), getConfig().getInt("DiamondHammerDurability"));
        configValues.put("IronHammer".toLowerCase(), getConfig().getInt("IronHammerDurability"));
        configValues.put("DiamondExcavator".toLowerCase(), getConfig().getInt("DiamondExcavatorDurability"));
        configValues.put("IronExcavator".toLowerCase(), getConfig().getInt("IronExcavatorDurability"));
        configValues.put("DiamondScythe".toLowerCase(), getConfig().getInt("DiamondScytheDurability"));
        configValues.put("IronScythe".toLowerCase(), getConfig().getInt("IronScytheDurability"));
        new DiamondHammer();
        new IronHammer();
        new DiamondExcavator();
        new IronExcavator();
        new DiamondScythe();
        new IronScythe();
        DiamondHammer.smithingRecipe();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new PrepareSmithingListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JavaPlugin getPlugin(){return plugin;}
    public static Map<String, ItemStack> getItems(){return items;}
    public static Map<String, Object> getConfigValues(){
        return configValues;
    }
}
