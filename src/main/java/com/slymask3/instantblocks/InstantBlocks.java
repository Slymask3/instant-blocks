package com.slymask3.instantblocks;

import java.io.File;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import com.slymask3.instantblocks.command.CommandInstantBlocks;
import com.slymask3.instantblocks.gui.GuiHandler;
import com.slymask3.instantblocks.handler.ClientTickHandler;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.handler.ConnectionHandler;
import com.slymask3.instantblocks.init.Loot;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.init.Recipes;
import com.slymask3.instantblocks.network.PacketPipeline;
import com.slymask3.instantblocks.proxy.IProxy;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.tileentity.TileEntityColorLadder;
import com.slymask3.instantblocks.tileentity.TileEntityFlat;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.tileentity.TileEntitySchematic;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.tileentity.TileEntityStatue;
import com.slymask3.instantblocks.tileentity.TileEntityTree;
import com.slymask3.instantblocks.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory=Reference.GUI_FACTORY_CLASS)
public class InstantBlocks {
	@Instance(Reference.MOD_ID)
	public static InstantBlocks instance = new InstantBlocks();
	
	public static final PacketPipeline packetPipeline = new PacketPipeline(); 
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		FMLCommonHandler.instance().bus().register(new ConnectionHandler());
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());

		TileEntity.addMapping(TileEntityColor.class, "TileEntityColor");
		TileEntity.addMapping(TileEntityStatue.class, "TileEntityStatue");
		TileEntity.addMapping(TileEntityHarvest.class, "TileEntityHarvest");
		TileEntity.addMapping(TileEntitySkydive.class, "TileEntitySkydive");
		TileEntity.addMapping(TileEntityColorLadder.class, "TileEntityColorLadder");
		TileEntity.addMapping(TileEntitySchematic.class, "TileEntitySchematic");
		TileEntity.addMapping(TileEntityTree.class, "TileEntityTree");
		TileEntity.addMapping(TileEntityFlat.class, "TileEntityFlat");
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		packetPipeline.initialise();
		
		ModItems.init();
		ModBlocks.init();
		
		createSchematicsFolder();
		
		LogHelper.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerInformation();
		
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
	
	private void createSchematicsFolder() {
		File theDir = new File("schematics");

		if (!theDir.exists()) {
		    LogHelper.info("Creating /schematics/ directory.");
		    boolean result = false;

		    try{
		    	theDir.mkdir();
		        result = true;
		    } catch(SecurityException se){}  
		    
		    if(result) { 
		    	LogHelper.info("/schematics/ directory created.");
		    }
		}
	}
}