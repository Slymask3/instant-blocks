package com.slymask3.instantblocks.core;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.item.InstantWandItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public class ModItems {
    public static Item WAND_WOOD;
    public static Item WAND_STONE;
    public static Item WAND_IRON;
    public static Item WAND_GOLD;
    public static Item WAND_DIAMOND;
    public static Item WAND_NETHERITE;

    public static void init() {
        Item.Properties properties = new Item.Properties().tab(Common.ITEM_GROUP);
        WAND_WOOD = new InstantWandItem(Tiers.WOOD,properties);
        WAND_STONE = new InstantWandItem(Tiers.STONE,properties);
        WAND_IRON = new InstantWandItem(Tiers.IRON,properties);
        WAND_GOLD = new InstantWandItem(Tiers.GOLD,properties);
        WAND_DIAMOND = new InstantWandItem(Tiers.DIAMOND,properties);
        WAND_NETHERITE = new InstantWandItem(Tiers.NETHERITE,properties);
    }
}