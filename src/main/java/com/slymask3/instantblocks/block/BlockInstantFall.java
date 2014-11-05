package com.slymask3.instantblocks.block;

import java.io.*;
import java.util.Map;
import java.util.Random;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.utility.BuildHelper;


import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.*;

public class BlockInstantFall extends BlockDirectionalIB {
	
    public BlockInstantFall() {
        super(ModBlocks.ibFall, Names.Blocks.IB_RAINBOW_SKYDIVE, Material.cloth, Block.soundTypeCloth, 1.5F);
        setTextures("instantblocks:skydive_bottom", "instantblocks:skydive_top", "instantblocks:skydive_");
        setCreateMsg(Strings.fallCreate);
        setBlockTextureName(Textures.Skydive.SIDE_A);
    }
    
    public static IIcon bottom;
    public static IIcon top;
    public static IIcon side;
    //public static IIcon side0;
    
	public void registerBlockIcons(IIconRegister ir) {
		//if (ConfigurationHandler.animated.getBoolean(true)) {
			bottom = ir.registerIcon(Textures.Skydive.BOTTOM_A);
			top = ir.registerIcon(Textures.Skydive.TOP_A);
			side = ir.registerIcon(Textures.Skydive.SIDE_A);
		//} else {
		//	side0 = ir.registerIcon(Textures.Skydive.SIDE);
		//}
	}
    
	public IIcon getIcon(int side, int meta) {
		//if (ConfigurationHandler.animated.getBoolean(true)) {
			if (side == 0) {
				return bottom;
			} else if (side == 1) {
				return top;
			} else if (side>=2 && side<=5) {
				return this.side;
			}
		//} else {
		//	if (side == 0) {
		//		return Blocks.wool.getIcon(0, 13);
		//	} else if (side == 1) {
		//		return Blocks.wool.getIcon(0, 14);
		//	} else if (side>=2 && side<=5) {
		//		return this.side0;
		//	}
		//}
		return blockIcon;
	}
    
    public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block wool = Blocks.wool;
		Block stone = Blocks.stone;
		Block water = Blocks.water;
		Block ladder = Blocks.ladder;
		
		int meta = world.getBlockMetadata(x, y, z);
		
