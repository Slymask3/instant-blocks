package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
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
		, Config.Common.DISABLE_FARM);
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
		
		Builder.Multiple.setup(world,x,y-1,z,direction,0,4,7,0,8,0,0,14,3,0).setBlock(air).build();
		Builder.Multiple.setup(world,x,y-1,z,direction,0,4,7,0,8,0,0,14,1,0).setBlock(stone).build();

		Builder.Multiple.setup(world,x,y+1,z,direction,0,4,7,0,8,0,0,0,0,0).setBlock(fence).build();
		Builder.Multiple.setup(world,x,y+1,z,direction,0,4,0,7,8,0,0,0,0,0).setBlock(fence).build();
		Builder.Multiple.setup(world,x,y+1,z,direction,0,4,6,0,0,0,0,12,0,0).setBlock(fence).build();
		Builder.Multiple.setup(world,x,y+1,z,direction,4,0,6,0,0,0,0,12,0,0).setBlock(fence).build();

		Builder.Multiple.setup(world,x,y,z,direction,0,3,6,0,6,0,0,1,0,0).setBlock(farm).build();
		Builder.Multiple.setup(world,x,y,z,direction,0,3,3,0,6,0,0,1,0,0).setBlock(farm).build();
		Builder.Multiple.setup(world,x,y,z,direction,0,3,0,2,6,0,0,1,0,0).setBlock(farm).build();
		Builder.Multiple.setup(world,x,y,z,direction,0,3,0,5,6,0,0,1,0,0).setBlock(farm).build();

		Builder.Multiple.setup(world,x,y,z,direction,0,3,4,0,6,0,0,0,0,0).setBlock(water).build();
		Builder.Multiple.setup(world,x,y,z,direction,0,3,0,4,6,0,0,0,0,0).setBlock(water).build();

		Builder.Multiple.setup(world,x,y+1,z,direction,0,3,6,0,6,0,0,1,0,0).setBlock(crop).build();
		Builder.Multiple.setup(world,x,y+1,z,direction,0,3,3,0,6,0,0,1,0,0).setBlock(crop).build();
		Builder.Multiple.setup(world,x,y+1,z,direction,0,3,0,2,6,0,0,1,0,0).setBlock(crop).build();
		Builder.Multiple.setup(world,x,y+1,z,direction,0,3,0,5,6,0,0,1,0,0).setBlock(crop).build();

		Builder.Single.setup(world,x,y+1,z).offset(direction,0,4,0,0).setBlock(gate).setDirection(direction).build();
		Builder.Single.setup(world,x,y+1,z).offset(direction,3,0,0,0).setBlock(craft).build();
		Builder.Single.setup(world,x,y+1,z).offset(direction,3,0,1,0).setBlock(chest).setDirection(direction).build();
		Builder.Single.setup(world,x,y+1,z).offset(direction,3,0,0,1).setBlock(chest).setDirection(direction).build();

		Builder.Single.setup(world,x,y+2,z).offset(direction,0,4,7,0).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,0,7,0).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,4,0,7,0).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,4,0,7).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,0,0,7).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,4,0,0,7).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,4,2,0).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,4,0,2,0).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,4,0,2).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,4,0,0,2).setBlock(torch).build();

		ChestBlockEntity chest1 = (ChestBlockEntity) Builder.Single.setup(world,x,y+1,z).offset(direction,3,0,1,0).getBlockEntity();
		ChestBlockEntity chest2 = (ChestBlockEntity) Builder.Single.setup(world,x,y+1,z).offset(direction,3,0,0,1).getBlockEntity();
		Helper.addToChest(chest1,Items.STONE_HOE,1);
		Helper.addToChest(chest2,Items.STONE_HOE,1);

		return true;
	}
}