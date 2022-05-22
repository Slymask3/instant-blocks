package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.*;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantHarvest extends BlockContainer implements ITileEntityProvider {

	public BlockInstantHarvest() {
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName(Reference.MOD_ID + ":" + Names.Blocks.IB_HARVEST);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
        setBlockTextureName(Textures.Harvest.SIDE0);
	}
	
	public int quantityDropped(Random random) {
		return 1;
	}

	public static IIcon top;
	public static IIcon side0;
	public static IIcon side1;

	public void registerBlockIcons(IIconRegister ir) {
		top = ir.registerIcon(Textures.Harvest.TOP);
		side0 = ir.registerIcon(Textures.Harvest.SIDE0);
		side1 = ir.registerIcon(Textures.Harvest.SIDE1);
	}

	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return Blocks.planks.getIcon(side, 0);
		} else if (side == 1) {
			return top;
		} else if (side == 3 || side == 5) {
			return side0;
		} else {
			return side1;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityHarvest();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.useWands) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.wandReq, Colors.c);
				return true;
			}
		}
		
		//LogHelper.info("player == " + player);
		
		//mainPlayer = player;
		
		player.openGui(InstantBlocks.instance, GuiID.HARVEST.ordinal(), world, x, y, z);
		
		return true;
	}
	
	/*public void handleClientSide(World world, int x, int y, int z, String playerS) {
		//EntityClientPlayerMP player = (EntityClientPlayerMP) world.getPlayerEntityByName(playerS);
		
		//EntityPlayer player = world.getPlayerEntityByName(playerS);
		
		//player.

		LogHelper.info("playerS == " + playerS);
		LogHelper.info("player == " + player);
		
		IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, Strings.harvestCreate, Colors.a);
	}*/
	
	public void build(World world, int x, int y, int z, String playerS, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		
		//if (canHarvest(world, x, y, z, ConfigurationHandler.radiusHarvest, logOak, logSpruce, logBirch, logJungle, logAcacia, logDark, wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart, replant)) {
			harvest(world, x, y, z, Config.radiusHarvest, logOak, logSpruce, logBirch, logJungle, logAcacia, logDark, wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart, replant);
			
			//organizeChest(world, x, y, z);
			
	        IBHelper.sound(world, Config.sound, x, y, z);
			
			ItemStack is = player.getCurrentEquippedItem();
			
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				is.damageItem(1, player);
			}
			
