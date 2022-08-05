package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.SkydiveBlockEntity;
import com.slymask3.instantblocks.builder.BlockType;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Circle;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.core.ModBlocks;
import com.slymask3.instantblocks.network.packet.SkydiveUpdatePacket;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.ColorHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.awt.*;

public class InstantSkydiveBlock extends InstantBlock implements EntityBlock {
    public InstantSkydiveBlock() {
		super(Properties.of(Material.WOOL)
				.strength(0.8F)
				.sound(SoundType.WOOL)
		);
		setScreen(ClientHelper.Screen.SKYDIVE);
		setCreateMessage(Strings.CREATE_SKYDIVE);
		setDirectional(true);
    }

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_SKYDIVE();
	}

	public void openScreen(Player player, BlockPos pos) {
		Common.NETWORK.sendToClient(player, new SkydiveUpdatePacket(Common.CONFIG.SKYDIVE_PRESETS(),pos));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SkydiveBlockEntity(pos,state);
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = new Builder();

		SkydiveBlockEntity blockEntity = (SkydiveBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
		int[] selectedColors = blockEntity.colorCode;
		int radius = blockEntity.radius;

		if(selectedColors.length == 0) {
			Helper.sendMessage(player,Strings.ERROR_NO_COLORS);
			return false;
		}

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		Single.setup(builder,world,x,y,z).setBlock(Blocks.AIR).build();

		Color[] colors;
		int index = 0;
		colors = new Color[selectedColors.length*10];
		for(int i=0; i<selectedColors.length; i++) {
			Color after;
			if(i+1 < selectedColors.length) {
				after = new Color(selectedColors[i+1]);
			} else {
				after = new Color(selectedColors[0]);
			}
			Color base = new Color(selectedColors[i]);
			colors[index] = base; index++;
			colors[index] = ColorHelper.getColorBetween(base,after,90,10); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,80,20); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,70,30); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,60,40); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,50,50); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,40,60); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,30,70); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,20,80); index++;
			colors[index] = ColorHelper.getColorBetween(base,after,10,90); index++;
		}

		int colorIndex = 0;
		int queueCount = 0;
		int queue = 0;
		int min = Helper.getMinSkydive(world);
		int max = Helper.getMaxSkydive(world);
		int water = Common.CONFIG.SKYDIVE_WATER();
		for(int c=max; c>=min; c--) {
			if(colorIndex >= colors.length) {
				colorIndex = 0;
			}
			int color = colors[colorIndex].getRGB();
			if(c == min) {
				Circle.setup(builder,world,x,c,z,radius).setBlock(BlockType.color(color)).queue(queue);
			} else if(c < min+water+1) {
				Circle.setup(builder,world,x,c,z,radius).setInner(BlockType.block(Blocks.WATER)).setOuter(BlockType.color(color)).queue(queue);
			} else {
				Circle.setup(builder,world,x,c,z,radius).setInner(BlockType.block(Blocks.AIR)).setOuter(BlockType.color(color)).queue(queue);
			}
			if(c == min+water+1) {
				Single.setup(builder,world,x+radius,c,z).setBlock(BlockType.color(color,ModBlocks.SKYDIVE_TP)).queue(queue);
				Single.setup(builder,world,x-radius,c,z).setBlock(BlockType.color(color,ModBlocks.SKYDIVE_TP)).queue(queue);
				Single.setup(builder,world,x,c,z+radius).setBlock(BlockType.color(color,ModBlocks.SKYDIVE_TP)).queue(queue);
				Single.setup(builder,world,x,c,z-radius).setBlock(BlockType.color(color,ModBlocks.SKYDIVE_TP)).queue(queue);
			}
			colorIndex++;
			queueCount++;
			if(queueCount == 3) {
				queue++;
				queueCount = 0;
			}
		}

		if(Helper.isNether(world)) {
			for(int i=max; i < max+4; ++i) {
				Circle.setup(builder,world,x,i,z,radius).setBlock(BlockType.block(Blocks.AIR)).queue();
			}
		}

		builder.build();

		if(blockEntity.teleport) {
			if(direction == Direction.SOUTH) {
				Helper.teleport(world,player,x,max+1,z+radius);
			} else if(direction == Direction.WEST) {
				Helper.teleport(world,player,x-radius,max+1,z);
			} else if(direction == Direction.NORTH) {
				Helper.teleport(world,player,x,max+1,z-radius);
			} else if(direction == Direction.EAST) {
				Helper.teleport(world,player,x+radius,max+1,z);
			}
		}

		return true;
	}
}