package com.slymask3.instantblocks.block;

import java.awt.image.BufferedImage;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.utility.InstantBlocksFunctions;

public class BlockInstantFarm extends BlockDirectional {
	private InstantBlocksFunctions ibf = new InstantBlocksFunctions();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantFarm() {
        super(Material.rock);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_FARM);
        setHardness(1.5F);
        setResistance(2000F);
        setStepSound(Block.soundTypeStone);
    }
    
    public int quantityDropped(Random random)
    {
        return 1;
    }
	
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon("instantblocks:farm_top_0"); //NORTH
		top1 = ir.registerIcon("instantblocks:farm_top_1"); //EAST
		top2 = ir.registerIcon("instantblocks:farm_top_0"); //SOUTH
		top3 = ir.registerIcon("instantblocks:farm_top_1"); //WEST
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side != 1) {
			return Blocks.stonebrick.getIcon(0, 0);
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
        
        System.out.println("meta = " + meta);
    }
	
	public Block crop = Blocks.wheat;
	public int r = 1;
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	/*Random ran = new Random();
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				is.damageItem(1, player);
			} else {
				ibf.msg(player, ibf.wandReq);
				return true;
			}
		}
		
		if (world.isRemote) { //IF CLIENT
			r = ran.nextInt(20);
			if (r == 0) {
				crop = Blocks.potatoes;
			} else if (r == 1) {
				crop = Blocks.carrots;
			} else {
				crop = Blocks.wheat;
			}
			System.out.println("r == " + r);
			
			//return true;
		}*/

		//build(world, x, y, z, crop);
		
		int meta = world.getBlockMetadata(x, y, z);
		buildPattern(world, x, y, z, meta);

		/*ibf.keepBlocks(world, x, y, z, mb.ibFarm);
		ibf.xp(world, player, config.xp);
		
		ibf.sound(world, config.sound, x, y, z);
		ibf.effectFull(world, "reddust", x, y, z);
		
		if (r == 0) {
			ibf.msg(player, ibf.farmCreateP);
			//player.triggerAchievement(ib.achFarm3);
		} else if (r == 1) {
			ibf.msg(player, ibf.farmCreateC);
			//player.triggerAchievement(ib.achFarm2);
		} else {
			ibf.msg(player, ibf.farmCreateW);
			//player.triggerAchievement(ib.achFarm);
		}*/
		
		return true;
    }
    
    private void buildPattern(World world, int x, int y, int z, int meta) {
		/*if(meta==0) {
			world.setBlock(x, y, z-2, Blocks.stone); //forward
			world.setBlock(x, y, z+2, Blocks.planks); //back
			world.setBlock(x-2, y, z, Blocks.netherrack); //left
			world.setBlock(x+2, y, z, Blocks.sandstone); //right
		} else if(meta==1) {
			world.setBlock(x+2, y, z, Blocks.stone); //forward
			world.setBlock(x-2, y, z, Blocks.planks); //back
			world.setBlock(x, y, z+2, Blocks.netherrack); //left
			world.setBlock(x, y, z-2, Blocks.sandstone); //right
		} else if(meta==2) {
			world.setBlock(x, y, z+2, Blocks.stone); //forward
			world.setBlock(x, y, z-2, Blocks.planks); //back
			world.setBlock(x+2, y, z, Blocks.netherrack); //left
			world.setBlock(x-2, y, z, Blocks.sandstone); //right
		} else if(meta==3) {
			world.setBlock(x-2, y, z, Blocks.stone); //forward
			world.setBlock(x+2, y, z, Blocks.planks); //back
			world.setBlock(x, y, z-2, Blocks.netherrack); //left
			world.setBlock(x, y, z+2, Blocks.sandstone); //right
		}*/
    	

		//setBlockDirectional(world, x, y, z, Blocks.stone, meta, 2, 0, 0, 0); //forward
		//setBlockDirectional(world, x, y, z, Blocks.planks, meta, 0, 2, 0, 0); //back
		//setBlockDirectional(world, x, y, z, Blocks.netherrack, meta, 0, 0, 2, 0); //left
		//setBlockDirectional(world, x, y, z, Blocks.sandstone, meta, 0, 0, 0, 2); //right
	}

	private void build(World world, int x, int y, int z, Block crop) {
    	/*int stone = Block.stoneBrick.blockID;
		int farm = Block.tilledField.blockID;
		int water = Block.waterStill.blockID;
		int fence = Block.fence.blockID;
		int gate = Block.fenceGate.blockID;
		int torch = Block.torchWood.blockID;
		int chest = Block.chest.blockID;
		int craft = Block.workbench.blockID;*/
		
		Block stone = Blocks.stonebrick;
		Block farm = Blocks.farmland;
		Block water = Blocks.water;
		Block fence = Blocks.fence;
		Block gate = Blocks.fence_gate;
		Block torch = Blocks.torch;
		Block chest = Blocks.chest;
		Block craft = Blocks.crafting_table;
		
		TileEntityChest chest1 = new TileEntityChest();
    	TileEntityChest chest2 = new TileEntityChest();
		
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 0) { //NORTH
			ibf.build(world, x-7, y-1, z-4, Blocks.air, 9, 5, 15); //AIR
			
			ibf.build(world, x+7, y+1, z-3, fence, 7, 1, 1); //FENCES
			ibf.build(world, x-7, y+1, z-3, fence, 7, 1, 1); //FENCES
			ibf.build(world, x-7, y+1, z-4, fence, 1, 1, 15); //FENCES
			ibf.build(world, x-7, y+1, z+4, fence, 1, 1, 15); //FENCES
		
			world.setBlock(x+7, y+2, z-4, torch); //TORCH
			world.setBlock(x+2, y+2, z-4, torch); //TORCH
			world.setBlock(x-2, y+2, z-4, torch); //TORCH
			world.setBlock(x-7, y+2, z-4, torch); //TORCH

			world.setBlock(x-7, y+2, z, torch); //TORCH
			world.setBlock(x+7, y+2, z, torch); //TORCH
		
			world.setBlock(x+7, y+2, z+4, torch); //TORCH
			world.setBlock(x+2, y+2, z+4, torch); //TORCH
			world.setBlock(x-2, y+2, z+4, torch); //TORCH
			world.setBlock(x-7, y+2, z+4, torch); //TORCH
		
			ibf.build(world, x-7, y-1, z-4, stone, 9, 2, 15); //STONE
		
			ibf.build(world, x-6, y, z-3, farm, 7, 1, 5); //DIRT
			ibf.build(world, x+2, y, z-3, farm, 7, 1, 5); //DIRT

			ibf.build(world, x-4, y, z-3, water, 7, 1, 1); //WATER
			ibf.build(world, x+4, y, z-3, water, 7, 1, 1); //WATER
		
			ibf.build(world, x-6, y+1, z-3, crop, 7, 1, 2); //CROP
			ibf.build(world, x-3, y+1, z-3, crop, 7, 1, 2); //CROP
			ibf.build(world, x+2, y+1, z-3, crop, 7, 1, 2); //CROP
			ibf.build(world, x+5, y+1, z-3, crop, 7, 1, 2); //CROP

			world.setBlock(x, y+1, z+4, gate, 0, 0); //GATE
			world.setBlock(x+1, y+1, z-3, chest, 5, 0); //CHEST
			world.setBlock(x, y+1, z-3, craft, 5, 0); //WORKBENCH
			world.setBlock(x-1, y+1, z-3, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x+1, y+1, z-3);
			chest2 = (TileEntityChest)world.getTileEntity(x-1, y+1, z-3);
		} else if (meta == 1) { //EAST
			ibf.build(world, x-4, y-1, z-7, Blocks.air, 15, 5, 9); //AIR
		
			ibf.build(world, x-3, y+1, z+7, fence, 1, 1, 7); //FENCES
			ibf.build(world, x-3, y+1, z-7, fence, 1, 1, 7); //FENCES
			ibf.build(world, x-4, y+1, z-7, fence, 15, 1, 1); //FENCES
			ibf.build(world, x+4, y+1, z-7, fence, 15, 1, 1); //FENCES
		
			world.setBlock(x-4, y+2, z+7, torch); //TORCH
			world.setBlock(x-4, y+2, z+2, torch); //TORCH
			world.setBlock(x-4, y+2, z-2, torch); //TORCH
			world.setBlock(x-4, y+2, z-7, torch); //TORCH

			world.setBlock(x, y+2, z-7, torch); //TORCH
			world.setBlock(x, y+2, z+7, torch); //TORCH
		
			world.setBlock(x+4, y+2, z+7, torch); //TORCH
			world.setBlock(x+4, y+2, z+2, torch); //TORCH
			world.setBlock(x+4, y+2, z-2, torch); //TORCH
			world.setBlock(x+4, y+2, z-7, torch); //TORCH
		
			ibf.build(world, x-4, y-1, z-7, stone, 15, 2, 9); //STONE
		
			ibf.build(world, x-3, y, z-6, farm, 5, 1, 7); //DIRT
			ibf.build(world, x-3, y, z+2, farm, 5, 1, 7); //DIRT

			ibf.build(world, x-3, y, z-4, water, 1, 1, 7); //WATER
			ibf.build(world, x-3, y, z+4, water, 1, 1, 7); //WATER
		
			ibf.build(world, x-3, y+1, z-6, crop, 2, 1, 7); //CROP
			ibf.build(world, x-3, y+1, z-3, crop, 2, 1, 7); //CROP
			ibf.build(world, x-3, y+1, z+2, crop, 2, 1, 7); //CROP
			ibf.build(world, x-3, y+1, z+5, crop, 2, 1, 7); //CROP

			world.setBlock(x-4, y+1, z, gate, 1, 0); //GATE
			world.setBlock(x+3, y+1, z+1, chest, 5, 0); //CHEST
			world.setBlock(x+3, y+1, z, craft, 5, 0); //WORKBENCH
			world.setBlock(x+3, y+1, z-1, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x+3, y+1, z+1);
			chest2 = (TileEntityChest)world.getTileEntity(x+3, y+1, z-1);
		} else if (meta == 2) { //SOUTH
			ibf.build(world, x-7, y-1, z-4, Blocks.air, 9, 5, 15); //AIR
			
			ibf.build(world, x+7, y+1, z-3, fence, 7, 1, 1); //FENCES
			ibf.build(world, x-7, y+1, z-3, fence, 7, 1, 1); //FENCES
			ibf.build(world, x-7, y+1, z-4, fence, 1, 1, 15); //FENCES
			ibf.build(world, x-7, y+1, z+4, fence, 1, 1, 15); //FENCES
		
			world.setBlock(x+7, y+2, z-4, torch); //TORCH
			world.setBlock(x+2, y+2, z-4, torch); //TORCH
			world.setBlock(x-2, y+2, z-4, torch); //TORCH
			world.setBlock(x-7, y+2, z-4, torch); //TORCH

			world.setBlock(x-7, y+2, z, torch); //TORCH
			world.setBlock(x+7, y+2, z, torch); //TORCH
		
			world.setBlock(x+7, y+2, z+4, torch); //TORCH
			world.setBlock(x+2, y+2, z+4, torch); //TORCH
			world.setBlock(x-2, y+2, z+4, torch); //TORCH
			world.setBlock(x-7, y+2, z+4, torch); //TORCH
		
			ibf.build(world, x-7, y-1, z-4, stone, 9, 2, 15); //STONE
		
			ibf.build(world, x-6, y, z-3, farm, 7, 1, 5); //DIRT
			ibf.build(world, x+2, y, z-3, farm, 7, 1, 5); //DIRT

			ibf.build(world, x-4, y, z-3, water, 7, 1, 1); //WATER
			ibf.build(world, x+4, y, z-3, water, 7, 1, 1); //WATER
		
			ibf.build(world, x-6, y+1, z-3, crop, 7, 1, 2); //CROP
			ibf.build(world, x-3, y+1, z-3, crop, 7, 1, 2); //CROP
			ibf.build(world, x+2, y+1, z-3, crop, 7, 1, 2); //CROP
			ibf.build(world, x+5, y+1, z-3, crop, 7, 1, 2); //CROP

			world.setBlock(x, y+1, z-4, gate, 0, 0); //GATE
			world.setBlock(x+1, y+1, z+3, chest, 5, 0); //CHEST
			world.setBlock(x, y+1, z+3, craft, 5, 0); //WORKBENCH
			world.setBlock(x-1, y+1, z+3, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x+1, y+1, z+3);
			chest2 = (TileEntityChest)world.getTileEntity(x-1, y+1, z+3);
		} else if (meta == 3) { //WEST (ORIGINAL)
			ibf.build(world, x-4, y-1, z-7, Blocks.air, 15, 5, 9); //AIR
		
			ibf.build(world, x-3, y+1, z+7, fence, 1, 1, 7); //FENCES
			ibf.build(world, x-3, y+1, z-7, fence, 1, 1, 7); //FENCES
			ibf.build(world, x-4, y+1, z-7, fence, 15, 1, 1); //FENCES
			ibf.build(world, x+4, y+1, z-7, fence, 15, 1, 1); //FENCES
		
			world.setBlock(x-4, y+2, z+7, torch); //TORCH
			world.setBlock(x-4, y+2, z+2, torch); //TORCH
			world.setBlock(x-4, y+2, z-2, torch); //TORCH
			world.setBlock(x-4, y+2, z-7, torch); //TORCH

			world.setBlock(x, y+2, z-7, torch); //TORCH
			world.setBlock(x, y+2, z+7, torch); //TORCH
		
			world.setBlock(x+4, y+2, z+7, torch); //TORCH
			world.setBlock(x+4, y+2, z+2, torch); //TORCH
			world.setBlock(x+4, y+2, z-2, torch); //TORCH
			world.setBlock(x+4, y+2, z-7, torch); //TORCH
		
			ibf.build(world, x-4, y-1, z-7, stone, 15, 2, 9); //STONE
		
			ibf.build(world, x-3, y, z-6, farm, 5, 1, 7); //DIRT
			ibf.build(world, x-3, y, z+2, farm, 5, 1, 7); //DIRT

			ibf.build(world, x-3, y, z-4, water, 1, 1, 7); //WATER
			ibf.build(world, x-3, y, z+4, water, 1, 1, 7); //WATER
		
			ibf.build(world, x-3, y+1, z-6, crop, 2, 1, 7); //CROP
			ibf.build(world, x-3, y+1, z-3, crop, 2, 1, 7); //CROP
			ibf.build(world, x-3, y+1, z+2, crop, 2, 1, 7); //CROP
			ibf.build(world, x-3, y+1, z+5, crop, 2, 1, 7); //CROP

			world.setBlock(x+4, y+1, z, gate, 1, 0); //GATE
			world.setBlock(x-3, y+1, z+1, chest, 5, 0); //CHEST
			world.setBlock(x-3, y+1, z, craft, 5, 0); //WORKBENCH
			world.setBlock(x-3, y+1, z-1, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x-3, y+1, z+1);
			chest2 = (TileEntityChest)world.getTileEntity(x-3, y+1, z-1);
		}
		
		chest1.setInventorySlotContents(0, new ItemStack(Items.stone_hoe, 1, 0));
		chest2.setInventorySlotContents(0, new ItemStack(Items.stone_hoe, 1, 0));
	}
}