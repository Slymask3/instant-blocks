package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockDirectionalIB;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantFarm extends BlockDirectionalIB {
	
    public BlockInstantFarm() {
        super(ModBlocks.ibFarm, Names.Blocks.IB_FARM, Material.rock, Block.soundTypeStone, 1.5F);
		setResistance(2000F);
        setBlockTextureName(Textures.Farm.TOP0);
    }
	
    public static IIcon top0;
    public static IIcon top1;
    public static IIcon top2;
    public static IIcon top3;
    
	public void registerBlockIcons(IIconRegister ir) {
		top0 = ir.registerIcon(Textures.Farm.TOP0); //NORTH
		top1 = ir.registerIcon(Textures.Farm.TOP1); //EAST
		top2 = ir.registerIcon(Textures.Farm.TOP0); //SOUTH
		top3 = ir.registerIcon(Textures.Farm.TOP1); //WEST
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
	
	//public Block crop = Blocks.wheat;
	//public int r = 1;
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.USE_WANDS) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				is.damageItem(1, player);
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}

		Random ran = new Random();
		Block crop = Blocks.wheat;
		int r = 0;
		
		if (!world.isRemote) { //IF SERVER
			r = ran.nextInt(20);
			if (r == 0) {
				crop = Blocks.potatoes;
			} else if (r == 1) {
				crop = Blocks.carrots;
			} else {
				crop = Blocks.wheat;
			}
		}
		
		build(world, x, y, z, crop);
		

		IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibFarm);
		IBHelper.xp(world, player, Config.XP_AMOUNT);
		
		IBHelper.sound(world, Config.SOUND, x, y, z);
		IBHelper.effectFull(world, "reddust", x, y, z);
		
