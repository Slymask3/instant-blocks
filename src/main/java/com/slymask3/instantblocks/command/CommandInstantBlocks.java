package com.slymask3.instantblocks.command;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.utility.ColorHelper;
import com.slymask3.instantblocks.utility.BuildHelper;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.config.Property;

public class CommandInstantBlocks extends CommandBase {
	private InstantBlocks ib = new InstantBlocks();
	private ConfigurationHandler config = new ConfigurationHandler();
	private BuildHelper ibf = new BuildHelper();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	//private IChatComponent icc;
	
	public String getCommandName() {
        return "instantblocks";
    }

	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
        return true;
    }
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List getCommandAliases() {
        return Arrays.asList(new String[] {"ib"});
    }
    
    public void processCommand(ICommandSender ics, String[] arg) {
    	EntityPlayer player = CommandBase.getCommandSenderAsPlayer(ics);
		
		//player.triggerAchievement(ib.achCMD);
    	
        if (arg.length == 1) {
        	if (arg[0].equalsIgnoreCase("1") || arg[0].equalsIgnoreCase("one")) {
        		list1(player);
        	} else if (arg[0].equalsIgnoreCase("2") || arg[0].equalsIgnoreCase("two")) {
        		list2(player);
        	} else if (arg[0].equalsIgnoreCase("3") || arg[0].equalsIgnoreCase("three")) {
        		list3(player);
        	} else if (arg[0].equalsIgnoreCase("4") || arg[0].equalsIgnoreCase("four")) {
        		list4(player);
        	} else if (arg[0].equalsIgnoreCase("5") || arg[0].equalsIgnoreCase("five")) {
        		list5(player);
        	} else if (arg[0].equalsIgnoreCase("info")) {
        		info(player);
        	} else if (arg[0].equalsIgnoreCase("changelog")) {
                changelog(player);
        	} else if (arg[0].equalsIgnoreCase("woolReset")) {
                woolReset(player);
        	} else if (arg[0].equalsIgnoreCase("woolRandom")) {
                woolRandom(player);
        	}
        	
        	else if (arg[0].equalsIgnoreCase("woolPattern")) {
        		woolPatternHelp(player);
        	}
        	
        	else {
            	list1(player);
        	}
        } else if (arg.length == 2) {
        	if (arg[0].equalsIgnoreCase("msg")) {
        		config.msg = configBoolean(arg, player, config.msg, "Messages");
        	} else if (arg[0].equalsIgnoreCase("keepBlocks")) {
        		config.keepBlocks = configBoolean(arg, player, config.keepBlocks, "Keep Blocks");
        	} else if (arg[0].equalsIgnoreCase("effect")) {
        		config.effect = configBoolean(arg, player, config.effect, "Effect");
        	} else if (arg[0].equalsIgnoreCase("tpFall") || arg[0].equalsIgnoreCase("tpSkydive") || arg[0].equalsIgnoreCase("tpRainbow") || arg[0].equalsIgnoreCase("tpRainbowSkydive")) {
        		config.tpFall = configBoolean(arg, player, config.tpFall, "Rainbow Skydive Teleport");
        	} else if (arg[0].equalsIgnoreCase("tpGrinder")) {
        		config.tpGrinder = configBoolean(arg, player, config.tpGrinder, "Grinder Teleport");
        	} else if (arg[0].equalsIgnoreCase("simpleWL") || arg[0].equalsIgnoreCase("simpleMode")) {
        		config.simpleWL = configBoolean(arg, player, config.simpleWL, "Simple Mode");
        	} else if (arg[0].equalsIgnoreCase("useWands") || arg[0].equalsIgnoreCase("wandReq")) {
        		config.useWands = configBoolean(arg, player, config.useWands, "Use Wands");
        	} else if (arg[0].equalsIgnoreCase("packWood") || arg[0].equalsIgnoreCase("packWoodenHouse") || arg[0].equalsIgnoreCase("packHouse")) {
        		config.packWood = configBoolean(arg, player, config.packWood, "Pack Wooden House");
        	} else if (arg[0].equalsIgnoreCase("xp") || arg[0].equalsIgnoreCase("exp")) {
        		config.xp = configInt(arg, player, config.xp, "XP From Instant Blocks");
        	} else if (arg[0].equalsIgnoreCase("maxWL") || arg[0].equalsIgnoreCase("max") || arg[0].equalsIgnoreCase("maxWaterLava")) {
        		config.max = configInt(arg, player, config.max, "Max Water/Lava");
        	} else if (arg[0].equalsIgnoreCase("maxSuction") || arg[0].equalsIgnoreCase("maxSuck")) {
        		config.maxSuck = configInt(arg, player, config.maxSuck, "Max Suction");
        	} else if (arg[0].equalsIgnoreCase("sound")) {
        		config.sound = configString(arg, player, config.sound, "Sound");
        		ibf.sound(player.worldObj, config.sound, (int)player.posX, (int)player.posY, (int)player.posZ);
        	}
        	
        	else if (arg[0].equalsIgnoreCase("wool1") || arg[0].equalsIgnoreCase("wool01")) {
         		config.wool[0] = configWool(arg, player, config.wool[0], "Wool #1");
         	} else if (arg[0].equalsIgnoreCase("wool2") || arg[0].equalsIgnoreCase("wool02")) {
         		config.wool[1] = configWool(arg, player, config.wool[1], "Wool #2");
         	} else if (arg[0].equalsIgnoreCase("wool3") || arg[0].equalsIgnoreCase("wool03")) {
         		config.wool[2] = configWool(arg, player, config.wool[2], "Wool #3");
         	} else if (arg[0].equalsIgnoreCase("wool4") || arg[0].equalsIgnoreCase("wool04")) {
         		config.wool[3] = configWool(arg, player, config.wool[3], "Wool #4");
         	} else if (arg[0].equalsIgnoreCase("wool5") || arg[0].equalsIgnoreCase("wool05")) {
         		config.wool[4] = configWool(arg, player, config.wool[4], "Wool #5");
         	} else if (arg[0].equalsIgnoreCase("wool6") || arg[0].equalsIgnoreCase("wool06")) {
         		config.wool[5] = configWool(arg, player, config.wool[5], "Wool #6");
         	} else if (arg[0].equalsIgnoreCase("wool7") || arg[0].equalsIgnoreCase("wool07")) {
         		config.wool[6] = configWool(arg, player, config.wool[6], "Wool #7");
         	} else if (arg[0].equalsIgnoreCase("wool8") || arg[0].equalsIgnoreCase("wool08")) {
         		config.wool[7] = configWool(arg, player, config.wool[7], "Wool #8");
         	} else if (arg[0].equalsIgnoreCase("wool9") || arg[0].equalsIgnoreCase("wool09")) {
         		config.wool[8] = configWool(arg, player, config.wool[8], "Wool #9");
         	} else if (arg[0].equalsIgnoreCase("wool10")) {
         		config.wool[9] = configWool(arg, player, config.wool[9], "Wool #10");
         	} else if (arg[0].equalsIgnoreCase("wool11")) {
         		config.wool[10] = configWool(arg, player, config.wool[10], "Wool #11");
         	} else if (arg[0].equalsIgnoreCase("woolAll")) {
         		woolAll(arg, player);
         	}
        	
         	else if (arg[0].equalsIgnoreCase("woolPattern")) {
        		woolPatternHelp(player);
        	}
        	
         	else {
             	list1(player);
         	}
        	
        } else if (arg.length > 2) {
        	if (arg[0].equalsIgnoreCase("woolPattern")) {
        		woolPattern(arg, player);
        	} else {
        		list1(player);
        	}
        }
        
        else {
    		list1(player);
    	}
    }
    
    public void list1(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 1/5")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib info \u00a7a- Instant blocks and items info.")); //3
    	player.addChatMessage(new ChatComponentText("\u00a72/ib changelog \u00a7a- Mod changelog.")); //4
    	player.addChatMessage(new ChatComponentText("\u00a72/ib msg [true/false] \u00a7a- Toggle messages from this mod.")); //5
    	player.addChatMessage(new ChatComponentText("\u00a72/ib keepBlocks [true/false] \u00a7a- Keep IBs after creation.")); //6
    	player.addChatMessage(new ChatComponentText("\u00a72/ib effect [true/false] \u00a7a- Show particles on activate.")); //7
    	player.addChatMessage(new ChatComponentText("\u00a72/ib tpSkydive [true/false] \u00a7a- Teleport to the top of Skydive.")); //8
    	player.addChatMessage(new ChatComponentText("\u00a72/ib tpGrinder [true/false] \u00a7a- Teleport to the collecting room.")); //9
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
    
    public void list2(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 2/5")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib simpleMode [true/false] \u00a7a- Simple water/lava creation.")); //3
    	player.addChatMessage(new ChatComponentText("\u00a72/ib useWands [true/false] \u00a7a- Toggle using wands.")); //4
    	player.addChatMessage(new ChatComponentText("\u00a72/ib packHouse [true/false] \u00a7a- Toggle packing house.")); //5
    	player.addChatMessage(new ChatComponentText("\u00a72/ib xp [number] \u00a7a- Amount of xp to give.")); //6
    	player.addChatMessage(new ChatComponentText("\u00a72/ib maxWL [number] \u00a7a- Max water/lava to create.")); //7
    	player.addChatMessage(new ChatComponentText("\u00a72/ib maxSuction [number] \u00a7a- Max water/lava to suck.")); //8
    	player.addChatMessage(new ChatComponentText("\u00a72/ib sound [sound] \u00a7a- Activation sound.")); //9
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
    
    public void list3(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 3/5")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool1 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool2 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool3 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool4 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool5 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool6 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool7 [metadata/color] \u00a7a- Wool in the skydive pattern."));
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
    
    public void list4(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 4/5")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool8 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool9 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool10 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib wool11 [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib woolAll [metadata/color] \u00a7a- Wool in the skydive pattern."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib woolPattern [m/c] [m/c] [m/c]... \u00a7a- Pattern for the skydive."));
    	player.addChatMessage(new ChatComponentText("\u00a72/ib woolRandom \u00a7a- Random pattern for the rainbow skydive."));
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
    
    public void list5(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 5/5")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib woolReset \u00a7a- Reset the rainbow skydive pattern."));
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
	
	public void info(EntityPlayer player) {
        ItemStack held = player.getCurrentEquippedItem();
        String wandReq = (config.useWands == true) ? "Yes" : "No";
    	
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l============================================="));
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Info"));
    	
    	//INSTANT BLOCKS
    	
    	if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibWood)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Wooden House Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a simple house with a bed, chests, furnaces, etc."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibLadder)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Mining Ladder Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a ladder down to layer 12, which is the 'diamond' area."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibGlassDome)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Glass Dome Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a small 9x9 glass dome. Useful for underwater."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibFarm)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Farm Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a simple farm with 56 seeds. You also have a 5% chance that the farm will have carrots planted instead of wheat, and another 5% chance that the farm will have potatoes planted instead of wheat."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibFall)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Rainbow Skydive Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a structure from layer 1 to 256 out of coloured wool."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    		player.addChatMessage(new ChatComponentText("\u00a72Pattern: \u00a7a"));
    		player.addChatMessage(new ChatComponentText("\u00a721) " + ColorHelper.toColor2(config.wool[0])));
    		player.addChatMessage(new ChatComponentText("\u00a722) " + ColorHelper.toColor2(config.wool[1])));
    		player.addChatMessage(new ChatComponentText("\u00a723) " + ColorHelper.toColor2(config.wool[2])));
    		player.addChatMessage(new ChatComponentText("\u00a724) " + ColorHelper.toColor2(config.wool[3])));
    		player.addChatMessage(new ChatComponentText("\u00a725) " + ColorHelper.toColor2(config.wool[4])));
    		player.addChatMessage(new ChatComponentText("\u00a726) " + ColorHelper.toColor2(config.wool[5])));
    		player.addChatMessage(new ChatComponentText("\u00a727) " + ColorHelper.toColor2(config.wool[6])));
    		player.addChatMessage(new ChatComponentText("\u00a728) " + ColorHelper.toColor2(config.wool[7])));
    		player.addChatMessage(new ChatComponentText("\u00a729) " + ColorHelper.toColor2(config.wool[8])));
    		player.addChatMessage(new ChatComponentText("\u00a7210) " + ColorHelper.toColor2(config.wool[9])));
    		player.addChatMessage(new ChatComponentText("\u00a7211) " + ColorHelper.toColor2(config.wool[10])));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibGrinder)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Grinder Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a simple experience orbs grinder. Only works for zombies and skeletons. The zombies/skeletons will be dragged by the current up, then dropped down into the collecting room, where you hit them once or twice with your fist."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7cNo."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibPool)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Pool Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a simple pool with two diving boards."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibUp)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Escape Ladder Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aPlace on a wall underground, and it will create a ladder going all the way up to the surface."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibWater)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Water Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aFills an area with water source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)"));
    		player.addChatMessage(new ChatComponentText("\u00a72Maximum: \u00a7a" + config.max + " Water Source Blocks."));
    		if (config.simpleWL == true) {
        		player.addChatMessage(new ChatComponentText("\u00a72Mode: \u00a7aSimple."));
    		} else {
    			player.addChatMessage(new ChatComponentText("\u00a72Mode: \u00a7aFull."));
			}
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a77N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibLava)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Lava Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aFills an area with lava source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)"));
    		player.addChatMessage(new ChatComponentText("\u00a72Maximum: \u00a7a" + config.max + " Lava Source Blocks."));
    		if (config.simpleWL == true) {
        		player.addChatMessage(new ChatComponentText("\u00a72Mode: \u00a7aSimple."));
    		} else {
    			player.addChatMessage(new ChatComponentText("\u00a72Mode: \u00a7aFull."));
			}
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a77N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibSucker)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Suction Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aSucks in all water/lava around it."));
    		player.addChatMessage(new ChatComponentText("\u00a72Maximum: \u00a7a" + config.maxSuck + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a77N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibRail)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Rail Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a simple railway path going forward 37 blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibStatue)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Statue Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a statue of any Minecraft player's skin."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
    	}
    	
    	//COLOR
    	
    	else if (held != null && held.getItem() == Item.getItemFromBlock(mb.color)) {
     		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aColor Block"));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aA block only avaliable in creative, used for Instant Statue."));
     	}
    	
    	//ITEMS
    	
    	else if (held != null && held.getItem() == mi.ibWandWood) {
    		player.addChatMessage(new ChatComponentText("\u00a72Item: \u00a7aInstant Wooden Wand"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aUsed to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Uses: \u00a7a" + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Required: \u00a7a" + wandReq + "."));
    	} else if (held != null && held.getItem() == mi.ibWandStone) {
    		player.addChatMessage(new ChatComponentText("\u00a72Item: \u00a7aInstant Stone Wand"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aUsed to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Uses: \u00a7a" + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Required: \u00a7a" + wandReq + "."));
    	} else if (held != null && held.getItem() == mi.ibWandIron) {
    		player.addChatMessage(new ChatComponentText("\u00a72Item: \u00a7aInstant Iron Wand"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aUsed to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Uses: \u00a7a" + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Required: \u00a7a" + wandReq + "."));
    	} else if (held != null && held.getItem() == mi.ibWandGold) {
    		player.addChatMessage(new ChatComponentText("\u00a72Item: \u00a7aInstant Golden Wand"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aUsed to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Uses: \u00a7a" + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Required: \u00a7a" + wandReq + "."));
    	} else if (held != null && held.getItem() == mi.ibWandDiamond) {
    		player.addChatMessage(new ChatComponentText("\u00a72Item: \u00a7aInstant Diamond Wand"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aUsed to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Uses: \u00a7a" + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Required: \u00a7a" + wandReq + "."));
    	} else if (held == null) {
    		player.addChatMessage(new ChatComponentText("\u00a7cHold an Instant Block or Item, and perform this command to get information on the Instant Block or Item."));
    	} else {
    		player.addChatMessage(new ChatComponentText("\u00a7c'" + held.getDisplayName() + "' is not an Instant Block or Item."));
    		player.addChatMessage(new ChatComponentText("\u00a7cHold an Instant Block or Item, and perform this command to get information on the Instant Block or Item."));
    	}
    	
    	player.addChatMessage(new ChatComponentText("\u00a78\u00a7l============================================="));
    }
	
	public void changelog(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l============================================="));
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Changelog"));
		player.addChatMessage(new ChatComponentText("\u00a72- Fixed Instant Farm Block to actually generate."));
		player.addChatMessage(new ChatComponentText("\u00a72- Fixed Instant Water/Lava/Suction Blocks to remove wand durability correctly."));
		player.addChatMessage(new ChatComponentText("\u00a72- Fixed error message with Instant Grinder Block."));
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l============================================="));
	}
	
	public boolean configBoolean(String[] arg, EntityPlayer player, boolean b, String var) {
		if (arg[1].equalsIgnoreCase("true")) {
	    	player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7a" + var + " set to true."));
			return true;
		} else if (arg[1].equalsIgnoreCase("false")) {
	    	player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7c" + var + " set to false."));
			return false;
		} else {
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7cCorrect Usage: /ib " + arg[0] + " [true/false]"));
			return b;
		}
	}
	
	public int configInt(String[] arg, EntityPlayer player, int i, String var) {
		int n;
		
		n = Integer.parseInt(arg[1]);
			
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7a" + var + " set to " + n + "."));
		return n;
	}
	
	public String configString(String[] arg, EntityPlayer player, String s, String var) {
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7a" + var + " set to '" + arg[1] + "'."));
		return arg[1];
	}
	
	public int configWool(String[] arg, EntityPlayer player, int i, String var) {
		int m = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7a" + var + " set to " + ColorHelper.toColor2(m) + "\u00a7a."));
		return m;
	}
	
	public void woolAll(String[] arg, EntityPlayer player) {
		config.wool[0] = configWool(arg, player, config.wool[0], "Wool #1");
 		config.wool[1] = configWool(arg, player, config.wool[1], "Wool #2");
 		config.wool[2] = configWool(arg, player, config.wool[2], "Wool #3");
 		config.wool[3] = configWool(arg, player, config.wool[3], "Wool #4");
 		config.wool[4] = configWool(arg, player, config.wool[4], "Wool #5");
 		config.wool[5] = configWool(arg, player, config.wool[5], "Wool #6");
 		config.wool[6] = configWool(arg, player, config.wool[6], "Wool #7");
 		config.wool[7] = configWool(arg, player, config.wool[7], "Wool #8");
 		config.wool[8] = configWool(arg, player, config.wool[8], "Wool #9");
 		config.wool[9] = configWool(arg, player, config.wool[9], "Wool #10");
 		config.wool[10] = configWool(arg, player, config.wool[10], "Wool #11");
	}
	
	public void woolReset(EntityPlayer player) {
		config.wool[0] = 14;
 		config.wool[1] = 1;
 		config.wool[2] = 4;
 		config.wool[3] = 5;
 		config.wool[4] = 13;
 		config.wool[5] = 9;
 		config.wool[6] = 3;
 		config.wool[7] = 11;
 		config.wool[8] = 10;
 		config.wool[9] = 2;
 		config.wool[10] = 6;
 		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7cR\u00a76a\u00a7ei\u00a7an\u00a72b\u00a73o\u00a7bw \u00a7aSkydive pattern reset."));
	}
	
	public void woolPattern(String[] arg, EntityPlayer player) {
		if (arg.length == 2) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			config.wool[0] = m1;
			config.wool[1] = m1;
			config.wool[2] = m1;
			config.wool[3] = m1;
			config.wool[4] = m1;
			config.wool[5] = m1;
			config.wool[6] = m1;
			config.wool[7] = m1;
			config.wool[8] = m1;
			config.wool[9] = m1;
			config.wool[10] = m1;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
		} else if (arg.length == 3) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m1;
			config.wool[3] = m2;
			config.wool[4] = m1;
			config.wool[5] = m2;
			config.wool[6] = m1;
			config.wool[7] = m2;
			config.wool[8] = m1;
			config.wool[9] = m2;
			config.wool[10] = m1;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
		} else if (arg.length == 4) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m1;
			config.wool[4] = m2;
			config.wool[5] = m3;
			config.wool[6] = m1;
			config.wool[7] = m2;
			config.wool[8] = m3;
			config.wool[9] = m1;
			config.wool[10] = m2;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
		} else if (arg.length == 5) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m1;
			config.wool[5] = m2;
			config.wool[6] = m3;
			config.wool[7] = m4;
			config.wool[8] = m1;
			config.wool[9] = m2;
			config.wool[10] = m3;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
		} else if (arg.length == 6) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m1;
			config.wool[6] = m2;
			config.wool[7] = m3;
			config.wool[8] = m4;
			config.wool[9] = m5;
			config.wool[10] = m1;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
		} else if (arg.length == 7) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			int m6 = arg[6].matches(".*\\d.*") ? Integer.parseInt(arg[6]) : ColorHelper.toMeta(arg[6]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m6;
			config.wool[6] = m1;
			config.wool[7] = m2;
			config.wool[8] = m3;
			config.wool[9] = m4;
			config.wool[10] = m5;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
		} else if (arg.length == 8) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			int m6 = arg[6].matches(".*\\d.*") ? Integer.parseInt(arg[6]) : ColorHelper.toMeta(arg[6]);
			int m7 = arg[7].matches(".*\\d.*") ? Integer.parseInt(arg[7]) : ColorHelper.toMeta(arg[7]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m6;
			config.wool[6] = m7;
			config.wool[7] = m1;
			config.wool[8] = m2;
			config.wool[9] = m3;
			config.wool[10] = m4;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m7) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
		} else if (arg.length == 9) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			int m6 = arg[6].matches(".*\\d.*") ? Integer.parseInt(arg[6]) : ColorHelper.toMeta(arg[6]);
			int m7 = arg[7].matches(".*\\d.*") ? Integer.parseInt(arg[7]) : ColorHelper.toMeta(arg[7]);
			int m8 = arg[8].matches(".*\\d.*") ? Integer.parseInt(arg[8]) : ColorHelper.toMeta(arg[8]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m6;
			config.wool[6] = m7;
			config.wool[7] = m8;
			config.wool[8] = m1;
			config.wool[9] = m2;
			config.wool[10] = m3;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m7) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m8) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
		} else if (arg.length == 10) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			int m6 = arg[6].matches(".*\\d.*") ? Integer.parseInt(arg[6]) : ColorHelper.toMeta(arg[6]);
			int m7 = arg[7].matches(".*\\d.*") ? Integer.parseInt(arg[7]) : ColorHelper.toMeta(arg[7]);
			int m8 = arg[8].matches(".*\\d.*") ? Integer.parseInt(arg[8]) : ColorHelper.toMeta(arg[8]);
			int m9 = arg[9].matches(".*\\d.*") ? Integer.parseInt(arg[9]) : ColorHelper.toMeta(arg[9]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m6;
			config.wool[6] = m7;
			config.wool[7] = m8;
			config.wool[8] = m9;
			config.wool[9] = m1;
			config.wool[10] = m2;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m7) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m8) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m9) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
		} else if (arg.length == 11) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			int m6 = arg[6].matches(".*\\d.*") ? Integer.parseInt(arg[6]) : ColorHelper.toMeta(arg[6]);
			int m7 = arg[7].matches(".*\\d.*") ? Integer.parseInt(arg[7]) : ColorHelper.toMeta(arg[7]);
			int m8 = arg[8].matches(".*\\d.*") ? Integer.parseInt(arg[8]) : ColorHelper.toMeta(arg[8]);
			int m9 = arg[9].matches(".*\\d.*") ? Integer.parseInt(arg[9]) : ColorHelper.toMeta(arg[9]);
			int m10 = arg[10].matches(".*\\d.*") ? Integer.parseInt(arg[10]) : ColorHelper.toMeta(arg[10]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m6;
			config.wool[6] = m7;
			config.wool[7] = m8;
			config.wool[8] = m9;
			config.wool[9] = m10;
			config.wool[10] = m1;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m7) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m8) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m9) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m10) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
		} else if (arg.length == 12) {
			int m1 = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			int m2 = arg[2].matches(".*\\d.*") ? Integer.parseInt(arg[2]) : ColorHelper.toMeta(arg[2]);
			int m3 = arg[3].matches(".*\\d.*") ? Integer.parseInt(arg[3]) : ColorHelper.toMeta(arg[3]);
			int m4 = arg[4].matches(".*\\d.*") ? Integer.parseInt(arg[4]) : ColorHelper.toMeta(arg[4]);
			int m5 = arg[5].matches(".*\\d.*") ? Integer.parseInt(arg[5]) : ColorHelper.toMeta(arg[5]);
			int m6 = arg[6].matches(".*\\d.*") ? Integer.parseInt(arg[6]) : ColorHelper.toMeta(arg[6]);
			int m7 = arg[7].matches(".*\\d.*") ? Integer.parseInt(arg[7]) : ColorHelper.toMeta(arg[7]);
			int m8 = arg[8].matches(".*\\d.*") ? Integer.parseInt(arg[8]) : ColorHelper.toMeta(arg[8]);
			int m9 = arg[9].matches(".*\\d.*") ? Integer.parseInt(arg[9]) : ColorHelper.toMeta(arg[9]);
			int m10 = arg[10].matches(".*\\d.*") ? Integer.parseInt(arg[10]) : ColorHelper.toMeta(arg[10]);
			int m11 = arg[11].matches(".*\\d.*") ? Integer.parseInt(arg[11]) : ColorHelper.toMeta(arg[11]);
			config.wool[0] = m1;
			config.wool[1] = m2;
			config.wool[2] = m3;
			config.wool[3] = m4;
			config.wool[4] = m5;
			config.wool[5] = m6;
			config.wool[6] = m7;
			config.wool[7] = m8;
			config.wool[8] = m9;
			config.wool[9] = m10;
			config.wool[10] = m11;
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m7) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m8) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m9) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m10) + "\u00a7a."));
			player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m11) + "\u00a7a."));
		} else {
    		list1(player);
    	}
	}
	
	public void woolRandom(EntityPlayer player) {
		Random rand = new Random();
		
		int m1 = rand.nextInt(15);
		int m2 = rand.nextInt(15);
		int m3 = rand.nextInt(15);
		int m4 = rand.nextInt(15);
		int m5 = rand.nextInt(15);
		int m6 = rand.nextInt(15);
		int m7 = rand.nextInt(15);
		int m8 = rand.nextInt(15);
		int m9 = rand.nextInt(15);
		int m10 = rand.nextInt(15);
		int m11 = rand.nextInt(15);
		config.wool[0] = m1;
		config.wool[1] = m2;
		config.wool[2] = m3;
		config.wool[3] = m4;
		config.wool[4] = m5;
		config.wool[5] = m6;
		config.wool[6] = m7;
		config.wool[7] = m8;
		config.wool[8] = m9;
		config.wool[9] = m10;
		config.wool[10] = m11;
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #1 set to " + ColorHelper.toColor2(m1) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #2 set to " + ColorHelper.toColor2(m2) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #3 set to " + ColorHelper.toColor2(m3) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #4 set to " + ColorHelper.toColor2(m4) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #5 set to " + ColorHelper.toColor2(m5) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #6 set to " + ColorHelper.toColor2(m6) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #7 set to " + ColorHelper.toColor2(m7) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #8 set to " + ColorHelper.toColor2(m8) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #9 set to " + ColorHelper.toColor2(m9) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #10 set to " + ColorHelper.toColor2(m10) + "\u00a7a."));
		player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aWool #11 set to " + ColorHelper.toColor2(m11) + "\u00a7a."));
	}
	
	public void woolPatternHelp (EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l============================================="));
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Wool Pattern"));
		player.addChatMessage(new ChatComponentText("\u00a72Command:"));
		player.addChatMessage(new ChatComponentText("\u00a7a/ib woolPattern [meta/color] [meta/color] ([meta/color] [m/c] [m/c] [m/c] [m/c] [m/c] [m/c] [m/c] [m/c])"));
		player.addChatMessage(new ChatComponentText(""));
		player.addChatMessage(new ChatComponentText("\u00a72Example:"));
		player.addChatMessage(new ChatComponentText("\u00a7a/ib woolPattern black gray lightgray white"));
		player.addChatMessage(new ChatComponentText("\u00a7cOR \u00a7a/ib woolPattern 15 7 8 0"));
		player.addChatMessage(new ChatComponentText(""));
		player.addChatMessage(new ChatComponentText("\u00a72Output:"));
		player.addChatMessage(new ChatComponentText("\u00a7aWool #1 = Black. Wool #2 = Gray. Wool #3 = Light Gray. Wool #4 = White. Wool #5 = Black. Wool #6 = Gray. Wool #7 = Light Gray. Wool #8 = White. Wool #9 = Black. Wool #10 = Gray. Wool #11 = Light Gray."));
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l============================================="));
	}

	//NEW 1.7.10 IMPLEMENTATION
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}