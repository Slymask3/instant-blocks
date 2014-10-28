package com.slymask3.instantblocks;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

import com.slymask3.instantblocks.command.CommandInstantBlocks;
import com.slymask3.instantblocks.gui.GuiHandler;
import com.slymask3.instantblocks.handler.ClientTickHandler;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.handler.ConnectionHandler;
import com.slymask3.instantblocks.handler.ServerHandler;
import com.slymask3.instantblocks.init.Loot;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.init.Recipes;
import com.slymask3.instantblocks.proxy.IProxy;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.tileentity.TileEntityInstantStatue;
import com.slymask3.instantblocks.utility.BuildHelper;
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
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class InstantBlocks {
	@Instance(Reference.MOD_ID)
	public static InstantBlocks instance = new InstantBlocks();
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		FMLCommonHandler.instance().bus().register(new ConnectionHandler());
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		//FMLCommonHandler.instance().bus().register(new ServerHandler());

		TileEntity.addMapping(TileEntityColor.class, "TileEntityColor");
		TileEntity.addMapping(TileEntityInstantStatue.class, "TileEntityInstantStatue");
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		ModItems.init();
		ModBlocks.init();
		//ModSounds.init();
		
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
		
		/*Color color1 = new Color(122, 255, 240);
		Color color2 = new Color(196, 207, 81);
		Color color3 = new Color(48, 156, 50);
		
		LogHelper.info("color1 (lightblue?): " + ColorHelper.getWoolColor(color1));		
		LogHelper.info("color2 (yellow?): " + ColorHelper.getWoolColor(color2));		
		LogHelper.info("color3 (green?): " + ColorHelper.getWoolColor(color3));*/
	}
	
	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager serverCommand = ((ServerCommandManager) command);
		
		serverCommand.registerCommand(new CommandInstantBlocks());
	}
}