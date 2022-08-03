package com.slymask3.instantblocks;

import com.slymask3.instantblocks.config.ClothConfig;
import com.slymask3.instantblocks.config.ForgeConfig;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.init.ForgeTiles;
import com.slymask3.instantblocks.init.IRegistryHelper;
import com.slymask3.instantblocks.init.Registration;
import com.slymask3.instantblocks.network.ForgePacketHandler;
import com.slymask3.instantblocks.network.IPacketHandler;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import com.slymask3.instantblocks.platform.Services;
import com.slymask3.instantblocks.util.Helper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

@Mod(Common.MOD_ID)
public class InstantBlocks {
	public InstantBlocks() {
		Common.ITEM_GROUP = new CreativeModeTab(CreativeModeTab.TABS.length,Common.MOD_ID) { public @NotNull ItemStack makeIcon() { return new ItemStack(ModBlocks.INSTANT_WOOD_HOUSE); } };
		Common.NETWORK = new PacketHandler();
		Common.TILES = new ForgeTiles();

		if(Services.PLATFORM.isModLoaded("cloth_config")) {
			ClothConfig.register();
			Common.CONFIG = ClothConfig.get();
		} else {
			ForgeConfig.init();
			Common.CONFIG = new ForgeConfig();
		}

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setupCommon);
		modEventBus.addListener(this::setupRegistry);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		ForgePacketHandler.register();
		SchematicHelper.createSchematicsDir();
	}

	private void setupRegistry(final RegisterEvent event) {
		if(event.getForgeRegistry() != null) {
			if(event.getForgeRegistry().getRegistryKey().equals(Registry.BLOCK_REGISTRY)) {
				Registration.registerBlocks(new ForgeRegistryHelper<>(event.getForgeRegistry()));
			} else if(event.getForgeRegistry().getRegistryKey().equals(Registry.ITEM_REGISTRY)) {
				Registration.registerItems(new ForgeRegistryHelper<>(event.getForgeRegistry()));
			} else if(event.getForgeRegistry().getRegistryKey().equals(Registry.BLOCK_ENTITY_TYPE_REGISTRY)) {
				Registration.registerTiles(new ForgeRegistryHelper<>(event.getForgeRegistry()));
			}
		}
	}

	public static class ForgeRegistryHelper<T> implements IRegistryHelper<T> {
		final IForgeRegistry<T> registry;
		public ForgeRegistryHelper(IForgeRegistry<T> registry) {
			this.registry = registry;
		}
		public void register(ResourceLocation name, T entry) {
			this.registry.register(name,entry);
		}
	}

	public static class PacketHandler implements IPacketHandler {
		public void sendToServer(AbstractPacket message) {
			ForgePacketHandler.INSTANCE.sendToServer(message);
		}
		public void sendToClient(Player player, AbstractPacket message) {
			if(Helper.isServer(player.getLevel())) {
				ForgePacketHandler.INSTANCE.sendTo(message, ((ServerPlayer)player).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			}
		}
		public void sendToAllAround(Level world, BlockPos pos, AbstractPacket message) {
			ForgePacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> world.getChunkAt(pos)), message);
		}
	}
}