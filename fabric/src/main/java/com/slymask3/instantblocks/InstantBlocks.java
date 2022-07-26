package com.slymask3.instantblocks;

import com.slymask3.instantblocks.config.ClothConfig;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.handler.LootHandler;
import com.slymask3.instantblocks.init.FabricTiles;
import com.slymask3.instantblocks.init.IRegistryHelper;
import com.slymask3.instantblocks.init.Registration;
import com.slymask3.instantblocks.network.FabricPacketHandler;
import com.slymask3.instantblocks.network.IPacketHandler;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import com.slymask3.instantblocks.platform.Services;
import com.slymask3.instantblocks.util.Helper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InstantBlocks implements ModInitializer {
    @Override
    public void onInitialize() {
        Common.ITEM_GROUP = FabricItemGroupBuilder.build(new ResourceLocation(Common.MOD_ID, "general"), () -> new ItemStack(ModBlocks.INSTANT_WOOD_HOUSE));
        Common.NETWORK = new PacketHandler();
        Common.TILES = new FabricTiles();

        if(Services.PLATFORM.isModLoaded("cloth-config")) {
            ClothConfig.register();
            Common.CONFIG = ClothConfig.get();
        }

        Registration.registerBlocks(new FabricRegistryHelper<>(Registry.BLOCK));
        Registration.registerItems(new FabricRegistryHelper<>(Registry.ITEM));
        Registration.registerTiles(new FabricRegistryHelper<>(Registry.BLOCK_ENTITY_TYPE));

        LootHandler.register();
        FabricPacketHandler.Common.init();
        SchematicHelper.createSchematicsDir();
    }

    public static class FabricRegistryHelper<T> implements IRegistryHelper<T> {
        final Registry<T> registry;
        public FabricRegistryHelper(Registry<T> registry) {
            this.registry = registry;
        }
        public void register(ResourceLocation name, T entry) {
            Registry.register(this.registry, name, entry);
        }
    }

    public static class PacketHandler implements IPacketHandler {
        public void sendToServer(AbstractPacket message) {
            ClientPlayNetworking.send(message.getKey(), message.getBuffer());
        }
        public void sendToClient(Player player, AbstractPacket message) {
            if(Helper.isServer(player.getLevel())) {
                ServerPlayNetworking.send((ServerPlayer)player, message.getKey(), message.getBuffer());
            }
        }
    }
}