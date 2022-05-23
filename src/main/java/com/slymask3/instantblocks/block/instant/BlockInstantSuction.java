package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockIB;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockInstantSuction extends BlockIB {
	
    public BlockInstantSuction() {
        super(ModBlocks.ibSucker, Names.Blocks.IB_SUCTION, Material.rock, Block.soundTypeStone, 1.5F);
		setResistance(2000F);
        setTextures(Textures.Suction.SIDE);
        setBlockTextureName(Textures.Suction.SIDE);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.useWands) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				//do not dmg yet
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
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
		
		if (Config.effect) {
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 1.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z + 1.2D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y - 0.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x - 0.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z - 0.2D, 0.0D, 0.0D, 0.0D);
		}
		
		if (BuildHelper.sucked) {
			if (BuildHelper.liq == 1) {
				world.setBlock(x, y, z, ModBlocks.ibWater);
				IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSucker);
				IBHelper.sound(world, Config.sound, x, y, z);
				IBHelper.effectFull(world, "reddust", x, y, z);
				if (BuildHelper.counter == 1) {
					IBHelper.msg(player, Strings.CREATE_SUCTION_WATER_1.replace("%i%",String.valueOf(BuildHelper.counter)), Colors.a);
				} else {
					IBHelper.msg(player, Strings.CREATE_SUCTION_WATER.replace("%i%",String.valueOf(BuildHelper.counter)), Colors.a);
				}
				IBHelper.xp(world, player, Config.xp);
				
				if (Config.useWands) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}
				
				//player.triggerAchievement(ib.achSuckerW);
			} else if (BuildHelper.liq == 2) {
				world.setBlock(x, y, z, ModBlocks.ibLava);
				IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibSucker);
				IBHelper.sound(world, Config.sound, x, y, z);
				IBHelper.effectFull(world, "reddust", x, y, z);
				if (BuildHelper.counter == 1) {
					IBHelper.msg(player, Strings.CREATE_SUCTION_LAVA_1.replace("%i%",String.valueOf(BuildHelper.counter)), Colors.a);
				} else {
					IBHelper.msg(player, Strings.CREATE_SUCTION_LAVA.replace("%i%",String.valueOf(BuildHelper.counter)), Colors.a);
				}
				IBHelper.xp(world, player, Config.xp);
				
				if (Config.useWands) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}

				//player.triggerAchievement(ib.achSuckerL);
			}
		} else {
			if (BuildHelper.liq == 0) {
				IBHelper.msg(player, Strings.ERROR_NO_LIQUID, Colors.c);
			} else if (BuildHelper.liq == -1) {
				IBHelper.msg(player, Strings.ERROR_SUCTION.replace("%i%",String.valueOf(Config.maxSuck)), Colors.c);
			}
		}
		
		BuildHelper.counter = 0;
		BuildHelper.liq = 0;
		BuildHelper.cs = 0;
		BuildHelper.cs2 = 0;
		BuildHelper.sucked = false;
    }
}