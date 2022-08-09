package com.slymask3.instantblocks;

import com.slymask3.instantblocks.config.IConfig;
import com.slymask3.instantblocks.init.ITileHelper;
import com.slymask3.instantblocks.network.IPacketHandler;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common {
    public static final String MOD_ID = "instantblocks";
    public static final String SCHEMATICS_DIR = "config/" + MOD_ID + "/schematics";
    public static final String STATUE_CACHE_DIR = "config/" + MOD_ID;
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);
    public static CreativeModeTab ITEM_GROUP;
    public static IPacketHandler NETWORK;
    public static ITileHelper TILES;
    public static IConfig CONFIG = new IConfig(){};

    public static void init() {
        Helper.createDirectory(SCHEMATICS_DIR);
        Helper.createDirectory(STATUE_CACHE_DIR);
    }
}