//			IBHelper.xp(world, mainPlayer, ConfigurationHandler.xp);
//	        IBHelper.effectFull(world, "reddust", x, y, z);
//	        IBHelper.msg(mainPlayer, Strings.harvestCreate, Colors.a);
//		} else {
//			IBHelper.msg(mainPlayer, "\u00c7aThere are no renewable resources to harvest in a radius of " + ConfigurationHandler.radiusLight + ".", Colors.c);
//		}
	}
	
	public void harvest(World world, int X, int Y, int Z, int radius, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		world.setBlock(X, Y, Z, Blocks.chest);
		TileEntityChest chest = (TileEntityChest)world.getTileEntity(X, Y, Z);
		
		Random rand = new Random();
    	
        int x = (int) (X -radius);
        int y = (int) (Y +radius);
        int z = (int) (Z -radius);
   
        int bx = x;
        int bz = z;
 
        for (int i=0; i<radius*2+1; i++) {
            for (int j=0; j<radius*2+1; j++) {
                for (int k=0; k<radius*2+1; k++) {
                    Block block = world.getBlock(x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    if (chest.getStackInSlot(26) != null) {
                    	if(world.getBlock(X+1, Y, Z) == Blocks.air) {
                    		world.setBlock(X+1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X+1, Y, Z);
                    		X++;
                    	} else if(world.getBlock(X-1, Y, Z) == Blocks.air) {
                    		world.setBlock(X-1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X-1, Y, Z);
                    		X--;
                    	} else if(world.getBlock(X, Y, Z+1) == Blocks.air) {
                    		world.setBlock(X, Y, Z+1, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X, Y, Z+1);
                    		Z++;
                    	} else if(world.getBlock(X, Y, Z-1) == Blocks.air) {
                    		world.setBlock(X, Y, Z-1, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X, Y, Z-1);
                    		Z--;
                    	} else {
                    		world.setBlock(X+1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X+1, Y, Z);
                    		X++;
                    	}
                    	LogHelper.info("had to create another chest");
                    }
                    
                    if ((block == Blocks.log && logOak) && (meta == 0 || meta == 4 || meta == 8 || meta == 12)) { //OAK
                    	IBHelper.addItemsToChest(chest, block, 1, 0);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if ((block == Blocks.log && logSpruce) && (meta == 1 || meta == 5 || meta == 9 || meta == 13)) { //SPRUCE
                    	IBHelper.addItemsToChest(chest, block, 1, 1);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 1, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if ((block == Blocks.log && logBirch) && (meta == 2 || meta == 6 || meta == 10 || meta == 14)) { //BIRCH
                    	IBHelper.addItemsToChest(chest, block, 1, 2);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 2, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if ((block == Blocks.log && logJungle) && (meta == 3 || meta == 7 || meta == 11 || meta == 15)) { //JUNGLE
                    	IBHelper.addItemsToChest(chest, block, 1, 3);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 3, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if ((block == Blocks.log2 && logAcacia) && (meta == 0 || meta == 4 || meta == 8 || meta == 12)) { //ACACIA
                    	IBHelper.addItemsToChest(chest, block, 1, 0);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 4, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if ((block == Blocks.log2 && logDark) && (meta == 1 || meta == 5 || meta == 9 || meta == 13)) { //DARK OAK
                    	IBHelper.addItemsToChest(chest, block, 1, 1);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 5, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.wheat && meta == 7 && wheat) { //WHEAT
                    	IBHelper.addItemsToChest(chest, Items.wheat, 1, 0);
                    	if (replant) {
                    		world.setBlock(x, y, z, Blocks.wheat, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.carrots && meta == 7 && carrot) { //CARROT
                    	IBHelper.addItemsToChest(chest, Items.carrot, rand.nextInt(4)+1, 0);
                    	if (replant) {
                    		world.setBlock(x, y, z, Blocks.carrots, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.potatoes && meta == 7 && potato) { //POTATO
                    	IBHelper.addItemsToChest(chest, Items.potato, rand.nextInt(4)+1, 0);
                    	if (replant) {
                    		world.setBlock(x, y, z, Blocks.potatoes, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.cactus && cactus) { //CACTUS
                    	if (world.getBlock(x, y-1, z) == Blocks.sand && replant) {
                    		world.setBlock(x, y, z, Blocks.cactus, 0, 2);
                    	} else {
                        	IBHelper.addItemsToChest(chest, block, 1, 0);
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.pumpkin && pumpkin) { //PUMPKIN
                    	IBHelper.addItemsToChest(chest, block, 1, 0);
                    	world.setBlock(x, y, z, Blocks.air);
                    } else if (block == Blocks.melon_block && melon) { //MELON
                    	IBHelper.addItemsToChest(chest, Items.melon, rand.nextInt(5)+3, 0);
                    	world.setBlock(x, y, z, Blocks.air);
                    } else if (block == Blocks.reeds && sugarcane) { //SUGARCANE
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass || world.getBlock(x, y-1, z) == Blocks.sand) && replant) {
                    		world.setBlock(x, y, z, Blocks.reeds, 0, 2);
                    	} else {
                        	IBHelper.addItemsToChest(chest, Items.reeds, 1, 0);
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.cocoa && meta == 6 && cocoa) { //COCOA
                    	IBHelper.addItemsToChest(chest, Items.dye, 3, 3);
                    	world.setBlock(x, y, z, Blocks.air);
                    } else if ((block == Blocks.red_mushroom_block || block == Blocks.red_mushroom) && mushroom) { //MUSHROOM RED
                    	IBHelper.addItemsToChest(chest, Blocks.red_mushroom, 1, 0);
                    	world.setBlock(x, y, z, Blocks.air);
                    } else if ((block == Blocks.brown_mushroom_block || block == Blocks.brown_mushroom) && mushroom) { //MUSHROOM BROWN
                    	IBHelper.addItemsToChest(chest, Blocks.brown_mushroom, 1, 0);
                    	world.setBlock(x, y, z, Blocks.air);
                    } else if (block == Blocks.nether_wart && meta == 3 && netherwart) { //NETHERWART
                    	IBHelper.addItemsToChest(chest, Items.nether_wart, rand.nextInt(3)+2, 0);
                    	if (replant) {
                    		world.setBlock(x, y, z, Blocks.nether_wart, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    }
                    
                    /**
                     * Quick test code for Instant Light Up / Torch Block
                     *
                    //LogHelper.info("getAmbientOcclusionLightValue() == "+block.getAmbientOcclusionLightValue());
                    LogHelper.info("getBlockLightValue() == "+world.getBlockLightValue(x, y, z));

                    Block block1 = world.getBlock(x+1, y, z);
                    Block block2 = world.getBlock(x-1, y, z);
                    //Block block3 = world.getBlock(x, y+1, z);
                    Block block4 = world.getBlock(x, y-1, z);
                    Block block5 = world.getBlock(x, y, z+1);
                    Block block6 = world.getBlock(x, y, z-1);
                    
                    Block air = Blocks.air;
                    
                    if((world.getBlockLightValue(x, y, z) < 10) && (block == air) && (block1 != air || block2 != air || block4 != air || block5 != air || block6 != air)) {
                    	world.setBlock(x, y, z, Blocks.torch);
                    }
                    */
                    
                    x++;
                }
                z++;
                x = bx;
            }
            z = bz;
            x = bx;
            y--;
        }
    }
	
//	public void organizeChest(World world, int x, int y, int z) {
//		TileEntityChest chest = (TileEntityChest)world.getTileEntity(x, y, z);
//		
//		ItemStack is[] = new ItemStack[chest.getSizeInventory()];
//		
//		for(int i=0; i<is.length; i++) {
//			is[i] = chest.getStackInSlot(i);
//		}
//		
//		LogHelper.info(is.toString());
//		
//		//Arrays.sort(is);
//		
//		//is = sort(is);
//		
//		//chest.
//		
//		
//		
//		is[0].getDisplayName();
//		is[0].getItem().
//		
//		
//		for(int i=0; i<is.length; i++) {
//			chest.setInventorySlotContents(i, is[i]);
//		}
//
//		LogHelper.info(is.toString());
//	}
	
//	public ItemStack[] sort(ItemStack[] is) {
//		
//	}
	
//	public boolean canHarvest(World world, int X, int Y, int Z, int radius, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
//		int amount = 0;
//		
//		int x = (int) (X -radius);
//        int y = (int) (Y +radius);
//        int z = (int) (Z -radius);
//   
//        int bx = x;
//        int bz = z;
// 
//        for (int i=0; i<radius*2+1; i++) {
//            for (int j=0; j<radius*2+1; j++) {
//                for (int k=0; k<radius*2+1; k++) {
//                    Block block = world.getBlock(x, y, z);
//                    int meta = world.getBlockMetadata(x, y, z);
//                    
//                    if ((block == Blocks.log && logOak) && (meta == 0 || meta == 4 || meta == 8 || meta == 12)) { //OAK
//                    	amount++;
//                    } else if ((block == Blocks.log && logSpruce) && (meta == 1 || meta == 5 || meta == 9 || meta == 13)) { //SPRUCE
//                    	amount++;
//                    } else if ((block == Blocks.log && logBirch) && (meta == 2 || meta == 6 || meta == 10 || meta == 14)) { //BIRCH
//                    	amount++;
//                    } else if ((block == Blocks.log && logJungle) && (meta == 3 || meta == 7 || meta == 11 || meta == 15)) { //JUNGLE
//                    	amount++;
//                    } else if ((block == Blocks.log2 && logAcacia) && (meta == 0 || meta == 4 || meta == 8 || meta == 12)) { //ACACIA
//                    	amount++;
//                    } else if ((block == Blocks.log2 && logDark) && (meta == 1 || meta == 5 || meta == 9 || meta == 13)) { //DARK OAK
//                    	amount++;
//                    } else if (block == Blocks.wheat && meta == 7 && wheat) { //WHEAT
//                    	amount++;
//                    } else if (block == Blocks.carrots && meta == 7 && carrot) { //CARROT
//                    	amount++;
//                    } else if (block == Blocks.potatoes && meta == 7 && potato) { //POTATO
//                    	amount++;
//                    } else if (block == Blocks.cactus && cactus) { //CACTUS
//                    	amount++;
//                    } else if (block == Blocks.pumpkin && pumpkin) { //PUMPKIN
//                    	amount++;
//                    } else if (block == Blocks.melon_block && melon) { //MELON
//                    	amount++;
//                    } else if (block == Blocks.reeds && sugarcane) { //SUGARCANE
//                    	amount++;
//                    } else if (block == Blocks.cocoa && meta == 6 && cocoa) { //COCOA
//                    	amount++;
//                    } else if ((block == Blocks.red_mushroom_block || block == Blocks.red_mushroom) && mushroom) { //MUSHROOM RED
//                    	amount++;
//                    } else if ((block == Blocks.brown_mushroom_block || block == Blocks.brown_mushroom) && mushroom) { //MUSHROOM BROWN
//                    	amount++;
//                    } else if (block == Blocks.nether_wart && meta == 3 && netherwart) { //NETHERWART
//                    	amount++;
//                    }
//                    x++;
//                }
//                z++;
//                x = bx;
//            }
//            z = bz;
//            x = bx;
//            y--;
//        }
//        
//        if (amount > 0) {
//        	return true;
//        } else {
//        	return false;
//        }
//    }
}
