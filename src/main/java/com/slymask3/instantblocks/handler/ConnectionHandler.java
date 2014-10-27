package com.slymask3.instantblocks.handler;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import net.minecraft.entity.player.EntityPlayer;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.utility.InstantBlocksFunctions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ConnectionHandler {
	public InstantBlocks ib = new InstantBlocks();
	public InstantBlocksFunctions ibf = new InstantBlocksFunctions();
	private ConfigurationHandler config = new ConfigurationHandler();
	
	@SubscribeEvent
	//public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
	public void onClientConnection(PlayerLoggedInEvent event) {
		EntityPlayer play = event.player;
		//EntityPlayer play = (EntityPlayer)player;
		ibf.msgClean(play, "\u00a7aLoaded successfully.", Colors.a);
		
		System.out.println("LOADED SUCCESSFULLY");
		
		try {
			URL url = new URL(config.checkURL);
			//@SuppressWarnings("resource")
			Scanner s = new Scanner(url.openStream());
			
			String ver = s.nextLine();
			String mc = s.nextLine();
			
			if (!ver.equalsIgnoreCase(Reference.VERSION)) {
				ibf.msgCleanBypass(play, "\u00a7cUpdate avaliable: \u00a7lv" + ver + " \u00a7r\u00a7c(For MC " + mc + ")!", Colors.c);
				ibf.msgCleanBypass(play, "\u00a7cLink: \u00a7b\u00a7nhttp://tinyurl.com/instantblocks", Colors.c);
				//System.out.println("\u00a7cNew version avaliable: " + ver + "!");
			}
			
			//System.out.println(ver);
			//System.out.println("###################################");
			//System.out.println(mc);
			
			s.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
