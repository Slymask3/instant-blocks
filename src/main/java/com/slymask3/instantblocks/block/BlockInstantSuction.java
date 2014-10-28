package com.slymask3.instantblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.slymask3.instantblocks.InstantBlocks;
import com.slymask3.instantblocks.creativetab.InstantBlocksTab;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.utility.BuildHelper;

public class BlockInstantSuction extends BlockIB {
	
    public BlockInstantSuction() {
        super(ModBlocks.ibSucker, Names.Blocks.IB_SUCTION, Material.rock, Block.soundTypeStone, 1.5F);
        setTextures("instantblocks:absorb_0");
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
				BuildHelper.keepBlocks(world, x, y, z, ModBlocks.ibSucker);
				BuildHelper.sound(world, ConfigurationHandler.sound, x, y, z);
				BuildHelper.effectFull(world, "reddust", x, y, z);
				if (BuildHelper.counter == 1) {
					BuildHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Water Block.", Colors.a);
				} else {
					BuildHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Water Blocks.", Colors.a);
				}
				BuildHelper.xp(world, player, ConfigurationHandler.xp);
				
				if (ConfigurationHandler.useWands == true) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}
				
				//player.triggerAchievement(ib.achSuckerW);
			} else if (BuildHelper.liq == 2) {
				world.setBlock(x, y, z, ModBlocks.ibLava);
				BuildHelper.keepBlocks(world, x, y, z, ModBlocks.ibSucker);
				BuildHelper.sound(world, ConfigurationHandler.sound, x, y, z);
				BuildHelper.effectFull(world, "reddust", x, y, z);
				if (BuildHelper.counter == 1) {
					BuildHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Lava Block.", Colors.a);
				} else {
					BuildHelper.msg(player, "\u00a7aSucked in " + (BuildHelper.counter) + " Lava Blocks.", Colors.a);
				}
				BuildHelper.xp(world, player, ConfigurationHandler.xp);
				
				player.getCurrentEquippedItem().damageItem(1, player);

				//player.triggerAchievement(ib.achSuckerL);
			}
		} else {
			if (BuildHelper.liq == 0) {
				BuildHelper.msg(player, "\u00a7cNo liquids found.", Colors.c);
			} else if (BuildHelper.liq == -1) {
				BuildHelper.msg(player, "\u00a7cPrevented from sucking in over " + ConfigurationHandler.maxSuck + " Water/Lava Blocks.", Colors.c);
			}
		}
		
		BuildHelper.counter = 0;
		BuildHelper.liq = 0;
		BuildHelper.cs = 0;
		BuildHelper.cs2 = 0;
		BuildHelper.sucked = false;
    }
}