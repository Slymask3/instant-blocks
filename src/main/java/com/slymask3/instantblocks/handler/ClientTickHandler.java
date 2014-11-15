package com.slymask3.instantblocks.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

import com.slymask3.instantblocks.block.instant.BlockInstantLava;
import com.slymask3.instantblocks.block.instant.BlockInstantWater;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.LogHelper;

public class ClientTickHandler {
    public BuildHelper ibf = new BuildHelper();

    @SubscribeEvent
    public void onTickInGame(ClientTickEvent event) {
        if (ibf.checkTick > 0) {
    		ibf.checkTick--;
    	}
    }
}