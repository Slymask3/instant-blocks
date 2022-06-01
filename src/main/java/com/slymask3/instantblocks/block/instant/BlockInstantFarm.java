package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class BlockInstantFarm extends BlockInstant {
    public BlockInstantFarm() {
		super(Block.Properties.of(Material.STONE)
				.strength(1.5F, 2000F)
				.sound(SoundType.STONE)
		);
		setCreateMessage(Strings.CREATE_FARM);
		setDirectional(true);
    }

	public void build(Level world, int x, int y, int z, Player player) {
		Block crop = Blocks.WHEAT;
		if(IBHelper.isServer(world)) {
			Random ran = new Random();
			int r = ran.nextInt(20);
			if(r == 0) {
				crop = Blocks.POTATOES;
			} else if(r == 1) {
				crop = Blocks.CARROTS;
			} else {
				crop = Blocks.WHEAT;
			}
		}

		Block stone = Blocks.STONE_BRICKS;
		Block farm = Blocks.FARMLAND;
		Block water = Blocks.WATER;
		Block fence = Blocks.OAK_FENCE;
		Block gate = Blocks.OAK_FENCE_GATE;
		Block torch = Blocks.TORCH;
		Block chest = Blocks.CHEST;
		Block craft = Blocks.CRAFTING_TABLE;
		Block air = Blocks.AIR;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		InstantBlocks.LOGGER.info("direction: "+direction);

		Direction directionChest = direction;
		Direction directionGate = direction;
		
		BuildHelper.buildDirectional(world,x,y-1,z,air,direction,0,4,7,0,8,0,0,14,1,0);
		BuildHelper.buildDirectional(world,x,y-1,z,stone,direction,0,4,7,0,8,0,0,14,1,0);

		BuildHelper.buildDirectional(world,x,y+1,z,fence,direction,0,4,7,0,8,0,0,0,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,fence,direction,0,4,0,7,8,0,0,0,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,fence,direction,0,4,6,0,0,0,0,12,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,fence,direction,4,0,6,0,0,0,0,12,0,0);

		BuildHelper.buildDirectional(world,x,y,z,farm,direction,0,3,6,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y,z,farm,direction,0,3,3,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y,z,farm,direction,0,3,0,2,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y,z,farm,direction,0,3,0,5,6,0,0,1,0,0);

		BuildHelper.buildDirectional(world,x,y,z,water,direction,0,3,4,0,6,0,0,0,0,0);
		BuildHelper.buildDirectional(world,x,y,z,water,direction,0,3,0,4,6,0,0,0,0,0);

		BuildHelper.buildDirectional(world,x,y+1,z,crop,direction,0,3,6,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,crop,direction,0,3,3,0,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,crop,direction,0,3,0,2,6,0,0,1,0,0);
		BuildHelper.buildDirectional(world,x,y+1,z,crop,direction,0,3,0,5,6,0,0,1,0,0);

		BuildHelper.setBlockDirectional(world,x,y+1,z,gate,direction,0,4,0,0,directionGate,2);
		BuildHelper.setBlockDirectional(world,x,y+1,z,craft,direction,3,0,0,0);
		BuildHelper.setBlockDirectional(world,x,y+1,z,chest,direction,3,0,1,0,directionChest,2);
		BuildHelper.setBlockDirectional(world,x,y+1,z,chest,direction,3,0,0,1,directionChest,2);

		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,0,4,7,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,0,0,7,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,4,0,7,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,0,4,0,7);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,0,0,0,7);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,4,0,0,7);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,0,4,2,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,4,0,2,0);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,0,4,0,2);
		BuildHelper.setBlockDirectional(world,x,y+2,z,torch,direction,4,0,0,2);

//		TileEntityChest chest1 = (TileEntityChest)BuildHelper.getTileEntityDirectional(world,x,y+1,z,direction,3,0,1,0);
//		TileEntityChest chest2 = (TileEntityChest)BuildHelper.getTileEntityDirectional(world,x,y+1,z,direction,3,0,0,1);
//		chest1.setInventorySlotContents(0, new ItemStack(Items.stone_hoe, 1, 0));
//		chest2.setInventorySlotContents(0, new ItemStack(Items.stone_hoe, 1, 0));
	}
}