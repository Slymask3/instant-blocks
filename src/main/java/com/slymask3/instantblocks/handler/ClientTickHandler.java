package com.slymask3.instantblocks.handler;

import com.slymask3.instantblocks.utility.BuildHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class ClientTickHandler {
    @SubscribeEvent
    public void onTickInGame(ClientTickEvent event) {
        if (BuildHelper.checkTick > 0) {
        	BuildHelper.checkTick--;
    	}
    }
}