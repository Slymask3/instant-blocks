package com.slymask3.instantblocks.block.instant;

import java.util.Random;

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

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.utility.IBHelper;

public class BlockInstantHarvest extends BlockContainer implements ITileEntityProvider {
	
	public BlockInstantHarvest() {
		super(Material.wood);
		setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
		setBlockName("instantblocks:" + Names.Blocks.IB_HARVEST);
		setHardness(1.5F);
		setResistance(2000F);
		setStepSound(Block.soundTypeWood);
        setBlockTextureName(Textures.Statue.FRONT);
	}
	
	public int quantityDropped(Random random) {
		return 1;
	}

	public static IIcon side;

	public void registerBlockIcons(IIconRegister ir) {
		side = ir.registerIcon(Textures.Statue.FRONT);
	}

	public IIcon getIcon(int side, int meta) {
		return this.side;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityHarvest();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (ConfigurationHandler.useWands == true) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.wandReq, Colors.c);
				return true;
			}
		}
		
		player.openGui(InstantBlocks.instance, GuiID.HARVEST.ordinal(), world, x, y, z);
		
		return true;
	}
	
	public void build(World world, int x, int y, int z, String playerS, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);
		
		harvest(world, x, y, z, 30, logOak, logSpruce, logBirch, logJungle, logAcacia, logDark, wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart, replant);
		
		//IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibStatue);
        IBHelper.xp(world, player, ConfigurationHandler.xp);
        IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
        IBHelper.effectFull(world, "reddust", x, y, z);
        IBHelper.msg(player, Strings.harvestCreate, Colors.a);
		
		ItemStack is = player.getCurrentEquippedItem();
		
		if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
			is.damageItem(1, player);
		}
	}
	
	public void harvest(World world, int X, int Y, int Z, int radius, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		world.setBlock(X, Y, Z, Blocks.chest);
		TileEntityChest chest = (TileEntityChest)world.getTileEntity(X, Y, Z);
		
		Random rand = new Random();
    	
        int x = (int) (X -radius);
        int y = (int) (Y -radius);
        int z = (int) (Z -radius);
   
        int bx = x;
        int bz = z;
 
        for (int i=0; i<radius*2+1; i++) {
            for (int j=0; j<radius*2+1; j++) {
                for (int k=0; k<radius*2+1; k++) {
                    Block block = world.getBlock(x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    if (block == Blocks.log && meta == 0 && logOak) { //OAK
                    	IBHelper.addItemsToChest(chest, block, 1, meta);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.log && meta == 1 && logSpruce) { //SPRUCE
                    	IBHelper.addItemsToChest(chest, block, 1, meta);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 1, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.log && meta == 2 && logBirch) { //BIRCH
                    	IBHelper.addItemsToChest(chest, block, 1, meta);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 2, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.log && meta == 3 && logJungle) { //JUNGLE
                    	IBHelper.addItemsToChest(chest, block, 1, meta);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 3, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.log2 && meta == 0 && logAcacia) { //ACACIA
                    	IBHelper.addItemsToChest(chest, block, 1, meta);
                    	if ((world.getBlock(x, y-1, z) == Blocks.dirt || world.getBlock(x, y-1, z) == Blocks.grass) && replant) {
                    		world.setBlock(x, y, z, Blocks.sapling, 4, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    } else if (block == Blocks.log2 && meta == 1 && logDark) { //DARK OAK
                    	IBHelper.addItemsToChest(chest, block, 1, meta);
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
                    	IBHelper.addItemsToChest(chest, Items.nether_wart, 1, 0);
                    	if (replant) {
                    		world.setBlock(x, y, z, Blocks.nether_wart, 0, 2);
                    	} else {
                    		world.setBlock(x, y, z, Blocks.air);
                    	}
                    }
                	
                    x++;
                }
                z++;
                x = bx;
            }
            z = bz;
            x = bx;
            y++;
        }
    }
}
