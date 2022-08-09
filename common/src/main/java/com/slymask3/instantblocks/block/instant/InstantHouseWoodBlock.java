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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantHouseWoodBlock extends InstantBlock {
	public InstantHouseWoodBlock() {
		super(Properties.of(Material.WOOD)
				.strength(1.5F)
				.sound(SoundType.WOOD)
		);
		setCreateMessage(Strings.CREATE_WOODEN_HOUSE);
		setDirectional(true);
	}

	public boolean isEnabled() {
		return Common.CONFIG.ENABLE_WOODEN_HOUSE();
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Builder builder = Builder.setup(world,x,y,z).setSpeed(5);

		String planks_one = Common.CONFIG.HOUSE_PLANKS_ONE();
		String planks_two = Common.CONFIG.HOUSE_PLANKS_TWO();

		Block light = Helper.readBlock(planks_one + "_planks",Blocks.BIRCH_PLANKS);
		Block dark = Helper.readBlock(planks_two + "_planks",Blocks.SPRUCE_PLANKS);
		Block slabD = Helper.readBlock( planks_two + "_slab",Blocks.SPRUCE_SLAB);
		Block fence = Helper.readBlock(planks_two + "_fence",Blocks.SPRUCE_FENCE);
		Block gate = Helper.readBlock(planks_two + "_fence_gate",Blocks.SPRUCE_FENCE_GATE);
		Block stair = Helper.readBlock(planks_two + "_stairs",Blocks.SPRUCE_STAIRS);
		Block sign = Helper.readBlock(planks_two + "_wall_sign",Blocks.SPRUCE_WALL_SIGN);
		Block plate_light = Helper.readBlock(planks_one + "_pressure_plate",Blocks.BIRCH_PRESSURE_PLATE);
		Block plate_dark = Helper.readBlock(planks_two + "_pressure_plate",Blocks.SPRUCE_PRESSURE_PLATE);
		Block log = Helper.readBlock(Common.CONFIG.HOUSE_LOG() + "_log",Blocks.BIRCH_LOG);
		Block door = Helper.readBlock(Common.CONFIG.HOUSE_DOOR() + "_door",Blocks.DARK_OAK_DOOR);

		Block craft = Blocks.CRAFTING_TABLE;
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
		Multiple.setup(builder,world,x,y,z,direction,5,0,0,5,0,7,10,0,5,0).setBlock(air).queue(); //HOUSE ROOM
		Multiple.setup(builder,world,x,y+5,z,direction,4,0,0,4,0,5,8,0,0,0).setBlock(air).queue(); //HOUSE ROOF 1
		Multiple.setup(builder,world,x,y+6,z,direction,3,0,0,3,0,3,6,0,0,0).setBlock(air).queue(); //HOUSE ROOF 2
		Multiple.setup(builder,world,x,y,z,direction,0,3,0,4,0,3,8,0,4,0).setBlock(air).queue(); //PORCH

		//floor
		Multiple.setup(builder,world,x,y,z,direction,5,0,0,5,0,7,10,0,0,0).setBlock(dark).queue(); //HOME FLOOR 1
		Multiple.setup(builder,world,x,y,z,direction,4,0,0,4,0,5,8,0,0,0).setBlock(light).queue(); //HOME FLOOR 2
		Multiple.setup(builder,world,x,y,z,direction,3,0,0,3,0,3,6,0,0,0).setBlock(dark).queue(); //HOME FLOOR 3
		Multiple.setup(builder,world,x,y,z,direction,2,0,0,2,0,1,4,0,0,0).setBlock(light).queue(); //HOME FLOOR 4
		Multiple.setup(builder,world,x,y,z,direction,0,3,0,4,0,3,8,0,0,0).setBlock(dark).queue(); //PORCH FLOOR 1
		Multiple.setup(builder,world,x,y,z,direction,0,4,0,3,0,1,6,0,0,0).setBlock(light).queue(); //PORCH FLOOR 2

		//house
		Multiple.setup(builder,world,x,y+1,z,direction,5,0,0,5,0,0,0,0,3,0).setBlock(log).queue(); //LOG CORNER 1
		Multiple.setup(builder,world,x,y+1,z,direction,5,0,5,0,0,0,0,0,3,0).setBlock(log).queue(); //LOG CORNER 2
		Multiple.setup(builder,world,x,y+1,z,direction,0,2,0,5,0,0,0,0,3,0).setBlock(log).queue(); //LOG CORNER 3
		Multiple.setup(builder,world,x,y+1,z,direction,0,2,5,0,0,0,0,0,3,0).setBlock(log).queue(); //LOG CORNER 4
		Multiple.setup(builder,world,x,y+1,z,direction,5,0,0,4,0,0,8,0,3,0).setBlock(light).queue(); //WOOD WALL 1
		Multiple.setup(builder,world,x,y+1,z,direction,0,2,0,4,0,0,8,0,3,0).setBlock(light).queue(); //WOOD WALL 2
		Multiple.setup(builder,world,x,y+1,z,direction,4,0,0,5,0,5,0,0,3,0).setBlock(light).queue(); //WOOD WALL 3
		Multiple.setup(builder,world,x,y+1,z,direction,4,0,5,0,0,5,0,0,3,0).setBlock(light).queue(); //WOOD WALL 4
		Multiple.setup(builder,world,x,y+2,z,direction,5,0,0,3,0,0,6,0,1,0).setBlock(pane).queue(1); //PANE WALL 1
		Multiple.setup(builder,world,x,y+2,z,direction,0,2,0,3,0,0,0,0,1,0).setBlock(pane).queue(1); //PANE WALL 2 (1)
		Multiple.setup(builder,world,x,y+2,z,direction,0,2,3,0,0,0,0,0,1,0).setBlock(pane).queue(1); //PANE WALL 2 (2)
		Multiple.setup(builder,world,x,y+2,z,direction,3,0,0,5,0,3,0,0,1,0).setBlock(pane).queue(1); //PANE WALL 3
		Multiple.setup(builder,world,x,y+2,z,direction,3,0,5,0,0,3,0,0,1,0).setBlock(pane).queue(1); //PANE WALL 4
		Multiple.setup(builder,world,x,y+1,z,direction,0,2,1,0,0,0,0,0,2,0).setBlock(log).queue(); //LOG ENTRANCE
		Multiple.setup(builder,world,x,y+1,z,direction,0,2,0,1,0,0,0,0,2,0).setBlock(log).queue(); //LOG ENTRANCE
		Single.setup(builder,world,x,y+3,z).offset(direction,0,2,0,0).setBlock(log).queue(); //LOG ENTRANCE
		Single.setup(builder,world,x,y+1,z).offset(direction,0,2,0,0).setBlock(door).setDirection(directionDoor).queue(1);

		//porch
		Multiple.setup(builder,world,x,y+4,z,direction,0,3,0,4,0,3,8,0,0,0).setBlock(slabD).setDirection(Direction.UP).queue(); //PORCH ROOF 1
		Multiple.setup(builder,world,x,y+4,z,direction,0,4,0,3,0,1,6,0,0,0).setBlock(slabD).setDirection(Direction.UP).queue(); //PORCH ROOF 2
		Multiple.setup(builder,world,x,y+1,z,direction,0,3,0,4,0,0,0,0,3,0).setBlock(dark).queue(); //PORCH CORNER 1
		Multiple.setup(builder,world,x,y+1,z,direction,0,3,4,0,0,0,0,0,3,0).setBlock(dark).queue(); //PORCH CORNER 2
		Multiple.setup(builder,world,x,y+1,z,direction,0,6,0,4,0,0,0,0,3,0).setBlock(dark).queue(); //PORCH CORNER 3
		Multiple.setup(builder,world,x,y+1,z,direction,0,6,4,0,0,0,0,0,3,0).setBlock(dark).queue(); //PORCH CORNER 4
		Multiple.setup(builder,world,x,y+2,z,direction,0,3,0,4,0,0,0,0,1,0).setBlock(fence).queue(2); //PORCH CORNER 1 (FENCE)
		Multiple.setup(builder,world,x,y+2,z,direction,0,3,4,0,0,0,0,0,1,0).setBlock(fence).queue(2); //PORCH CORNER 2 (FENCE)
		Multiple.setup(builder,world,x,y+2,z,direction,0,6,0,4,0,0,0,0,1,0).setBlock(fence).queue(2); //PORCH CORNER 3 (FENCE)
		Multiple.setup(builder,world,x,y+2,z,direction,0,6,4,0,0,0,0,0,1,0).setBlock(fence).queue(2); //PORCH CORNER 4 (FENCE)
		Multiple.setup(builder,world,x,y+1,z,direction,0,4,0,4,0,1,0,0,0,0).setBlock(fence).queue(2); //PORCH SIDE 1
		Multiple.setup(builder,world,x,y+1,z,direction,0,4,4,0,0,1,0,0,0,0).setBlock(fence).queue(2); //PORCH SIDE 2
		Multiple.setup(builder,world,x,y+1,z,direction,0,6,0,3,0,0,6,0,0,0).setBlock(fence).queue(2); //PORCH SIDE 3 (FRONT)
		Single.setup(builder,world,x,y+1,z).offset(direction,0,6,0,0).setBlock(gate).setDirection(direction).queue(2); //PORCH GATE

		//roof
		Multiple.setup(builder,world,x,y+5,z,direction,5,0,0,5,0,0,10,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 1
		Multiple.setup(builder,world,x,y+5,z,direction,0,2,0,5,0,0,10,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 2
		Multiple.setup(builder,world,x,y+5,z,direction,4,0,0,5,0,5,0,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 3
		Multiple.setup(builder,world,x,y+5,z,direction,4,0,5,0,0,5,0,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 4
		Multiple.setup(builder,world,x,y+5,z,direction,4,0,0,4,0,0,8,0,0,0).setBlock(light).queue(); //HOUSE ROOF 1
		Multiple.setup(builder,world,x,y+5,z,direction,0,1,0,4,0,0,8,0,0,0).setBlock(light).queue(); //HOUSE ROOF 2
		Multiple.setup(builder,world,x,y+5,z,direction,3,0,0,4,0,3,0,0,0,0).setBlock(light).queue(); //HOUSE ROOF 3
		Multiple.setup(builder,world,x,y+5,z,direction,3,0,4,0,0,3,0,0,0,0).setBlock(light).queue(); //HOUSE ROOF 4
		Multiple.setup(builder,world,x,y+6,z,direction,3,0,0,3,0,0,6,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 1
		Multiple.setup(builder,world,x,y+6,z,direction,0,0,0,3,0,0,6,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 2
		Multiple.setup(builder,world,x,y+6,z,direction,2,0,0,3,0,1,0,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 3
		Multiple.setup(builder,world,x,y+6,z,direction,2,0,3,0,0,1,0,0,0,0).setBlock(slabD).queue(); //HOUSE ROOF 4
		Multiple.setup(builder,world,x,y+6,z,direction,2,0,0,2,0,1,4,0,0,0).setBlock(light).queue(); //HOUSE ROOF TOP

		//inside
		Single.setup(builder,world,x,y+1,z).offset(direction,4,0,0,4).setBlock(bed).setDirection(directionLeft).queue(2,false); //BED
		Single.setup(builder,world,x,y+1,z).offset(direction,4,0,3,0).setBlock(BlockType.chest(true)).setDirection(direction).queue(2,false); //CHEST
		Single.setup(builder,world,x,y+1,z).offset(direction,2,0,4,0).setBlock(BlockType.chest(true)).setDirection(directionChestLeft).queue(2,false); //CHEST
		Single.setup(builder,world,x,y+1,z).offset(direction,4,0,4,0).setBlock(craft).queue(2,false); //WORKBENCH
		Single.setup(builder,world,x,y+1,z).offset(direction,2,0,0,4).setBlock(furnace).setDirection(directionRight).queue(2,false); //FURNACE
		Single.setup(builder,world,x,y+1,z).offset(direction,1,0,0,4).setBlock(furnace).setDirection(directionRight).queue(2,false); //FURNACE
		Single.setup(builder,world,x,y+1,z).offset(direction,0,1,0,4).setBlock(stair).setDirection(directionStairLeft).queue(2,false); //CHAIR
		Single.setup(builder,world,x,y+1,z).offset(direction,0,1,4,0).setBlock(stair).setDirection(directionStairRight).queue(2,false); //CHAIR
		Single.setup(builder,world,x,y+1,z).offset(direction,0,0,0,4).setBlock(sign).setDirection(directionBack).queue(2,false); //CHAIR
		Single.setup(builder,world,x,y+1,z).offset(direction,0,0,4,0).setBlock(sign).setDirection(directionBack).queue(2,false); //CHAIR
		Single.setup(builder,world,x,y+2,z).offset(direction,0,1,0,2).setBlock(plate_dark).queue(2,false); //TABLE
		Single.setup(builder,world,x,y+2,z).offset(direction,0,1,2,0).setBlock(plate_dark).queue(2,false); //TABLE
		Single.setup(builder,world,x,y+1,z).offset(direction,0,1,0,2).setBlock(fence).queue(2,false); //TABLE
		Single.setup(builder,world,x,y+1,z).offset(direction,0,1,2,0).setBlock(fence).queue(2,false); //TABLE

		//above door torches
		Single.setup(builder,world,x,y+3,z).offset(direction,0,3,1,0).setBlock(torch_wall).setDirection(direction).queue(3);
		Single.setup(builder,world,x,y+3,z).offset(direction,0,3,0,1).setBlock(torch_wall).setDirection(direction).queue(3);

		//outdoor torches
		Single.setup(builder,world,x,y+4,z).offset(direction,0,7,4,0).setBlock(torch_wall).setDirection(direction).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,0,7,0,4).setBlock(torch_wall).setDirection(direction).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,0,6,5,0).setBlock(torch_wall).setDirection(directionRight).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,0,6,0,5).setBlock(torch_wall).setDirection(directionLeft).queue(3);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,6,0,1).setBlock(torch).queue(3);
		Single.setup(builder,world,x,y+2,z).offset(direction,0,6,1,0).setBlock(torch).queue(3);

		//indoor torches
		Single.setup(builder,world,x,y+4,z).offset(direction,0,1,2,0).setBlock(torch_wall).setDirection(directionBack).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,0,1,0,2).setBlock(torch_wall).setDirection(directionBack).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,4,0,2,0).setBlock(torch_wall).setDirection(direction).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,4,0,0,2).setBlock(torch_wall).setDirection(direction).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,1,0,4,0).setBlock(torch_wall).setDirection(directionLeft).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,2,0,4,0).setBlock(torch_wall).setDirection(directionLeft).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,1,0,0,4).setBlock(torch_wall).setDirection(directionRight).queue(3);
		Single.setup(builder,world,x,y+4,z).offset(direction,2,0,0,4).setBlock(torch_wall).setDirection(directionRight).queue(3);

		//pressure plates
		Single.setup(builder,world,x,y+1,z).offset(direction,0,1,0,0).setBlock(plate_light).queue(1);
		Single.setup(builder,world,x,y+1,z).offset(direction,0,3,0,0).setBlock(plate_dark).queue(1);
		Single.setup(builder,world,x,y+1,z).offset(direction,0,5,0,0).setBlock(plate_light).queue(1);

		builder.build();

		return true;
	}
}