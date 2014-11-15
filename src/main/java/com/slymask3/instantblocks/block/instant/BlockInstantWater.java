package com.slymask3.instantblocks.block.instant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.slymask3.instantblocks.block.BlockIB;
import com.slymask3.instantblocks.handler.ConfigurationHandler;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.init.ModItems;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.sound.SoundTypeLiquid;
import com.slymask3.instantblocks.utility.BuildHelper;
import com.slymask3.instantblocks.utility.IBHelper;

public class BlockInstantWater extends BlockIB {
	
    public BlockInstantWater() {
        super(ModBlocks.ibWater, Names.Blocks.IB_WATER, Material.water, new SoundTypeLiquid("random.splash", 1.0F, 1.0F), 0.5F);
        setTextures(Blocks.water);
        setTextureBooleans(false, false, false, false, false, false);
        setLightOpacity(3);
        setBlockTextureName(Textures.Water.SIDE);
    }
	
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		int checkWater = 0;
		if (checkWater == 0) {
			for (int l = 0; l < 8; ++l) {
	            world.spawnParticle("bubble", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
	        }
			checkWater = 30;
		} else if (checkWater > 0) {
        	checkWater--;
        }
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
        entity.extinguish();
    }
	
	public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }
    
    public int getRenderBlockPass() {
        return this.blockMaterial == Material.water ? 1 : 0;
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
    	Block liquid2 = Blocks.water; // FIX TO waterStill? 1.7.10
		Block liquid = Blocks.water; // FIX TO waterMoving? 1.7.10
		Block air = Blocks.air;
    	
		if (ConfigurationHandler.simpleWL == true) {
			BuildHelper.checkLiquid5S(world, x, y, z, liquid);
			BuildHelper.buildLiquid4S(world, x, y, z, liquid);
			BuildHelper.checkLiquid5UndoS(world, x, y, z, liquid);
		} else {
			BuildHelper.checkLiquid5(world, x, y, z, liquid);
			BuildHelper.buildLiquid4(world, x, y, z, liquid);
			BuildHelper.checkLiquid5Undo(world, x, y, z, liquid);
		}
		
		if (BuildHelper.built == true) {
			world.setBlock(x, y, z, liquid);
			IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibWater);
			IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
			IBHelper.effectFull(world, "reddust", x, y, z);
			IBHelper.msg(player, "\u00a7aInstant Water created with " + (BuildHelper.counter + 1) + " water blocks.", Colors.a);
			IBHelper.xp(world, player, ConfigurationHandler.xp);
			if (ConfigurationHandler.useWands == true) {
				player.getCurrentEquippedItem().damageItem(1, player);
			}
		} else {
			if (world.getBlock(x+1, y, z) != Blocks.air && world.getBlock(x-1, y, z) != Blocks.air && world.getBlock(x, y, z+1) != Blocks.air && world.getBlock(x, y, z-1) != Blocks.air && world.getBlock(x, y-1, z) != Blocks.air) {
				world.setBlock(x, y, z, liquid);
				IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibWater);
				IBHelper.sound(world, ConfigurationHandler.sound, x, y, z);
				IBHelper.effectFull(world, "reddust", x, y, z);
				IBHelper.msg(player, Strings.waterCreate1, Colors.a);
				IBHelper.xp(world, player, ConfigurationHandler.xp);
				if (ConfigurationHandler.useWands == true) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}
			} else {
				IBHelper.msg(player, "\u00a7cPrevented from creating over " + ConfigurationHandler.max + " water blocks.", Colors.c);
			}
		}
		
		BuildHelper.counter = 0;
		BuildHelper.c2 = 0;
		BuildHelper.c5 = 0;
		BuildHelper.built = false;
    }
}