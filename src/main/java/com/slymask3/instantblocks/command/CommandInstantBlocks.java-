package com.slymask3.instantblocks.command;

import com.slymask3.instantblocks.block.BlockColor;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Reference;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntityColor;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CommandInstantBlocks extends CommandBase {
	ArrayList<String> list = new ArrayList<>();
	int perPage = 7;

	public CommandInstantBlocks() {
		super();
		init();
	}

	public String getCommandName() {
        return Reference.MOD_ID;
    }

	public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List getCommandAliases() {
        return Collections.singletonList("ib");
    }
    
    public void processCommand(ICommandSender sender, String[] arg) {
    	EntityPlayer player = CommandBase.getCommandSenderAsPlayer(sender);
        if(arg.length == 1) {
        	if(arg[0].equalsIgnoreCase("1") || arg[0].equalsIgnoreCase("one") || arg[0].equalsIgnoreCase("help")) {
        		list(player,1);
        	} else if(arg[0].equalsIgnoreCase("2") || arg[0].equalsIgnoreCase("two")) {
        		list(player,2);
        	} else if(arg[0].equalsIgnoreCase("info")) {
        		info(player);
        	} else if(arg[0].equalsIgnoreCase("changelog")) {
                changelog(player);
        	} else if(arg[0].equalsIgnoreCase("blockcolor") || arg[0].equalsIgnoreCase("bc") || arg[0].equalsIgnoreCase("color") || arg[0].equalsIgnoreCase("colorblock") || arg[0].equalsIgnoreCase("cb")) {
                colorBlock(player);
        	} else {
				list(player,1);
        	}
        } else if(arg.length == 2) {
			if(arg[0].equalsIgnoreCase("help")) {
				if(arg[1].equalsIgnoreCase("1") || arg[1].equalsIgnoreCase("one")) {
					list(player,1);
				} else if(arg[1].equalsIgnoreCase("2") || arg[1].equalsIgnoreCase("two")) {
					list(player,2);
				} else {
					list(player,1);
				}
			} else if(arg[0].equalsIgnoreCase("msg")) {
        		Config.SHOW_MESSAGES = ConfigBoolean(arg, player, Config.SHOW_MESSAGES, "Messages");
        	} else if(arg[0].equalsIgnoreCase("keepBlocks")) {
        		Config.KEEP_BLOCKS = ConfigBoolean(arg, player, Config.KEEP_BLOCKS, "Keep Blocks");
        	} else if(arg[0].equalsIgnoreCase("effect")) {
        		Config.SHOW_EFFECTS = ConfigBoolean(arg, player, Config.SHOW_EFFECTS, "Effect");
        	} else if(arg[0].equalsIgnoreCase("tpGrinder")) {
        		Config.TP_GRINDER = ConfigBoolean(arg, player, Config.TP_GRINDER, "Grinder Teleport");
        	} else if(arg[0].equalsIgnoreCase("simpleLiquid") || arg[0].equalsIgnoreCase("simpleMode")) {
        		Config.SIMPLE_LIQUID = ConfigBoolean(arg, player, Config.SIMPLE_LIQUID, "Simple Mode");
        	} else if(arg[0].equalsIgnoreCase("useWands") || arg[0].equalsIgnoreCase("wandReq")) {
        		Config.USE_WANDS = ConfigBoolean(arg, player, Config.USE_WANDS, "Use Wands");
        	} else if(arg[0].equalsIgnoreCase("packWood") || arg[0].equalsIgnoreCase("packWoodenHouse") || arg[0].equalsIgnoreCase("packHouse")) {
        		Config.PACK_HOUSE = ConfigBoolean(arg, player, Config.PACK_HOUSE, "Pack Wooden House");
        	} else if(arg[0].equalsIgnoreCase("xp") || arg[0].equalsIgnoreCase("exp")) {
        		Config.XP_AMOUNT = ConfigInt(arg, player, Config.XP_AMOUNT, "XP From Instant Blocks");
        	} else if(arg[0].equalsIgnoreCase("maxLiquid") || arg[0].equalsIgnoreCase("max") || arg[0].equalsIgnoreCase("maxWaterLava")) {
        		Config.MAX_LIQUID = ConfigInt(arg, player, Config.MAX_LIQUID, "Max Water/Lava");
        	} else if(arg[0].equalsIgnoreCase("maxSuction") || arg[0].equalsIgnoreCase("maxSuck") || arg[0].equalsIgnoreCase("maxFill")) {
        		Config.MAX_FILL = ConfigInt(arg, player, Config.MAX_FILL, "Max Suction");
        	} else if(arg[0].equalsIgnoreCase("sound")) {
        		Config.SOUND = ConfigString(arg, player, Config.SOUND, "Sound");
        		IBHelper.sound(player.worldObj, Config.SOUND, (int)player.posX, (int)player.posY, (int)player.posZ);
        	} else {
				list(player,1);
         	}
        } else {
			list(player,1);
    	}

		Config.configuration.save();
    }

	private void init() {
		add("info", "Instant blocks and items info.");
		add("changelog", "Mod changelog.");
		add("msg [true|false]", "Toggle messages from this mod.");
		add("keepBlocks [true|false]", "Keep IBs after creation.");
		add("effect [true|false]", "Show particles on activation.");
		add("tpGrinder [true|false]", "Teleport to the collecting room.");
		add("simpleLiquid [true|false]", "Simple liquid creation.");
		add("useWands [true|false]", "Toggle using wands.");
		add("packHouse [true|false]", "Toggle packing house.");
		add("xp [number]", "Amount of xp to give.");
		add("maxLiquid [number]", "Max liquid to create.");
		add("maxSuction [number]", "Max liquid to fill.");
		add("sound [sound]", "Activation sound.");
		add("color", "Sets the texture of color block to held block.");
	}

	private void add(String command, String desc) {
		list.add(Colors._2 + "/ib " + command + Colors.a + " - " + desc);
	}

	private void send(EntityPlayer player, String message) {
		player.addChatMessage(new ChatComponentText(message));
	}

	private void line(EntityPlayer player) {
		send(player,Colors._8 + Colors.BOLD + "=============================================");
	}

	private void list(EntityPlayer player, int page) {
		line(player);
		send(player,Colors._3 + Colors.BOLD + Reference.MOD_NAME + " - Command List - Page " + page + "/" + (int)Math.ceil((float)list.size() / perPage));
		Iterator<String> iterator = list.iterator();
		for(int i=0; i<(page-1)*perPage; i++) {
			iterator.next();
		}
		for(int i=0; i<perPage && iterator.hasNext(); i++) {
			send(player,iterator.next());
		}
		line(player);
	}
    
    public void colorBlock(EntityPlayer player) {
    	Block block = Block.getBlockFromItem(player.getCurrentEquippedItem().getItem());
		int meta = player.getCurrentEquippedItem().getItemDamage();
		BlockColor.blockType = block;
		BlockColor.blockMeta = meta;
    	send(player,Strings.PREFIX + Colors.a + "Color Block Texture set to: " + block.getLocalizedName());
    	World world = player.worldObj;
		for(int i = 0; i<world.loadedTileEntityList.size(); i++) {
    	    if(world.loadedTileEntityList.get(i) instanceof TileEntityColor) {
    	    	TileEntityColor tec = (TileEntityColor) world.loadedTileEntityList.get(i);
    	    	world.markBlockForUpdate(tec.xCoord, tec.yCoord, tec.zCoord);
    	    }
    	}
    }
	
	public void info(EntityPlayer player) {
        ItemStack held = player.getCurrentEquippedItem();
        String wandReq = (Config.USE_WANDS) ? "Yes" : "No";
        String simpleWL = (Config.SIMPLE_LIQUID) ? "Simple" : "Full";
    	
        line(player);
    	send(player,Colors._3 + Colors.BOLD + Reference.MOD_NAME + " - Info");
    	
    	//INSTANT BLOCKS
    	if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibWoodHouse)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Wooden House Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a simple house with a bed, chests, furnaces, etc.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibMiningLadder)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Mining Ladder Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a ladder down to layer 12, which is the 'diamond' area.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibGlassDome)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Glass Dome Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a small 9x9 glass dome. Useful for underwater.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibFarm)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Farm Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a simple farm with 56 seeds. You also have a 5% chance that the farm will have carrots planted instead of wheat, and another 5% chance that the farm will have potatoes planted instead of wheat.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibSkydive)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Skydive Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a structure from layer 1 to 256 out of coloured wool.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibGrinder)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Grinder Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a simple experience orbs grinder. Only works for zombies and skeletons. The zombies/skeletons will be dragged by the current up, then dropped down into the collecting room, where you hit them once or twice with your fist.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.c + "No.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibPool)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Pool Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a simple pool with two diving boards.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibUp)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Escape Ladder Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Place on a wall underground, and it will create a ladder going all the way up to the surface.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibWater)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Water Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Fills an area with water source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)");
    		send(player,Colors._2 + "Maximum: " + Colors.a + Config.MAX_LIQUID + " Water Source Blocks.");
    		send(player,Colors._2 + "Mode: " + Colors.a+simpleWL+".");
    		send(player,Colors._2 + "Multi-Directional: " + Colors._7 + "N/A.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibLava)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Lava Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Fills an area with lava source blocks. 'Simple' mode only fills one layer that the core block is on. 'Full' mode fills the layer that the core block is on, and all the layers below it. (Change to 'Simple' in the config file for 'not-so-good' computers.)");
    		send(player,Colors._2 + "Maximum: " + Colors.a + Config.MAX_LIQUID + " Lava Source Blocks.");
    		send(player,Colors._2 + "Mode: " + Colors.a+simpleWL+".");
    		send(player,Colors._2 + "Multi-Directional: " + Colors._7 + "N/A.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibSuction)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Suction Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Sucks in all water/lava around it.");
    		send(player,Colors._2 + "Maximum: " + Colors.a + Config.MAX_FILL + ".");
    		send(player,Colors._2 + "Multi-Directional: " + Colors._7 + "N/A.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibRail)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Rail Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a simple railway path going forward 37 blocks.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibStatue)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Statue Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Creates a statue of any Minecraft player's skin.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.a + "Yes.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibHarvest)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Harvester Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Harvests renewable resources in a radius Configurable in the config.");
    		send(player,Colors._2 + "Radius: " + Colors.a + Config.RADIUS_HARVEST + ".");
    		send(player,Colors._2 + "Multi-Directional: " + Colors._7 + "N/A.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibLight)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Light Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Lights up dark areas in a radius Configurable in the config.");
    		send(player,Colors._2 + "Radius: " + Colors.a + Config.RADIUS_LIGHT + ".");
    		send(player,Colors._2 + "Multi-Directional: " + Colors._7 + "N/A.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibSchematic)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Schematic Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Generates any .schematic file from the /schematics/ folder.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.c + "No.");
    	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.ibTree)) {
    		send(player,Colors._2 + "Block: " + Colors.a + "Instant Huge Tree Block");
    		send(player,Colors._2 + "Description: " + Colors.a + "Generates any type of huge tree.");
    		send(player,Colors._2 + "Multi-Directional: " + Colors.c + "No.");
    	}
    	
    	//OTHER
    	else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.color)) {
     		send(player,Colors._2 + "Block: " + Colors.a + "Color Block");
     		send(player,Colors._2 + "Description: " + Colors.a + "A block only available in creative, used for Instant Statue and Instant Skydive.");
     		send(player,Colors._2 + "Description: " + Colors.a + "When placed, will generate a random color.");
     	} else if(held != null && held.getItem() == Item.getItemFromBlock(ModBlocks.skydiveTP)) {
     		send(player,Colors._2 + "Block: " + Colors.a + "Skydive TP Block");
     		send(player,Colors._2 + "Description: " + Colors.a + "A block only available in creative, used for Instant Skydive.");
     		send(player,Colors._2 + "Description: " + Colors.a + "Simply teleports the player up 256 blocks.");
     	}
    	
    	//ITEMS
    	else if(held != null && held.getItem() == ModItems.ibWandWood) {
    		send(player,Colors._2 + "Item: " + Colors.a + "Instant Wooden Wand");
    		send(player,Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks.");
    		send(player,Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + ".");
    		send(player,Colors._2 + "Required: " + Colors.a + wandReq + ".");
    	} else if(held != null && held.getItem() == ModItems.ibWandStone) {
    		send(player,Colors._2 + "Item: " + Colors.a + "Instant Stone Wand");
    		send(player,Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks.");
    		send(player,Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + ".");
    		send(player,Colors._2 + "Required: " + Colors.a + wandReq + ".");
    	} else if(held != null && held.getItem() == ModItems.ibWandIron) {
    		send(player,Colors._2 + "Item: " + Colors.a + "Instant Iron Wand");
    		send(player,Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks.");
    		send(player,Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + ".");
    		send(player,Colors._2 + "Required: " + Colors.a + wandReq + ".");
    	} else if(held != null && held.getItem() == ModItems.ibWandGold) {
    		send(player,Colors._2 + "Item: " + Colors.a + "Instant Golden Wand");
    		send(player,Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks.");
    		send(player,Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + ".");
    		send(player,Colors._2 + "Required: " + Colors.a + wandReq + ".");
    	} else if(held != null && held.getItem() == ModItems.ibWandDiamond) {
    		send(player,Colors._2 + "Item: " + Colors.a + "Instant Diamond Wand");
    		send(player,Colors._2 + "Description: " + Colors.a + "Used to activate Instant Blocks.");
    		send(player,Colors._2 + "Uses: " + Colors.a + (held.getMaxDamage() - held.getItemDamage() + 1) + "/" + (held.getMaxDamage() + 1) + ".");
    		send(player,Colors._2 + "Required: " + Colors.a + wandReq + ".");
    	} else if(held == null) {
    		send(player,Colors.c + "Hold an Instant Block or Item, and perform this command to get information on the Instant Block or Item.");
    	} else {
    		send(player,Colors.c + "'" + held.getDisplayName() + "' is not an Instant Block or Item.");
    		send(player,Colors.c + "Hold an Instant Block or Item, and perform this command to get information on the Instant Block or Item.");
    	}
    	
    	line(player);
    }
	
	public void changelog(EntityPlayer player) {
		line(player);
    	send(player,Colors._3 + Colors.BOLD + Reference.MOD_NAME + " - Changelog");
		send(player,Colors._2 + "- Added Instant Schematic Block.");
		send(player,Colors._2 + "- Added Instant Huge Tree Block.");
		line(player);
	}
	
	public boolean ConfigBoolean(String[] arg, EntityPlayer player, boolean b, String var) {
		if(arg[1].equalsIgnoreCase("true")) {
	    	send(player,Strings.PREFIX + Colors.a + var + " set to true.");
			return true;
		} else if(arg[1].equalsIgnoreCase("false")) {
	    	send(player,Strings.PREFIX + Colors.c + var + " set to false.");
			return false;
		} else {
			send(player,Strings.PREFIX + Colors.c + "Correct Usage: /ib " + arg[0] + " [true|false]");
			return b;
		}
	}
	
	public int ConfigInt(String[] arg, EntityPlayer player, int i, String var) {
		int n = Integer.parseInt(arg[1]);
		send(player,Strings.PREFIX + Colors.a + var + " set to " + n + ".");
		return n;
	}
	
	public String ConfigString(String[] arg, EntityPlayer player, String s, String var) {
		send(player,Strings.PREFIX + Colors.a + var + " set to '" + arg[1] + "'.");
		return arg[1];
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/ib help";
	}
}