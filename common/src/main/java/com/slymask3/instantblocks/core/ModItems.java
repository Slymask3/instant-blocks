package com.slymask3.instantblocks.core;

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
        WAND_WOOD = new InstantWandItem(Tiers.WOOD);
        WAND_STONE = new InstantWandItem(Tiers.STONE);
        WAND_IRON = new InstantWandItem(Tiers.IRON);
        WAND_GOLD = new InstantWandItem(Tiers.GOLD);
        WAND_DIAMOND = new InstantWandItem(Tiers.DIAMOND);
        WAND_NETHERITE = new InstantWandItem(Tiers.NETHERITE);
    }
}