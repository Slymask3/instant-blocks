package com.slymask3.instantblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.utility.BuildHelper;

public class BlockInstantLadder extends BlockDirectionalIB {
	
    public BlockInstantLadder() {
        super(ModBlocks.ibLadder, Names.Blocks.IB_MINING_LADDER, Material.rock, Block.soundTypeStone, 1.5F);
        setBlockTextureName(Textures.MiningLadder.TOP0);
    }
	
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon(Textures.MiningLadder.TOP0); //NORTH
		top1 = ir.registerIcon(Textures.MiningLadder.TOP1); //EAST
		top2 = ir.registerIcon(Textures.MiningLadder.TOP2); //SOUTH
		top3 = ir.registerIcon(Textures.MiningLadder.TOP3); //WEST
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side != 1) {
			return Blocks.stone.getIcon(0, 0);
		} else if (side == 1) {
			if (meta == 0) {
				return top0;
			} else if (meta == 1) {
				return top1;
			} else if (meta == 2) {
				return top2;
			} else if (meta == 3) {
				return top3;
			}
		}
		
		return blockIcon;
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if (y <= 15) {
			BuildHelper.msg(player, Strings.ladderError, Colors.c);
			return true;
		}
    	
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (ConfigurationHandler.useWands == true) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achLadder);
			} else {
				BuildHelper.msg(player, Strings.wandReq, Colors.c);
				return true;
			}
		}
		
		build(world, x, y, z);
				
		BuildHelper.keepBlocks(world, x, y, z, ModBlocks.ibLadder);
		BuildHelper.xp(world, player, ConfigurationHandler.xp);
			
		BuildHelper.sound(world, ConfigurationHandler.sound, x, y, z);
		BuildHelper.effectFull(world, "reddust", x, y, z);
		BuildHelper.msg(player, Strings.ladderCreate, Colors.a);
    	
    	//ComponentVillageWell.buildComponent(null, null, null) {
    	//	
    	//}
    	
    	//MapGenVillage.getStructureStart(x, z);
    	
    	//Random rand = new Random();
    	
    	//new net.minecraft.world.gen.structure.StructureVillageStart(world, rand, x, z, 1);
    	
    	//Map map = new HashMap();
    	
    	//new MapGenVillage(map);
    	
    	//new StructureVillageStart(world, rand, x, y, z);

    	//world.setBlock(x, y, z, Block.sand.blockID);
    	//world.setBlock(x, y, z, Block.waterStill.blockID);
    	
    	//System.out.println("Started");
    	
    	//WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
    	//WorldGenDungeons gen = new WorldGenDungeons();
    	//WorldGenClay gen = new WorldGenClay(10);
        //gen.generate(world, rand, x, y, z);

    	//System.out.println("Ended");
		
		return true;
    }

	public void build(World world, int x, int y, int z) {
		Block ladder = Blocks.ladder;
		Block stone = Blocks.stone;
		Block torch = Blocks.torch;
		Block water = Blocks.water;
		Block sign = Blocks.wall_sign;
		Block brick = Blocks.brick_block;
		Block wool = Blocks.wool;
		Block dirt = Blocks.dirt;

		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 0) { //NORTH
				for (int c = y; c > 10; c--) {
					BuildHelper.build(world, x-2, c, z-1, stone, 3, 1, 5); //STONE
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x-1, c, z, ladder, 5, 0); //LADDERS 5
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x+1, c, z, Blocks.air); //AIR
				}
				for (int c = y; c > 13; c = c - 3) {
					c = c - 3;
					world.setBlock(x, c, z, torch); //TORCHES
				}
				
				world.setBlock(x, 12, z, Blocks.air); //MIDDLE AIR
				world.setBlock(x, 13, z, torch); //MIDDLE TORCH
				world.setBlock(x, 14, z, stone); //ABOVE MIDDLE TORCH
				world.setBlock(x+1, 14, z, water); //WATER
				world.setBlock(x+1, 13, z, sign, 2, 0); //SIGN 2
			} else if (meta == 1) { //EAST
				for (int c = y; c > 10; c--) {
					BuildHelper.build(world, x-1, c, z-2, stone, 5, 1, 3); //STONE
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x, c, z-1, ladder, 3, 0); //LADDERS 3
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x, c, z+1, Blocks.air); //AIR
				}
				for (int c = y; c > 13; c = c - 3) {
					c = c - 3;
					world.setBlock(x, c, z, torch); //TORCHES
				}
				
				world.setBlock(x, 12, z, Blocks.air); //MIDDLE AIR
				world.setBlock(x, 13, z, torch); //MIDDLE TORCH
				world.setBlock(x, 14, z, stone); //ABOVE MIDDLE TORCH
				world.setBlock(x, 14, z+1, water); //WATER
				world.setBlock(x, 13, z+1, sign, 5, 0); //SIGN 5
			} else if (meta == 2) { //SOUTH
				for (int c = y; c > 10; c--) {
					BuildHelper.build(world, x-2, c, z-1, stone, 3, 1, 5); //STONE
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x+1, c, z, ladder, 4, 0); //LADDERS 4
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x-1, c, z, Blocks.air); //AIR
				}
				for (int c = y; c > 13; c = c - 3) {
					c = c - 3;
					world.setBlock(x, c, z, torch); //TORCHES
				}
				
				world.setBlock(x, 12, z, Blocks.air); //MIDDLE AIR
				world.setBlock(x, 13, z, torch); //MIDDLE TORCH
				world.setBlock(x, 14, z, stone); //ABOVE MIDDLE TORCH
				world.setBlock(x-1, 14, z, water); //WATER
				world.setBlock(x-1, 13, z, sign, 3, 0); //SIGN 3
			} else if (meta == 3) { //WEST (ORIGINAL)
				for (int c = y; c > 10; c--) {
					BuildHelper.build(world, x-1, c, z-2, stone, 5, 1, 3); //STONE
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x, c, z+1, ladder, 2, 0); //LADDERS 2
				}
				for (int c = y; c > 11; c--) {
					world.setBlock(x, c, z-1, Blocks.air); //AIR
				}
				for (int c = y; c > 13; c = c - 3) {
					c = c - 3;
					world.setBlock(x, c, z, torch); //TORCHES
				}
				
				world.setBlock(x, 12, z, Blocks.air); //MIDDLE AIR
				world.setBlock(x, 13, z, torch); //MIDDLE TORCH
				world.setBlock(x, 14, z, stone); //ABOVE MIDDLE TORCH
				world.setBlock(x, 14, z-1, water); //WATER
				world.setBlock(x, 13, z-1, sign, 4, 0); //SIGN 4
			}
			
			world.setBlock(x, 11, z, stone); //MIDDLE STONE
	}
}