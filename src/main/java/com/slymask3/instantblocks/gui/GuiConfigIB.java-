package com.slymask3.instantblocks.gui;

import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

import java.util.List;

public class GuiConfigIB extends GuiConfig {  
    private static List list = new ConfigElement(Config.configuration.getCategory(Config.GENERAL)).getChildElements();
	private static String titleDir = GuiConfig.getAbridgedConfigPath(Config.configuration.toString());

	public GuiConfigIB(GuiScreen parent) {
        super(parent, list, Reference.MOD_ID, false, false, titleDir);
    }
}