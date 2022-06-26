package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantMiningLadderBlock extends InstantBlock {
    public InstantMiningLadderBlock() {
		super(Block.Properties.of(Material.STONE)
				.strength(1.5F, 2000F)
				.sound(SoundType.STONE)
		, Config.Common.DISABLE_MINING_LADDER);
		setCreateMessage(Strings.CREATE_MINING_LADDER);
		setDirectional(true);
    }

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		if(y <= Config.Common.MINING_LADDER_LAYER.get() + 4) {
			Helper.sendMessage(player, Strings.ERROR_LADDER, ChatFormatting.RED + String.valueOf(Config.Common.MINING_LADDER_LAYER.get() + 4));
			return false;
		}
		return true;
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Block ladder = Blocks.LADDER;
		Block torch = Blocks.TORCH;
		Block water = Blocks.WATER;
		Block sign = Blocks.OAK_WALL_SIGN;
		Block air = Blocks.AIR;

		int layer = Config.Common.MINING_LADDER_LAYER.get();

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
		Direction directionLadder = direction.getCounterClockWise();
		Direction directionSign = direction.getClockWise();

		for(int c = y; c > layer-2; c--) {
			BuildHelper.buildStoneDirectional(world,x,c,z,direction,0,1,2,0,2,0,0,4,0,0);
		}
		for(int c = y; c > layer-1; c--) {
			BuildHelper.setBlockDirectional(world,x, c, z, ladder, direction, 0,0,1,0,directionLadder);
		}
		for(int c = y; c > layer-1; c--) {
			BuildHelper.setBlockDirectional(world,x, c, z, air, direction, 0,0,0,1);
		}
		for(int c = y; c > layer+1; c = c - 3) {
			c = c - 3;
			BuildHelper.setBlock(world,x, c, z, torch);
		}

		BuildHelper.setBlock(world,x, layer, z, air); //MIDDLE AIR
		BuildHelper.setBlockLight(world,new BlockPos(x,layer+1,z),Blocks.WALL_TORCH,direction); //MIDDLE TORCH
		BuildHelper.setStone(world,x, layer+2, z); //ABOVE MIDDLE TORCH
		BuildHelper.setBlockDirectional(world,x, layer+2, z, water,direction,0,0,0,1); //WATER
		BuildHelper.setBlockDirectional(world,x, layer+1, z, sign, direction, 0,0,0,1,directionSign);
		BuildHelper.setStone(world,x, layer-1, z); //MIDDLE STONE

		return true;
	}
}