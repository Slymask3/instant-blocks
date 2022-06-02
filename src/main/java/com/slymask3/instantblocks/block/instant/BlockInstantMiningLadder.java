package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockInstantMiningLadder extends BlockInstant {
    public BlockInstantMiningLadder() {
		super(Block.Properties.of(Material.STONE)
				.strength(1.5F, 2000F)
				.sound(SoundType.STONE)
		);
		setCreateMessage(Strings.CREATE_MINING_LADDER);
		setDirectional(true);
    }

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		if(y <= Config.Common.MINING_LADDER_LAYER.get() + 4) {
			IBHelper.msg(player, Strings.ERROR_LADDER.replace("%i%",String.valueOf(Config.Common.MINING_LADDER_LAYER.get() + 4)), Colors.c);
			return false;
		}
		return true;
	}

	public void build(Level world, int x, int y, int z, Player player) {
		Block ladder = Blocks.LADDER;
		Block stone = Blocks.STONE;
		Block torch = Blocks.WALL_TORCH;
		Block water = Blocks.WATER;
		Block sign = Blocks.OAK_WALL_SIGN;
		Block air = Blocks.AIR;

		int layer = Config.Common.MINING_LADDER_LAYER.get();

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
		Direction directionLadder = direction.getCounterClockWise();
		Direction directionSign = direction.getClockWise();

		for(int c = y; c > layer-2; c--) {
			BuildHelper.buildDirectional(world,x,c,z,stone,direction,0,1,2,0,2,0,0,4,0,0);
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
		BuildHelper.setBlock(world,x, layer+1, z, torch); //MIDDLE TORCH
		BuildHelper.setBlock(world,x, layer+2, z, stone); //ABOVE MIDDLE TORCH
		BuildHelper.setBlockDirectional(world,x, layer+2, z, water,direction,0,0,0,1); //WATER
		BuildHelper.setBlockDirectional(world,x, layer+1, z, sign, direction, 0,0,0,1,directionSign);
		BuildHelper.setBlock(world,x, layer-1, z, stone); //MIDDLE STONE
	}
}