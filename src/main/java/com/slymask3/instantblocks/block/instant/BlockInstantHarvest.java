package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.tileentity.TileEntityHarvest;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import com.slymask3.instantblocks.util.LogHelper;
import net.minecraft.block.Block;
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

public class BlockInstantHarvest extends BlockInstant {
	public BlockInstantHarvest() {
		super(Names.Blocks.IB_HARVEST, Material.wood, Block.soundTypeWood, 1.5F);
        setBlockTextureName(Textures.Harvest.SIDE0);
		setGuiID(GuiID.HARVEST);
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
		if(side == 0) {
			return Blocks.planks.getIcon(side, 0);
		} else if(side == 1) {
			return top;
		} else if(side == 3 || side == 5) {
			return side0;
		} else {
			return side1;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityHarvest();
	}
	
	public void build(World world, int x, int y, int z, String playerS, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		EntityPlayer player = world.getPlayerEntityByName(playerS);

		harvest(world, x, y, z, Config.RADIUS_HARVEST, logOak, logSpruce, logBirch, logJungle, logAcacia, logDark, wheat, carrot, potato, cactus, pumpkin, melon, sugarcane, cocoa, mushroom, netherwart, replant);

		IBHelper.sound(world, Config.SOUND, x, y, z);

		ItemStack is = player.getCurrentEquippedItem();

		if(IBHelper.isWand(is)) {
			is.damageItem(1, player);
		}
	}
	
	public void harvest(World world, int X, int Y, int Z, int radius, boolean logOak, boolean logSpruce, boolean logBirch, boolean logJungle, boolean logAcacia, boolean logDark, boolean wheat, boolean carrot, boolean potato, boolean cactus, boolean pumpkin, boolean melon, boolean sugarcane, boolean cocoa, boolean mushroom, boolean netherwart, boolean replant) {
		BuildHelper.setBlock(world,X, Y, Z, Blocks.chest);
		TileEntityChest chest = (TileEntityChest)world.getTileEntity(X, Y, Z);
		
		Random rand = new Random();
    	
        int x = (int) (X -radius);
        int y = (int) (Y +radius);
        int z = (int) (Z -radius);
   
        int bx = x;
        int bz = z;
 
        for(int i=0; i<radius*2+1; i++) {
            for(int j=0; j<radius*2+1; j++) {
                for(int k=0; k<radius*2+1; k++) {
                    Block block = BuildHelper.getBlock(world,x, y, z);
                    int meta = world.getBlockMetadata(x, y, z);
                    
                    if(chest.getStackInSlot(26) != null) {
                    	if(BuildHelper.getBlock(world,X+1, Y, Z) == Blocks.air) {
                    		BuildHelper.setBlock(world,X+1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X+1, Y, Z);
                    		X++;
                    	} else if(BuildHelper.getBlock(world,X-1, Y, Z) == Blocks.air) {
                    		BuildHelper.setBlock(world,X-1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X-1, Y, Z);
                    		X--;
                    	} else if(BuildHelper.getBlock(world,X, Y, Z+1) == Blocks.air) {
                    		BuildHelper.setBlock(world,X, Y, Z+1, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X, Y, Z+1);
                    		Z++;
                    	} else if(BuildHelper.getBlock(world,X, Y, Z-1) == Blocks.air) {
                    		BuildHelper.setBlock(world,X, Y, Z-1, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X, Y, Z-1);
                    		Z--;
                    	} else {
                    		BuildHelper.setBlock(world,X+1, Y, Z, Blocks.chest);
                    		chest = (TileEntityChest)world.getTileEntity(X+1, Y, Z);
                    		X++;
                    	}
                    	LogHelper.info("had to create another chest");
                    }
                    
                    if((block == Blocks.log && logOak) && (meta == 0 || meta == 4 || meta == 8 || meta == 12)) { //OAK
                    	IBHelper.addItemsToChest(chest, block, 1, 0);
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.sapling, 0, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if((block == Blocks.log && logSpruce) && (meta == 1 || meta == 5 || meta == 9 || meta == 13)) { //SPRUCE
                    	IBHelper.addItemsToChest(chest, block, 1, 1);
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.sapling, 1, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if((block == Blocks.log && logBirch) && (meta == 2 || meta == 6 || meta == 10 || meta == 14)) { //BIRCH
                    	IBHelper.addItemsToChest(chest, block, 1, 2);
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.sapling, 2, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if((block == Blocks.log && logJungle) && (meta == 3 || meta == 7 || meta == 11 || meta == 15)) { //JUNGLE
                    	IBHelper.addItemsToChest(chest, block, 1, 3);
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.sapling, 3, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if((block == Blocks.log2 && logAcacia) && (meta == 0 || meta == 4 || meta == 8 || meta == 12)) { //ACACIA
                    	IBHelper.addItemsToChest(chest, block, 1, 0);
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.sapling, 4, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if((block == Blocks.log2 && logDark) && (meta == 1 || meta == 5 || meta == 9 || meta == 13)) { //DARK OAK
                    	IBHelper.addItemsToChest(chest, block, 1, 1);
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.sapling, 5, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if(block == Blocks.wheat && meta == 7 && wheat) { //WHEAT
                    	IBHelper.addItemsToChest(chest, Items.wheat, 1, 0);
                    	if(replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.wheat, 0, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if(block == Blocks.carrots && meta == 7 && carrot) { //CARROT
                    	IBHelper.addItemsToChest(chest, Items.carrot, rand.nextInt(4)+1, 0);
                    	if(replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.carrots, 0, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if(block == Blocks.potatoes && meta == 7 && potato) { //POTATO
                    	IBHelper.addItemsToChest(chest, Items.potato, rand.nextInt(4)+1, 0);
                    	if(replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.potatoes, 0, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if(block == Blocks.cactus && cactus) { //CACTUS
                    	if(BuildHelper.getBlock(world,x, y-1, z) == Blocks.sand && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.cactus, 0, 2);
                    	} else {
                        	IBHelper.addItemsToChest(chest, block, 1, 0);
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if(block == Blocks.pumpkin && pumpkin) { //PUMPKIN
                    	IBHelper.addItemsToChest(chest, block, 1, 0);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    } else if(block == Blocks.melon_block && melon) { //MELON
                    	IBHelper.addItemsToChest(chest, Items.melon, rand.nextInt(5)+3, 0);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    } else if(block == Blocks.reeds && sugarcane) { //SUGARCANE
                    	if((BuildHelper.getBlock(world,x, y-1, z) == Blocks.dirt || BuildHelper.getBlock(world,x, y-1, z) == Blocks.grass || BuildHelper.getBlock(world,x, y-1, z) == Blocks.sand) && replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.reeds, 0, 2);
                    	} else {
                        	IBHelper.addItemsToChest(chest, Items.reeds, 1, 0);
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    } else if(block == Blocks.cocoa && meta == 6 && cocoa) { //COCOA
                    	IBHelper.addItemsToChest(chest, Items.dye, 3, 3);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    } else if((block == Blocks.red_mushroom_block || block == Blocks.red_mushroom) && mushroom) { //MUSHROOM RED
                    	IBHelper.addItemsToChest(chest, Blocks.red_mushroom, 1, 0);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    } else if((block == Blocks.brown_mushroom_block || block == Blocks.brown_mushroom) && mushroom) { //MUSHROOM BROWN
                    	IBHelper.addItemsToChest(chest, Blocks.brown_mushroom, 1, 0);
                    	BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    } else if(block == Blocks.nether_wart && meta == 3 && netherwart) { //NETHERWART
                    	IBHelper.addItemsToChest(chest, Items.nether_wart, rand.nextInt(3)+2, 0);
                    	if(replant) {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.nether_wart, 0, 2);
                    	} else {
                    		BuildHelper.setBlock(world,x, y, z, Blocks.air);
                    	}
                    }
                    
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
}
