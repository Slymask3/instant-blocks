package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
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
		);
		setCreateMessage(Strings.CREATE_WOODEN_HOUSE);
		setDirectional(true);
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Block light = Blocks.BIRCH_PLANKS;
		Block dark = Blocks.SPRUCE_PLANKS;
		Block log = Blocks.BIRCH_LOG;
		Block craft = Blocks.CRAFTING_TABLE;
		Block chest = Blocks.CHEST;
		Block furnace = Blocks.FURNACE;
		Block fence = Blocks.SPRUCE_FENCE;
		Block gate = Blocks.SPRUCE_FENCE_GATE;
		Block stair = Blocks.SPRUCE_STAIRS;
		Block sign = Blocks.SPRUCE_WALL_SIGN;
		Block bed = Blocks.RED_BED;
		Block door = Blocks.DARK_OAK_DOOR;
		Block pane = Blocks.GLASS_PANE;
		Block torch = Blocks.TORCH;
		Block torch_wall = Blocks.WALL_TORCH;
		Block plate = Blocks.SPRUCE_PRESSURE_PLATE;
		Block slabL = Blocks.BIRCH_SLAB;
		Block slabD = Blocks.SPRUCE_SLAB;
		Block air = Blocks.AIR;

		Direction direction = world.getBlockState(new BlockPos(x,y,z)).getValue(FACING);

		Direction directionDoor = direction.getOpposite();
		Direction directionGate = direction;
		Direction directionChestFront = direction;
		Direction directionChestLeft = direction.getCounterClockWise();
		Direction directionStairLeft = direction.getCounterClockWise();
		Direction directionStairRight = direction.getClockWise();
		Direction directionFront = direction;
		Direction directionBack = direction.getOpposite();
		Direction directionLeft = direction.getCounterClockWise();
		Direction directionRight = direction.getClockWise();
		
		/************************ meta == 0 ************************/
		if(direction == Direction.SOUTH) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			BuildHelper.build(world, x-5, y, z-5, air, 8, 6, 11); //HOUSE ROOM
			BuildHelper.build(world, x-4, y+5, z-4, air, 6, 1, 9); //HOUSE ROOF 1
			BuildHelper.build(world, x-3, y+6, z-3, air, 4, 1, 7); //HOUSE ROOF 2

			BuildHelper.build(world, x-4, y, z+3, air, 4, 5, 9); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //DONE
			BuildHelper.build(world, x-5, y, z-5, dark, 8, 1, 11); //HOME FLOOR 1
			BuildHelper.build(world, x-4, y, z-4, light, 6, 1, 9); //HOME FLOOR 2
			BuildHelper.build(world, x-3, y, z-3, dark, 4, 1, 7); //HOME FLOOR 3
			BuildHelper.build(world, x-2, y, z-2, light, 2, 1, 5); //HOME FLOOR 4

			BuildHelper.build(world, x-4, y, z+3, dark, 4, 1, 9); //PORCH FLOOR 1
			BuildHelper.build(world, x-3, y, z+4, light, 2, 1, 7); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/ //DONE
			BuildHelper.build(world, x-5, y+1, z-5, log, 1, 4, 1); //LOG CORNER 1
			BuildHelper.build(world, x+5, y+1, z-5, log, 1, 4, 1); //LOG CORNER 2
			BuildHelper.build(world, x-5, y+1, z+2, log, 1, 4, 1); //LOG CORNER 3
			BuildHelper.build(world, x+5, y+1, z+2, log, 1, 4, 1); //LOG CORNER 4
			BuildHelper.build(world, x-4, y+1, z-5, light, 1, 4, 9); //WOOD WALL 1
			BuildHelper.build(world, x-4, y+1, z+2, light, 1, 4, 9); //WOOD WALL 2
			BuildHelper.build(world, x-5, y+1, z-4, light, 6, 4, 1); //WOOD WALL 3
			BuildHelper.build(world, x+5, y+1, z-4, light, 6, 4, 1); //WOOD WALL 4
			
			BuildHelper.build(world, x-3, y+2, z-5, pane, 1, 2, 7); //PANE WALL 1
			BuildHelper.build(world, x-3, y+2, z+2, pane, 1, 2, 1); //PANE WALL 2 (1)
			BuildHelper.build(world, x+3, y+2, z+2, pane, 1, 2, 1); //PANE WALL 2 (2)
			BuildHelper.build(world, x-5, y+2, z-3, pane, 4, 2, 1); //PANE WALL 3
			BuildHelper.build(world, x+5, y+2, z-3, pane, 4, 2, 1); //PANE WALL 4

			BuildHelper.build(world, x-1, y+1, z+2, log, 1, 3, 3); //LOG ENTRANCE

			BuildHelper.setBlock(world, x, y+1, z+2,door,directionDoor);

			/************************ Layer 1 to 4 : PORCH ************************/ //DONE
			BuildHelper.build(world, x-4, y+4, z+3, slabD, Direction.UP, 4, 1, 9); //PORCH ROOF 1
			BuildHelper.build(world, x-3, y+4, z+4, slabD, Direction.UP, 2, 1, 7); //PORCH ROOF 2

			BuildHelper.build(world, x-4, y+1, z+3, dark, 1, 4, 1); //PORCH CORNER 1
			BuildHelper.build(world, x+4, y+1, z+3, dark, 1, 4, 1); //PORCH CORNER 2
			BuildHelper.build(world, x-4, y+1, z+6, dark, 1, 4, 1); //PORCH CORNER 3
			BuildHelper.build(world, x+4, y+1, z+6, dark, 1, 4, 1); //PORCH CORNER 4
			
			BuildHelper.build(world, x-4, y+2, z+3, fence, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			BuildHelper.build(world, x+4, y+2, z+3, fence, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			BuildHelper.build(world, x-4, y+2, z+6, fence, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			BuildHelper.build(world, x+4, y+2, z+6, fence, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			BuildHelper.build(world, x-4, y+1, z+4, fence, 2, 1, 1); //PORCH SIDE 1
			BuildHelper.build(world, x+4, y+1, z+4, fence, 2, 1, 1); //PORCH SIDE 2
			BuildHelper.build(world, x-3, y+1, z+6, fence, 1, 1, 7); //PORCH SIDE 3 (FRONT)
			BuildHelper.setBlock(world,x, y+1, z+6, gate,directionGate); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/
			BuildHelper.build(world, x-5, y+5, z-5, slabD, 1, 1, 11); //HOUSE ROOF 1
			BuildHelper.build(world, x-5, y+5, z+2, slabD, 1, 1, 11); //HOUSE ROOF 2
			BuildHelper.build(world, x-5, y+5, z-4, slabD, 6, 1, 1); //HOUSE ROOF 3
			BuildHelper.build(world, x+5, y+5, z-4, slabD, 6, 1, 1); //HOUSE ROOF 4

			BuildHelper.build(world, x-4, y+5, z-4, light, 1, 1, 9); //HOUSE ROOF 1
			BuildHelper.build(world, x-4, y+5, z+1, light, 1, 1, 9); //HOUSE ROOF 2
			BuildHelper.build(world, x-4, y+5, z-3, light, 4, 1, 1); //HOUSE ROOF 3
			BuildHelper.build(world, x+4, y+5, z-3, light, 4, 1, 1); //HOUSE ROOF 4

			BuildHelper.build(world, x-3, y+6, z-3, slabD, 1, 1, 7); //HOUSE ROOF 1
			BuildHelper.build(world, x-3, y+6, z, slabD, 1, 1, 7); //HOUSE ROOF 2
			BuildHelper.build(world, x-3, y+6, z-2, slabD, 2, 1, 1); //HOUSE ROOF 3
			BuildHelper.build(world, x+3, y+6, z-2, slabD, 2, 1, 1); //HOUSE ROOF 4

			BuildHelper.build(world, x-2, y+6, z-2, light, 2, 1, 5); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			BuildHelper.setBlock(world,x + 4, y + 1, z - 4, bed, directionLeft); //BED //NOT 2 //GOOD
			
			BuildHelper.setBlock(world,x-2, y + 1, z-4, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x-3, y + 1, z-4, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x-4, y + 1, z-3, chest, directionChestLeft); //CHEST
			BuildHelper.setBlock(world,x-4, y + 1, z-2, chest, directionChestLeft); //CHEST
			
			BuildHelper.setBlock(world,x - 4, y + 1, z - 4, craft); //WORKBENCH
			BuildHelper.setBlock(world,x + 4, y + 1, z - 2, furnace, directionRight); //FURNACE
			BuildHelper.setBlock(world,x + 4, y + 1, z - 1, furnace, directionRight); //FURNACE
			
			BuildHelper.setBlock(world,x-4, y + 1, z+1, stair, directionStairLeft); //CHAIR
			BuildHelper.setBlock(world,x + 4, y + 1, z + 1, stair, directionStairRight); //CHAIR
			BuildHelper.setBlock(world,x-4, y + 1, z, sign, directionBack); //CHAIR
			BuildHelper.setBlock(world,x+4, y + 1, z, sign, directionBack); //CHAIR
			
			BuildHelper.setBlock(world,x-2,y+2,z+1,plate); //TABLE
			BuildHelper.setBlock(world,x+2,y+2,z+1,plate); //TABLE
			BuildHelper.setBlock(world,x-2,y+1,z+1,fence); //TABLE
			BuildHelper.setBlock(world,x+2,y+1,z+1,fence); //TABLE
			
			BuildHelper.setBlock(world,x-1, y + 2, z+6, torch);
			BuildHelper.setBlock(world,x + 1, y + 2, z + 6, torch);
		}
		
		/************************ meta == 1 ************************/
		else if(direction == Direction.WEST) {
			/************************ Layer 0 to 7 : AIR ************************/
			BuildHelper.build(world, x-2, y, z-5, air, 11, 6, 8); //HOUSE ROOM
			BuildHelper.build(world, x-1, y+5, z-4, air, 9, 1, 6); //HOUSE ROOF 1
			BuildHelper.build(world, x, y+6, z-3, air, 7, 1, 4); //HOUSE ROOF 2

			BuildHelper.build(world, x-6, y, z-4, air, 9, 5, 4); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //GOOD
			BuildHelper.build(world, x-2, y, z-5, dark, 11, 1, 8); //HOME FLOOR 1
			BuildHelper.build(world, x-1, y, z-4, light, 9, 1, 6); //HOME FLOOR 2
			BuildHelper.build(world, x, y, z-3, dark, 7, 1, 4); //HOME FLOOR 3
			BuildHelper.build(world, x+1, y, z-2, light, 5, 1, 2); //HOME FLOOR 4

			BuildHelper.build(world, x-6, y, z-4, dark, 9, 1, 4); //PORCH FLOOR 1
			BuildHelper.build(world, x-5, y, z-3, light, 7, 1, 2); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/
			BuildHelper.build(world, x+5, y+1, z-5, log, 1, 4, 1); //LOG CORNER 1
			BuildHelper.build(world, x+5, y+1, z+5, log, 1, 4, 1); //LOG CORNER 2
			BuildHelper.build(world, x-2, y+1, z-5, log, 1, 4, 1); //LOG CORNER 3
			BuildHelper.build(world, x-2, y+1, z+5, log, 1, 4, 1); //LOG CORNER 4
			BuildHelper.build(world, x+5, y+1, z-4, light, 9, 4, 1); //WOOD WALL 1
			BuildHelper.build(world, x-2, y+1, z-4, light, 9, 4, 1); //WOOD WALL 2
			BuildHelper.build(world, x-1, y+1, z-5, light, 1, 4, 6); //WOOD WALL 3 //GOOD
			BuildHelper.build(world, x-1, y+1, z+5, light, 1, 4, 6); //WOOD WALL 4 //GOOD
			
			BuildHelper.build(world, x+5, y+2, z-3, pane, 7, 2, 1); //PANE WALL 1
			BuildHelper.build(world, x-2, y+2, z-3, pane, 1, 2, 1); //PANE WALL 2 (1)
			BuildHelper.build(world, x-2, y+2, z+3, pane, 1, 2, 1); //PANE WALL 2 (2)
			BuildHelper.build(world, x, y+2, z-5, pane, 1, 2, 4); //PANE WALL 3 //GOOD
			BuildHelper.build(world, x, y+2, z+5, pane, 1, 2, 4); //PANE WALL 4 //GOOD

			BuildHelper.build(world, x-2, y+1, z-1, log, 3, 3, 1); //LOG ENTRANCE

			BuildHelper.setBlock(world, x-2, y+1, z,door,directionDoor);

			/************************ Layer 1 to 4 : PORCH ************************/
			BuildHelper.build(world, x-6, y+4, z-4, slabD, Direction.UP, 9, 1, 4); //PORCH ROOF 1
			BuildHelper.build(world, x-5, y+4, z-3, slabD, Direction.UP, 7, 1, 2); //PORCH ROOF 2

			BuildHelper.build(world, x-3, y+1, z-4, dark, 1, 4, 1); //PORCH CORNER 1
			BuildHelper.build(world, x-3, y+1, z+4, dark, 1, 4, 1); //PORCH CORNER 2
			BuildHelper.build(world, x-6, y+1, z-4, dark, 1, 4, 1); //PORCH CORNER 3
			BuildHelper.build(world, x-6, y+1, z+4, dark, 1, 4, 1); //PORCH CORNER 4
			
			BuildHelper.build(world, x-3, y+2, z-4, fence, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			BuildHelper.build(world, x-3, y+2, z+4, fence, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			BuildHelper.build(world, x-6, y+2, z-4, fence, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			BuildHelper.build(world, x-6, y+2, z+4, fence, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			BuildHelper.build(world, x-5, y+1, z-4, fence, 1, 1, 2); //PORCH SIDE 1
			BuildHelper.build(world, x-5, y+1, z+4, fence, 1, 1, 2); //PORCH SIDE 2
			BuildHelper.build(world, x-6, y+1, z-3, fence, 7, 1, 1); //PORCH SIDE 3 (FRONT)
			BuildHelper.setBlock(world,x-6, y+1, z, gate,directionGate); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/ //GOOD
			BuildHelper.build(world, x+5, y+5, z-5, slabD, 11, 1, 1); //HOUSE ROOF 1
			BuildHelper.build(world, x-2, y+5, z-5, slabD, 11, 1, 1); //HOUSE ROOF 2
			BuildHelper.build(world, x-1, y+5, z-5, slabD, 1, 1, 6); //HOUSE ROOF 3 //GOOD
			BuildHelper.build(world, x-1, y+5, z+5, slabD, 1, 1, 6); //HOUSE ROOF 4 //GOOD

			BuildHelper.build(world, x+4, y+5, z-4, light, 9, 1, 1); //HOUSE ROOF 1
			BuildHelper.build(world, x-1, y+5, z-4, light, 9, 1, 1); //HOUSE ROOF 2
			BuildHelper.build(world, x, y+5, z-4, light, 1, 1, 4); //HOUSE ROOF 3
			BuildHelper.build(world, x, y+5, z+4, light, 1, 1, 4); //HOUSE ROOF 4

			BuildHelper.build(world, x+3, y+6, z-3, slabD, 7, 1, 1); //HOUSE ROOF 1
			BuildHelper.build(world, x, y+6, z-3, slabD, 7, 1, 1); //HOUSE ROOF 2
			BuildHelper.build(world, x+1, y+6, z-3, slabD, 1, 1, 2); //HOUSE ROOF 3
			BuildHelper.build(world, x+1, y+6, z+3, slabD, 1, 1, 2); //HOUSE ROOF 4

			BuildHelper.build(world, x+1, y+6, z-2, light, 5, 1, 2); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			BuildHelper.setBlock(world,x + 4, y + 1, z + 4, bed, directionLeft); //BED //NOT 2, 1, 3 //GOOD
			
			BuildHelper.setBlock(world,x + 4, y + 1, z - 2, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x + 4, y + 1, z - 3, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x + 3, y + 1, z - 4, chest, directionChestLeft); //CHEST
			BuildHelper.setBlock(world,x + 2, y + 1, z - 4, chest, directionChestLeft); //CHEST
			
			BuildHelper.setBlock(world,x + 4, y + 1, z - 4, craft); //WORKBENCH
			BuildHelper.setBlock(world,x + 2, y + 1, z + 4, furnace, directionRight); //FURNACE //NOT 3
			BuildHelper.setBlock(world,x + 1, y + 1, z + 4, furnace, directionRight); //FURNACE //NOT 3

			BuildHelper.setBlock(world,x - 1, y + 1, z - 4, stair, directionStairLeft); //CHAIR
			BuildHelper.setBlock(world,x - 1, y + 1, z + 4, stair, directionStairRight); //CHAIR
			BuildHelper.setBlock(world,x, y + 1, z - 4, sign, directionBack); //CHAIR //NOT 4, 3
			BuildHelper.setBlock(world,x, y + 1, z + 4, sign, directionBack); //CHAIR //NOT 4, 3
			
			BuildHelper.setBlock(world,x-1,y+2,z-2,plate); //TABLE
			BuildHelper.setBlock(world,x-1,y+2,z+2,plate); //TABLE
			BuildHelper.setBlock(world,x-1,y+1,z-2,fence); //TABLE
			BuildHelper.setBlock(world,x-1,y+1,z+2,fence); //TABLE
			
			BuildHelper.setBlock(world,x - 6, y + 2, z - 1, torch);
			BuildHelper.setBlock(world,x - 6, y + 2, z + 1, torch);
		}
		
		/************************ meta == 2 ************************/
		else if(direction == Direction.NORTH) {
			/************************ Layer 0 to 7 : AIR ************************/ //DONE
			BuildHelper.build(world, x-5, y, z-2, air, 8, 6, 11); //HOUSE ROOM
			BuildHelper.build(world, x-4, y+5, z-1, air, 6, 1, 9); //HOUSE ROOF 1
			BuildHelper.build(world, x-3, y+6, z, air, 4, 1, 7); //HOUSE ROOF 2

			BuildHelper.build(world, x-4, y, z-6, air, 4, 5, 9); //PORCH

			/************************ Layer 0 : FLOOR ************************/ //DONE
			BuildHelper.build(world, x-5, y, z-2, dark, 8, 1, 11); //HOME FLOOR 1
			BuildHelper.build(world, x-4, y, z-1, light, 6, 1, 9); //HOME FLOOR 2
			BuildHelper.build(world, x-3, y, z, dark, 4, 1, 7); //HOME FLOOR 3
			BuildHelper.build(world, x-2, y, z+1, light, 2, 1, 5); //HOME FLOOR 4

			BuildHelper.build(world, x-4, y, z-6, dark, 4, 1, 9); //PORCH FLOOR 1
			BuildHelper.build(world, x-3, y, z-5, light, 2, 1, 7); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/ //DONE
			BuildHelper.build(world, x-5, y+1, z+5, log, 1, 4, 1); //LOG CORNER 1
			BuildHelper.build(world, x+5, y+1, z+5, log, 1, 4, 1); //LOG CORNER 2
			BuildHelper.build(world, x-5, y+1, z-2, log, 1, 4, 1); //LOG CORNER 3
			BuildHelper.build(world, x+5, y+1, z-2, log, 1, 4, 1); //LOG CORNER 4
			BuildHelper.build(world, x-4, y+1, z+5, light, 1, 4, 9); //WOOD WALL 1
			BuildHelper.build(world, x-4, y+1, z-2, light, 1, 4, 9); //WOOD WALL 2
			BuildHelper.build(world, x-5, y+1, z-1, light, 6, 4, 1); //WOOD WALL 3 //GOOD
			BuildHelper.build(world, x+5, y+1, z-1, light, 6, 4, 1); //WOOD WALL 4 //GOOD
			
			BuildHelper.build(world, x-3, y+2, z+5, pane, 1, 2, 7); //PANE WALL 1
			BuildHelper.build(world, x-3, y+2, z-2, pane, 1, 2, 1); //PANE WALL 2 (1)
			BuildHelper.build(world, x+3, y+2, z-2, pane, 1, 2, 1); //PANE WALL 2 (2)
			BuildHelper.build(world, x-5, y+2, z, pane, 4, 2, 1); //PANE WALL 3 //GOOD
			BuildHelper.build(world, x+5, y+2, z, pane, 4, 2, 1); //PANE WALL 4 //GOOD

			BuildHelper.build(world, x-1, y+1, z-2, log, 1, 3, 3); //LOG ENTRANCE

			BuildHelper.setBlock(world, x, y+1, z-2,door,directionDoor);

			/************************ Layer 1 to 4 : PORCH ************************/ //DONE
			BuildHelper.build(world, x-4, y+4, z-6, slabD, Direction.UP, 4, 1, 9); //PORCH ROOF 1
			BuildHelper.build(world, x-3, y+4, z-5, slabD, Direction.UP, 2, 1, 7); //PORCH ROOF 2

			BuildHelper.build(world, x-4, y+1, z-3, dark, 1, 4, 1); //PORCH CORNER 1
			BuildHelper.build(world, x+4, y+1, z-3, dark, 1, 4, 1); //PORCH CORNER 2
			BuildHelper.build(world, x-4, y+1, z-6, dark, 1, 4, 1); //PORCH CORNER 3
			BuildHelper.build(world, x+4, y+1, z-6, dark, 1, 4, 1); //PORCH CORNER 4
			
			BuildHelper.build(world, x-4, y+2, z-3, fence, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			BuildHelper.build(world, x+4, y+2, z-3, fence, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			BuildHelper.build(world, x-4, y+2, z-6, fence, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			BuildHelper.build(world, x+4, y+2, z-6, fence, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			BuildHelper.build(world, x-4, y+1, z-5, fence, 2, 1, 1); //PORCH SIDE 1
			BuildHelper.build(world, x+4, y+1, z-5, fence, 2, 1, 1); //PORCH SIDE 2
			BuildHelper.build(world, x-3, y+1, z-6, fence, 1, 1, 7); //PORCH SIDE 3 (FRONT)
			BuildHelper.setBlock(world,x, y+1, z-6, gate,directionGate); //PORCH GATE
			
			/************************ Layer 5 to 6 : ROOF ************************/ //DONE
			BuildHelper.build(world, x-5, y+5, z+5, slabD, 1, 1, 11); //HOUSE ROOF 1
			BuildHelper.build(world, x-5, y+5, z-2, slabD, 1, 1, 11); //HOUSE ROOF 2
			BuildHelper.build(world, x-5, y+5, z-1, slabD, 6, 1, 1); //HOUSE ROOF 3 //GOOD
			BuildHelper.build(world, x+5, y+5, z-1, slabD, 6, 1, 1); //HOUSE ROOF 4 //GOOD

			BuildHelper.build(world, x-4, y+5, z+4, light, 1, 1, 9); //HOUSE ROOF 1
			BuildHelper.build(world, x-4, y+5, z-1, light, 1, 1, 9); //HOUSE ROOF 2
			BuildHelper.build(world, x-4, y+5, z, light, 4, 1, 1); //HOUSE ROOF 3
			BuildHelper.build(world, x+4, y+5, z, light, 4, 1, 1); //HOUSE ROOF 4

			BuildHelper.build(world, x-3, y+6, z+3, slabD, 1, 1, 7); //HOUSE ROOF 1
			BuildHelper.build(world, x-3, y+6, z, slabD, 1, 1, 7); //HOUSE ROOF 2
			BuildHelper.build(world, x-3, y+6, z+1, slabD, 2, 1, 1); //HOUSE ROOF 3
			BuildHelper.build(world, x+3, y+6, z+1, slabD, 2, 1, 1); //HOUSE ROOF 4

			BuildHelper.build(world, x-2, y+6, z+1, light, 2, 1, 5); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			BuildHelper.setBlock(world,x - 4, y + 1, z + 4, bed, directionLeft); //BED //NOT 2, 1, 3, 0
			
			BuildHelper.setBlock(world,x+2, y + 1, z+4, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x+3, y + 1, z+4, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x+4, y + 1, z+3, chest, directionChestLeft); //CHEST
			BuildHelper.setBlock(world,x+4, y + 1, z+2, chest, directionChestLeft); //CHEST
			
			BuildHelper.setBlock(world,x + 4, y + 1, z + 4, craft); //WORKBENCH
			BuildHelper.setBlock(world,x - 4, y + 1, z + 2, furnace, directionRight); //FURNACE //NOT 3
			BuildHelper.setBlock(world,x - 4, y + 1, z + 1, furnace, directionRight); //FURNACE //NOT 3

			BuildHelper.setBlock(world,x - 4, y + 1, z - 1, stair, directionStairLeft); //CHAIR //NOT 3, 2, 1
			BuildHelper.setBlock(world,x+4, y + 1, z-1, stair, directionStairRight); //CHAIR //NOT 10 //GOOD
			BuildHelper.setBlock(world,x-4, y + 1, z, sign, directionBack); //CHAIR //NOT 4, 5
			BuildHelper.setBlock(world,x+4, y + 1, z, sign, directionBack); //CHAIR //NOT 4, 5
			
			BuildHelper.setBlock(world,x-2,y+2,z-1,plate); //TABLE
			BuildHelper.setBlock(world,x+2,y+2,z-1,plate); //TABLE
			BuildHelper.setBlock(world,x-2,y+1,z-1,fence); //TABLE
			BuildHelper.setBlock(world,x+2,y+1,z-1,fence); //TABLE
			
			BuildHelper.setBlock(world,x - 1, y + 2, z - 6, torch);
			BuildHelper.setBlock(world,x+1, y + 2, z-6, torch);
		}
		
		/************************ meta == 3 ************************/
		else if(direction == Direction.EAST) {
			/************************ Layer 0 to 7 : AIR ************************/
			BuildHelper.build(world, x-5, y, z-5, air, 11, 6, 8); //HOUSE ROOM
			BuildHelper.build(world, x-4, y+5, z-4, air, 9, 1, 6); //HOUSE ROOF 1
			BuildHelper.build(world, x-3, y+6, z-3, air, 7, 1, 4); //HOUSE ROOF 2

			BuildHelper.build(world, x+3, y, z-4, air, 9, 5, 4); //PORCH

			/************************ Layer 0 : FLOOR ************************/
			BuildHelper.build(world, x-5, y, z-5, dark, 11, 1, 8); //HOME FLOOR 1
			BuildHelper.build(world, x-4, y, z-4, light, 9, 1, 6); //HOME FLOOR 2
			BuildHelper.build(world, x-3, y, z-3, dark, 7, 1, 4); //HOME FLOOR 3
			BuildHelper.build(world, x-2, y, z-2, light, 5, 1, 2); //HOME FLOOR 4

			BuildHelper.build(world, x+3, y, z-4, dark, 9, 1, 4); //PORCH FLOOR 1
			BuildHelper.build(world, x+4, y, z-3, light, 7, 1, 2); //PORCH FLOOR 2

			/************************ Layer 1 to 4 : HOUSE ************************/
			BuildHelper.build(world, x-5, y+1, z-5, log, 1, 4, 1); //LOG CORNER 1
			BuildHelper.build(world, x-5, y+1, z+5, log, 1, 4, 1); //LOG CORNER 2
			BuildHelper.build(world, x+2, y+1, z-5, log, 1, 4, 1); //LOG CORNER 3
			BuildHelper.build(world, x+2, y+1, z+5, log, 1, 4, 1); //LOG CORNER 4
			BuildHelper.build(world, x-5, y+1, z-4, light, 9, 4, 1); //WOOD WALL 1
			BuildHelper.build(world, x+2, y+1, z-4, light, 9, 4, 1); //WOOD WALL 2
			BuildHelper.build(world, x-4, y+1, z-5, light, 1, 4, 6); //WOOD WALL 3
			BuildHelper.build(world, x-4, y+1, z+5, light, 1, 4, 6); //WOOD WALL 4
			
			BuildHelper.build(world, x-5, y+2, z-3, pane, 7, 2, 1); //PANE WALL 1
			BuildHelper.build(world, x+2, y+2, z-3, pane, 1, 2, 1); //PANE WALL 2 (1)
			BuildHelper.build(world, x+2, y+2, z+3, pane, 1, 2, 1); //PANE WALL 2 (2)
			BuildHelper.build(world, x-3, y+2, z-5, pane, 1, 2, 4); //PANE WALL 3
			BuildHelper.build(world, x-3, y+2, z+5, pane, 1, 2, 4); //PANE WALL 4

			BuildHelper.build(world, x+2, y+1, z-1, log, 3, 3, 1); //LOG ENTRANCE

			BuildHelper.setBlock(world,x+2, y+1, z,door,directionDoor);

			/************************ Layer 1 to 4 : PORCH ************************/
			BuildHelper.build(world, x+3, y+4, z-4, slabD, Direction.UP, 9, 1, 4); //PORCH ROOF 1
			BuildHelper.build(world, x+4, y+4, z-3, slabD, Direction.UP, 7, 1, 2); //PORCH ROOF 2

			BuildHelper.build(world, x+3, y+1, z-4, dark, 1, 4, 1); //PORCH CORNER 1
			BuildHelper.build(world, x+3, y+1, z+4, dark, 1, 4, 1); //PORCH CORNER 2
			BuildHelper.build(world, x+6, y+1, z-4, dark, 1, 4, 1); //PORCH CORNER 3
			BuildHelper.build(world, x+6, y+1, z+4, dark, 1, 4, 1); //PORCH CORNER 4
			
			BuildHelper.build(world, x+3, y+2, z-4, fence, 1, 2, 1); //PORCH CORNER 1 (FENCE)
			BuildHelper.build(world, x+3, y+2, z+4, fence, 1, 2, 1); //PORCH CORNER 2 (FENCE)
			BuildHelper.build(world, x+6, y+2, z-4, fence, 1, 2, 1); //PORCH CORNER 3 (FENCE)
			BuildHelper.build(world, x+6, y+2, z+4, fence, 1, 2, 1); //PORCH CORNER 4 (FENCE)
			
			BuildHelper.build(world, x+4, y+1, z-4, fence, 1, 1, 2); //PORCH SIDE 1
			BuildHelper.build(world, x+4, y+1, z+4, fence, 1, 1, 2); //PORCH SIDE 2
			BuildHelper.build(world, x+6, y+1, z-3, fence, 7, 1, 1); //PORCH SIDE 3 (FRONT)
			BuildHelper.setBlock(world,x+6, y+1, z, gate,directionGate); //PORCH GATE
			
			/************************ Layer 5 tp 6 : ROOF ************************/
			BuildHelper.build(world, x-5, y+5, z-5, slabD, 11, 1, 1); //HOUSE ROOF 1
			BuildHelper.build(world, x+2, y+5, z-5, slabD, 11, 1, 1); //HOUSE ROOF 2
			BuildHelper.build(world, x-4, y+5, z-5, slabD, 1, 1, 6); //HOUSE ROOF 3
			BuildHelper.build(world, x-4, y+5, z+5, slabD, 1, 1, 6); //HOUSE ROOF 4

			BuildHelper.build(world, x-4, y+5, z-4, light, 9, 1, 1); //HOUSE ROOF 1
			BuildHelper.build(world, x+1, y+5, z-4, light, 9, 1, 1); //HOUSE ROOF 2
			BuildHelper.build(world, x-3, y+5, z-4, light, 1, 1, 4); //HOUSE ROOF 3
			BuildHelper.build(world, x-3, y+5, z+4, light, 1, 1, 4); //HOUSE ROOF 4

			BuildHelper.build(world, x-3, y+6, z-3, slabD, 7, 1, 1); //HOUSE ROOF 1
			BuildHelper.build(world, x, y+6, z-3, slabD, 7, 1, 1); //HOUSE ROOF 2
			BuildHelper.build(world, x-2, y+6, z-3, slabD, 1, 1, 2); //HOUSE ROOF 3
			BuildHelper.build(world, x-2, y+6, z+3, slabD, 1, 1, 2); //HOUSE ROOF 4

			BuildHelper.build(world, x-2, y+6, z-2, light, 5, 1, 2); //HOUSE ROOF TOP
			
			/************************ Layer 1 to 4 : INSIDE ************************/
			BuildHelper.setBlock(world,x - 4, y + 1, z - 4, bed, directionLeft); //BED
			
			BuildHelper.setBlock(world,x - 4, y + 1, z + 2, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x - 4, y + 1, z + 3, chest, directionChestFront); //CHEST
			BuildHelper.setBlock(world,x - 3, y + 1, z + 4, chest, directionChestLeft); //CHEST
			BuildHelper.setBlock(world,x - 2, y + 1, z + 4, chest, directionChestLeft); //CHEST
			
			BuildHelper.setBlock(world,x - 4, y + 1, z + 4, craft); //WORKBENCH
			BuildHelper.setBlock(world,x - 2, y + 1, z - 4, furnace, directionRight); //FURNACE
			BuildHelper.setBlock(world,x - 1, y + 1, z - 4, furnace, directionRight); //FURNACE

			BuildHelper.setBlock(world,x + 1, y + 1, z - 4, stair, directionStairLeft); //CHAIR
			BuildHelper.setBlock(world,x + 1, y + 1, z + 4, stair, directionStairRight); //CHAIR
			BuildHelper.setBlock(world,x, y + 1, z - 4, sign, directionBack); //CHAIR
			BuildHelper.setBlock(world,x, y + 1, z + 4, sign, directionBack); //CHAIR
			
			BuildHelper.setBlock(world,x+1,y+2,z-2,plate); //TABLE
			BuildHelper.setBlock(world,x+1,y+2,z+2,plate); //TABLE
			BuildHelper.setBlock(world,x+1,y+1,z-2,fence); //TABLE
			BuildHelper.setBlock(world,x+1,y+1,z+2,fence); //TABLE
			
			BuildHelper.setBlock(world,x + 6, y + 2, z - 1, torch);
			BuildHelper.setBlock(world,x + 6, y + 2, z + 1, torch);
		}

		//above door torches
		BuildHelper.setBlockDirectional(world,x,y+3,z,torch_wall,direction,0,3,1,0,directionFront);
		BuildHelper.setBlockDirectional(world,x,y+3,z,torch_wall,direction,0,3,0,1,directionFront);

		//outdoor torches
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,0,7,4,0,directionFront);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,0,7,0,4,directionFront);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,0,6,5,0,directionRight);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,0,6,0,5,directionLeft);

		//indoor torches
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,0,1,2,0,directionBack);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,0,1,0,2,directionBack);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,4,0,2,0,directionFront);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,4,0,0,2,directionFront);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,1,0,4,0,directionLeft);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,2,0,4,0,directionLeft);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,1,0,0,4,directionRight);
		BuildHelper.setBlockDirectional(world,x,y+4,z,torch_wall,direction,2,0,0,4,directionRight);

		return true;
	}
}