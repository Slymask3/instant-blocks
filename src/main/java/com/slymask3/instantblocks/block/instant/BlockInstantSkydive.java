package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.GuiID;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.tileentity.TileEntitySkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.Coords;
import com.slymask3.instantblocks.util.IBHelper;
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
import java.util.ArrayList;

public class BlockInstantSkydive extends BlockInstant implements EntityBlock {
    public BlockInstantSkydive() {
		super(Block.Properties.of(Material.WOOL)
				.strength(1.5F, 2000F)
				.sound(SoundType.WOOL)
		);
		setGuiID(GuiID.SKYDIVE);
		setCreateMessage(Strings.CREATE_SKYDIVE);
    }

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileEntitySkydive(pos,state);
	}

	public void build(Level world, int x, int y, int z, Player player, int[] selectedColors, int radius, boolean tp) {
		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		ArrayList<Coords> coordsList = new ArrayList<>();
		ArrayList<Coords> coordsAirList = new ArrayList<>();
		double distance;
		for (int row = 0; row <= 2 * radius; row++) {
			for (int col = 0; col <= 2 * radius; col++) {
				distance = Math.sqrt((row - radius) * (row - radius) + (col - radius) * (col - radius));
				if(distance > radius - 0.4 && distance < radius + 0.5) {
					coordsList.add(new Coords(x+row-radius,0,z+col-radius));
				} else if(distance < radius - 0.3) {
					coordsAirList.add(new Coords(x+row-radius,0,z+col-radius));
				}
			}
		}

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
			colors[index] = Colors.getColorBetween(base,after,90,10); index++;
			colors[index] = Colors.getColorBetween(base,after,80,20); index++;
			colors[index] = Colors.getColorBetween(base,after,70,30); index++;
			colors[index] = Colors.getColorBetween(base,after,60,40); index++;
			colors[index] = Colors.getColorBetween(base,after,50,50); index++;
			colors[index] = Colors.getColorBetween(base,after,40,60); index++;
			colors[index] = Colors.getColorBetween(base,after,30,70); index++;
			colors[index] = Colors.getColorBetween(base,after,20,80); index++;
			colors[index] = Colors.getColorBetween(base,after,10,90); index++;
		}

		int i = 0;
		int min = Config.Common.SKYDIVE_MIN.get();
		int max = IBHelper.getMaxSkydive(world);
		int water = Config.Common.SKYDIVE_WATER.get();
		for(int c=max; c>=min; c--) {
			for(Coords coords : coordsAirList) {
				if(c == min) {
					BuildHelper.setColorBlock(world,coords.getX(),c,coords.getZ(),colors[i].getRGB());
				} else if(c < min+water+1) {
					BuildHelper.setBlock(world,coords.getX(),c,coords.getZ(),Blocks.WATER);
				} else {
					BuildHelper.setBlock(world,coords.getX(),c,coords.getZ(),Blocks.AIR);
				}
			}
			for(Coords coords : coordsList) {
				if(i>=colors.length) {
					i = 0;
				}
				if(c == min+water+1 && (((coords.getX()-radius==x || coords.getX()+radius==x) && coords.getZ()==z) || ((coords.getZ()-radius==z || coords.getZ()+radius==z) && coords.getX()==x))) {
					BuildHelper.setBlock(world,coords.getX(),c,coords.getZ(),ModBlocks.skydiveTP.get());
				} else {
					BuildHelper.setColorBlock(world,coords.getX(),c,coords.getZ(),colors[i].getRGB());
				}
			}
			i++;
		}

		if(direction == Direction.SOUTH) {
			IBHelper.teleport(world,player,x,max+1,z+radius, tp);
		} else if(direction == Direction.WEST) {
			IBHelper.teleport(world,player,x-radius,max+1,z, tp);
		} else if(direction == Direction.NORTH) {
			IBHelper.teleport(world,player,x,max+1,z-radius, tp);
		} else if(direction == Direction.EAST) {
			IBHelper.teleport(world,player,x+radius,max+1,z, tp);
		}
	}
}