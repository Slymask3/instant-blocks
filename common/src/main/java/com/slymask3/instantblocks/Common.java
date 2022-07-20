package com.slymask3.instantblocks;

import com.slymask3.instantblocks.config.IConfig;
import com.slymask3.instantblocks.init.ITileHelper;
import com.slymask3.instantblocks.network.IPacketHandler;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common {
    public static final String MOD_ID = "instantblocks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);
    public static CreativeModeTab ITEM_GROUP;
    public static IPacketHandler NETWORK;
    public static ITileHelper TILES;
    public static IConfig CONFIG = new IConfig(){};
}