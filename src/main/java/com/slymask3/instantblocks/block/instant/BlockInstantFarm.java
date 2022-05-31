package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
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
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantFarm extends BlockInstant {
    public BlockInstantFarm() {
        super(Names.Blocks.IB_FARM, Material.rock, Block.soundTypeStone, 1.5F);
        setBlockTextureName(Textures.Farm.TOP0);
		setCreateMessage(Strings.CREATE_FARM);
		setDirectional(true);
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
		if(side != 1) {
			return Blocks.stonebrick.getIcon(0, 0);
		} else {
			if(meta == 0) {
				return top0;
			} else if(meta == 1) {
				return top1;
			} else if(meta == 2) {
				return top2;
			} else if(meta == 3) {
				return top3;
			}
		}
		return blockIcon;
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block crop = Blocks.wheat;
		if(IBHelper.isServer(world)) {
			Random ran = new Random();
			int r = ran.nextInt(20);
			if(r == 0) {
				crop = Blocks.potatoes;
			} else if(r == 1) {
				crop = Blocks.carrots;
			} else {
				crop = Blocks.wheat;
			}
		}

		Block stone = Blocks.stonebrick;
		Block farm = Blocks.farmland;
		Block water = Blocks.water;
		Block fence = Blocks.fence;
		Block gate = Blocks.fence_gate;
		Block torch = Blocks.torch;
		Block chest = Blocks.chest;
		Block craft = Blocks.crafting_table;
		Block air = Blocks.air;

		int meta = world.getBlockMetadata(x, y, z);

		LogHelper.info("meta: "+meta);

		int metaChest;
		switch(meta) {
			default:
			case 0: metaChest = 3; break;
			case 1: metaChest = 4; break;
			case 2: metaChest = 2; break;
			case 3: metaChest = 5; break;
		}

		int metaGate;
		switch(meta) {
			default:
			case 0: case 2: metaGate = 0; break;
			case 1: case 3: metaGate = 1; break;
		}

		BuildHelper.buildDirectional(world,x,y-1,z,air,meta,0,4,7,0,8,0,0,14,1,0);
		BuildHelper.buildDirectional(world,x,y-1,z,stone,meta,0,4,7,0,8,0,0,14,1,0);

		BuildHelper.buildDirectional(world,x,y+1,z,fence,meta,0,4,7,0,8,0,0,0,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,fence,meta,0,4,0,7,8,0,0,0,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,fence,meta,0,4,6,0,0,0,0,12,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,fence,meta,4,0,6,0,0,0,0,12,0,0);

		BuildHelper.buildDirectional(world,x,y,z,farm,meta,0,3,6,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y,z,farm,meta,0,3,3,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y,z,farm,meta,0,3,0,2,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y,z,farm,meta,0,3,0,5,6,0,0,1,0,0);

		BuildHelper.buildDirectional(world,x,y,z,water,meta,0,3,4,0,6,0,0,0,0,0);
		BuildHelper.buildDirectional(world,x,y,z,water,meta,0,3,0,4,6,0,0,0,0,0);

		BuildHelper.buildDirectional(world,x,y+1,z,crop,meta,0,3,6,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,crop,meta,0,3,3,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,crop,meta,0,3,0,2,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,crop,meta,0,3,0,5,6,0,0,1,0,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,gate,meta,0,4,0,0,metaGate,2);
		BuildHelper.setBlockDirectional(world,x,y+1,z,craft,meta,3,0,0,0);
		BuildHelper.setBlockDirectional(world,x,y+1,z,chest,meta,3,0,1,0,metaChest,2);
		BuildHelper.setBlockDirectional(world,x,y+1,z,chest,meta,3,0,0,1,metaChest,2);

		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,0,4,7,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,0,0,7,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,4,0,7,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,0,4,0,7);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,0,0,0,7);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,4,0,0,7);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,0,4,2,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,4,0,2,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,0,4,0,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,meta,4,0,0,2);

		TileEntityChest chest1 = (TileEntityChest)BuildHelper.getTileEntityDirectional(world,x,y+1,z,meta,3,0,1,0);
		TileEntityChest chest2 = (TileEntityChest)BuildHelper.getTileEntityDirectional(world,x,y+1,z,meta,3,0,0,1);
		chest1.setInventorySlotContents(0, new ItemStack(Items.stone_hoe, 1, 0));
		chest2.setInventorySlotContents(0, new ItemStack(Items.stone_hoe, 1, 0));
	}
}