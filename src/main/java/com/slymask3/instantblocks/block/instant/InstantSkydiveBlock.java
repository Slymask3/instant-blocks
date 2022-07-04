package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.block.entity.SkydiveBlockEntity;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import com.slymask3.instantblocks.util.ClientHelper;
import com.slymask3.instantblocks.util.ColorHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class InstantSkydiveBlock extends InstantBlock implements EntityBlock {
    public InstantSkydiveBlock() {
		super(Block.Properties.of(Material.WOOL)
				.strength(1.5F)
				.sound(SoundType.WOOL)
		, Config.Common.DISABLE_SKYDIVE);
		setScreen(ClientHelper.Screen.SKYDIVE);
		setCreateMessage(Strings.CREATE_SKYDIVE);
		setDirectional(true);
    }

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SkydiveBlockEntity(pos,state);
	}

	public boolean build(Level world, int x, int y, int z, Player player, int[] selectedColors, int radius, boolean teleportToTop) {
		if(selectedColors.length == 0) {
			Helper.sendMessage(player,Strings.ERROR_NO_COLORS);
			return false;
		}

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		Builder.Single.setup(world,x,y,z).setBlock(Blocks.AIR).build();

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

		int i = 0;
		int min = Helper.getMinSkydive(world);
		int max = Helper.getMaxSkydive(world);
		int water = Config.Common.SKYDIVE_WATER.get();
		for(int c=max; c>=min; c--) {
			if(i>=colors.length) {
				i = 0;
			}
			int color = colors[i].getRGB();
			if(c == min) {
				Builder.Circle.setup(world,x,c,z,radius).setBlock(Builder.BlockType.color(color)).build();
			} else if(c < min+water+1) {
				Builder.Circle.setup(world,x,c,z,radius).setInner(Builder.BlockType.block(Blocks.WATER)).setOuter(Builder.BlockType.color(color)).build();
			} else {
				Builder.Circle.setup(world,x,c,z,radius).setInner(Builder.BlockType.block(Blocks.AIR)).setOuter(Builder.BlockType.color(color)).build();
			}
			if(c == min+water+1) {
				Builder.Single.setup(world,x+radius,c,z).setBlock(ModBlocks.SKYDIVE_TP.get()).build();
				Builder.Single.setup(world,x-radius,c,z).setBlock(ModBlocks.SKYDIVE_TP.get()).build();
				Builder.Single.setup(world,x,c,z+radius).setBlock(ModBlocks.SKYDIVE_TP.get()).build();
				Builder.Single.setup(world,x,c,z-radius).setBlock(ModBlocks.SKYDIVE_TP.get()).build();
			}
			i++;
		}

		if(teleportToTop) {
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