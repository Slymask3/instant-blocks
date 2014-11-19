package com.slymask3.instantblocks.command;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import com.google.common.collect.Collections2;
import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.ColorHelper;
import com.slymask3.instantblocks.utility.IBHelper;

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
        	} else if (arg[0].equalsIgnoreCase("info")) {
        		info(player);
        	} else if (arg[0].equalsIgnoreCase("changelog")) {
                changelog(player);
        	} else if (arg[0].equalsIgnoreCase("blockcolor") || arg[0].equalsIgnoreCase("bc") || arg[0].equalsIgnoreCase("color") || arg[0].equalsIgnoreCase("colorblock") || arg[0].equalsIgnoreCase("cb")) {
                colorBlock(player);
        	} else {
            	list1(player);
        	}
        } else if (arg.length == 2) {
        	if (arg[0].equalsIgnoreCase("msg")) {
        		config.msg = configBoolean(arg, player, config.msg, "Messages");
        	} else if (arg[0].equalsIgnoreCase("keepBlocks")) {
        		config.keepBlocks = configBoolean(arg, player, config.keepBlocks, "Keep Blocks");
        	} else if (arg[0].equalsIgnoreCase("effect")) {
        		config.effect = configBoolean(arg, player, config.effect, "Effect");
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
        		IBHelper.sound(player.worldObj, config.sound, (int)player.posX, (int)player.posY, (int)player.posZ);
        	}
        	
         	else {
             	list1(player);
         	}
        
        } else {
    		list1(player);
    	}
    }
    
    public void list1(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 1/2")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib info \u00a7a- Instant blocks and items info.")); //3
    	player.addChatMessage(new ChatComponentText("\u00a72/ib changelog \u00a7a- Mod changelog.")); //4
    	player.addChatMessage(new ChatComponentText("\u00a72/ib msg [true/false] \u00a7a- Toggle messages from this mod.")); //5
    	player.addChatMessage(new ChatComponentText("\u00a72/ib keepBlocks [true/false] \u00a7a- Keep IBs after creation.")); //6
    	player.addChatMessage(new ChatComponentText("\u00a72/ib effect [true/false] \u00a7a- Show particles on activate.")); //7
    	player.addChatMessage(new ChatComponentText("\u00a72/ib tpGrinder [true/false] \u00a7a- Teleport to the collecting room.")); //8
    	player.addChatMessage(new ChatComponentText("\u00a72/ib simpleMode [true/false] \u00a7a- Simple water/lava creation.")); //9
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
    
    public void list2(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //1
    	player.addChatMessage(new ChatComponentText("\u00a73\u00a7lInstant Blocks v" + Reference.VERSION + " - Command List - Page 2/2")); //2
    	player.addChatMessage(new ChatComponentText("\u00a72/ib useWands [true/false] \u00a7a- Toggle using wands.")); //4
    	player.addChatMessage(new ChatComponentText("\u00a72/ib packHouse [true/false] \u00a7a- Toggle packing house.")); //5
    	player.addChatMessage(new ChatComponentText("\u00a72/ib xp [number] \u00a7a- Amount of xp to give.")); //6
    	player.addChatMessage(new ChatComponentText("\u00a72/ib maxWL [number] \u00a7a- Max water/lava to create.")); //7
    	player.addChatMessage(new ChatComponentText("\u00a72/ib maxSuction [number] \u00a7a- Max water/lava to suck.")); //8
    	player.addChatMessage(new ChatComponentText("\u00a72/ib sound [sound] \u00a7a- Activation sound.")); //9
    	player.addChatMessage(new ChatComponentText("\u00a72/ib colorblock \u00a7a- Sets the texture of color block to held block texture.")); //3
        player.addChatMessage(new ChatComponentText("\u00a78\u00a7l=============================================")); //10
	}
    
    public void colorBlock(EntityPlayer player) {
    	Block block = Block.getBlockFromItem(player.getCurrentEquippedItem().getItem());
        BlockColor.blockTexture = block;
    	player.addChatMessage(new ChatComponentText("\u00a78[\u00a73Instant Blocks v" + Reference.VERSION + "\u00a78] \u00a7aColor Block Texture set to: " + block.getLocalizedName()));
    	
    	World world = player.worldObj;
    	
		for (int i = 0; i<world.loadedTileEntityList.size(); i++) {
    	    if (world.loadedTileEntityList.get(i) instanceof TileEntityColor) {
    	    	TileEntityColor tec = (TileEntityColor) world.loadedTileEntityList.get(i);
    	    	world.markBlockForUpdate(tec.xCoord, tec.yCoord, tec.zCoord);
    	    }
    	}
    }
	
	public void info(EntityPlayer player) {
        ItemStack held = player.getCurrentEquippedItem();
        String wandReq = (config.useWands == true) ? "Yes" : "No";
        String simpleWL = (config.simpleWL == true) ? "Simple" : "Full";
    	
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
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Skydive Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aCreates a structure from layer 1 to 256 out of coloured wool."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7aYes."));
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
    		player.addChatMessage(new ChatComponentText("\u00a72Mode: \u00a7a"+simpleWL+"."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a77N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibLava)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Lava Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aFills an area with lava source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)"));
    		player.addChatMessage(new ChatComponentText("\u00a72Maximum: \u00a7a" + config.max + " Lava Source Blocks."));
    		player.addChatMessage(new ChatComponentText("\u00a72Mode: \u00a7a"+simpleWL+"."));
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
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibHarvest)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Harvester Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aHarvests renewable resources in a radius configurable in the config."));
    		player.addChatMessage(new ChatComponentText("\u00a72Radius: \u00a7a" + config.radiusHarvest + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a77N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibLight)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Light Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aLights up dark areas in a radius configurable in the config."));
    		player.addChatMessage(new ChatComponentText("\u00a72Radius: \u00a7a" + config.radiusLight + "."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a77N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.ibSchematic)) {
    		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aInstant Schematic Block"));
    		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aGenerates any .schematic file from the /schematics/ folder."));
    		player.addChatMessage(new ChatComponentText("\u00a72Multi-Directional: \u00a7cNo."));
    	}
    	
    	//OTHER
    	
    	else if (held != null && held.getItem() == Item.getItemFromBlock(mb.color)) {
     		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aColor Block"));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aA block only avaliable in creative, used for Instant Statue and Instant Skydive."));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aWhen placed, will generate a random color."));
     	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.colorLadder)) {
     		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aColor Ladder Block"));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aA block only avaliable in creative, used for Instant Skydive."));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aWhen placed, will generate a random color."));
     	} else if (held != null && held.getItem() == Item.getItemFromBlock(mb.skydiveTP)) {
     		player.addChatMessage(new ChatComponentText("\u00a72Block: \u00a7aSkydive TP Block"));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aA block only avaliable in creative, used for Instant Skydive."));
     		player.addChatMessage(new ChatComponentText("\u00a72Description: \u00a7aSimply teleports the player up 256 blocks."));
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
		player.addChatMessage(new ChatComponentText("\u00a72- Added Instant Light Block."));
		player.addChatMessage(new ChatComponentText("\u00a72- Added Color Ladder block to be generated with color with the skydive."));
		player.addChatMessage(new ChatComponentText("\u00a72- Added Skydive TP block."));
		player.addChatMessage(new ChatComponentText("\u00a72- Added color block command (/ib colorblock)."));
		player.addChatMessage(new ChatComponentText("\u00a72- Removed Skydive Tp and Skydive wool color config options."));
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

	//NEW 1.7.10 IMPLEMENTATION
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}