package com.slymask3.instantblocks;

import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.handler.ForgeConfig;
import com.slymask3.instantblocks.init.ForgeTiles;
import com.slymask3.instantblocks.init.Registration;
import com.slymask3.instantblocks.network.ForgePacketHandler;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import com.slymask3.instantblocks.network.IPacketHandler;
import com.slymask3.instantblocks.init.IRegistryHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

@Mod(Common.MOD_ID)
public class InstantBlocks {
	public InstantBlocks() {
		Common.ITEM_GROUP = new CreativeModeTab(CreativeModeTab.TABS.length,Common.MOD_ID) { public @NotNull ItemStack makeIcon() { return new ItemStack(ModBlocks.INSTANT_WOOD_HOUSE); } };
		Common.NETWORK = new PacketHandler();
		Common.TILES = new ForgeTiles();
		Common.init();

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ForgeConfig.init();
		modEventBus.addListener(this::setupClient);
		modEventBus.addListener(this::setupCommon);
		modEventBus.addListener((RegisterEvent e) -> {
			if(e.getForgeRegistry() != null) {
				if(e.getForgeRegistry().getRegistryKey().equals(Registry.BLOCK_REGISTRY)) {
					Registration.registerBlocks(new ForgeRegistryHelper<>(e.getForgeRegistry()));
				} else if(e.getForgeRegistry().getRegistryKey().equals(Registry.ITEM_REGISTRY)) {
					Registration.registerItems(new ForgeRegistryHelper<>(e.getForgeRegistry()));
				} else if(e.getForgeRegistry().getRegistryKey().equals(Registry.BLOCK_ENTITY_TYPE_REGISTRY)) {
					Registration.registerTiles(new ForgeRegistryHelper<>(e.getForgeRegistry()));
				}
			}
		});

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setupClient(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_GLASS_DOME, RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_ESCAPE_LADDER, RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_RAIL, RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_WATER, RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_TREE, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_LIGHT, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(ModBlocks.INSTANT_GRINDER, RenderType.translucent());
	}

	private void setupCommon(final FMLCommonSetupEvent event) {
		ForgePacketHandler.register();
		SchematicHelper.createSchematicsDir();
	}

	public static class ForgeRegistryHelper<T> implements IRegistryHelper<T> {
		IForgeRegistry<T> registry;
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
		public void sendToClient(ServerPlayer player, AbstractPacket message) {
			ForgePacketHandler.INSTANCE.sendTo(message, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}