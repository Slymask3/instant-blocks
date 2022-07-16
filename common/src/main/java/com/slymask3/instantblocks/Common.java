package com.slymask3.instantblocks;

import com.slymask3.instantblocks.config.IConfig;
import com.slymask3.instantblocks.init.ITileHelper;
import com.slymask3.instantblocks.network.IPacketHandler;
import com.slymask3.instantblocks.platform.Services;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common {
    public static final String MOD_ID = "instantblocks";
    public static final String MOD_NAME = "Instant Blocks";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static CreativeModeTab ITEM_GROUP;
    public static IPacketHandler NETWORK;
    public static ITileHelper TILES;
    public static IConfig CONFIG = new IConfig(){};

    public static void init() {
        Common.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.isDevelopmentEnvironment() ? "development" : "production");
    }
}