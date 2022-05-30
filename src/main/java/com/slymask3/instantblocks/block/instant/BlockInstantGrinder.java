package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.Colors;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockInstantGrinder extends BlockInstant {
	public BlockInstantGrinder() {
        super(Names.Blocks.IB_GRINDER, Material.rock, Block.soundTypeMetal, 1.5F);
        setTextures(Textures.Grinder.SIDE);
        setCreateMessage(Strings.CREATE_GRINDER);
        setBlockTextureName(Textures.Grinder.SIDE);
    }

	public boolean canActivate(World world, int x, int y, int z, EntityPlayer player) {
		if(BuildHelper.getBlock(world,x, y-1, z) != Blocks.mob_spawner) {
			IBHelper.msg(player, Strings.ERROR_GRINDER, Colors.c);
			return false;
		}
		return true;
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block stone = Blocks.stone;
		Block water = Blocks.flowing_water;
		Block torch = Blocks.torch;
		Block chest = Blocks.chest;
		Block craft = Blocks.crafting_table;
		Block door = Blocks.wooden_door;
		Block glass = Blocks.glass;
		Block pane = Blocks.glass_pane;
		Block sign = Blocks.wall_sign;
		Block air = Blocks.air;
		
		/************************ Layer -5 : Stone ************************/
		BuildHelper.build(world, x-5, y-5, z-5, stone, 11, 1, 11);
		BuildHelper.build(world, x+6, y-5, z-1, stone, 3, 1, 3);
		BuildHelper.build(world, x+9, y-5, z-3, stone, 7, 1, 8);

		//BuildHelper.buildMulti2(world, x, y, z, -5, -5, -5, stone, 11, 1, 11);
		//BuildHelper.buildMulti2(world, x, y, z, +6, -5, -1, stone, 3, 1, 3);
		//BuildHelper.buildMulti2(world, x, y, z, +9, -5, -3, stone, 7, 1, 8);

		//BuildHelper.buildDirectionalSimple(world, x, y-5, z, stone, dir, -5, -5, 11, 1, 11);
		//BuildHelper.buildDirectionalSimple(world, x, y-5, z, stone, dir, +6, -1, 3, 1, 3);
		//BuildHelper.buildDirectionalSimple(world, x, y-5, z, stone, dir, +9, -3, 7, 1, 8);

		/************************ Layer -4 to -2 : Air (Spawn Room) ************************/
		BuildHelper.build(world, x-4, y-4, z-4, air, 9, 3, 9);

		/************************ Layer -1 to 0 : Air (Spawn Room) ************************/
		BuildHelper.build(world, x-4, y-1, z-4, air, 4, 2, 9);
		BuildHelper.build(world, x-4, y-1, z+1, air, 4, 2, 9);
		BuildHelper.build(world, x-4, y-1, z, air, 1, 2, 4);
		BuildHelper.build(world, x+1, y-1, z, air, 1, 2, 4);

		/************************ Layer 1 to 2 : Air (Spawn Room) ************************/
		BuildHelper.build(world, x-4, y+1, z-4, air, 9, 2, 9);

		/************************ Layer -4 to -1 : Air (Output Room) ************************/
		BuildHelper.build(world, x+10, y-4, z-2, air, 5, 3, 6);
		
		/************************ Layer -4 ************************/
		BuildHelper.build(world, x-5, y-4, z-5, stone, 11, 1, 5);
		BuildHelper.build(world, x, y-4, z-5, stone, 5, 1, 1);
		BuildHelper.build(world, x, y-4, z+1, stone, 5, 1, 1);
		BuildHelper.build(world, x+1, y-4, z-5, stone, 4, 1, 1);
		BuildHelper.build(world, x+1, y-4, z+2, stone, 4, 1, 1);
		BuildHelper.build(world, x+2, y-4, z-5, stone, 3, 1, 1);
		BuildHelper.build(world, x+2, y-4, z+3, stone, 3, 1, 1);
		BuildHelper.build(world, x+3, y-4, z-5, stone, 2, 1, 1);
		BuildHelper.build(world, x+3, y-4, z+4, stone, 2, 1, 1);
		BuildHelper.build(world, x+4, y-4, z-5, stone, 1, 1, 1);
		BuildHelper.build(world, x+4, y-4, z+5, stone, 1, 1, 1);
		BuildHelper.build(world, x+5, y-4, z-5, stone, 5, 1, 1);
		BuildHelper.build(world, x+5, y-4, z+1, stone, 5, 1, 1);
		BuildHelper.build(world, x+6, y-4, z-1, stone, 1, 1, 4);
		BuildHelper.build(world, x+6, y-4, z+1, stone, 1, 1, 4);
		BuildHelper.build(world, x+9, y-4, z-3, stone, 2, 1, 1);
		BuildHelper.build(world, x+9, y-4, z+2, stone, 2, 1, 1);
		BuildHelper.build(world, x+10, y-4, z-3, stone, 1, 1, 6);
		BuildHelper.build(world, x+10, y-4, z+3, stone, 1, 1, 6);
		BuildHelper.build(world, x+16, y-4, z-3, stone, 7, 1, 1);

		BuildHelper.setBlock(world,x+5, y-4, z, air);
		BuildHelper.setBlock(world,x+6, y-4, z, sign, 3, 0); //SIGN
		//BuildHelper.setBlock(world,x+7, y-4, z, door, 5, 0); //DOOR
		BuildHelper.setBlock(world,x+8, y-4, z, air);
		BuildHelper.setBlock(world,x+9, y-4, z, door, 7, 0); //DOOR
		BuildHelper.setBlock(world,x+10, y-4, z-2, stone);
		BuildHelper.setBlock(world,x+10, y-4, z+2, stone);
		BuildHelper.setBlock(world,x+15, y-4, z+2, chest);
		BuildHelper.setBlock(world,x+15, y-4, z+1, chest);
		BuildHelper.setBlock(world,x+15, y-4, z, craft);
		BuildHelper.setBlock(world,x+15, y-4, z-1, chest);
		BuildHelper.setBlock(world,x+15, y-4, z-2, chest);

		/************************ Layer -3 ************************/
		BuildHelper.build(world, x-5, y-3, z-5, stone, 11, 1, 1);
		BuildHelper.build(world, x-4, y-3, z-5, stone, 3, 1, 1);
		BuildHelper.build(world, x-4, y-3, z+3, stone, 3, 1, 1);
		BuildHelper.build(world, x-3, y-3, z-5, stone, 2, 1, 1);
		BuildHelper.build(world, x-3, y-3, z+4, stone, 2, 1, 1);
		BuildHelper.build(world, x-2, y-3, z-5, stone, 1, 1, 7);
		BuildHelper.build(world, x-2, y-3, z+5, stone, 1, 1, 7);
		BuildHelper.build(world, x+5, y-3, z-5, stone, 11, 1, 1);
		BuildHelper.build(world, x+6, y-3, z-1, stone, 1, 1, 4);
		BuildHelper.build(world, x+6, y-3, z+1, stone, 1, 1, 4);
		BuildHelper.build(world, x+9, y-3, z-3, stone, 2, 1, 1);
		BuildHelper.build(world, x+9, y-3, z+2, stone, 2, 1, 1);
		BuildHelper.build(world, x+10, y-3, z-3, stone, 1, 1, 6);
		BuildHelper.build(world, x+10, y-3, z+3, stone, 1, 1, 6);
		BuildHelper.build(world, x+16, y-3, z-3, stone, 7, 1, 1);

		BuildHelper.setBlock(world,x+5, y-3, z, sign, 3, 0); //SIGN
		//BuildHelper.setBlock(world,x+7, y-3, z, door, 11, 0); //DOOR
		BuildHelper.setBlock(world,x+8, y-3, z, air);
		BuildHelper.setBlock(world,x+9, y-3, z, door, 9, 0); //DOOR
		BuildHelper.setBlock(world,x+6, y-3, z, water, 0, 2);

		/************************ Layer -2 to 2 : Stone (Spawn Room Walls) ************************/
		BuildHelper.build(world, x-5, y-2, z-5, stone, 11, 5, 1);
		BuildHelper.build(world, x-4, y-2, z-5, stone, 1, 5, 9);
		BuildHelper.build(world, x-4, y-2, z+5, stone, 1, 5, 9);
		BuildHelper.build(world, x+5, y-2, z-5, stone, 11, 5, 1);

		/************************ Layer -2 ************************/
		BuildHelper.build(world, x+6, y-2, z-1, stone, 3, 1, 4);
		BuildHelper.build(world, x+9, y-2, z-3, stone, 2, 1, 1);
		BuildHelper.build(world, x+9, y-2, z+2, stone, 2, 1, 1);
		BuildHelper.build(world, x+10, y-2, z-3, stone, 1, 1, 6);
		BuildHelper.build(world, x+10, y-2, z+3, stone, 1, 1, 6);
		BuildHelper.build(world, x+16, y-2, z-3, stone, 7, 1, 1);

		BuildHelper.setBlock(world,x-4, y-2, z-4, water, 0, 2);
		BuildHelper.setBlock(world,x-4, y-2, z+4, water, 0, 2);
		BuildHelper.setBlock(world,x+10, y-2, z-1, pane);
		BuildHelper.setBlock(world,x+10, y-2, z+1, pane);
		BuildHelper.setBlock(world,x+11, y-2, z-2, pane);
		BuildHelper.setBlock(world,x+11, y-2, z-1, pane);
		BuildHelper.setBlock(world,x+11, y-2, z+2, pane);
		BuildHelper.setBlock(world,x+11, y-2, z+1, pane);
		BuildHelper.setBlock(world,x+10, y-2, z, torch);
		BuildHelper.setBlock(world,x+12, y-2, z-2, torch);
		BuildHelper.setBlock(world,x+12, y-2, z+2, torch);
		BuildHelper.setBlock(world,x+15, y-2, z-1, torch);
		BuildHelper.setBlock(world,x+15, y-2, z+1, torch);
		BuildHelper.setBlock(world,x+6, y-2, z, sign, 3, 0); //SIGN

		/************************ Layer 3 : Stone (Spawn Room Roof) ************************/
		BuildHelper.build(world, x-5, y+3, z-5, stone, 11, 1, 11);

		/************************ Layer -1 : Stone (Output Room Roof) ************************/
		BuildHelper.build(world, x+9, y-1, z-3, stone, 7, 1, 8);

		/************************ Layer -1 to 21 : Stone (Input Tube) ************************/
		BuildHelper.build(world, x+5, y-1, z-1, stone, 3, 23, 3);

		/************************ Layer -1 to 21 : Stone (Output Tube 1) ************************/
		BuildHelper.build(world, x+9, y-1, z+1, stone, 3, 23, 3);

		/************************ Layer -1 to 21 : Stone (Output Tube 1) ************************/
		BuildHelper.build(world, x+9, y-1, z-3, stone, 3, 23, 3);

		/************************ Layer 18 to 21 : Stone (Top Room) ************************/
		BuildHelper.build(world, x+7, y+18, z-3, stone, 7, 4, 4);

		/************************ Layer -1 to 20 : Air (Input Tube) ************************/
		BuildHelper.build(world, x+6, y-1, z, air, 1, 22, 1);

		/************************ Layer -1 to 20 : Air (Output Tube 1) ************************/
		BuildHelper.build(world, x+10, y-1, z+2, air, 1, 22, 1);

		/************************ Layer -1 to 20 : Air (Output Tube 1) ************************/
		BuildHelper.build(world, x+10, y-1, z-2, air, 1, 22, 1);

		/************************ Layer 19 to 20 : Stone (Top Room) ************************/
		BuildHelper.build(world, x+7, y+19, z, air, 1, 2, 1);
		BuildHelper.build(world, x+8, y+19, z-2, air, 5, 2, 1);
		BuildHelper.build(world, x+9, y+19, z-2, air, 1, 2, 2);
		BuildHelper.build(world, x+9, y+19, z+2, air, 1, 2, 2);

		/************************ Layer 19 (Top Room) ************************/
		BuildHelper.setBlock(world,x+8, y+19, z, sign, 4, 0);
		BuildHelper.setBlock(world,x+8, y+19, z-1, water, 0, 2);
		BuildHelper.setBlock(world,x+8, y+19, z+1, water, 0, 2);
		BuildHelper.setBlock(world,x+10, y+19, z-2, sign, 4, 0);
		BuildHelper.setBlock(world,x+10, y+19, z+2, sign, 4, 0);

		/************************ Layer -1 to 19 (Input Tube) ************************/
		BuildHelper.setBlock(world,x+6, y-1, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+1, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+2, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+3, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+4, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+5, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+6, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+7, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+8, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+9, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+10, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+11, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+12, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+13, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+14, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+15, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+16, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+17, z, water, 0, 2);
		BuildHelper.setBlock(world,x+6, y+18, z, sign, 3, 0);
		BuildHelper.setBlock(world,x+6, y+19, z, water, 0, 2);
		
		BuildHelper.setBlock(world,x, y, z, air);
		
		/************************ Teleport ************************/
		IBHelper.teleport(world,player,x+13,y-4,z,Config.TP_GRINDER);
		if(Config.TP_GRINDER) {
			BuildHelper.setBlock(world,x+7, y-4, z, glass);
			BuildHelper.setBlock(world,x+7, y-3, z, glass);
		}
	}
}