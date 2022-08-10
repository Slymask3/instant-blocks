package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.builder.BlockType;
import com.slymask3.instantblocks.builder.Builder;
import com.slymask3.instantblocks.builder.type.Multiple;
import com.slymask3.instantblocks.builder.type.Single;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;

public class InstantFarmBlock extends InstantBlock {
    public InstantFarmBlock() {
		super(Properties.of(Material.STONE)
				.strength(1.5F)
				.sound(SoundType.STONE)
		);
		setCreateMessage(Strings.CREATE_FARM);
		setDirectional(true);
    }

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_FARM();
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = Builder.setup(world,x,y,z).setSpeed(5);

		ArrayList<Helper.WeightedBlock> blocks = new ArrayList<>();
		blocks.add(new Helper.WeightedBlock(Blocks.WHEAT, Common.CONFIG.WEIGHT_WHEAT()));
		blocks.add(new Helper.WeightedBlock(Blocks.POTATOES, Common.CONFIG.WEIGHT_POTATOES()));
		blocks.add(new Helper.WeightedBlock(Blocks.CARROTS, Common.CONFIG.WEIGHT_CARROTS()));
		blocks.add(new Helper.WeightedBlock(Blocks.BEETROOTS, Common.CONFIG.WEIGHT_BEETROOTS()));
		Block crop = Helper.getRandomBlock(blocks);

		Block stone = Blocks.STONE_BRICKS;
		Block farm = Blocks.FARMLAND;
		Block water = Blocks.WATER;
		Block fence = Blocks.OAK_FENCE;
		Block gate = Blocks.OAK_FENCE_GATE;
		Block torch = Blocks.TORCH;
		Block craft = Blocks.CRAFTING_TABLE;
		Block air = Blocks.AIR;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);
		
		Multiple.setup(builder,world,x,y-1,z,direction,0,4,7,0,8,0,0,14,3,0).setBlock(air).queue();
		Multiple.setup(builder,world,x,y-1,z,direction,0,4,7,0,8,0,0,14,1,0).setBlock(stone).queue();

		Multiple.setup(builder,world,x,y+1,z,direction,0,4,7,0,8,0,0,0,0,0).setBlock(fence).queue(1);
		Multiple.setup(builder,world,x,y+1,z,direction,0,4,0,7,8,0,0,0,0,0).setBlock(fence).queue(1);
		Multiple.setup(builder,world,x,y+1,z,direction,0,4,6,0,0,0,0,12,0,0).setBlock(fence).queue(1);
		Multiple.setup(builder,world,x,y+1,z,direction,4,0,6,0,0,0,0,12,0,0).setBlock(fence).queue(1);

		Multiple.setup(builder,world,x,y,z,direction,0,3,6,0,6,0,0,1,0,0).setBlock(farm).queue(2);
		Multiple.setup(builder,world,x,y,z,direction,0,3,3,0,6,0,0,1,0,0).setBlock(farm).queue(2);
		Multiple.setup(builder,world,x,y,z,direction,0,3,0,2,6,0,0,1,0,0).setBlock(farm).queue(2);
		Multiple.setup(builder,world,x,y,z,direction,0,3,0,5,6,0,0,1,0,0).setBlock(farm).queue(2);

		Multiple.setup(builder,world,x,y,z,direction,0,3,4,0,6,0,0,0,0,0).setBlock(water).queue(3);
		Multiple.setup(builder,world,x,y,z,direction,0,3,0,4,6,0,0,0,0,0).setBlock(water).queue(3);

		Multiple.setup(builder,world,x,y+1,z,direction,0,3,6,0,6,0,0,1,0,0).setBlock(crop).queue(3,false);
		Multiple.setup(builder,world,x,y+1,z,direction,0,3,3,0,6,0,0,1,0,0).setBlock(crop).queue(3,false);
		Multiple.setup(builder,world,x,y+1,z,direction,0,3,0,2,6,0,0,1,0,0).setBlock(crop).queue(3,false);
		Multiple.setup(builder,world,x,y+1,z,direction,0,3,0,5,6,0,0,1,0,0).setBlock(crop).queue(3,false);

		Single.setup(builder,world,x,y+1,z).offset(direction,0,4,0,0).setBlock(gate).setDirection(direction).queue(1);
		Single.setup(builder,world,x,y+1,z).offset(direction,3,0,0,0).setBlock(craft).queue(4,false);
		Single.setup(builder,world,x,y+1,z).offset(direction,3,0,1,0).setBlock(BlockType.chest(false, new ItemStack(Items.STONE_HOE))).setDirection(direction).queue(4,false);
		Single.setup(builder,world,x,y+1,z).offset(direction,3,0,0,1).setBlock(BlockType.chest(false, new ItemStack(Items.STONE_HOE))).setDirection(direction).queue(4,false);

		Single.setup(builder,world,x,y+2,z).offset(direction,0,4,7,0).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,0,7,0).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,4,0,7,0).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,4,0,7).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,0,0,7).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,4,0,0,7).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,4,2,0).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,4,0,2,0).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,4,0,2).setBlock(torch).queue(4,false);
		Single.setup(builder,world,x,y+2,z).offset(direction,4,0,0,2).setBlock(torch).queue(4,false);

		builder.build();

		return true;
	}
}