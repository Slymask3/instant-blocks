package com.slymask3.instantblocks.gui;

import java.util.ArrayList;
import java.util.List;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;

import cpw.mods.fml.client.config.GuiConfig;  
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;  
import net.minecraftforge.common.config.ConfigElement;  
import net.minecraftforge.common.config.Configuration;

public class GuiConfigIB extends GuiConfig {  
    static List list = new ConfigElement(ConfigurationHandler.configuration.getCategory("options")).getChildElements();
    		///.add(new ConfigElement(ConfigurationHandler.configuration.getCategory("other")).getChildElements()
    				//.add(new ConfigElement(ConfigurationHandler.configuration.getCategory("wool colors")).getChildElements()));
    
	private static String titleDir = GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString());
    
	
	
	public GuiConfigIB(GuiScreen parent) {
        super(parent, list, "instantblocks", false, false, titleDir);
    }
	
	/*private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add((IConfigElement) new ConfigElement(ConfigurationHandler.configuration.getCategory("options")).getChildElements());
        list.add((IConfigElement) new ConfigElement(ConfigurationHandler.configuration.getCategory("wool colors")).getChildElements());
        list.add((IConfigElement) new ConfigElement(ConfigurationHandler.configuration.getCategory("other")).getChildElements());
        list.add((IConfigElement) new ConfigElement(ConfigurationHandler.configuration.getCategory("cheats")).getChildElements());
        return list;
    }*/
}