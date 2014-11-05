package com.slymask3.instantblocks.handler;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import net.minecraft.entity.player.EntityPlayer;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.utility.BuildHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ConnectionHandler {
	@SubscribeEvent
	public void onClientConnection(PlayerLoggedInEvent event) {
		EntityPlayer play = event.player;
		
		BuildHelper.msgClean(play, "\u00a7aLoaded successfully.", Colors.a);
		
		try {
			URL url = new URL(Strings.checkURL);
			Scanner s = new Scanner(url.openStream());
			
			String ver = s.nextLine();
			String mc = s.nextLine();
			
			if (!ver.equalsIgnoreCase(Reference.VERSION)) {
				BuildHelper.msgCleanBypass(play, "\u00a7cUpdate avaliable: \u00a7lv" + ver + " \u00a7r\u00a7c(For MC " + mc + ")!", Colors.c);
				BuildHelper.msgCleanBypass(play, "\u00a7cLink: \u00a7b\u00a7nhttp://tinyurl.com/instantblocks", Colors.c);
			}
			
			s.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
