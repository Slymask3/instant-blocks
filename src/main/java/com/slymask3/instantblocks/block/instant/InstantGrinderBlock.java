package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantGrinderBlock extends InstantBlock {
	public InstantGrinderBlock() {
		super(Block.Properties.of(Material.METAL)
				.strength(1.5F, 2000F)
				.sound(SoundType.METAL)
		, Config.Common.DISABLE_GRINDER);
        setCreateMessage(Strings.CREATE_GRINDER);
    }

	public boolean canActivate(Level world, int x, int y, int z, Player player) {
		if(BuildHelper.getBlock(world,x, y-1, z) != Blocks.SPAWNER) {
			Helper.sendMessage(player, Strings.ERROR_GRINDER);
			return false;
		}
		return true;
	}

	public boolean build(Level world, int x, int y, int z, Player player) {
		Block stone = Blocks.STONE;
		Block water = Blocks.WATER;
		Block torch = Blocks.WALL_TORCH;
		Block chest = Blocks.CHEST;
		Block craft = Blocks.CRAFTING_TABLE;
		Block door = Blocks.OAK_DOOR;
		Block glass = Blocks.GLASS;
		Block pane = Blocks.GLASS_PANE;
		Block sign = Blocks.OAK_WALL_SIGN;
		Block plate = Blocks.OAK_PRESSURE_PLATE;
		Block air = Blocks.AIR;

		/************************ Layer -5 : Stone ************************/
		BuildHelper.buildStone(world, x-5, y-5, z-5, 11, 1, 11);
		BuildHelper.buildStone(world, x+6, y-5, z-1, 3, 1, 3);
		BuildHelper.buildStone(world, x+9, y-5, z-3, 7, 1, 8);

		/************************ Layer -4 to -2 : Air (Spawn Room) ************************/
		BuildHelper.build(world, x-4, y-4, z-4, air, 9, 3, 9);

		/************************ Layer -1 to 0 : Air (Spawn Room) ************************/
		BuildHelper.build(world, x-4, y-1, z-4, air, 9, 2, 4);
		BuildHelper.build(world, x-4, y-1, z+1, air, 9, 2, 4);
		BuildHelper.build(world, x-4, y-1, z, air, 4, 2, 1);
		BuildHelper.build(world, x+1, y-1, z, air, 4, 2, 1);

		/************************ Layer 1 to 2 : Air (Spawn Room) ************************/
		BuildHelper.build(world, x-4, y+1, z-4, air, 9, 2, 9);

		/************************ Layer -4 to -1 : Air (Output Room) ************************/
		BuildHelper.build(world, x+10, y-4, z-2, air, 6, 3, 5);
		
		/************************ Layer -4 ************************/
		BuildHelper.buildStone(world, x-5, y-4, z-5, 11, 1, 5);
		BuildHelper.buildStone(world, x, y-4, z-5, 5, 1, 1);
		BuildHelper.buildStone(world, x, y-4, z+1, 5, 1, 1);
		BuildHelper.buildStone(world, x+1, y-4, z-5, 4, 1, 1);
		BuildHelper.buildStone(world, x+1, y-4, z+2, 4, 1, 1);
		BuildHelper.buildStone(world, x+2, y-4, z-5, 3, 1, 1);
		BuildHelper.buildStone(world, x+2, y-4, z+3, 3, 1, 1);
		BuildHelper.buildStone(world, x+3, y-4, z-5, 2, 1, 1);
		BuildHelper.buildStone(world, x+3, y-4, z+4, 2, 1, 1);
		BuildHelper.buildStone(world, x+4, y-4, z-5, 1, 1, 1);
		BuildHelper.buildStone(world, x+4, y-4, z+5, 1, 1, 1);
		BuildHelper.buildStone(world, x+5, y-4, z-5, 5, 1, 1);
		BuildHelper.buildStone(world, x+5, y-4, z+1, 5, 1, 1);
		BuildHelper.buildStone(world, x+6, y-4, z-1, 1, 1, 4);
		BuildHelper.buildStone(world, x+6, y-4, z+1, 1, 1, 4);
		BuildHelper.buildStone(world, x+9, y-4, z-3, 2, 1, 1);
		BuildHelper.buildStone(world, x+9, y-4, z+2, 2, 1, 1);
		BuildHelper.buildStone(world, x+10, y-4, z-3, 1, 1, 6);
		BuildHelper.buildStone(world, x+10, y-4, z+3, 1, 1, 6);
		BuildHelper.buildStone(world, x+16, y-4, z-3, 7, 1, 1);

		BuildHelper.setBlock(world,x+5, y-4, z, air);
		BuildHelper.setBlock(world,x+6, y-4, z, sign, Direction.NORTH); //SIGN
		BuildHelper.setBlock(world,x+8, y-4, z, air);
		BuildHelper.setBlock(world,x+9, y-4, z, door, Direction.WEST); //DOOR
		BuildHelper.setStone(world,x+10, y-4, z-2);
		BuildHelper.setStone(world,x+10, y-4, z+2);
		BuildHelper.setBlock(world,x+15, y-4, z+2, chest, Direction.WEST);
		BuildHelper.setBlock(world,x+15, y-4, z+1, chest, Direction.WEST);
		BuildHelper.setBlock(world,x+15, y-4, z, craft);
		BuildHelper.setBlock(world,x+15, y-4, z-1, chest, Direction.WEST);
		BuildHelper.setBlock(world,x+15, y-4, z-2, chest, Direction.WEST);

		/************************ Layer -3 ************************/
		BuildHelper.buildStone(world, x-5, y-3, z-5, 11, 1, 1);
		BuildHelper.buildStone(world, x-4, y-3, z-5, 3, 1, 1);
		BuildHelper.buildStone(world, x-4, y-3, z+3, 3, 1, 1);
		BuildHelper.buildStone(world, x-3, y-3, z-5, 2, 1, 1);
		BuildHelper.buildStone(world, x-3, y-3, z+4, 2, 1, 1);
		BuildHelper.buildStone(world, x-2, y-3, z-5, 1, 1, 7);
		BuildHelper.buildStone(world, x-2, y-3, z+5, 1, 1, 7);
		BuildHelper.buildStone(world, x+5, y-3, z-5, 11, 1, 1);
		BuildHelper.buildStone(world, x+6, y-3, z-1, 1, 1, 4);
		BuildHelper.buildStone(world, x+6, y-3, z+1, 1, 1, 4);
		BuildHelper.buildStone(world, x+9, y-3, z-3, 2, 1, 1);
		BuildHelper.buildStone(world, x+9, y-3, z+2, 2, 1, 1);
		BuildHelper.buildStone(world, x+10, y-3, z-3, 1, 1, 6);
		BuildHelper.buildStone(world, x+10, y-3, z+3, 1, 1, 6);
		BuildHelper.buildStone(world, x+16, y-3, z-3, 7, 1, 1);

		BuildHelper.setBlock(world,x+5, y-3, z, sign, Direction.NORTH, 0); //SIGN
		BuildHelper.setBlock(world,x+8, y-3, z, air);
		//BuildHelper.setBlock(world,x+9, y-3, z, door, Direction.WEST); //DOOR
		BuildHelper.setBlock(world,x+6, y-3, z, water);

		/************************ Layer -2 to 2 : Stone (Spawn Room Walls) ************************/
		BuildHelper.buildStone(world, x-5, y-2, z-5, 11, 5, 1);
		BuildHelper.buildStone(world, x-4, y-2, z-5, 1, 5, 9);
		BuildHelper.buildStone(world, x-4, y-2, z+5, 1, 5, 9);
		BuildHelper.buildStone(world, x+5, y-2, z-5, 11, 5, 1);

		/************************ Layer -2 ************************/
		BuildHelper.buildStone(world, x+6, y-2, z-1, 3, 1, 4);
		BuildHelper.buildStone(world, x+9, y-2, z-3, 2, 1, 1);
		BuildHelper.buildStone(world, x+9, y-2, z+2, 2, 1, 1);
		BuildHelper.buildStone(world, x+10, y-2, z-3, 1, 1, 6);
		BuildHelper.buildStone(world, x+10, y-2, z+3, 1, 1, 6);
		BuildHelper.buildStone(world, x+16, y-2, z-3, 7, 1, 1);

		BuildHelper.setBlock(world,x-4, y-2, z-4, water);
		BuildHelper.setBlock(world,x-4, y-2, z+4, water);
		BuildHelper.setBlock(world,x+10, y-2, z-1, pane);
		BuildHelper.setBlock(world,x+10, y-2, z+1, pane);
		BuildHelper.setBlock(world,x+11, y-2, z-2, pane);
		BuildHelper.setBlock(world,x+11, y-2, z-1, pane);
		BuildHelper.setBlock(world,x+11, y-2, z+2, pane);
		BuildHelper.setBlock(world,x+11, y-2, z+1, pane);
		BuildHelper.setBlock(world,x+10, y-2, z, torch, Direction.EAST);
		BuildHelper.setBlock(world,x+12, y-2, z-2, torch, Direction.SOUTH);
		BuildHelper.setBlock(world,x+12, y-2, z+2, torch, Direction.NORTH);
		BuildHelper.setBlock(world,x+15, y-2, z-1, torch, Direction.WEST);
		BuildHelper.setBlock(world,x+15, y-2, z+1, torch, Direction.WEST);
		BuildHelper.setBlock(world,x+6, y-2, z, sign, Direction.NORTH, 0); //SIGN

		/************************ Layer 3 : Stone (Spawn Room Roof) ************************/
		BuildHelper.buildStone(world, x-5, y+3, z-5, 11, 1, 11);

		/************************ Layer -1 : Stone (Output Room Roof) ************************/
		BuildHelper.buildStone(world, x+9, y-1, z-3, 7, 1, 8);

		/************************ Layer -1 to 21 : Stone (Input Tube) ************************/
		BuildHelper.buildStone(world, x+5, y-1, z-1, 3, 23, 3);

		/************************ Layer -1 to 21 : Stone (Output Tube 1) ************************/
		BuildHelper.buildStone(world, x+9, y-1, z+1, 3, 23, 3);

		/************************ Layer -1 to 21 : Stone (Output Tube 1) ************************/
		BuildHelper.buildStone(world, x+9, y-1, z-3, 3, 23, 3);

		/************************ Layer 18 to 21 : Stone (Top Room) ************************/
		BuildHelper.buildStone(world, x+7, y+18, z-3, 7, 4, 4);

		/************************ Layer -1 to 20 : Air (Input Tube) ************************/
		BuildHelper.build(world, x+6, y-1, z, air, 1, 22, 1);

		/************************ Layer -1 to 20 : Air (Output Tube 1) ************************/
		BuildHelper.build(world, x+10, y-1, z+2, air, 1, 22, 1);

		/************************ Layer -1 to 20 : Air (Output Tube 1) ************************/
		BuildHelper.build(world, x+10, y-1, z-2, air, 1, 22, 1);

		/************************ Layer 19 to 20 : Stone (Top Room) ************************/
		BuildHelper.build(world, x+7, y+19, z, air, 1, 2, 1);
		BuildHelper.build(world, x+8, y+19, z-2, air, 1, 2, 5);
		BuildHelper.build(world, x+9, y+19, z-2, air, 2, 2, 1);
		BuildHelper.build(world, x+9, y+19, z+2, air, 2, 2, 1);

		/************************ Layer 19 (Top Room) ************************/
		BuildHelper.setBlock(world,x+8, y+19, z, plate);
		BuildHelper.setBlock(world,x+8, y+19, z-1, water);
		BuildHelper.setBlock(world,x+8, y+19, z+1, water);
		BuildHelper.setBlock(world,x+10, y+19, z-2, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+10, y+19, z+2, sign, Direction.NORTH);

		/************************ Layer -1 to 19 (Input Tube) ************************/
		BuildHelper.setBlock(world,x+6, y-1, z, water);
		BuildHelper.setBlock(world,x+6, y, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+1, z, water);
		BuildHelper.setBlock(world,x+6, y+2, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+3, z, water);
		BuildHelper.setBlock(world,x+6, y+4, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+5, z, water);
		BuildHelper.setBlock(world,x+6, y+6, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+7, z, water);
		BuildHelper.setBlock(world,x+6, y+8, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+9, z, water);
		BuildHelper.setBlock(world,x+6, y+10, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+11, z, water);
		BuildHelper.setBlock(world,x+6, y+12, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+13, z, water);
		BuildHelper.setBlock(world,x+6, y+14, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+15, z, water);
		BuildHelper.setBlock(world,x+6, y+16, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+17, z, water);
		BuildHelper.setBlock(world,x+6, y+18, z, sign, Direction.NORTH);
		BuildHelper.setBlock(world,x+6, y+19, z, water);
		
		BuildHelper.setBlock(world,x, y, z, air);
		
		/************************ Teleport ************************/
		if(Config.Common.TP_GRINDER.get()) {
			BuildHelper.setBlock(world,x+7, y-4, z, glass);
			BuildHelper.setBlock(world,x+7, y-3, z, glass);
			Helper.teleport(world,player,x+13,y-4,z);
		}

		return true;
	}
}