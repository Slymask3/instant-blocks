package com.slymask3.instantblocks.handler;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import net.minecraft.entity.player.EntityPlayer;

import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.IBHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ConnectionHandler {
	@SubscribeEvent
	public void onClientConnection(PlayerLoggedInEvent event) {
		EntityPlayer play = event.player;
		
		IBHelper.msgClean(play, "\u00a7aLoaded successfully.", Colors.a);
		
		try {
			URL url = new URL(Strings.checkURL);
			Scanner s = new Scanner(url.openStream());
			
			String ver = s.nextLine();
			String mc = s.nextLine();
			
			if (!ver.equalsIgnoreCase(Reference.VERSION)) {
				IBHelper.msgCleanBypass(play, "\u00a7cUpdate avaliable: v" + ver + " \u00a7c(MC " + mc + ")!", Colors.c);
				IBHelper.msgCleanBypass(play, "\u00a7cLink: \u00a7b\u00a7nhttp://tinyurl.com/instantblocks", Colors.c);
			}
			
			s.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
