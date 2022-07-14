package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.core.Config;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class LootHandler {
    private static final String BLOCKS = "chests/blocks";
    private static final String STARTER = "chests/starter";

    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            String name = id.toString();
            if(Config.Common.GENERATE_IN_CHESTS_BONUS && name.equals("minecraft:chests/spawn_bonus_chest")) {
                tableBuilder.pool(injectPool(STARTER));
            }
            if(Config.Common.GENERATE_IN_CHESTS && name.contains("minecraft:chests/") && !name.equals("minecraft:chests/spawn_bonus_chest")) {
                tableBuilder.pool(injectPool(BLOCKS));
            }
        });
    }

    private static LootPool injectPool(String name) {
        return LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(Common.MOD_ID, "inject/" + name)).setWeight(1)).setBonusRolls(UniformGenerator.between(0, 1)).build();
    }
}