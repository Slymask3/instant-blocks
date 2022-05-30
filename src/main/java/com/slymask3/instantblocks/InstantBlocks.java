package com.slymask3.instantblocks;

import com.slymask3.instantblocks.command.CommandInstantBlocks;
import com.slymask3.instantblocks.gui.GuiHandler;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.Loot;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.init.Recipes;
import com.slymask3.instantblocks.network.PacketPipeline;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.*;
import com.slymask3.instantblocks.util.LogHelper;
import com.slymask3.instantblocks.util.SchematicHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory=Reference.GUI_FACTORY_CLASS)
public class InstantBlocks {
	@Instance(Reference.MOD_ID)
	public static InstantBlocks instance = new InstantBlocks();
	
	public static final PacketPipeline packetPipeline = new PacketPipeline();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new Config());
		TileEntity.addMapping(TileEntityColor.class, "TileEntityColor");
		TileEntity.addMapping(TileEntityStatue.class, "TileEntityStatue");
		TileEntity.addMapping(TileEntityHarvest.class, "TileEntityHarvest");
		TileEntity.addMapping(TileEntitySkydive.class, "TileEntitySkydive");
		TileEntity.addMapping(TileEntitySchematic.class, "TileEntitySchematic");
		TileEntity.addMapping(TileEntityTree.class, "TileEntityTree");
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		packetPipeline.initialise();
		ModItems.init();
		ModBlocks.init();
		SchematicHelper.createSchematicsDir();
		LogHelper.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Recipes.init();
		Loot.init();
		LogHelper.info("Initialization Complete!");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Post Initialization Complete!");
	}
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager serverCommand = ((ServerCommandManager) command);
		serverCommand.registerCommand(new CommandInstantBlocks());
	}
}