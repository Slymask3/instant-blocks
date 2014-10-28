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
import com.slymask3.instantblocks.utility.BuildHelper;

public class BlockInstantLadder extends BlockDirectional {
	private BuildHelper ibf = new BuildHelper();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantLadder() {
        super(Material.rock);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_MINING_LADDER);
        setHardness(1.5F);
        setResistance(2000F);
        setStepSound(Block.soundTypeStone);
    }
    
    public int quantityDropped(Random random) {
        return 1;
    }
	
    public static IIcon[] textures = new IIcon[6];
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		textures[0] = ir.registerIcon("stone");
		textures[1] = ir.registerIcon("stone");
		textures[2] = ir.registerIcon("stone");
		textures[3] = ir.registerIcon("stone");
		textures[4] = ir.registerIcon("stone");
		textures[5] = ir.registerIcon("stone");
		
		top0 = ir.registerIcon("instantblocks:ladder_top_0"); //NORTH
		top1 = ir.registerIcon("instantblocks:ladder_top_1"); //EAST
		top2 = ir.registerIcon("instantblocks:ladder_top_2"); //SOUTH
		top3 = ir.registerIcon("instantblocks:ladder_top_3"); //WEST
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side != 1) {
			return textures[side];
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
    
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int meta = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 2);
    }
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if (y <= 15) {
			ibf.msg(player, ibf.ladderError, Colors.c);
			return true;
		}
    	
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achLadder);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		build(world, x, y, z);
				
		ibf.keepBlocks(world, x, y, z, mb.ibLadder);
		ibf.xp(world, player, config.xp);
			
		ibf.sound(world, config.sound, x, y, z);
		ibf.effectFull(world, "reddust", x, y, z);
		ibf.msg(player, ibf.ladderCreate, Colors.a);
    	
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

	private void build(World world, int x, int y, int z) {
		/*int ladder = Block.ladder.blockID;
		int stone = Block.stone.blockID;
		int torch = Block.torchWood.blockID;
		int water = Block.waterStill.blockID;
		int sign = Block.signWall.blockID;
		int brick = Block.brick.blockID;
		int wool = Block.cloth.blockID;
		int dirt = Block.dirt.blockID;*/
		
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
					ibf.build(world, x-2, c, z-1, stone, 3, 1, 5); //STONE
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
					ibf.build(world, x-1, c, z-2, stone, 5, 1, 3); //STONE
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
					ibf.build(world, x-2, c, z-1, stone, 3, 1, 5); //STONE
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
					ibf.build(world, x-1, c, z-2, stone, 5, 1, 3); //STONE
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