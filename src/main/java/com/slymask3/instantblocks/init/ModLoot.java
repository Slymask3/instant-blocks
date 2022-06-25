package com.slymask3.instantblocks.init;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InstantBlocks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModLoot {
    private static final String BLOCKS = "chests/blocks";
    private static final String STARTER = "chests/starter";

    @SubscribeEvent
    public static void register(LootTableLoadEvent event) {
        String name = event.getName().toString();
        if(Config.Common.GENERATE_IN_CHESTS_BONUS.get() && name.equals("minecraft:chests/spawn_bonus_chest")) {
            event.getTable().addPool(injectPool(STARTER));
        } else if(Config.Common.GENERATE_IN_CHESTS.get() && name.contains("minecraft:chests/")) {
            event.getTable().addPool(injectPool(BLOCKS));
        }
    }

    private static LootPool injectPool(String name) {
        return LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(InstantBlocks.MOD_ID, "inject/" + name)).setWeight(1)).setBonusRolls(UniformGenerator.between(0, 1)).name(InstantBlocks.MOD_ID+"_inject_pool").build();
    }
}