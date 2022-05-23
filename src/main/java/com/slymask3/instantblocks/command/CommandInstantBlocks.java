package com.slymask3.instantblocks.command;

import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.util.ColorHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class CommandInstantBlocks extends CommandBase {
	public String getCommandName() {
        return Reference.MOD_ID;
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
        		Config.SHOW_MESSAGES = ConfigBoolean(arg, player, Config.SHOW_MESSAGES, "Messages");
        	} else if (arg[0].equalsIgnoreCase("keepBlocks")) {
        		Config.KEEP_BLOCKS = ConfigBoolean(arg, player, Config.KEEP_BLOCKS, "Keep Blocks");
        	} else if (arg[0].equalsIgnoreCase("effect")) {
        		Config.SHOW_EFFECTS = ConfigBoolean(arg, player, Config.SHOW_EFFECTS, "Effect");
        	} else if (arg[0].equalsIgnoreCase("tpGrinder")) {
        		Config.TP_GRINDER = ConfigBoolean(arg, player, Config.TP_GRINDER, "Grinder Teleport");
        	} else if (arg[0].equalsIgnoreCase("simpleWL") || arg[0].equalsIgnoreCase("simpleMode")) {
        		Config.SIMPLE_LIQUID = ConfigBoolean(arg, player, Config.SIMPLE_LIQUID, "Simple Mode");
        	} else if (arg[0].equalsIgnoreCase("useWands") || arg[0].equalsIgnoreCase("wandReq")) {
        		Config.USE_WANDS = ConfigBoolean(arg, player, Config.USE_WANDS, "Use Wands");
        	} else if (arg[0].equalsIgnoreCase("packWood") || arg[0].equalsIgnoreCase("packWoodenHouse") || arg[0].equalsIgnoreCase("packHouse")) {
        		Config.PACK_HOUSE = ConfigBoolean(arg, player, Config.PACK_HOUSE, "Pack Wooden House");
        	} else if (arg[0].equalsIgnoreCase("xp") || arg[0].equalsIgnoreCase("exp")) {
        		Config.XP_AMOUNT = ConfigInt(arg, player, Config.XP_AMOUNT, "XP From Instant Blocks");
        	} else if (arg[0].equalsIgnoreCase("maxWL") || arg[0].equalsIgnoreCase("max") || arg[0].equalsIgnoreCase("maxWaterLava")) {
        		Config.MAX_LIQUID = ConfigInt(arg, player, Config.MAX_LIQUID, "Max Water/Lava");
        	} else if (arg[0].equalsIgnoreCase("maxSuction") || arg[0].equalsIgnoreCase("maxSuck")) {
        		Config.MAX_FILL = ConfigInt(arg, player, Config.MAX_FILL, "Max Suction");
        	} else if (arg[0].equalsIgnoreCase("sound")) {
        		Config.SOUND = ConfigString(arg, player, Config.SOUND, "Sound");
        		IBHelper.sound(player.worldObj, Config.SOUND, (int)player.posX, (int)player.posY, (int)player.posZ);
        	}
        	
         	else {
             	list1(player);
         	}
        
        } else {
    		list1(player);
    	}
    }
    
    public void list1(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "=============================================")); //1
    	player.addChatMessage(new ChatComponentText(Colors._3 + Colors.BOLD + "InstantBlocks" + " - Command List - Page 1/2")); //2
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib info " + Colors.a + "- Instant blocks and items info.")); //3
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib changelog " + Colors.a + "- Mod changelog.")); //4
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib msg [true/false] " + Colors.a + "- Toggle messages from this mod.")); //5
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib keepBlocks [true/false] " + Colors.a + "- Keep IBs after creation.")); //6
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib effect [true/false] " + Colors.a + "- Show particles on activate.")); //7
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib tpGrinder [true/false] " + Colors.a + "- Teleport to the collecting room.")); //8
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib simpleMode [true/false] " + Colors.a + "- Simple water/lava creation.")); //9
        player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "=============================================")); //10
	}
    
    public void list2(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "=============================================")); //1
    	player.addChatMessage(new ChatComponentText(Colors._3 + Colors.BOLD + "InstantBlocks" + " - Command List - Page 2/2")); //2
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib useWands [true/false] " + Colors.a + "- Toggle using wands.")); //4
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib packHouse [true/false] " + Colors.a + "- Toggle packing house.")); //5
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib xp [number] " + Colors.a + "- Amount of xp to give.")); //6
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib maxWL [number] " + Colors.a + "- Max water/lava to create.")); //7
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib maxSuction [number] " + Colors.a + "- Max water/lava to suck.")); //8
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib sound [sound] " + Colors.a + "- Activation sound.")); //9
    	player.addChatMessage(new ChatComponentText(Colors._2 + "/ib colorblock " + Colors.a + "- Sets the texture of color block to held block texture.")); //3
        player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "=============================================")); //10
	}
    
    public void colorBlock(EntityPlayer player) {
    	Block block = Block.getBlockFromItem(player.getCurrentEquippedItem().getItem());
		int meta = player.getCurrentEquippedItem().getItemDamage();
		BlockColor.blockType = block;
		BlockColor.blockMeta = meta;
    	player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.a + "Color Block Texture set to: " + block.getLocalizedName()));
    	
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
        String wandReq = (Config.USE_WANDS) ? "Yes" : "No";
        String simpleWL = (Config.SIMPLE_LIQUID) ? "Simple" : "Full";
    	
        player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "============================================="));
    	player.addChatMessage(new ChatComponentText(Colors._3 + Colors.BOLD + "InstantBlocks" + " - Info"));
    	
    	//INSTANT BLOCKS
    	
    	if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibWood)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Wooden House Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a simple house with a bed, chests, furnaces, etc."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibLadder)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Mining Ladder Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a ladder down to layer 12, which is the 'diamond' area."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibGlassDome)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Glass Dome Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a small 9x9 glass dome. Useful for underwater."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibFarm)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Farm Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a simple farm with 56 seeds. You also have a 5% chance that the farm will have carrots planted instead of wheat, and another 5% chance that the farm will have potatoes planted instead of wheat."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibFall)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Skydive Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a structure from layer 1 to 256 out of coloured wool."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibGrinder)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Grinder Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a simple experience orbs grinder. Only works for zombies and skeletons. The zombies/skeletons will be dragged by the current up, then dropped down into the collecting room, where you hit them once or twice with your fist."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.c + "No."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibPool)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Pool Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a simple pool with two diving boards."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibUp)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Escape Ladder Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Place on a wall underground, and it will create a ladder going all the way up to the surface."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibWater)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Water Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Fills an area with water source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Maximum: " + Colors.a + Config.MAX_LIQUID + " Water Source Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Mode: " + Colors.a+simpleWL+"."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors._7 + "N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibLava)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Lava Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Fills an area with lava source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Maximum: " + Colors.a + Config.MAX_LIQUID + " Lava Source Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Mode: " + Colors.a+simpleWL+"."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors._7 + "N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibSucker)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Suction Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Sucks in all water/lava around it."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Maximum: " + Colors.a + Config.MAX_FILL + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors._7 + "N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibRail)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Rail Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a simple railway path going forward 37 blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibStatue)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Statue Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Creates a statue of any Minecraft player's skin."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.a + "Yes."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibHarvest)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Harvester Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Harvests renewable resources in a radius Configurable in the config."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Radius: " + Colors.a + Config.RADIUS_HARVEST + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors._7 + "N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibLight)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Light Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Lights up dark areas in a radius Configurable in the config."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Radius: " + Colors.a + Config.RADIUS_LIGHT + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors._7 + "N/A."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibSchematic)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Schematic Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Generates any .schematic file from the /schematics/ folder."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.c + "No."));
    	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibTree)) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Instant Huge Tree Block"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Generates any type of huge tree."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Multi-Directional: " + Colors.c + "No."));
    	}
    	
    	//OTHER
    	
    	else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.color)) {
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Color Block"));
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "A block only avaliable in creative, used for Instant Statue and Instant Skydive."));
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "When placed, will generate a random color."));
     	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.colorLadder)) {
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Color Ladder Block"));
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "A block only avaliable in creative, used for Instant Skydive."));
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "When placed, will generate a random color."));
     	} else if (held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.skydiveTP)) {
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Block: " + Colors.a + "Skydive TP Block"));
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "A block only avaliable in creative, used for Instant Skydive."));
     		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Simply teleports the player up 256 blocks."));
     	}
    	
    	//ITEMS
    	
    	else if (held != null && held.getItem() == ModItems.ibWandWood) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Item: " + Colors.a + "Instant Wooden Wand"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Required: " + Colors.a + wandReq + "."));
    	} else if (held != null && held.getItem() == ModItems.ibWandStone) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Item: " + Colors.a + "Instant Stone Wand"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Required: " + Colors.a + wandReq + "."));
    	} else if (held != null && held.getItem() == ModItems.ibWandIron) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Item: " + Colors.a + "Instant Iron Wand"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Required: " + Colors.a + wandReq + "."));
    	} else if (held != null && held.getItem() == ModItems.ibWandGold) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Item: " + Colors.a + "Instant Golden Wand"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Required: " + Colors.a + wandReq + "."));
    	} else if (held != null && held.getItem() == ModItems.ibWandDiamond) {
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Item: " + Colors.a + "Instant Diamond Wand"));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + "."));
    		player.addChatMessage(new ChatComponentText(Colors._2 + "Required: " + Colors.a + wandReq + "."));
    	} else if (held == null) {
    		player.addChatMessage(new ChatComponentText(Colors.c + "Hold an Instant Block or Item, and perform this command to get information on the Instant Block or Item."));
    	} else {
    		player.addChatMessage(new ChatComponentText(Colors.c + "'" + held.getDisplayName() + "' is not an Instant Block or Item."));
    		player.addChatMessage(new ChatComponentText(Colors.c + "Hold an Instant Block or Item, and perform this command to get information on the Instant Block or Item."));
    	}
    	
    	player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "============================================="));
    }
	
	public void changelog(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "============================================="));
    	player.addChatMessage(new ChatComponentText(Colors._3 + Colors.BOLD + "InstantBlocks" + " - Changelog"));
		player.addChatMessage(new ChatComponentText(Colors._2 + "- Added Instant Schematic Block."));
		player.addChatMessage(new ChatComponentText(Colors._2 + "- Added Instant Huge Tree Block."));
		player.addChatMessage(new ChatComponentText(Colors._8 + Colors.BOLD + "============================================="));
	}
	
	public boolean ConfigBoolean(String[] arg, EntityPlayer player, boolean b, String var) {
		if (arg[1].equalsIgnoreCase("true")) {
	    	player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.a + var + " set to true."));
			return true;
		} else if (arg[1].equalsIgnoreCase("false")) {
	    	player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.c + var + " set to false."));
			return false;
		} else {
			player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.c + "Correct Usage: /ib " + arg[0] + " [true/false]"));
			return b;
		}
	}
	
	public int ConfigInt(String[] arg, EntityPlayer player, int i, String var) {
		int n;
		
		n = Integer.parseInt(arg[1]);
			
		player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.a + var + " set to " + n + "."));
		return n;
	}
	
	public String ConfigString(String[] arg, EntityPlayer player, String s, String var) {
		player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.a + var + " set to '" + arg[1] + "'."));
		return arg[1];
	}
	
	public int ConfigWool(String[] arg, EntityPlayer player, int i, String var) {
		int m = arg[1].matches(".*\\d.*") ? Integer.parseInt(arg[1]) : ColorHelper.toMeta(arg[1]);
			
		player.addChatMessage(new ChatComponentText(Colors._8 + "[" + Colors._3 + "InstantBlocks" + Colors._8 + "] " + Colors.a + var + " set to " + ColorHelper.toColor2(m) + Colors.a + "."));
		return m;
	}

	//NEW 1.7.10 IMPLEMENTATION
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}