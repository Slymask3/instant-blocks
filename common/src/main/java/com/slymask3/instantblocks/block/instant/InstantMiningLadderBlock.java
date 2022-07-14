package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.core.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
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
		super(Properties.of(Material.STONE)
				.strength(1.5F)
				.sound(SoundType.STONE)
		, Config.Common.DISABLE_MINING_LADDER);
		setCreateMessage(Strings.CREATE_MINING_LADDER);
		setDirectional(true);
    }

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		if(y <= Config.Common.MINING_LADDER_LAYER + 4) {
			Helper.sendMessage(player, Strings.ERROR_LADDER, ChatFormatting.RED + String.valueOf(Config.Common.MINING_LADDER_LAYER + 4));
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

		int layer = Config.Common.MINING_LADDER_LAYER;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
		Direction directionLadder = direction.getCounterClockWise();
		Direction directionSign = direction.getClockWise();

		for(int c = y; c > layer-2; c--) {
			Builder.Multiple.setup(world,x,c,z,direction,0,1,2,0,2,0,0,4,0,0).setStone().build();
		}
		for(int c = y; c > layer-1; c--) {
			Builder.Single.setup(world,x,c,z).offset(direction,0,0,1,0).setBlock(ladder).setDirection(directionLadder).build();
		}
		for(int c = y; c > layer-1; c--) {
			Builder.Single.setup(world,x,c,z).offset(direction,0,0,0,1).setBlock(air).build();
		}
		for(int c = y; c > layer+1; c = c - 3) {
			c = c - 3;
			Builder.Single.setup(world,x,c,z).setBlock(torch).build();
		}

		Builder.Single.setup(world,x,layer,z).setBlock(air).build(); //MIDDLE AIR
		Builder.Single.setup(world,x,layer+1,z).setBlock(Blocks.WALL_TORCH).setDirection(direction).build(); //MIDDLE TORCH
		Builder.Single.setup(world,x,layer+2,z).setStone().build(); //ABOVE MIDDLE TORCH
		Builder.Single.setup(world,x,layer+2,z).offset(direction,0,0,0,1).setBlock(water).build(); //WATER
		Builder.Single.setup(world,x,layer+1,z).offset(direction,0,0,0,1).setBlock(sign).setDirection(directionSign).build();
		Builder.Single.setup(world,x,layer-1,z).setStone().build(); //MIDDLE STONE

		return true;
	}
}