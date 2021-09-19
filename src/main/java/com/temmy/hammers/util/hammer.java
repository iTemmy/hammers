package com.temmy.hammers.util;

import com.temmy.hammers.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class hammer {

    static final NamespacedKey maxDurabilityKey = new NamespacedKey(Main.getPlugin(), "durability_max");
    static final NamespacedKey durabilityKey = new NamespacedKey(Main.getPlugin(), "durability");
    static final NamespacedKey typeKey = new NamespacedKey(Main.getPlugin(), "type");
    static final NamespacedKey itemKey = new NamespacedKey(Main.getPlugin(), "item");

    public static void breakBlocksNaturallyIndirection(Block block, Vector dirVector, int radius, int depth, ItemStack tool, Player player){
        for (Block relativeBlock : getBlocksInDirection(block, dirVector, radius, depth, tool, player, ClickType.LEFT)){
            int damage =0;
            Random rnd = new Random();
            if (tool.getItemMeta() == null) continue;
            if (tool.getItemMeta().hasEnchant(Enchantment.DURABILITY))
                if (rnd.nextInt(100) <= 100 / (tool.getEnchantmentLevel(Enchantment.DURABILITY) + 1)) {
                    if (relativeBlock.getType() != Material.AIR) {
                        CustomDurability.IncrementCustomDurability(tool, 1, player, tool.getItemMeta().getPersistentDataContainer().get(itemKey, PersistentDataType.STRING));
                        relativeBlock.breakNaturally(tool);
                    }
                }else {
                    relativeBlock.breakNaturally(tool);
                    continue;
                }
            else {
                if (relativeBlock.getType() != Material.AIR) {
                    CustomDurability.IncrementCustomDurability(tool, 1, player, tool.getItemMeta().getPersistentDataContainer().get(itemKey, PersistentDataType.STRING));
                    relativeBlock.breakNaturally(tool);
                }
            }
        }
    }

    public static List<Block> getBlocksInDirection(Block block, Vector dirVector, int radius, int depth, ItemStack tool, Player player, ClickType clickType) {
        List<Block> blocks = new ArrayList<>();
        List<Material> excludedBlocks = new ArrayList<>();
        List<Material> mineableBlocksDiamond = new ArrayList<>();
        List<Material> mineableBlocksIron = new ArrayList<>();
        int vecX = (int)dirVector.getX();
        int vecY = (int)dirVector.getY();
        int vecZ = (int)dirVector.getZ();
        String type = null;
        if (tool.getItemMeta().getPersistentDataContainer().has(getTypeKey(), PersistentDataType.STRING))
            type = tool.getItemMeta().getPersistentDataContainer().get(getTypeKey(), PersistentDataType.STRING);
        if (tool == null) return new ArrayList<>(null);

        switch (type){
            case "Hammer":
                excludedBlocks.addAll(Arrays.asList(Material.OBSIDIAN, Material.CRYING_OBSIDIAN,Material.SPAWNER));
                if(excludedBlocks.contains(block.getType()))
                    excludedBlocks.clear();
                mineableBlocksDiamond.addAll(Arrays.asList(Material.OBSIDIAN, Material.CRYING_OBSIDIAN));

                mineableBlocksDiamond.addAll(Arrays.asList(Material.OBSIDIAN,Material.ANCIENT_DEBRIS,
                        Material.CRYING_OBSIDIAN,Material.RESPAWN_ANCHOR,Material.ENDER_CHEST,
                        Material.ANVIL,Material.CHIPPED_ANVIL,Material.DAMAGED_ANVIL,
                        Material.COAL_BLOCK,Material.DIAMOND_BLOCK,Material.NETHERITE_BLOCK,
                        Material.EMERALD_BLOCK,Material.IRON_BLOCK,Material.REDSTONE_BLOCK,
                        Material.ENCHANTING_TABLE,Material.IRON_BARS,Material.IRON_DOOR,
                        Material.IRON_TRAPDOOR,Material.SPAWNER,Material.BELL,Material.DISPENSER,
                        Material.DROPPER,Material.OBSERVER,Material.FURNACE,Material.BLAST_FURNACE,
                        Material.SMOKER,Material.STONECUTTER,Material.LODESTONE,Material.LANTERN,
                        Material.CONDUIT,Material.GOLD_BLOCK,Material.COAL_ORE,Material.DIAMOND_ORE,
                        Material.EMERALD_ORE,Material.END_STONE,Material.GOLD_ORE,Material.HOPPER,
                        Material.IRON_ORE,Material.LAPIS_BLOCK,Material.LAPIS_ORE,Material.NETHER_QUARTZ_ORE,
                        Material.NETHER_GOLD_ORE,Material.REDSTONE_ORE,Material.GRINDSTONE,
                        Material.BONE_BLOCK,Material.BRICK_STAIRS,Material.BRICKS,Material.CAULDRON,
                        Material.COBBLESTONE,Material.COBBLESTONE_STAIRS,Material.MOSSY_COBBLESTONE,
                        Material.COBBLESTONE_WALL,Material.NETHER_BRICKS,Material.RED_NETHER_BRICKS,
                        Material.NETHER_BRICK_FENCE,Material.NETHER_BRICK_STAIRS,Material.STONE_SLAB,
                        Material.POLISHED_BLACKSTONE,Material.CYAN_CONCRETE,Material.BLACK_CONCRETE,
                        Material.BLUE_CONCRETE,Material.BROWN_CONCRETE,Material.GRAY_CONCRETE,
                        Material.GREEN_CONCRETE,Material.LIGHT_BLUE_CONCRETE,Material.LIGHT_GRAY_CONCRETE,
                        Material.LIME_CONCRETE,Material.MAGENTA_CONCRETE,Material.ORANGE_CONCRETE,
                        Material.PINK_CONCRETE,Material.PURPLE_CONCRETE,Material.RED_CONCRETE,
                        Material.WHITE_CONCRETE,Material.YELLOW_CONCRETE,Material.SHULKER_BOX,
                        Material.LIGHT_GRAY_SHULKER_BOX,Material.GRAY_SHULKER_BOX,
                        Material.PINK_SHULKER_BOX,Material.LIME_SHULKER_BOX,Material.YELLOW_SHULKER_BOX,
                        Material.LIGHT_BLUE_SHULKER_BOX,Material.MAGENTA_SHULKER_BOX,
                        Material.ORANGE_SHULKER_BOX,Material.WHITE_SHULKER_BOX,Material.BLACK_SHULKER_BOX,
                        Material.RED_SHULKER_BOX,Material.GREEN_SHULKER_BOX,Material.BLUE_SHULKER_BOX,
                        Material.PURPLE_SHULKER_BOX,Material.CYAN_SHULKER_BOX,Material.BROWN_SHULKER_BOX,
                        Material.ANDESITE,Material.DARK_PRISMARINE,Material.DIORITE,Material.GRANITE,
                        Material.PRISMARINE,Material.PRISMARINE_BRICKS,Material.STONE,Material.SMOOTH_STONE,
                        Material.PURPUR_BLOCK,Material.PURPUR_PILLAR,Material.STONE_BRICKS,
                        Material.STONE_BRICK_STAIRS,Material.BLACKSTONE,Material.CHISELED_POLISHED_BLACKSTONE,
                        Material.POLISHED_BLACKSTONE_BRICKS,Material.GILDED_BLACKSTONE,Material.ORANGE_GLAZED_TERRACOTTA,
                        Material.WHITE_GLAZED_TERRACOTTA,Material.BLACK_GLAZED_TERRACOTTA,Material.RED_GLAZED_TERRACOTTA,
                        Material.GREEN_GLAZED_TERRACOTTA,Material.BROWN_GLAZED_TERRACOTTA,Material.BLUE_GLAZED_TERRACOTTA,
                        Material.CYAN_GLAZED_TERRACOTTA,Material.PURPLE_GLAZED_TERRACOTTA,Material.GRAY_GLAZED_TERRACOTTA,
                        Material.LIGHT_GRAY_GLAZED_TERRACOTTA,Material.PINK_GLAZED_TERRACOTTA,Material.YELLOW_GLAZED_TERRACOTTA,
                        Material.LIGHT_BLUE_GLAZED_TERRACOTTA,Material.MAGENTA_GLAZED_TERRACOTTA,Material.LIME_GLAZED_TERRACOTTA,
                        Material.TERRACOTTA,Material.BASALT,Material.QUARTZ_BLOCK,Material.QUARTZ_STAIRS,Material.RED_SANDSTONE,
                        Material.RED_SANDSTONE_STAIRS,Material.SANDSTONE,Material.SANDSTONE_STAIRS,Material.CRIMSON_NYLIUM,
                        Material.WARPED_NYLIUM,Material.RAIL,Material.ACTIVATOR_RAIL,Material.DETECTOR_RAIL,Material.POWERED_RAIL,
                        Material.STONE_BUTTON,Material.MAGMA_BLOCK,Material.STONE_PRESSURE_PLATE,Material.NETHERRACK,
                        Material.END_ROD,Material.BROWN_BED,Material.GREEN_BED,Material.RED_BED,Material.MAGENTA_BED,
                        Material.WHITE_BED,Material.BLACK_BED,Material.BLUE_BED,Material.PURPLE_BED,Material.LIGHT_BLUE_BED,
                        Material.GRAY_BED,Material.PINK_BED,Material.CYAN_BED,Material.YELLOW_BED,Material.LIME_BED,
                        Material.LIGHT_GRAY_BED,Material.COBBLESTONE_SLAB, Material.SMOOTH_STONE_SLAB,
                        Material.BRICK_SLAB, Material.QUARTZ_SLAB, Material.SMOOTH_QUARTZ_SLAB, Material.BLACKSTONE_SLAB,
                        Material.POLISHED_BLACKSTONE_SLAB, Material.POLISHED_BLACKSTONE_BRICK_SLAB, Material.DIORITE_SLAB,
                        Material.POLISHED_DIORITE_SLAB, Material.ANDESITE_SLAB, Material.RED_NETHER_BRICK_SLAB,
                        Material.GRANITE_SLAB, Material.END_STONE_BRICK_SLAB, Material.POLISHED_GRANITE_SLAB,
                        Material.MOSSY_COBBLESTONE_SLAB, Material.SMOOTH_SANDSTONE_SLAB, Material.POLISHED_ANDESITE_SLAB,
                        Material.DARK_PRISMARINE_SLAB, Material.PRISMARINE_SLAB, Material.PRISMARINE_BRICK_SLAB,
                        Material.CUT_RED_SANDSTONE_SLAB, Material.RED_SANDSTONE_SLAB, Material.NETHER_BRICK_SLAB,
                        Material.STONE_BRICK_SLAB, Material.BRICK_SLAB, Material.PURPUR_SLAB,Material.SMOOTH_RED_SANDSTONE_SLAB,
                        Material.MOSSY_STONE_BRICK_SLAB,Material.RED_SANDSTONE_WALL,Material.PRISMARINE_WALL,
                        Material.BRICK_WALL,Material.MOSSY_COBBLESTONE_WALL, Material.MOSSY_STONE_BRICK_WALL,
                        Material.BLACKSTONE_WALL, Material.POLISHED_BLACKSTONE_WALL,Material.POLISHED_BLACKSTONE_BRICK_WALL,
                        Material.GRANITE_WALL,Material.DIORITE_WALL,Material.STONE_BRICK_WALL,Material.END_STONE_BRICK_WALL,
                        Material.NETHER_BRICK_WALL, Material.SANDSTONE_WALL,Material.RED_NETHER_BRICK_SLAB,
                        Material.ANDESITE_WALL,Material.CHAIN,Material.SOUL_LANTERN,Material.SMITHING_TABLE,
                        Material.POWERED_RAIL, Material.RAIL,Material.DETECTOR_RAIL,Material.ACTIVATOR_RAIL,
                        Material.BREWING_STAND,Material.PURPUR_STAIRS, Material.PRISMARINE_STAIRS,Material.PRISMARINE_BRICK_STAIRS,
                        Material.MOSSY_COBBLESTONE_STAIRS,Material.DIORITE_STAIRS,Material.END_STONE_BRICK_STAIRS,
                        Material.ANDESITE_STAIRS,Material.POLISHED_ANDESITE_STAIRS,Material.RED_SANDSTONE_STAIRS,
                        Material.SMOOTH_RED_SANDSTONE_STAIRS,Material.SANDSTONE_STAIRS, Material.DARK_PRISMARINE_STAIRS,
                        Material.MOSSY_STONE_BRICK_STAIRS, Material.RED_NETHER_BRICK_STAIRS, Material.SMOOTH_QUARTZ_STAIRS,
                        Material.POLISHED_GRANITE_STAIRS, Material.POLISHED_DIORITE_STAIRS, Material.GRANITE_STAIRS,
                        Material.CHISELED_RED_SANDSTONE,Material.SMOOTH_RED_SANDSTONE,Material.CUT_RED_SANDSTONE,
                        Material.POLISHED_BLACKSTONE_STAIRS,Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
                        Material.BLACKSTONE_STAIRS,Material.CHISELED_SANDSTONE,Material.CUT_SANDSTONE,Material.SMOOTH_SANDSTONE,
                        Material.HORN_CORAL_BLOCK,Material.FIRE_CORAL_BLOCK,Material.TUBE_CORAL_BLOCK,
                        Material.BRAIN_CORAL_BLOCK,Material.BUBBLE_CORAL_BLOCK,Material.DEAD_BRAIN_CORAL_BLOCK,
                        Material.DEAD_BUBBLE_CORAL_BLOCK,Material.DEAD_TUBE_CORAL_BLOCK,Material.DEAD_FIRE_CORAL_BLOCK,
                        Material.DEAD_HORN_CORAL_BLOCK,Material.CRACKED_POLISHED_BLACKSTONE_BRICKS,Material.SMOOTH_QUARTZ,
                        Material.QUARTZ_BRICKS,Material.QUARTZ_PILLAR,Material.CHISELED_QUARTZ_BLOCK,Material.PACKED_ICE,
                        Material.BLUE_ICE,Material.ICE,Material.BEACON,Material.INFESTED_CHISELED_STONE_BRICKS,
                        Material.INFESTED_COBBLESTONE,Material.INFESTED_CRACKED_STONE_BRICKS,
                        Material.INFESTED_MOSSY_STONE_BRICKS,Material.INFESTED_STONE,Material.INFESTED_STONE_BRICKS,
                        Material.POLISHED_BASALT, Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
                        Material.STONE_STAIRS,Material.SMOOTH_SANDSTONE_STAIRS,Material.DEEPSLATE_COAL_ORE,
                        Material.DEEPSLATE_IRON_ORE,Material.DEEPSLATE_COPPER_ORE,Material.DEEPSLATE_GOLD_ORE,
                        Material.DEEPSLATE_REDSTONE_ORE,Material.DEEPSLATE_EMERALD_ORE,Material.DEEPSLATE_LAPIS_ORE,
                        Material.DEEPSLATE_DIAMOND_ORE,Material.RAW_IRON_BLOCK,Material.RAW_COPPER_BLOCK,
                        Material.RAW_GOLD_BLOCK,Material.AMETHYST_BLOCK,Material.BUDDING_AMETHYST,
                        Material.DEEPSLATE,Material.COBBLED_DEEPSLATE,Material.POLISHED_DEEPSLATE,Material.CALCITE,
                        Material.TUFF,Material.DRIPSTONE_BLOCK,Material.COPPER_BLOCK,Material.EXPOSED_COPPER,
                        Material.WEATHERED_COPPER,Material.OXIDIZED_COPPER,Material.CUT_COPPER,Material.EXPOSED_CUT_COPPER,
                        Material.WEATHERED_CUT_COPPER,Material.OXIDIZED_CUT_COPPER,Material.CUT_COPPER_STAIRS,
                        Material.EXPOSED_CUT_COPPER_STAIRS,Material.WEATHERED_CUT_COPPER_STAIRS,Material.OXIDIZED_CUT_COPPER_STAIRS,
                        Material.CUT_COPPER_SLAB,Material.EXPOSED_CUT_COPPER_SLAB,Material.WEATHERED_CUT_COPPER_SLAB,
                        Material.OXIDIZED_CUT_COPPER_SLAB,Material.WAXED_COPPER_BLOCK,Material.WAXED_EXPOSED_COPPER,
                        Material.WAXED_WEATHERED_COPPER,Material.WAXED_OXIDIZED_COPPER,Material.WAXED_CUT_COPPER,
                        Material.WAXED_EXPOSED_CUT_COPPER,Material.WAXED_WEATHERED_CUT_COPPER,Material.WAXED_OXIDIZED_CUT_COPPER,
                        Material.WAXED_CUT_COPPER_STAIRS,Material.WAXED_EXPOSED_CUT_COPPER_STAIRS,Material.COPPER_ORE,
                        Material.WAXED_WEATHERED_CUT_COPPER_STAIRS,Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS,
                        Material.WAXED_CUT_COPPER_SLAB,Material.WAXED_EXPOSED_CUT_COPPER_SLAB,Material.WAXED_WEATHERED_CUT_COPPER_SLAB,
                        Material.WAXED_OXIDIZED_CUT_COPPER_SLAB,Material.SMOOTH_BASALT,Material.DEEPSLATE_BRICKS,
                        Material.CRACKED_DEEPSLATE_BRICKS,Material.DEEPSLATE_TILES,Material.CRACKED_DEEPSLATE_TILES,
                        Material.CHISELED_DEEPSLATE,Material.COBBLED_DEEPSLATE_STAIRS,Material.POLISHED_DEEPSLATE_STAIRS,
                        Material.DEEPSLATE_BRICK_STAIRS,Material.DEEPSLATE_TILE_STAIRS,Material.COBBLED_DEEPSLATE_SLAB,
                        Material.POLISHED_DEEPSLATE_SLAB,Material.DEEPSLATE_BRICK_SLAB,Material.DEEPSLATE_TILE_SLAB,
                        Material.INFESTED_DEEPSLATE,Material.COBBLED_DEEPSLATE_WALL,Material.POLISHED_DEEPSLATE_WALL,
                        Material.DEEPSLATE_BRICK_WALL,Material.DEEPSLATE_TILE_WALL
                ));

                mineableBlocksIron.addAll(Arrays.asList(Material.ENDER_CHEST,Material.ANVIL,Material.CHIPPED_ANVIL,
                        Material.DAMAGED_ANVIL,Material.COAL_BLOCK,Material.DIAMOND_BLOCK,Material.EMERALD_BLOCK,
                        Material.IRON_BLOCK,Material.REDSTONE_BLOCK,Material.ENCHANTING_TABLE,Material.IRON_BARS,
                        Material.IRON_DOOR,Material.IRON_TRAPDOOR,Material.SPAWNER,Material.BELL,Material.DISPENSER,
                        Material.DROPPER,Material.OBSERVER,Material.FURNACE,Material.BLAST_FURNACE,
                        Material.SMOKER,Material.STONECUTTER,Material.LODESTONE,Material.LANTERN,
                        Material.CONDUIT,Material.GOLD_BLOCK,Material.COAL_ORE,Material.DIAMOND_ORE,
                        Material.EMERALD_ORE,Material.END_STONE,Material.GOLD_ORE,Material.HOPPER,
                        Material.IRON_ORE,Material.LAPIS_BLOCK,Material.LAPIS_ORE,Material.NETHER_QUARTZ_ORE,
                        Material.NETHER_GOLD_ORE,Material.REDSTONE_ORE,Material.GRINDSTONE,
                        Material.BONE_BLOCK,Material.BRICK_STAIRS,Material.BRICKS,Material.CAULDRON,
                        Material.COBBLESTONE,Material.COBBLESTONE_STAIRS,Material.MOSSY_COBBLESTONE,
                        Material.COBBLESTONE_WALL,Material.NETHER_BRICKS,Material.RED_NETHER_BRICKS,
                        Material.NETHER_BRICK_FENCE,Material.NETHER_BRICK_STAIRS,Material.STONE_SLAB,
                        Material.POLISHED_BLACKSTONE,Material.CYAN_CONCRETE,Material.BLACK_CONCRETE,
                        Material.BLUE_CONCRETE,Material.BROWN_CONCRETE,Material.GRAY_CONCRETE,
                        Material.GREEN_CONCRETE,Material.LIGHT_BLUE_CONCRETE,Material.LIGHT_GRAY_CONCRETE,
                        Material.LIME_CONCRETE,Material.MAGENTA_CONCRETE,Material.ORANGE_CONCRETE,
                        Material.PINK_CONCRETE,Material.PURPLE_CONCRETE,Material.RED_CONCRETE,
                        Material.WHITE_CONCRETE,Material.YELLOW_CONCRETE,Material.SHULKER_BOX,
                        Material.LIGHT_GRAY_SHULKER_BOX,Material.GRAY_SHULKER_BOX,
                        Material.PINK_SHULKER_BOX,Material.LIME_SHULKER_BOX,Material.YELLOW_SHULKER_BOX,
                        Material.LIGHT_BLUE_SHULKER_BOX,Material.MAGENTA_SHULKER_BOX,
                        Material.ORANGE_SHULKER_BOX,Material.WHITE_SHULKER_BOX,Material.BLACK_SHULKER_BOX,
                        Material.RED_SHULKER_BOX,Material.GREEN_SHULKER_BOX,Material.BLUE_SHULKER_BOX,
                        Material.PURPLE_SHULKER_BOX,Material.CYAN_SHULKER_BOX,Material.BROWN_SHULKER_BOX,
                        Material.ANDESITE,Material.DARK_PRISMARINE,Material.DIORITE,Material.GRANITE,
                        Material.PRISMARINE,Material.PRISMARINE_BRICKS,Material.STONE,Material.SMOOTH_STONE,
                        Material.PURPUR_BLOCK,Material.PURPUR_PILLAR,Material.STONE_BRICKS,
                        Material.STONE_BRICK_STAIRS,Material.BLACKSTONE,Material.CHISELED_POLISHED_BLACKSTONE,
                        Material.POLISHED_BLACKSTONE_BRICKS,Material.GILDED_BLACKSTONE,Material.ORANGE_GLAZED_TERRACOTTA,
                        Material.WHITE_GLAZED_TERRACOTTA,Material.BLACK_GLAZED_TERRACOTTA,Material.RED_GLAZED_TERRACOTTA,
                        Material.GREEN_GLAZED_TERRACOTTA,Material.BROWN_GLAZED_TERRACOTTA,Material.BLUE_GLAZED_TERRACOTTA,
                        Material.CYAN_GLAZED_TERRACOTTA,Material.PURPLE_GLAZED_TERRACOTTA,Material.GRAY_GLAZED_TERRACOTTA,
                        Material.LIGHT_GRAY_GLAZED_TERRACOTTA,Material.PINK_GLAZED_TERRACOTTA,Material.YELLOW_GLAZED_TERRACOTTA,
                        Material.LIGHT_BLUE_GLAZED_TERRACOTTA,Material.MAGENTA_GLAZED_TERRACOTTA,Material.LIME_GLAZED_TERRACOTTA,
                        Material.TERRACOTTA,Material.BASALT,Material.QUARTZ_BLOCK,Material.QUARTZ_STAIRS,Material.RED_SANDSTONE,
                        Material.RED_SANDSTONE_STAIRS,Material.SANDSTONE,Material.SANDSTONE_STAIRS,Material.CRIMSON_NYLIUM,
                        Material.WARPED_NYLIUM,Material.RAIL,Material.ACTIVATOR_RAIL,Material.DETECTOR_RAIL,Material.POWERED_RAIL,
                        Material.STONE_BUTTON,Material.MAGMA_BLOCK,Material.STONE_PRESSURE_PLATE,Material.NETHERRACK,
                        Material.END_ROD,Material.BROWN_BED,Material.GREEN_BED,Material.RED_BED,Material.MAGENTA_BED,
                        Material.WHITE_BED,Material.BLACK_BED,Material.BLUE_BED,Material.PURPLE_BED,Material.LIGHT_BLUE_BED,
                        Material.GRAY_BED,Material.PINK_BED,Material.CYAN_BED,Material.YELLOW_BED,Material.LIME_BED,
                        Material.LIGHT_GRAY_BED,Material.DEEPSLATE_COAL_ORE,Material.COPPER_ORE,
                        Material.DEEPSLATE_IRON_ORE,Material.DEEPSLATE_COPPER_ORE,Material.DEEPSLATE_GOLD_ORE,
                        Material.DEEPSLATE_REDSTONE_ORE,Material.DEEPSLATE_EMERALD_ORE,Material.DEEPSLATE_LAPIS_ORE,
                        Material.DEEPSLATE_DIAMOND_ORE,Material.RAW_IRON_BLOCK,Material.RAW_COPPER_BLOCK,
                        Material.RAW_GOLD_BLOCK,Material.AMETHYST_BLOCK,Material.BUDDING_AMETHYST,
                        Material.DEEPSLATE,Material.COBBLED_DEEPSLATE,Material.POLISHED_DEEPSLATE,Material.CALCITE,
                        Material.TUFF,Material.DRIPSTONE_BLOCK,Material.COPPER_BLOCK,Material.EXPOSED_COPPER,
                        Material.WEATHERED_COPPER,Material.OXIDIZED_COPPER,Material.CUT_COPPER,Material.EXPOSED_CUT_COPPER,
                        Material.WEATHERED_CUT_COPPER,Material.OXIDIZED_CUT_COPPER,Material.CUT_COPPER_STAIRS,
                        Material.EXPOSED_CUT_COPPER_STAIRS,Material.WEATHERED_CUT_COPPER_STAIRS,Material.OXIDIZED_CUT_COPPER_STAIRS,
                        Material.CUT_COPPER_SLAB,Material.EXPOSED_CUT_COPPER_SLAB,Material.WEATHERED_CUT_COPPER_SLAB,
                        Material.OXIDIZED_CUT_COPPER_SLAB,Material.WAXED_COPPER_BLOCK,Material.WAXED_EXPOSED_COPPER,
                        Material.WAXED_WEATHERED_COPPER,Material.WAXED_OXIDIZED_COPPER,Material.WAXED_CUT_COPPER,
                        Material.WAXED_EXPOSED_CUT_COPPER,Material.WAXED_WEATHERED_CUT_COPPER,Material.WAXED_OXIDIZED_CUT_COPPER,
                        Material.WAXED_CUT_COPPER_STAIRS,Material.WAXED_EXPOSED_CUT_COPPER_STAIRS,
                        Material.WAXED_WEATHERED_CUT_COPPER_STAIRS,Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS,
                        Material.WAXED_CUT_COPPER_SLAB,Material.WAXED_EXPOSED_CUT_COPPER_SLAB,Material.WAXED_WEATHERED_CUT_COPPER_SLAB,
                        Material.WAXED_OXIDIZED_CUT_COPPER_SLAB,Material.SMOOTH_BASALT,Material.DEEPSLATE_BRICKS,
                        Material.CRACKED_DEEPSLATE_BRICKS,Material.DEEPSLATE_TILES,Material.CRACKED_DEEPSLATE_TILES,
                        Material.CHISELED_DEEPSLATE,Material.COBBLED_DEEPSLATE_STAIRS,Material.POLISHED_DEEPSLATE_STAIRS,
                        Material.DEEPSLATE_BRICK_STAIRS,Material.DEEPSLATE_TILE_STAIRS,Material.COBBLED_DEEPSLATE_SLAB,
                        Material.POLISHED_DEEPSLATE_SLAB,Material.DEEPSLATE_BRICK_SLAB,Material.DEEPSLATE_TILE_SLAB,
                        Material.INFESTED_DEEPSLATE,Material.COBBLED_DEEPSLATE_WALL,Material.POLISHED_DEEPSLATE_WALL,
                        Material.DEEPSLATE_BRICK_WALL,Material.DEEPSLATE_TILE_WALL));

                for(int i = 0; i < depth; i++){
                    for(int x = -radius; x <= radius; x++)
                        for(int y = -radius; y <= radius; y++)
                            for(int z = -radius; z <= radius; z++){
                                Block relativeBlock = block.getRelative(x * boolToInt(vecX == 0), y * boolToInt(vecY == 0), z * boolToInt(vecZ == 0));
                                if (excludedBlocks.contains(block.getType())){ blocks.clear(); return blocks;}
                                if(relativeBlock.getType().isSolid() && player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE && mineableBlocksDiamond.contains(relativeBlock.getType())){
                                    blocks.add(relativeBlock);
                                }else if(!excludedBlocks.contains(relativeBlock.getType()) && relativeBlock.getType().isSolid() && tool.getType()==Material.IRON_PICKAXE &&
                                        mineableBlocksIron.contains(relativeBlock.getType())){
                                    blocks.add(relativeBlock);
                                }else if (relativeBlock.getType().isSolid() && player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_PICKAXE && mineableBlocksDiamond.contains(relativeBlock.getType())){
                                    blocks.add(relativeBlock);
                                }
                            }
                    block = block.getRelative(-vecX, -vecY, -vecZ);

                }

                break;
            case "Excavator":

                excludedBlocks.addAll(Arrays.asList(Material.DIRT,Material.GRASS_BLOCK,Material.DIRT_PATH,
                        Material.PODZOL,Material.COARSE_DIRT,Material.SAND,Material.RED_SAND,
                        Material.GRAVEL,Material.SOUL_SAND,Material.SOUL_SOIL,Material.MYCELIUM,
                        Material.PINK_CONCRETE_POWDER,Material.PURPLE_CONCRETE_POWDER,
                        Material.BLACK_CONCRETE_POWDER,Material.BLUE_CONCRETE_POWDER,
                        Material.CYAN_CONCRETE_POWDER,Material.BROWN_CONCRETE_POWDER,
                        Material.GRAY_CONCRETE_POWDER,Material.GREEN_CONCRETE_POWDER,
                        Material.LIGHT_BLUE_CONCRETE_POWDER,Material.LIGHT_GRAY_CONCRETE_POWDER,
                        Material.LIME_CONCRETE_POWDER,Material.MAGENTA_CONCRETE_POWDER,
                        Material.ORANGE_CONCRETE_POWDER,Material.RED_CONCRETE_POWDER,
                        Material.WHITE_CONCRETE_POWDER,Material.YELLOW_CONCRETE_POWDER,
                        Material.MOSS_BLOCK, Material.MOSS_CARPET));

                for(int i = 0; i < depth; i++){
                    for(int x = -radius; x <= radius; x++)
                        for(int y = -radius; y <= radius; y++)
                            for(int z = -radius; z <= radius; z++){
                                Block relativeBlock = block.getRelative(x * boolToInt(vecX == 0), y * boolToInt(vecY == 0), z * boolToInt(vecZ == 0));
                                if(excludedBlocks.contains(relativeBlock.getType()))
                                    blocks.add(relativeBlock);
                            }
                    block = block.getRelative(-vecX, -vecY, -vecZ);
                }
                break;
            case "Scythe":
                switch (clickType) {
                    case LEFT -> {
                        excludedBlocks.addAll(Arrays.asList(Material.WHEAT, Material.BEETROOTS, Material.CARROTS,
                                Material.POTATOES, Material.ACACIA_LEAVES,Material.AZALEA_LEAVES, Material.BIRCH_LEAVES,
                                Material.DARK_OAK_LEAVES, Material.FLOWERING_AZALEA_LEAVES, Material.JUNGLE_LEAVES,
                                Material.OAK_LEAVES, Material.SPRUCE_LEAVES));
                        for (int i = 0; i < depth; i++) {
                            for (int x = -radius; x <= radius; x++)
                                for (int y = -radius; y <= radius; y++)
                                    for (int z = -radius; z <= radius; z++) {
                                        Block relativeBlock = block.getRelative(x * boolToInt(vecX == 0), y * boolToInt(vecY == 0), z * boolToInt(vecZ == 0));
                                        if (excludedBlocks.contains(relativeBlock.getType()))
                                            blocks.add(relativeBlock);
                                    }
                            block = block.getRelative(-vecX, -vecY, -vecZ);
                        }
                        break;
                    }
                    case RIGHT -> {
                        excludedBlocks.addAll(Arrays.asList(Material.DIRT, Material.GRASS_BLOCK, Material.COARSE_DIRT,
                                Material.ROOTED_DIRT, Material.DIRT_PATH));
                        for (int i = 0; i < depth; i++) {
                            for (int x = -radius; x <= radius; x++)
                                for (int y = -radius; y <= radius; y++)
                                    for (int z = -radius; z <= radius; z++) {
                                        Block relativeBlock = block.getRelative(x * boolToInt(vecX == 0), y * boolToInt(vecY == 0), z * boolToInt(vecZ == 0));
                                        if (excludedBlocks.contains(relativeBlock.getType()))
                                            blocks.add(relativeBlock);
                                    }
                            block = block.getRelative(-vecX, -vecY, -vecZ);
                        }
                        break;
                    }
                }
                break;
            default:
                return null;
            }
        return blocks;
    }

    private static int boolToInt(boolean b) {
            return Boolean.compare(b, false);
    }
    public static NamespacedKey getTypeKey(){return typeKey;}
    public static NamespacedKey getMaxDurabilityKey(){return maxDurabilityKey;}
    public static NamespacedKey getDurabilityKey(){return durabilityKey;}
    public static NamespacedKey getItemKey(){return itemKey;}

    public static void setBlocksToFarmLand(Block block, Vector dirVector, int radius, int depth, ItemStack tool, Player player){
        for (Block relativeBlock : getBlocksInDirection(block, dirVector, radius, depth, tool, player, ClickType.RIGHT)){
            int damage =0;
            Random rnd = new Random();
            if (tool.getItemMeta() == null) continue;
            if (tool.getItemMeta().hasEnchant(Enchantment.DURABILITY))
                if (rnd.nextInt(100) <= 100 / (tool.getEnchantmentLevel(Enchantment.DURABILITY) + 1)) {
                    if (relativeBlock.getType() != Material.AIR)
                        CustomDurability.IncrementCustomDurability(tool, 1, player, tool.getItemMeta().getPersistentDataContainer().get(itemKey, PersistentDataType.STRING));
                }else continue;
            else {
                if (relativeBlock.getType() != Material.AIR)
                    CustomDurability.IncrementCustomDurability(tool, 1, player, tool.getItemMeta().getPersistentDataContainer().get(itemKey, PersistentDataType.STRING));
            }
            if (relativeBlock.getType() != Material.AIR) {
                relativeBlock.setType(Material.FARMLAND);
            }
        }
    }
}
