package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.Common;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Common.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootHandler {
    private static final String BLOCKS = "chests/blocks";
    private static final String STARTER = "chests/starter";

    @SubscribeEvent
    public static void register(LootTableLoadEvent event) {
        String name = event.getName().toString();
        if(Common.CONFIG.GENERATE_IN_CHESTS_BONUS() && name.equals("minecraft:chests/spawn_bonus_chest")) {
            event.getTable().addPool(injectPool(STARTER));
        }
        if(Common.CONFIG.GENERATE_IN_CHESTS() && name.contains("minecraft:chests/") && !name.equals("minecraft:chests/spawn_bonus_chest")) {
            event.getTable().addPool(injectPool(BLOCKS));
        }
    }

    private static LootPool injectPool(String name) {
        return LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(Common.MOD_ID, "inject/" + name)).setWeight(1)).setBonusRolls(UniformGenerator.between(0, 1)).name(Common.MOD_ID+"_inject_pool").build();
    }
}