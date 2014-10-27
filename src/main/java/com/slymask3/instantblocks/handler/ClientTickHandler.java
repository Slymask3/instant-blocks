package com.slymask3.instantblocks.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

import com.slymask3.instantblocks.block.BlockInstantLava;
import com.slymask3.instantblocks.block.BlockInstantWater;
import com.slymask3.instantblocks.utility.InstantBlocksFunctions;
import com.slymask3.instantblocks.utility.LogHelper;

public class ClientTickHandler {
    public InstantBlocksFunctions ibf = new InstantBlocksFunctions();

    @SubscribeEvent
    public void onTickInGame(ClientTickEvent event) {
        if (ibf.checkTick > 0) {
    		ibf.checkTick--;
    	}
        
        if (BlockInstantLava.checkLava > 0) {
        	BlockInstantLava.checkLava--;
        }
        
        if (BlockInstantWater.checkWater > 0) {
        	BlockInstantWater.checkWater--;
        }
    }
}