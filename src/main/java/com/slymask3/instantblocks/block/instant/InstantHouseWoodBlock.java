package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.Builder;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantHouseWoodBlock extends InstantBlock {
	public InstantHouseWoodBlock() {
		super(Block.Properties.of(Material.WOOD)
				.strength(1.5F, 2000F)
				.sound(SoundType.WOOD)
		, Config.Common.DISABLE_WOODEN_HOUSE);
		setCreateMessage(Strings.CREATE_WOODEN_HOUSE);
		setDirectional(true);
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		String planks_one = Config.Common.HOUSE_PLANKS_ONE.get();
		String planks_two = Config.Common.HOUSE_PLANKS_TWO.get();

		Block light = Helper.readBlock(planks_one + "_planks",Blocks.BIRCH_PLANKS);
		Block dark = Helper.readBlock(planks_two + "_planks",Blocks.SPRUCE_PLANKS);
		Block slabD = Helper.readBlock( planks_two + "_slab",Blocks.SPRUCE_SLAB);
		Block fence = Helper.readBlock(planks_two + "_fence",Blocks.SPRUCE_FENCE);
		Block gate = Helper.readBlock(planks_two + "_fence_gate",Blocks.SPRUCE_FENCE_GATE);
		Block stair = Helper.readBlock(planks_two + "_stairs",Blocks.SPRUCE_STAIRS);
		Block sign = Helper.readBlock(planks_two + "_wall_sign",Blocks.SPRUCE_WALL_SIGN);
		Block plate_light = Helper.readBlock(planks_one + "_pressure_plate",Blocks.BIRCH_PRESSURE_PLATE);
		Block plate_dark = Helper.readBlock(planks_two + "_pressure_plate",Blocks.SPRUCE_PRESSURE_PLATE);
		Block log = Helper.readBlock(Config.Common.HOUSE_LOG.get() + "_log",Blocks.BIRCH_LOG);
		Block door = Helper.readBlock(Config.Common.HOUSE_DOOR.get() + "_door",Blocks.DARK_OAK_DOOR);

		Block craft = Blocks.CRAFTING_TABLE;
		Block chest = Blocks.CHEST;
		Block furnace = Blocks.FURNACE;
		Block bed = Blocks.RED_BED;
		Block pane = Blocks.GLASS_PANE;
		Block torch = Blocks.TORCH;
		Block torch_wall = Blocks.WALL_TORCH;
		Block air = Blocks.AIR;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		Direction directionDoor = direction.getOpposite();
		Direction directionChestLeft = direction.getCounterClockWise();
		Direction directionStairLeft = direction.getCounterClockWise();
		Direction directionStairRight = direction.getClockWise();
		Direction directionBack = direction.getOpposite();
		Direction directionLeft = direction.getCounterClockWise();
		Direction directionRight = direction.getClockWise();

		//air
		Builder.Multiple.setup(world,x,y,z,direction,5,0,0,5,0,7,10,0,5,0).setBlock(air).build(); //HOUSE ROOM
		Builder.Multiple.setup(world,x,y+5,z,direction,4,0,0,4,0,5,8,0,0,0).setBlock(air).build(); //HOUSE ROOF 1
		Builder.Multiple.setup(world,x,y+6,z,direction,3,0,0,3,0,3,6,0,0,0).setBlock(air).build(); //HOUSE ROOF 2
		Builder.Multiple.setup(world,x,y,z,direction,0,3,0,4,0,3,8,0,4,0).setBlock(air).build(); //PORCH

		//floor
		Builder.Multiple.setup(world,x,y,z,direction,5,0,0,5,0,7,10,0,0,0).setBlock(dark).build(); //HOME FLOOR 1
		Builder.Multiple.setup(world,x,y,z,direction,4,0,0,4,0,5,8,0,0,0).setBlock(light).build(); //HOME FLOOR 2
		Builder.Multiple.setup(world,x,y,z,direction,3,0,0,3,0,3,6,0,0,0).setBlock(dark).build(); //HOME FLOOR 3
		Builder.Multiple.setup(world,x,y,z,direction,2,0,0,2,0,1,4,0,0,0).setBlock(light).build(); //HOME FLOOR 4
		Builder.Multiple.setup(world,x,y,z,direction,0,3,0,4,0,3,8,0,0,0).setBlock(dark).build(); //PORCH FLOOR 1
		Builder.Multiple.setup(world,x,y,z,direction,0,4,0,3,0,1,6,0,0,0).setBlock(light).build(); //PORCH FLOOR 2

		//house
		Builder.Multiple.setup(world,x,y+1,z,direction,5,0,0,5,0,0,0,0,3,0).setBlock(log).build(); //LOG CORNER 1
		Builder.Multiple.setup(world,x,y+1,z,direction,5,0,5,0,0,0,0,0,3,0).setBlock(log).build(); //LOG CORNER 2
		Builder.Multiple.setup(world,x,y+1,z,direction,0,2,0,5,0,0,0,0,3,0).setBlock(log).build(); //LOG CORNER 3
		Builder.Multiple.setup(world,x,y+1,z,direction,0,2,5,0,0,0,0,0,3,0).setBlock(log).build(); //LOG CORNER 4
		Builder.Multiple.setup(world,x,y+1,z,direction,5,0,0,4,0,0,8,0,3,0).setBlock(light).build(); //WOOD WALL 1
		Builder.Multiple.setup(world,x,y+1,z,direction,0,2,0,4,0,0,8,0,3,0).setBlock(light).build(); //WOOD WALL 2
		Builder.Multiple.setup(world,x,y+1,z,direction,4,0,0,5,0,5,0,0,3,0).setBlock(light).build(); //WOOD WALL 3
		Builder.Multiple.setup(world,x,y+1,z,direction,4,0,5,0,0,5,0,0,3,0).setBlock(light).build(); //WOOD WALL 4
		Builder.Multiple.setup(world,x,y+2,z,direction,5,0,0,3,0,0,6,0,1,0).setBlock(pane).build(); //PANE WALL 1
		Builder.Multiple.setup(world,x,y+2,z,direction,0,2,0,3,0,0,0,0,1,0).setBlock(pane).build(); //PANE WALL 2 (1)
		Builder.Multiple.setup(world,x,y+2,z,direction,0,2,3,0,0,0,0,0,1,0).setBlock(pane).build(); //PANE WALL 2 (2)
		Builder.Multiple.setup(world,x,y+2,z,direction,3,0,0,5,0,3,0,0,1,0).setBlock(pane).build(); //PANE WALL 3
		Builder.Multiple.setup(world,x,y+2,z,direction,3,0,5,0,0,3,0,0,1,0).setBlock(pane).build(); //PANE WALL 4
		Builder.Multiple.setup(world,x,y+1,z,direction,0,2,0,1,0,0,2,0,2,0).setBlock(log).build(); //LOG ENTRANCE
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,2,0,0).setBlock(door).setDirection(directionDoor).build();

		//porch
		Builder.Multiple.setup(world,x,y+4,z,direction,0,3,0,4,0,3,8,0,0,0).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 1
		Builder.Multiple.setup(world,x,y+4,z,direction,0,4,0,3,0,1,6,0,0,0).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 2
		Builder.Multiple.setup(world,x,y+1,z,direction,0,3,0,4,0,0,0,0,3,0).setBlock(dark).build(); //PORCH CORNER 1
		Builder.Multiple.setup(world,x,y+1,z,direction,0,3,4,0,0,0,0,0,3,0).setBlock(dark).build(); //PORCH CORNER 2
		Builder.Multiple.setup(world,x,y+1,z,direction,0,6,0,4,0,0,0,0,3,0).setBlock(dark).build(); //PORCH CORNER 3
		Builder.Multiple.setup(world,x,y+1,z,direction,0,6,4,0,0,0,0,0,3,0).setBlock(dark).build(); //PORCH CORNER 4
		Builder.Multiple.setup(world,x,y+2,z,direction,0,3,0,4,0,0,0,0,1,0).setBlock(fence).build(); //PORCH CORNER 1 (FENCE)
		Builder.Multiple.setup(world,x,y+2,z,direction,0,3,4,0,0,0,0,0,1,0).setBlock(fence).build(); //PORCH CORNER 2 (FENCE)
		Builder.Multiple.setup(world,x,y+2,z,direction,0,6,0,4,0,0,0,0,1,0).setBlock(fence).build(); //PORCH CORNER 3 (FENCE)
		Builder.Multiple.setup(world,x,y+2,z,direction,0,6,4,0,0,0,0,0,1,0).setBlock(fence).build(); //PORCH CORNER 4 (FENCE)
		Builder.Multiple.setup(world,x,y+1,z,direction,0,4,0,4,0,1,0,0,0,0).setBlock(fence).build(); //PORCH SIDE 1
		Builder.Multiple.setup(world,x,y+1,z,direction,0,4,4,0,0,1,0,0,0,0).setBlock(fence).build(); //PORCH SIDE 2
		Builder.Multiple.setup(world,x,y+1,z,direction,0,6,0,3,0,0,6,0,0,0).setBlock(fence).build(); //PORCH SIDE 3 (FRONT)
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,6,0,0).setBlock(gate).setDirection(direction).build(); //PORCH GATE

		//roof
		Builder.Multiple.setup(world,x,y+5,z,direction,5,0,0,5,0,0,10,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 1
		Builder.Multiple.setup(world,x,y+5,z,direction,0,2,0,5,0,0,10,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 2
		Builder.Multiple.setup(world,x,y+5,z,direction,4,0,0,5,0,5,0,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 3
		Builder.Multiple.setup(world,x,y+5,z,direction,4,0,5,0,0,5,0,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 4
		Builder.Multiple.setup(world,x,y+5,z,direction,4,0,0,4,0,0,8,0,0,0).setBlock(light).build(); //HOUSE ROOF 1
		Builder.Multiple.setup(world,x,y+5,z,direction,0,1,0,4,0,0,8,0,0,0).setBlock(light).build(); //HOUSE ROOF 2
		Builder.Multiple.setup(world,x,y+5,z,direction,3,0,0,4,0,3,0,0,0,0).setBlock(light).build(); //HOUSE ROOF 3
		Builder.Multiple.setup(world,x,y+5,z,direction,3,0,4,0,0,3,0,0,0,0).setBlock(light).build(); //HOUSE ROOF 4
		Builder.Multiple.setup(world,x,y+6,z,direction,3,0,0,3,0,0,6,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 1
		Builder.Multiple.setup(world,x,y+6,z,direction,0,0,0,3,0,0,6,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 2
		Builder.Multiple.setup(world,x,y+6,z,direction,2,0,0,3,0,1,0,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 3
		Builder.Multiple.setup(world,x,y+6,z,direction,2,0,3,0,0,1,0,0,0,0).setBlock(slabD).build(); //HOUSE ROOF 4
		Builder.Multiple.setup(world,x,y+6,z,direction,2,0,0,2,0,1,4,0,0,0).setBlock(light).build(); //HOUSE ROOF TOP

		//inside
		Builder.Single.setup(world,x,y+1,z).offset(direction,4,0,0,4).setBlock(bed).setDirection(directionLeft).build(); //BED
		Builder.Single.setup(world,x,y+1,z).offset(direction,4,0,2,0).setBlock(chest).setDirection(direction).build(); //CHEST
		Builder.Single.setup(world,x,y+1,z).offset(direction,4,0,3,0).setBlock(chest).setDirection(direction).build(); //CHEST
		Builder.Single.setup(world,x,y+1,z).offset(direction,3,0,4,0).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
		Builder.Single.setup(world,x,y+1,z).offset(direction,2,0,4,0).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
		Builder.Single.setup(world,x,y+1,z).offset(direction,4,0,4,0).setBlock(craft).build(); //WORKBENCH
		Builder.Single.setup(world,x,y+1,z).offset(direction,2,0,0,4).setBlock(furnace).setDirection(directionRight).build(); //FURNACE
		Builder.Single.setup(world,x,y+1,z).offset(direction,1,0,0,4).setBlock(furnace).setDirection(directionRight).build(); //FURNACE
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,1,0,4).setBlock(stair).setDirection(directionStairLeft).build(); //CHAIR
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,1,4,0).setBlock(stair).setDirection(directionStairRight).build(); //CHAIR
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,0,0,4).setBlock(sign).setDirection(directionBack).build(); //CHAIR
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,0,4,0).setBlock(sign).setDirection(directionBack).build(); //CHAIR
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,1,0,2).setBlock(plate_dark).build(); //TABLE
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,1,2,0).setBlock(plate_dark).build(); //TABLE
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,1,0,2).setBlock(fence).build(); //TABLE
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,1,2,0).setBlock(fence).build(); //TABLE

		//above door torches
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,3,1,0).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,3,0,1).setBlock(torch_wall).setDirection(direction).build();

		//outdoor torches
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,7,4,0).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,7,0,4).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,5,0).setBlock(torch_wall).setDirection(directionRight).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,0,5).setBlock(torch_wall).setDirection(directionLeft).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,0,1).setBlock(torch).build();
		Builder.Single.setup(world,x,y+2,z).offset(direction,0,6,1,0).setBlock(torch).build();

		//indoor torches
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,1,2,0).setBlock(torch_wall).setDirection(directionBack).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,1,0,2).setBlock(torch_wall).setDirection(directionBack).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,4,0,2,0).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,4,0,0,2).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,1,0,4,0).setBlock(torch_wall).setDirection(directionLeft).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,2,0,4,0).setBlock(torch_wall).setDirection(directionLeft).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,1,0,0,4).setBlock(torch_wall).setDirection(directionRight).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,2,0,0,4).setBlock(torch_wall).setDirection(directionRight).build();

		//pressure plates
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,1,0,0).setBlock(plate_light).build();
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,3,0,0).setBlock(plate_dark).build();
		Builder.Single.setup(world,x,y+1,z).offset(direction,0,5,0,0).setBlock(plate_light).build();

		return true;
	}
}