		/************************ 0 : Red (14) ************************/
		for (int c = 286; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[0], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[0], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[0], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[0], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[0], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 1 : Orange (1) ************************/
		for (int c = 283; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[1], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[1], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[1], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[1], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[1], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 2 : Yellow (4) ************************/
		for (int c = 280; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[2], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[2], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[2], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[2], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[2], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 3 : Lime (5) ************************/
		for (int c = 277; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[3], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[3], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[3], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[3], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[3], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 4 : Green (13) ************************/
		for (int c = 274; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[4], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[4], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[4], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[4], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[4], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 5 : Cyan (9) ************************/
		for (int c = 271; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[5], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[5], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[5], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[5], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[5], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 6 : Light Blue (3) ************************/
		for (int c = 268; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[6], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[6], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[6], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[6], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[6], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 7 : Blue (11) ************************/
		for (int c = 265; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[7], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[7], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[7], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[7], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[7], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 8 : Purple (10) ************************/
		for (int c = 262; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[8], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[8], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[8], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[8], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[8], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 9 : Light Purple (2) ************************/
		for (int c = 259; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[9], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[9], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[9], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[9], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[9], 0, 1, 3, 1); //CORNER
		}
		
		/************************ 10 : Pink (6) ************************/
		for (int c = 256; c >= 1; c =  c - 33) {
			BuildHelper.buildMeta(world, x-5, c, z-2, wool, ConfigurationHandler.wool[10], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x+5, c, z-2, wool, ConfigurationHandler.wool[10], 0, 5, 3, 1); //WALL
			BuildHelper.buildMeta(world, x-2, c, z+5, wool, ConfigurationHandler.wool[10], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x-2, c, z-5, wool, ConfigurationHandler.wool[10], 0, 1, 3, 5); //WALL
			BuildHelper.buildMeta(world, x+3, c, z+4, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+3, c, z-4, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z+3, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x+4, c, z-3, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z+4, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-3, c, z-4, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z+3, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
			BuildHelper.buildMeta(world, x-4, c, z-3, wool, ConfigurationHandler.wool[10], 0, 1, 3, 1); //CORNER
		}

		/************************ Air ************************/
		for (int c = 256; c >= 1; c--) {
			BuildHelper.build(world, x-3, c, z-3, Blocks.air, 7, 1, 7); //CENTER
			BuildHelper.build(world, x-4, c, z-2, Blocks.air, 5, 1, 1); //WALL
			BuildHelper.build(world, x+4, c, z-2, Blocks.air, 5, 1, 1); //WALL
			BuildHelper.build(world, x-2, c, z+4, Blocks.air, 1, 1, 5); //WALL
			BuildHelper.build(world, x-2, c, z-4, Blocks.air, 1, 1, 5); //WALL
		}

		/************************ Stone (Prevent Lava Burning) ************************/
		for (int c = 50; c >= 1; c--) {
			BuildHelper.build(world, x-6, c, z-3, stone, 7, 1, 1); //WALL
			BuildHelper.build(world, x+6, c, z-3, stone, 7, 1, 1); //WALL
			BuildHelper.build(world, x-3, c, z+6, stone, 1, 1, 7); //WALL
			BuildHelper.build(world, x-3, c, z-6, stone, 1, 1, 7); //WALL
			
			BuildHelper.build(world, x-5, c, z-4, stone, 2, 1, 1); //PRE-CORNER
			BuildHelper.build(world, x-5, c, z+3, stone, 2, 1, 1); //PRE-CORNER

			BuildHelper.build(world, x+5, c, z-4, stone, 2, 1, 1); //PRE-CORNER
			BuildHelper.build(world, x+5, c, z+3, stone, 2, 1, 1); //PRE-CORNER
			
			BuildHelper.build(world, x-4, c, z-5, stone, 1, 1, 2); //PRE-CORNER
			BuildHelper.build(world, x+3, c, z-5, stone, 1, 1, 2); //PRE-CORNER

			BuildHelper.build(world, x-4, c, z+5, stone, 1, 1, 2); //PRE-CORNER
			BuildHelper.build(world, x+3, c, z+5, stone, 1, 1, 2); //PRE-CORNER

			BuildHelper.build(world, x+4, c, z+4, stone, 1, 1, 1); //CORNER
			BuildHelper.build(world, x+4, c, z-4, stone, 1, 1, 1); //CORNER
			BuildHelper.build(world, x-4, c, z+4, stone, 1, 1, 1); //CORNER
			BuildHelper.build(world, x-4, c, z-4, stone, 1, 1, 1); //CORNER
		}

		/************************ Water ************************/
		for (int c = 4; c > 1; c--) {
			BuildHelper.build(world, x-3, c, z-3, water, 7, 1, 7); //CENTER
			BuildHelper.build(world, x-4, c, z-2, water, 5, 1, 1); //WALL
			BuildHelper.build(world, x+4, c, z-2, water, 5, 1, 1); //WALL
			BuildHelper.build(world, x-2, c, z+4, water, 1, 1, 5); //WALL
			BuildHelper.build(world, x-2, c, z-4, water, 1, 1, 5); //WALL
		}
		
		/************************ Floor ************************/
		BuildHelper.buildMeta(world, x-3, 1, z-3, wool, ConfigurationHandler.wool[7], 0, 7, 1, 7); //CENTER
		BuildHelper.buildMeta(world, x-4, 1, z-2, wool, ConfigurationHandler.wool[7], 0, 5, 1, 1); //WALL
		BuildHelper.buildMeta(world, x+4, 1, z-2, wool, ConfigurationHandler.wool[7], 0, 5, 1, 1); //WALL
		BuildHelper.buildMeta(world, x-2, 1, z+4, wool, ConfigurationHandler.wool[7], 0, 1, 1, 5); //WALL
		BuildHelper.buildMeta(world, x-2, 1, z-4, wool, ConfigurationHandler.wool[7], 0, 1, 1, 5); //WALL

		/************************ Ladder ************************/
		if (meta == 0) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMeta(world, x-2, c, z-4, ladder, 3, 0, 1, 1, 5);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				BuildHelper.sound(world, ConfigurationHandler.sound, x, 256, z+5);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x + 0.5, 257 + 0.5, z+5 + 0.5);
				}
			}
		} else if (meta == 1) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMeta(world, x+4, c, z-2, ladder, 4, 0, 5, 1, 1);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				BuildHelper.sound(world, ConfigurationHandler.sound, x-5, 256, z);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x-5 + 0.5, 257 + 0.5, z + 0.5);
				}
			}
		} else if (meta == 2) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMeta(world, x-2, c, z+4, ladder, 2, 0, 1, 1, 5);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				BuildHelper.sound(world, ConfigurationHandler.sound, x, 256, z-5);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x + 0.5, 257 + 0.5, z-5 + 0.5);
				}
			}
		} else if (meta == 3) {
			for (int c = 256; c >= 5; c--) {
				BuildHelper.buildMeta(world, x-4, c, z-2, ladder, 5, 0, 5, 1, 1);
			}
		
			if (ConfigurationHandler.tpFall == true) {
				BuildHelper.sound(world, ConfigurationHandler.sound, x+5, 256, z);
				if (!world.isRemote) { //IF SERVER
					player.setPositionAndUpdate(x+5 + 0.5, 257 + 0.5, z + 0.5);
	        		BuildHelper.sound(world, ConfigurationHandler.sound, (int)player.posX, (int)player.posY, (int)player.posZ);
				}
			}
		}
	}
}