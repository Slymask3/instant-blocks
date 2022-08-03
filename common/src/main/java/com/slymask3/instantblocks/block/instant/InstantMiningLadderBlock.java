package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Multiple;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
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
		);
		setCreateMessage(Strings.CREATE_MINING_LADDER);
		setDirectional(true);
    }

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_MINING_LADDER();
	}

	public boolean canActivate(Level world, BlockPos pos, Player player) {
		if(pos.getY() <= Common.CONFIG.MINING_LADDER_LAYER() + 4) {
			Helper.sendMessage(player, Strings.ERROR_LADDER, ChatFormatting.RED + String.valueOf(Common.CONFIG.MINING_LADDER_LAYER() + 4));
			return false;
		}
		return true;
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		Builder builder = new Builder(2, direction.getCounterClockWise());

		Block ladder = Blocks.LADDER;
		Block torch = Blocks.TORCH;
		Block water = Blocks.WATER;
		Block sign = Blocks.OAK_WALL_SIGN;
		Block air = Blocks.AIR;

		int layer = Common.CONFIG.MINING_LADDER_LAYER();

		Direction directionLadder = direction.getCounterClockWise();
		Direction directionSign = direction.getClockWise();

		for(int c = y; c > layer-2; c--) {
			Multiple.setup(builder,world,x,c,z,direction,0,1,2,0,2,0,0,4,0,0).setStone().queue();
		}
		for(int c = y; c > layer-1; c--) {
			Single.setup(builder,world,x,c,z).offset(direction,0,0,1,0).setBlock(ladder).setDirection(directionLadder).queue();
		}
		for(int c = y; c > layer-1; c--) {
			Single.setup(builder,world,x,c,z).offset(direction,0,0,0,1).setBlock(air).queue();
		}
		for(int c = y; c > layer+1; c = c - 3) {
			c = c - 3;
			Single.setup(builder,world,x,c,z).setBlock(torch).queue();
		}

		Single.setup(builder,world,x,layer,z).setBlock(air).queue(); //MIDDLE AIR
		Single.setup(builder,world,x,layer+1,z).setBlock(Blocks.WALL_TORCH).setDirection(direction).queue(); //MIDDLE TORCH
		Single.setup(builder,world,x,layer+2,z).setStone().queue(); //ABOVE MIDDLE TORCH
		Single.setup(builder,world,x,layer+2,z).offset(direction,0,0,0,1).setBlock(water).queue(); //WATER
		Single.setup(builder,world,x,layer+1,z).offset(direction,0,0,0,1).setBlock(sign).setDirection(directionSign).queue();
		Single.setup(builder,world,x,layer-1,z).setStone().queue(); //MIDDLE STONE

		builder.build();

		return true;
	}
}