//		if (r == 0) {
//			BuildHelper.msg(player, Strings.CREATE_FARMP, Colors.a);
//			//player.triggerAchievement(ib.achFarm3);
//		} else if (r == 1) {
//			BuildHelper.msg(player, Strings.CREATE_FARMC, Colors.a);
//			//player.triggerAchievement(ib.achFarm2);
//		} else {
//			BuildHelper.msg(player, Strings.CREATE_FARMW, Colors.a);
//			//player.triggerAchievement(ib.achFarm);
//		}
		
		IBHelper.msg(player, Strings.CREATE_FARM, Colors.a);
		
		return true;
    }

	public void build(World world, int x, int y, int z, Block crop) {
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
			BuildHelper.build(world, x-7, y-1, z-4, Blocks.air, 9, 5, 15); //AIR
			
			BuildHelper.build(world, x+7, y+1, z-3, fence, 7, 1, 1); //FENCES
			BuildHelper.build(world, x-7, y+1, z-3, fence, 7, 1, 1); //FENCES
			BuildHelper.build(world, x-7, y+1, z-4, fence, 1, 1, 15); //FENCES
			BuildHelper.build(world, x-7, y+1, z+4, fence, 1, 1, 15); //FENCES
		
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
		
			BuildHelper.build(world, x-7, y-1, z-4, stone, 9, 2, 15); //STONE
		
			BuildHelper.build(world, x-6, y, z-3, farm, 7, 1, 5); //DIRT
			BuildHelper.build(world, x+2, y, z-3, farm, 7, 1, 5); //DIRT

			BuildHelper.build(world, x-4, y, z-3, water, 7, 1, 1); //WATER
			BuildHelper.build(world, x+4, y, z-3, water, 7, 1, 1); //WATER
		
			BuildHelper.build(world, x-6, y+1, z-3, crop, 7, 1, 2); //CROP
			BuildHelper.build(world, x-3, y+1, z-3, crop, 7, 1, 2); //CROP
			BuildHelper.build(world, x+2, y+1, z-3, crop, 7, 1, 2); //CROP
			BuildHelper.build(world, x+5, y+1, z-3, crop, 7, 1, 2); //CROP

			world.setBlock(x, y+1, z+4, gate, 0, 0); //GATE
			world.setBlock(x+1, y+1, z-3, chest, 5, 0); //CHEST
			world.setBlock(x, y+1, z-3, craft, 5, 0); //WORKBENCH
			world.setBlock(x-1, y+1, z-3, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x+1, y+1, z-3);
			chest2 = (TileEntityChest)world.getTileEntity(x-1, y+1, z-3);
		} else if (meta == 1) { //EAST
			BuildHelper.build(world, x-4, y-1, z-7, Blocks.air, 15, 5, 9); //AIR
		
			BuildHelper.build(world, x-3, y+1, z+7, fence, 1, 1, 7); //FENCES
			BuildHelper.build(world, x-3, y+1, z-7, fence, 1, 1, 7); //FENCES
			BuildHelper.build(world, x-4, y+1, z-7, fence, 15, 1, 1); //FENCES
			BuildHelper.build(world, x+4, y+1, z-7, fence, 15, 1, 1); //FENCES
		
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
		
			BuildHelper.build(world, x-4, y-1, z-7, stone, 15, 2, 9); //STONE
		
			BuildHelper.build(world, x-3, y, z-6, farm, 5, 1, 7); //DIRT
			BuildHelper.build(world, x-3, y, z+2, farm, 5, 1, 7); //DIRT

			BuildHelper.build(world, x-3, y, z-4, water, 1, 1, 7); //WATER
			BuildHelper.build(world, x-3, y, z+4, water, 1, 1, 7); //WATER
		
			BuildHelper.build(world, x-3, y+1, z-6, crop, 2, 1, 7); //CROP
			BuildHelper.build(world, x-3, y+1, z-3, crop, 2, 1, 7); //CROP
			BuildHelper.build(world, x-3, y+1, z+2, crop, 2, 1, 7); //CROP
			BuildHelper.build(world, x-3, y+1, z+5, crop, 2, 1, 7); //CROP

			world.setBlock(x-4, y+1, z, gate, 1, 0); //GATE
			world.setBlock(x+3, y+1, z+1, chest, 5, 0); //CHEST
			world.setBlock(x+3, y+1, z, craft, 5, 0); //WORKBENCH
			world.setBlock(x+3, y+1, z-1, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x+3, y+1, z+1);
			chest2 = (TileEntityChest)world.getTileEntity(x+3, y+1, z-1);
		} else if (meta == 2) { //SOUTH
			BuildHelper.build(world, x-7, y-1, z-4, Blocks.air, 9, 5, 15); //AIR
			
			BuildHelper.build(world, x+7, y+1, z-3, fence, 7, 1, 1); //FENCES
			BuildHelper.build(world, x-7, y+1, z-3, fence, 7, 1, 1); //FENCES
			BuildHelper.build(world, x-7, y+1, z-4, fence, 1, 1, 15); //FENCES
			BuildHelper.build(world, x-7, y+1, z+4, fence, 1, 1, 15); //FENCES
		
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
		
			BuildHelper.build(world, x-7, y-1, z-4, stone, 9, 2, 15); //STONE
		
			BuildHelper.build(world, x-6, y, z-3, farm, 7, 1, 5); //DIRT
			BuildHelper.build(world, x+2, y, z-3, farm, 7, 1, 5); //DIRT

			BuildHelper.build(world, x-4, y, z-3, water, 7, 1, 1); //WATER
			BuildHelper.build(world, x+4, y, z-3, water, 7, 1, 1); //WATER
		
			BuildHelper.build(world, x-6, y+1, z-3, crop, 7, 1, 2); //CROP
			BuildHelper.build(world, x-3, y+1, z-3, crop, 7, 1, 2); //CROP
			BuildHelper.build(world, x+2, y+1, z-3, crop, 7, 1, 2); //CROP
			BuildHelper.build(world, x+5, y+1, z-3, crop, 7, 1, 2); //CROP

			world.setBlock(x, y+1, z-4, gate, 0, 0); //GATE
			world.setBlock(x+1, y+1, z+3, chest, 5, 0); //CHEST
			world.setBlock(x, y+1, z+3, craft, 5, 0); //WORKBENCH
			world.setBlock(x-1, y+1, z+3, chest, 5, 0); //CHEST
			
			chest1 = (TileEntityChest)world.getTileEntity(x+1, y+1, z+3);
			chest2 = (TileEntityChest)world.getTileEntity(x-1, y+1, z+3);
		} else if (meta == 3) { //WEST (ORIGINAL)
			BuildHelper.build(world, x-4, y-1, z-7, Blocks.air, 15, 5, 9); //AIR
		
			BuildHelper.build(world, x-3, y+1, z+7, fence, 1, 1, 7); //FENCES
			BuildHelper.build(world, x-3, y+1, z-7, fence, 1, 1, 7); //FENCES
			BuildHelper.build(world, x-4, y+1, z-7, fence, 15, 1, 1); //FENCES
			BuildHelper.build(world, x+4, y+1, z-7, fence, 15, 1, 1); //FENCES
		
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
		
			BuildHelper.build(world, x-4, y-1, z-7, stone, 15, 2, 9); //STONE
		
			BuildHelper.build(world, x-3, y, z-6, farm, 5, 1, 7); //DIRT
			BuildHelper.build(world, x-3, y, z+2, farm, 5, 1, 7); //DIRT

			BuildHelper.build(world, x-3, y, z-4, water, 1, 1, 7); //WATER
			BuildHelper.build(world, x-3, y, z+4, water, 1, 1, 7); //WATER
		
			BuildHelper.build(world, x-3, y+1, z-6, crop, 2, 1, 7); //CROP
			BuildHelper.build(world, x-3, y+1, z-3, crop, 2, 1, 7); //CROP
			BuildHelper.build(world, x-3, y+1, z+2, crop, 2, 1, 7); //CROP
			BuildHelper.build(world, x-3, y+1, z+5, crop, 2, 1, 7); //CROP

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