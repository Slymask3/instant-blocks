package com.slymask3.instantblocks.block.instant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.block.BlockIB;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.IBHelper;

public class BlockInstantSuction extends BlockIB {
	
    public BlockInstantSuction() {
        super(ModBlocks.ibSucker, Names.Blocks.IB_SUCTION, Material.rock, Block.soundTypeStone, 1.5F);
        setTextures(Textures.Suction.SIDE);
        setBlockTextureName(Textures.Suction.SIDE);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (ConfigurationHandler.useWands == true) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//do not dmg yet
			} else {
				IBHelper.msg(player, Strings.wandReq, Colors.c);
				return true;
			}
		}

		build(world, x, y, z, player);
    		
    	return true;
    }
    
    public void build(World world, int x, int y, int z, EntityPlayer player) {
    	BuildHelper.checkSuck(world, x, y, z);
		BuildHelper.buildSuck(world, x, y, z);
		BuildHelper.checkSuckUndo(world, x, y, z);
		
		if (ConfigurationHandler.effect == true) {
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 1.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z + 1.2D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y - 0.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x - 0.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z - 0.2D, 0.0D, 0.0D, 0.0D);
		}
		
		if (BuildHelper.sucked == true) {
			if (BuildHelper.liq == 1) {
				world.setBlock(x, y, z, ModBlocks.ibWater);
				IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSucker);
				IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
				IBHelper.effectFull(world, "reddust", x, y, z);
				if (BuildHelper.counter == 1) {
					IBHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Water Block.", Colors.a);
				} else {
					IBHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Water Blocks.", Colors.a);
				}
				IBHelper.xp(world, player, ConfigurationHandler.xp);
				
				if (ConfigurationHandler.useWands == true) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}
				
				//player.triggerAchievement(ib.achSuckerW);
			} else if (BuildHelper.liq == 2) {
				world.setBlock(x, y, z, ModBlocks.ibLava);
				IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSucker);
				IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
				IBHelper.effectFull(world, "reddust", x, y, z);
				if (BuildHelper.counter == 1) {
					IBHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Lava Block.", Colors.a);
				} else {
					IBHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Lava Blocks.", Colors.a);
				}
				IBHelper.xp(world, player, ConfigurationHandler.xp);
				
				if (ConfigurationHandler.useWands == true) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}

				//player.triggerAchievement(ib.achSuckerL);
			}
		} else {
			if (BuildHelper.liq == 0) {
				IBHelper.msg(player, "\u00a7cNo liquids found.", Colors.c);
			} else if (BuildHelper.liq == -1) {
				IBHelper.msg(player, "\u00a7cPrevented from sucking in over " + ConfigurationHandler.maxSuck + " Water/Lava Blocks.", Colors.c);
			}
		}
		
		BuildHelper.counter = 0;
		BuildHelper.liq = 0;
		BuildHelper.cs = 0;
		BuildHelper.cs2 = 0;
		BuildHelper.sucked = false;
    }
}