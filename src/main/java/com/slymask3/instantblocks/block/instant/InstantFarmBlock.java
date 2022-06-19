package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;

public class InstantFarmBlock extends InstantBlock {
    public InstantFarmBlock() {
		super(Block.Properties.of(Material.STONE)
				.strength(1.5F, 2000F)
				.sound(SoundType.STONE)
		);
		setCreateMessage(Strings.CREATE_FARM);
		setDirectional(true);
    }

	public boolean build(Level world, int x, int y, int z, Player player) {
		ArrayList<Helper.WeightedBlock> blocks = new ArrayList<>();
		blocks.add(new Helper.WeightedBlock(Blocks.WHEAT, Config.Common.WEIGHT_WHEAT.get()));
		blocks.add(new Helper.WeightedBlock(Blocks.POTATOES, Config.Common.WEIGHT_POTATOES.get()));
		blocks.add(new Helper.WeightedBlock(Blocks.CARROTS, Config.Common.WEIGHT_CARROTS.get()));
		blocks.add(new Helper.WeightedBlock(Blocks.BEETROOTS, Config.Common.WEIGHT_BEETROOTS.get()));
		Block crop = Helper.getRandomBlock(blocks);

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

		BuildHelper.setBlockDirectional(world,x,y+1,z,gate,direction,0,4,0,0,direction);
		BuildHelper.setBlockDirectional(world,x,y+1,z,craft,direction,3,0,0,0);
		BuildHelper.setBlockDirectional(world,x,y+1,z,chest,direction,3,0,1,0,direction);
		BuildHelper.setBlockDirectional(world,x,y+1,z,chest,direction,3,0,0,1,direction);

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

		ChestBlockEntity chest1 = (ChestBlockEntity)BuildHelper.getBlockEntityDirectional(world,x,y+1,z,direction,3,0,1,0);
		ChestBlockEntity chest2 = (ChestBlockEntity)BuildHelper.getBlockEntityDirectional(world,x,y+1,z,direction,3,0,0,1);
		Helper.addToChest(chest1,Items.STONE_HOE,1);
		Helper.addToChest(chest2,Items.STONE_HOE,1);

		return true;
	}
}