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
		Block fence = Helper.readBlock( planks_two + "_fence",Blocks.SPRUCE_FENCE);
		Block gate = Helper.readBlock( planks_two + "_fence_gate",Blocks.SPRUCE_FENCE_GATE);
		Block stair = Helper.readBlock( planks_two + "_stairs",Blocks.SPRUCE_STAIRS);
		Block sign = Helper.readBlock( planks_two + "_wall_sign",Blocks.SPRUCE_WALL_SIGN);
		Block plate = Helper.readBlock( planks_two + "_pressure_plate",Blocks.SPRUCE_PRESSURE_PLATE);
		Block log = Helper.readBlock( Config.Common.HOUSE_LOG.get() + "_log",Blocks.BIRCH_LOG);
		Block door = Helper.readBlock( Config.Common.HOUSE_DOOR.get() + "_door",Blocks.DARK_OAK_DOOR);

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
		
		/************************ meta == 0 ************************/
		if(direction == Direction.SOUTH) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y,z-5,11,6,8).setBlock(air).build(); //HOUSE ROOM
			Builder.Multiple.setup(world,x-4,y+5,z-4,9,1,6).setBlock(air).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-3,y+6,z-3,7,1,4).setBlock(air).build(); //HOUSE ROOF 2

			Builder.Multiple.setup(world,x-4,y,z+3,9,5,4).setBlock(air).build(); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y,z-5,11,1,8).setBlock(dark).build(); //HOME FLOOR 1
			Builder.Multiple.setup(world,x-4,y,z-4,9,1,6).setBlock(light).build(); //HOME FLOOR 2
			Builder.Multiple.setup(world,x-3,y,z-3,7,1,4).setBlock(dark).build(); //HOME FLOOR 3
			Builder.Multiple.setup(world,x-2,y,z-2,5,1,2).setBlock(light).build(); //HOME FLOOR 4

			Builder.Multiple.setup(world,x-4,y,z+3,9,1,4).setBlock(dark).build(); //PORCH FLOOR 1
			Builder.Multiple.setup(world,x-3,y,z+4,7,1,2).setBlock(light).build(); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y+1,z-5,1,4,1).setBlock(log).build(); //LOG CORNER 1
			Builder.Multiple.setup(world,x+5,y+1,z-5,1,4,1).setBlock(log).build(); //LOG CORNER 2
			Builder.Multiple.setup(world,x-5,y+1,z+2,1,4,1).setBlock(log).build(); //LOG CORNER 3
			Builder.Multiple.setup(world,x+5,y+1,z+2,1,4,1).setBlock(log).build(); //LOG CORNER 4
			Builder.Multiple.setup(world,x-4,y+1,z-5,9,4,1).setBlock(light).build(); //WOOD WALL 1
			Builder.Multiple.setup(world,x-4,y+1,z+2,9,4,1).setBlock(light).build(); //WOOD WALL 2
			Builder.Multiple.setup(world,x-5,y+1,z-4,1,4,6).setBlock(light).build(); //WOOD WALL 3
			Builder.Multiple.setup(world,x+5,y+1,z-4,1,4,6).setBlock(light).build(); //WOOD WALL 4
			
			Builder.Multiple.setup(world,x-3,y+2,z-5,7,2,1).setBlock(pane).build(); //PANE WALL 1
			Builder.Multiple.setup(world,x-3,y+2,z+2,1,2,1).setBlock(pane).build(); //PANE WALL 2 (1)
			Builder.Multiple.setup(world,x+3,y+2,z+2,1,2,1).setBlock(pane).build(); //PANE WALL 2 (2)
			Builder.Multiple.setup(world,x-5,y+2,z-3,1,2,4).setBlock(pane).build(); //PANE WALL 3
			Builder.Multiple.setup(world,x+5,y+2,z-3,1,2,4).setBlock(pane).build(); //PANE WALL 4

			Builder.Multiple.setup(world,x-1,y+1,z+2,3,3,1).setBlock(log).build(); //LOG ENTRANCE

			Builder.Single.setup(world,x,y+1,z+2).setBlock(door).setDirection(directionDoor).build();

			/************************ Layer 1 to 4 : PORCH ************************/ //DONE
			Builder.Multiple.setup(world,x-4,y+4,z+3,9,1,4).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 1
			Builder.Multiple.setup(world,x-3,y+4,z+4,7,1,2).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 2

			Builder.Multiple.setup(world,x-4,y+1,z+3,1,4,1).setBlock(dark).build(); //PORCH CORNER 1
			Builder.Multiple.setup(world,x+4,y+1,z+3,1,4,1).setBlock(dark).build(); //PORCH CORNER 2
			Builder.Multiple.setup(world,x-4,y+1,z+6,1,4,1).setBlock(dark).build(); //PORCH CORNER 3
			Builder.Multiple.setup(world,x+4,y+1,z+6,1,4,1).setBlock(dark).build(); //PORCH CORNER 4
			
			Builder.Multiple.setup(world,x-4,y+2,z+3,1,2,1).setBlock(fence).build(); //PORCH CORNER 1 (FENCE)
			Builder.Multiple.setup(world,x+4,y+2,z+3,1,2,1).setBlock(fence).build(); //PORCH CORNER 2 (FENCE)
			Builder.Multiple.setup(world,x-4,y+2,z+6,1,2,1).setBlock(fence).build(); //PORCH CORNER 3 (FENCE)
			Builder.Multiple.setup(world,x+4,y+2,z+6,1,2,1).setBlock(fence).build(); //PORCH CORNER 4 (FENCE)
			
			Builder.Multiple.setup(world,x-4,y+1,z+4,1,1,2).setBlock(fence).build(); //PORCH SIDE 1
			Builder.Multiple.setup(world,x+4,y+1,z+4,1,1,2).setBlock(fence).build(); //PORCH SIDE 2
			Builder.Multiple.setup(world,x-3,y+1,z+6,7,1,1).setBlock(fence).build(); //PORCH SIDE 3 (FRONT)
			Builder.Single.setup(world,x,y+1,z+6).setBlock(gate).setDirection(direction).build(); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/
			Builder.Multiple.setup(world,x-5,y+5,z-5,11,1,1).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-5,y+5,z+2,11,1,1).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-5,y+5,z-4,1,1,6).setBlock(slabD).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x+5,y+5,z-4,1,1,6).setBlock(slabD).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-4,y+5,z-4,9,1,1).setBlock(light).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-4,y+5,z+1,9,1,1).setBlock(light).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-4,y+5,z-3,1,1,4).setBlock(light).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x+4,y+5,z-3,1,1,4).setBlock(light).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-3,y+6,z-3,7,1,1).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-3,y+6,z,7,1,1).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-3,y+6,z-2,1,1,2).setBlock(slabD).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x+3,y+6,z-2,1,1,2).setBlock(slabD).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-2,y+6,z-2,5,1,2).setBlock(light).build(); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			Builder.Single.setup(world,x + 4,y + 1,z - 4).setBlock(bed).setDirection(directionLeft).build(); //BED //NOT 2 //GOOD
			
			Builder.Single.setup(world,x-2,y + 1,z-4).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x-3,y + 1,z-4).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x-4,y + 1,z-3).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			Builder.Single.setup(world,x-4,y + 1,z-2).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			
			Builder.Single.setup(world,x - 4,y + 1,z - 4).setBlock(craft).build(); //WORKBENCH
			Builder.Single.setup(world,x + 4,y + 1,z - 2).setBlock(furnace).setDirection(directionRight).build(); //FURNACE
			Builder.Single.setup(world,x + 4,y + 1,z - 1).setBlock(furnace).setDirection(directionRight).build(); //FURNACE
			
			Builder.Single.setup(world,x-4,y + 1,z+1).setBlock(stair).setDirection(directionStairRight).build(); //CHAIR
			Builder.Single.setup(world,x + 4,y + 1,z + 1).setBlock(stair).setDirection(directionStairLeft).build(); //CHAIR
			Builder.Single.setup(world,x-4,y + 1,z).setBlock(sign).setDirection(directionBack).build(); //CHAIR
			Builder.Single.setup(world,x+4,y + 1,z).setBlock(sign).setDirection(directionBack).build(); //CHAIR
			
			Builder.Single.setup(world,x-2,y+2,z+1).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x+2,y+2,z+1).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x-2,y+1,z+1).setBlock(fence).build(); //TABLE
			Builder.Single.setup(world,x+2,y+1,z+1).setBlock(fence).build(); //TABLE
			
			Builder.Single.setup(world,x-1,y + 2,z+6).setBlock(torch).build();
			Builder.Single.setup(world,x + 1,y + 2,z + 6).setBlock(torch).build();
		}
		
		/************************ meta == 1 ************************/
		else if(direction == Direction.WEST) {
			/************************ Layer 0 to 7 : AIR ************************/
			Builder.Multiple.setup(world,x-2,y,z-5,8,6,11).setBlock(air).build(); //HOUSE ROOM
			Builder.Multiple.setup(world,x-1,y+5,z-4,6,1,9).setBlock(air).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x,y+6,z-3,4,1,7).setBlock(air).build(); //HOUSE ROOF 2

			Builder.Multiple.setup(world,x-6,y,z-4,4,5,9).setBlock(air).build(); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //GOOD
			Builder.Multiple.setup(world,x-2,y,z-5,8,1,11).setBlock(dark).build(); //HOME FLOOR 1
			Builder.Multiple.setup(world,x-1,y,z-4,6,1,9).setBlock(light).build(); //HOME FLOOR 2
			Builder.Multiple.setup(world,x,y,z-3,4,1,7).setBlock(dark).build(); //HOME FLOOR 3
			Builder.Multiple.setup(world,x+1,y,z-2,2,1,5).setBlock(light).build(); //HOME FLOOR 4

			Builder.Multiple.setup(world,x-6,y,z-4,4,1,9).setBlock(dark).build(); //PORCH FLOOR 1
			Builder.Multiple.setup(world,x-5,y,z-3,2,1,7).setBlock(light).build(); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/
			Builder.Multiple.setup(world,x+5,y+1,z-5,1,4,1).setBlock(log).build(); //LOG CORNER 1
			Builder.Multiple.setup(world,x+5,y+1,z+5,1,4,1).setBlock(log).build(); //LOG CORNER 2
			Builder.Multiple.setup(world,x-2,y+1,z-5,1,4,1).setBlock(log).build(); //LOG CORNER 3
			Builder.Multiple.setup(world,x-2,y+1,z+5,1,4,1).setBlock(log).build(); //LOG CORNER 4
			Builder.Multiple.setup(world,x+5,y+1,z-4,1,4,9).setBlock(light).build(); //WOOD WALL 1
			Builder.Multiple.setup(world,x-2,y+1,z-4,1,4,9).setBlock(light).build(); //WOOD WALL 2
			Builder.Multiple.setup(world,x-1,y+1,z-5,6,4,1).setBlock(light).build(); //WOOD WALL 3 //GOOD
			Builder.Multiple.setup(world,x-1,y+1,z+5,6,4,1).setBlock(light).build(); //WOOD WALL 4 //GOOD
			
			Builder.Multiple.setup(world,x+5,y+2,z-3,1,2,7).setBlock(pane).build(); //PANE WALL 1
			Builder.Multiple.setup(world,x-2,y+2,z-3,1,2,1).setBlock(pane).build(); //PANE WALL 2 (1)
			Builder.Multiple.setup(world,x-2,y+2,z+3,1,2,1).setBlock(pane).build(); //PANE WALL 2 (2)
			Builder.Multiple.setup(world,x,y+2,z-5,4,2,1).setBlock(pane).build(); //PANE WALL 3 //GOOD
			Builder.Multiple.setup(world,x,y+2,z+5,4,2,1).setBlock(pane).build(); //PANE WALL 4 //GOOD

			Builder.Multiple.setup(world,x-2,y+1,z-1,1,3,3).setBlock(log).build(); //LOG ENTRANCE

			Builder.Single.setup(world,x-2,y+1,z).setBlock(door).setDirection(directionDoor).build();

			/************************ Layer 1 to 4 : PORCH ************************/
			Builder.Multiple.setup(world,x-6,y+4,z-4,4,1,9).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 1
			Builder.Multiple.setup(world,x-5,y+4,z-3,2,1,7).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 2

			Builder.Multiple.setup(world,x-3,y+1,z-4,1,4,1).setBlock(dark).build(); //PORCH CORNER 1
			Builder.Multiple.setup(world,x-3,y+1,z+4,1,4,1).setBlock(dark).build(); //PORCH CORNER 2
			Builder.Multiple.setup(world,x-6,y+1,z-4,1,4,1).setBlock(dark).build(); //PORCH CORNER 3
			Builder.Multiple.setup(world,x-6,y+1,z+4,1,4,1).setBlock(dark).build(); //PORCH CORNER 4
			
			Builder.Multiple.setup(world,x-3,y+2,z-4,1,2,1).setBlock(fence).build(); //PORCH CORNER 1 (FENCE)
			Builder.Multiple.setup(world,x-3,y+2,z+4,1,2,1).setBlock(fence).build(); //PORCH CORNER 2 (FENCE)
			Builder.Multiple.setup(world,x-6,y+2,z-4,1,2,1).setBlock(fence).build(); //PORCH CORNER 3 (FENCE)
			Builder.Multiple.setup(world,x-6,y+2,z+4,1,2,1).setBlock(fence).build(); //PORCH CORNER 4 (FENCE)
			
			Builder.Multiple.setup(world,x-5,y+1,z-4,2,1,1).setBlock(fence).build(); //PORCH SIDE 1
			Builder.Multiple.setup(world,x-5,y+1,z+4,2,1,1).setBlock(fence).build(); //PORCH SIDE 2
			Builder.Multiple.setup(world,x-6,y+1,z-3,1,1,7).setBlock(fence).build(); //PORCH SIDE 3 (FRONT)
			Builder.Single.setup(world,x-6,y+1,z).setBlock(gate).setDirection(direction).build(); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/ //GOOD
			Builder.Multiple.setup(world,x+5,y+5,z-5,1,1,11).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-2,y+5,z-5,1,1,11).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-1,y+5,z-5,6,1,1).setBlock(slabD).build(); //HOUSE ROOF 3 //GOOD
			Builder.Multiple.setup(world,x-1,y+5,z+5,6,1,1).setBlock(slabD).build(); //HOUSE ROOF 4 //GOOD

			Builder.Multiple.setup(world,x+4,y+5,z-4,1,1,9).setBlock(light).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-1,y+5,z-4,1,1,9).setBlock(light).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x,y+5,z-4,4,1,1).setBlock(light).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x,y+5,z+4,4,1,1).setBlock(light).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x+3,y+6,z-3,1,1,7).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x,y+6,z-3,1,1,7).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x+1,y+6,z-3,2,1,1).setBlock(slabD).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x+1,y+6,z+3,2,1,1).setBlock(slabD).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x+1,y+6,z-2,2,1,5).setBlock(light).build(); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			Builder.Single.setup(world,x + 4,y + 1,z + 4).setBlock(bed).setDirection(directionLeft).build(); //BED //NOT 2, 1, 3 //GOOD
			
			Builder.Single.setup(world,x + 4,y + 1,z - 2).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x + 4,y + 1,z - 3).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x + 3,y + 1,z - 4).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			Builder.Single.setup(world,x + 2,y + 1,z - 4).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			
			Builder.Single.setup(world,x + 4,y + 1,z - 4).setBlock(craft).build(); //WORKBENCH
			Builder.Single.setup(world,x + 2,y + 1,z + 4).setBlock(furnace).setDirection(directionRight).build(); //FURNACE //NOT 3
			Builder.Single.setup(world,x + 1,y + 1,z + 4).setBlock(furnace).setDirection(directionRight).build(); //FURNACE //NOT 3

			Builder.Single.setup(world,x - 1,y + 1,z - 4).setBlock(stair).setDirection(directionStairRight).build(); //CHAIR
			Builder.Single.setup(world,x - 1,y + 1,z + 4).setBlock(stair).setDirection(directionStairLeft).build(); //CHAIR
			Builder.Single.setup(world,x,y + 1,z - 4).setBlock(sign).setDirection(directionBack).build(); //CHAIR //NOT 4, 3
			Builder.Single.setup(world,x,y + 1,z + 4).setBlock(sign).setDirection(directionBack).build(); //CHAIR //NOT 4, 3
			
			Builder.Single.setup(world,x-1,y+2,z-2).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x-1,y+2,z+2).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x-1,y+1,z-2).setBlock(fence).build(); //TABLE
			Builder.Single.setup(world,x-1,y+1,z+2).setBlock(fence).build(); //TABLE
			
			Builder.Single.setup(world,x - 6,y + 2,z - 1).setBlock(torch).build();
			Builder.Single.setup(world,x - 6,y + 2,z + 1).setBlock(torch).build();
		}
		
		/************************ meta == 2 ************************/
		else if(direction == Direction.NORTH) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y,z-2,11,6,8).setBlock(air).build(); //HOUSE ROOM
			Builder.Multiple.setup(world,x-4,y+5,z-1,9,1,6).setBlock(air).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-3,y+6,z,7,1,4).setBlock(air).build(); //HOUSE ROOF 2

			Builder.Multiple.setup(world,x-4,y,z-6,9,5,4).setBlock(air).build(); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y,z-2,11,1,8).setBlock(dark).build(); //HOME FLOOR 1
			Builder.Multiple.setup(world,x-4,y,z-1,9,1,6).setBlock(light).build(); //HOME FLOOR 2
			Builder.Multiple.setup(world,x-3,y,z,7,1,4).setBlock(dark).build(); //HOME FLOOR 3
			Builder.Multiple.setup(world,x-2,y,z+1,5,1,2).setBlock(light).build(); //HOME FLOOR 4

			Builder.Multiple.setup(world,x-4,y,z-6,9,1,4).setBlock(dark).build(); //PORCH FLOOR 1
			Builder.Multiple.setup(world,x-3,y,z-5,7,1,2).setBlock(light).build(); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y+1,z+5,1,4,1).setBlock(log).build(); //LOG CORNER 1
			Builder.Multiple.setup(world,x+5,y+1,z+5,1,4,1).setBlock(log).build(); //LOG CORNER 2
			Builder.Multiple.setup(world,x-5,y+1,z-2,1,4,1).setBlock(log).build(); //LOG CORNER 3
			Builder.Multiple.setup(world,x+5,y+1,z-2,1,4,1).setBlock(log).build(); //LOG CORNER 4
			Builder.Multiple.setup(world,x-4,y+1,z+5,9,4,1).setBlock(light).build(); //WOOD WALL 1
			Builder.Multiple.setup(world,x-4,y+1,z-2,9,4,1).setBlock(light).build(); //WOOD WALL 2
			Builder.Multiple.setup(world,x-5,y+1,z-1,1,4,6).setBlock(light).build(); //WOOD WALL 3 //GOOD
			Builder.Multiple.setup(world,x+5,y+1,z-1,1,4,6).setBlock(light).build(); //WOOD WALL 4 //GOOD
			
			Builder.Multiple.setup(world,x-3,y+2,z+5,7,2,1).setBlock(pane).build(); //PANE WALL 1
			Builder.Multiple.setup(world,x-3,y+2,z-2,1,2,1).setBlock(pane).build(); //PANE WALL 2 (1)
			Builder.Multiple.setup(world,x+3,y+2,z-2,1,2,1).setBlock(pane).build(); //PANE WALL 2 (2)
			Builder.Multiple.setup(world,x-5,y+2,z,1,2,4).setBlock(pane).build(); //PANE WALL 3 //GOOD
			Builder.Multiple.setup(world,x+5,y+2,z,1,2,4).setBlock(pane).build(); //PANE WALL 4 //GOOD

			Builder.Multiple.setup(world,x-1,y+1,z-2,3,3,1).setBlock(log).build(); //LOG ENTRANCE

			Builder.Single.setup(world,x,y+1,z-2).setBlock(door).setDirection(directionDoor).build();

			/************************ Layer 1 to 4 : PORCH ************************/ //DONE
			Builder.Multiple.setup(world,x-4,y+4,z-6,9,1,4).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 1
			Builder.Multiple.setup(world,x-3,y+4,z-5,7,1,2).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 2

			Builder.Multiple.setup(world,x-4,y+1,z-3,1,4,1).setBlock(dark).build(); //PORCH CORNER 1
			Builder.Multiple.setup(world,x+4,y+1,z-3,1,4,1).setBlock(dark).build(); //PORCH CORNER 2
			Builder.Multiple.setup(world,x-4,y+1,z-6,1,4,1).setBlock(dark).build(); //PORCH CORNER 3
			Builder.Multiple.setup(world,x+4,y+1,z-6,1,4,1).setBlock(dark).build(); //PORCH CORNER 4
			
			Builder.Multiple.setup(world,x-4,y+2,z-3,1,2,1).setBlock(fence).build(); //PORCH CORNER 1 (FENCE)
			Builder.Multiple.setup(world,x+4,y+2,z-3,1,2,1).setBlock(fence).build(); //PORCH CORNER 2 (FENCE)
			Builder.Multiple.setup(world,x-4,y+2,z-6,1,2,1).setBlock(fence).build(); //PORCH CORNER 3 (FENCE)
			Builder.Multiple.setup(world,x+4,y+2,z-6,1,2,1).setBlock(fence).build(); //PORCH CORNER 4 (FENCE)
			
			Builder.Multiple.setup(world,x-4,y+1,z-5,1,1,2).setBlock(fence).build(); //PORCH SIDE 1
			Builder.Multiple.setup(world,x+4,y+1,z-5,1,1,2).setBlock(fence).build(); //PORCH SIDE 2
			Builder.Multiple.setup(world,x-3,y+1,z-6,7,1,1).setBlock(fence).build(); //PORCH SIDE 3 (FRONT)
			Builder.Single.setup(world,x,y+1,z-6).setBlock(gate).setDirection(direction).build(); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/ //DONE
			Builder.Multiple.setup(world,x-5,y+5,z+5,11,1,1).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-5,y+5,z-2,11,1,1).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-5,y+5,z-1,1,1,6).setBlock(slabD).build(); //HOUSE ROOF 3 //GOOD
			Builder.Multiple.setup(world,x+5,y+5,z-1,1,1,6).setBlock(slabD).build(); //HOUSE ROOF 4 //GOOD

			Builder.Multiple.setup(world,x-4,y+5,z+4,9,1,1).setBlock(light).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-4,y+5,z-1,9,1,1).setBlock(light).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-4,y+5,z,1,1,4).setBlock(light).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x+4,y+5,z,1,1,4).setBlock(light).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-3,y+6,z+3,7,1,1).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-3,y+6,z,7,1,1).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-3,y+6,z+1,1,1,2).setBlock(slabD).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x+3,y+6,z+1,1,1,2).setBlock(slabD).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-2,y+6,z+1,5,1,2).setBlock(light).build(); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			Builder.Single.setup(world,x - 4,y + 1,z + 4).setBlock(bed).setDirection(directionLeft).build(); //BED //NOT 2, 1, 3, 0
			
			Builder.Single.setup(world,x+2,y + 1,z+4).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x+3,y + 1,z+4).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x+4,y + 1,z+3).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			Builder.Single.setup(world,x+4,y + 1,z+2).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			
			Builder.Single.setup(world,x + 4,y + 1,z + 4).setBlock(craft).build(); //WORKBENCH
			Builder.Single.setup(world,x - 4,y + 1,z + 2).setBlock(furnace).setDirection(directionRight).build(); //FURNACE //NOT 3
			Builder.Single.setup(world,x - 4,y + 1,z + 1).setBlock(furnace).setDirection(directionRight).build(); //FURNACE //NOT 3

			Builder.Single.setup(world,x - 4,y + 1,z - 1).setBlock(stair).setDirection(directionStairLeft).build(); //CHAIR //NOT 3, 2, 1
			Builder.Single.setup(world,x+4,y + 1,z-1).setBlock(stair).setDirection(directionStairRight).build(); //CHAIR //NOT 10 //GOOD
			Builder.Single.setup(world,x-4,y + 1,z).setBlock(sign).setDirection(directionBack).build(); //CHAIR //NOT 4, 5
			Builder.Single.setup(world,x+4,y + 1,z).setBlock(sign).setDirection(directionBack).build(); //CHAIR //NOT 4, 5
			
			Builder.Single.setup(world,x-2,y+2,z-1).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x+2,y+2,z-1).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x-2,y+1,z-1).setBlock(fence).build(); //TABLE
			Builder.Single.setup(world,x+2,y+1,z-1).setBlock(fence).build(); //TABLE
			
			Builder.Single.setup(world,x - 1,y + 2,z - 6).setBlock(torch).build();
			Builder.Single.setup(world,x+1,y + 2,z-6).setBlock(torch).build();
		}
		
		/************************ meta == 3 ************************/
		else if(direction == Direction.EAST) {
			/************************ Layer 0 to 7 : AIR ************************/
			Builder.Multiple.setup(world,x-5,y,z-5,8,6,11).setBlock(air).build(); //HOUSE ROOM
			Builder.Multiple.setup(world,x-4,y+5,z-4,6,1,9).setBlock(air).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x-3,y+6,z-3,4,1,7).setBlock(air).build(); //HOUSE ROOF 2

			Builder.Multiple.setup(world,x+3,y,z-4,4,5,9).setBlock(air).build(); //PORCH

			/************************ Layer 0 : FLOOR ************************/
			Builder.Multiple.setup(world,x-5,y,z-5,8,1,11).setBlock(dark).build(); //HOME FLOOR 1
			Builder.Multiple.setup(world,x-4,y,z-4,6,1,9).setBlock(light).build(); //HOME FLOOR 2
			Builder.Multiple.setup(world,x-3,y,z-3,4,1,7).setBlock(dark).build(); //HOME FLOOR 3
			Builder.Multiple.setup(world,x-2,y,z-2,2,1,5).setBlock(light).build(); //HOME FLOOR 4

			Builder.Multiple.setup(world,x+3,y,z-4,4,1,9).setBlock(dark).build(); //PORCH FLOOR 1
			Builder.Multiple.setup(world,x+4,y,z-3,2,1,7).setBlock(light).build(); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/
			Builder.Multiple.setup(world,x-5,y+1,z-5,1,4,1).setBlock(log).build(); //LOG CORNER 1
			Builder.Multiple.setup(world,x-5,y+1,z+5,1,4,1).setBlock(log).build(); //LOG CORNER 2
			Builder.Multiple.setup(world,x+2,y+1,z-5,1,4,1).setBlock(log).build(); //LOG CORNER 3
			Builder.Multiple.setup(world,x+2,y+1,z+5,1,4,1).setBlock(log).build(); //LOG CORNER 4
			Builder.Multiple.setup(world,x-5,y+1,z-4,1,4,9).setBlock(light).build(); //WOOD WALL 1
			Builder.Multiple.setup(world,x+2,y+1,z-4,1,4,9).setBlock(light).build(); //WOOD WALL 2
			Builder.Multiple.setup(world,x-4,y+1,z-5,6,4,1).setBlock(light).build(); //WOOD WALL 3
			Builder.Multiple.setup(world,x-4,y+1,z+5,6,4,1).setBlock(light).build(); //WOOD WALL 4
			
			Builder.Multiple.setup(world,x-5,y+2,z-3,1,2,7).setBlock(pane).build(); //PANE WALL 1
			Builder.Multiple.setup(world,x+2,y+2,z-3,1,2,1).setBlock(pane).build(); //PANE WALL 2 (1)
			Builder.Multiple.setup(world,x+2,y+2,z+3,1,2,1).setBlock(pane).build(); //PANE WALL 2 (2)
			Builder.Multiple.setup(world,x-3,y+2,z-5,4,2,1).setBlock(pane).build(); //PANE WALL 3
			Builder.Multiple.setup(world,x-3,y+2,z+5,4,2,1).setBlock(pane).build(); //PANE WALL 4

			Builder.Multiple.setup(world,x+2,y+1,z-1,1,3,3).setBlock(log).build(); //LOG ENTRANCE

			Builder.Single.setup(world,x+2,y+1,z).setBlock(door).setDirection(directionDoor).build();

			/************************ Layer 1 to 4 : PORCH ************************/
			Builder.Multiple.setup(world,x+3,y+4,z-4,4,1,9).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 1
			Builder.Multiple.setup(world,x+4,y+4,z-3,2,1,7).setBlock(slabD).setDirection(Direction.UP).build(); //PORCH ROOF 2

			Builder.Multiple.setup(world,x+3,y+1,z-4,1,4,1).setBlock(dark).build(); //PORCH CORNER 1
			Builder.Multiple.setup(world,x+3,y+1,z+4,1,4,1).setBlock(dark).build(); //PORCH CORNER 2
			Builder.Multiple.setup(world,x+6,y+1,z-4,1,4,1).setBlock(dark).build(); //PORCH CORNER 3
			Builder.Multiple.setup(world,x+6,y+1,z+4,1,4,1).setBlock(dark).build(); //PORCH CORNER 4
			
			Builder.Multiple.setup(world,x+3,y+2,z-4,1,2,1).setBlock(fence).build(); //PORCH CORNER 1 (FENCE)
			Builder.Multiple.setup(world,x+3,y+2,z+4,1,2,1).setBlock(fence).build(); //PORCH CORNER 2 (FENCE)
			Builder.Multiple.setup(world,x+6,y+2,z-4,1,2,1).setBlock(fence).build(); //PORCH CORNER 3 (FENCE)
			Builder.Multiple.setup(world,x+6,y+2,z+4,1,2,1).setBlock(fence).build(); //PORCH CORNER 4 (FENCE)
			
			Builder.Multiple.setup(world,x+4,y+1,z-4,2,1,1).setBlock(fence).build(); //PORCH SIDE 1
			Builder.Multiple.setup(world,x+4,y+1,z+4,2,1,1).setBlock(fence).build(); //PORCH SIDE 2
			Builder.Multiple.setup(world,x+6,y+1,z-3,1,1,7).setBlock(fence).build(); //PORCH SIDE 3 (FRONT)
			Builder.Single.setup(world,x+6,y+1,z).setBlock(gate).setDirection(direction).build(); //PORCH GATE
			
			/************************ Layer 5 tp 6 : ROOF ************************/
			Builder.Multiple.setup(world,x-5,y+5,z-5,1,1,11).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x+2,y+5,z-5,1,1,11).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-4,y+5,z-5,6,1,1).setBlock(slabD).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x-4,y+5,z+5,6,1,1).setBlock(slabD).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-4,y+5,z-4,1,1,9).setBlock(light).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x+1,y+5,z-4,1,1,9).setBlock(light).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-3,y+5,z-4,4,1,1).setBlock(light).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x-3,y+5,z+4,4,1,1).setBlock(light).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-3,y+6,z-3,1,1,7).setBlock(slabD).build(); //HOUSE ROOF 1
			Builder.Multiple.setup(world,x,y+6,z-3,1,1,7).setBlock(slabD).build(); //HOUSE ROOF 2
			Builder.Multiple.setup(world,x-2,y+6,z-3,2,1,1).setBlock(slabD).build(); //HOUSE ROOF 3
			Builder.Multiple.setup(world,x-2,y+6,z+3,2,1,1).setBlock(slabD).build(); //HOUSE ROOF 4

			Builder.Multiple.setup(world,x-2,y+6,z-2,2,1,5).setBlock(light).build(); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			Builder.Single.setup(world,x - 4,y + 1,z - 4).setBlock(bed).setDirection(directionLeft).build(); //BED
			
			Builder.Single.setup(world,x - 4,y + 1,z + 2).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x - 4,y + 1,z + 3).setBlock(chest).setDirection(direction).build(); //CHEST
			Builder.Single.setup(world,x - 3,y + 1,z + 4).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			Builder.Single.setup(world,x - 2,y + 1,z + 4).setBlock(chest).setDirection(directionChestLeft).build(); //CHEST
			
			Builder.Single.setup(world,x - 4,y + 1,z + 4).setBlock(craft).build(); //WORKBENCH
			Builder.Single.setup(world,x - 2,y + 1,z - 4).setBlock(furnace).setDirection(directionRight).build(); //FURNACE
			Builder.Single.setup(world,x - 1,y + 1,z - 4).setBlock(furnace).setDirection(directionRight).build(); //FURNACE

			Builder.Single.setup(world,x + 1,y + 1,z - 4).setBlock(stair).setDirection(directionStairLeft).build(); //CHAIR
			Builder.Single.setup(world,x + 1,y + 1,z + 4).setBlock(stair).setDirection(directionStairRight).build(); //CHAIR
			Builder.Single.setup(world,x,y + 1,z - 4).setBlock(sign).setDirection(directionBack).build(); //CHAIR
			Builder.Single.setup(world,x,y + 1,z + 4).setBlock(sign).setDirection(directionBack).build(); //CHAIR
			
			Builder.Single.setup(world,x+1,y+2,z-2).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x+1,y+2,z+2).setBlock(plate).build(); //TABLE
			Builder.Single.setup(world,x+1,y+1,z-2).setBlock(fence).build(); //TABLE
			Builder.Single.setup(world,x+1,y+1,z+2).setBlock(fence).build(); //TABLE
			
			Builder.Single.setup(world,x + 6,y + 2,z - 1).setBlock(torch).build();
			Builder.Single.setup(world,x + 6,y + 2,z + 1).setBlock(torch).build();
		}

		//above door torches
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,3,1,0).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+3,z).offset(direction,0,3,0,1).setBlock(torch_wall).setDirection(direction).build();

		//outdoor torches
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,7,4,0).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,7,0,4).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,5,0).setBlock(torch_wall).setDirection(directionRight).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,6,0,5).setBlock(torch_wall).setDirection(directionLeft).build();

		//indoor torches
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,1,2,0).setBlock(torch_wall).setDirection(directionBack).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,0,1,0,2).setBlock(torch_wall).setDirection(directionBack).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,4,0,2,0).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,4,0,0,2).setBlock(torch_wall).setDirection(direction).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,1,0,4,0).setBlock(torch_wall).setDirection(directionLeft).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,2,0,4,0).setBlock(torch_wall).setDirection(directionLeft).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,1,0,0,4).setBlock(torch_wall).setDirection(directionRight).build();
		Builder.Single.setup(world,x,y+4,z).offset(direction,2,0,0,4).setBlock(torch_wall).setDirection(directionRight).build();

		return true;
	}
}