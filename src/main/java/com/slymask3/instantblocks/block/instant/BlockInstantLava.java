package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Colors;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.sound.SoundTypeLiquid;
import com.slymask3.instantblocks.util.BuildHelper;
import com.slymask3.instantblocks.util.IBHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class BlockInstantLava extends BlockInstant {
	
    public BlockInstantLava() {
        super(ModBlocks.ibLava, Names.Blocks.IB_LAVA, Material.lava, new SoundTypeLiquid("random.fizz", 1.0F, 1.0F), 0.5F);
        setTextures(Blocks.lava);
        setTextureBooleans(false, false, false, false, false, false);
        setLightLevel(1.0F);
        setBlockTextureName(Textures.Lava.SIDE);
    }

	int checkLava = 0;
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if(checkLava == 0) {
	        for(int l = 0; l < 8; ++l) {
	            world.spawnParticle("largesmoke", (double)x + Math.random(), (double)y + 1.2D, (double)z + Math.random(), 0.0D, 0.0D, 0.0D);
	        }
			checkLava = 5;
		} else if(checkLava > 0) {
        	checkLava--;
        }
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int x, int y, int z) {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity) {
        entity.attackEntityFrom(DamageSource.lava, 1);
        entity.setFire(5);
    }
	
	public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();

		if(Config.USE_WANDS) {
			if(IBHelper.isWand(is)) {
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
		Block liquid = Blocks.lava;
		Block air = Blocks.air;
		
		if(Config.SIMPLE_LIQUID) {
			BuildHelper.checkLiquid5S(world, x, y, z, liquid);
			BuildHelper.buildLiquid4S(world, x, y, z, liquid);
			BuildHelper.checkLiquid5UndoS(world, x, y, z, liquid);
		} else {
			BuildHelper.checkLiquid5(world, x, y, z, liquid);
			BuildHelper.buildLiquid4(world, x, y, z, liquid);
			BuildHelper.checkLiquid5Undo(world, x, y, z, liquid);
		}
		
		if(BuildHelper.built) {
			BuildHelper.setBlock(world,x, y, z, liquid);
			IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibLava);
			IBHelper.sound(world, Config.SOUND, x, y, z);
			IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
			IBHelper.msg(player, Strings.CREATE_LAVA.replace("%i%",String.valueOf(BuildHelper.counter+1)), Colors.a);
			IBHelper.xp(world, player, Config.XP_AMOUNT);
			if(Config.USE_WANDS) {
				player.getCurrentEquippedItem().damageItem(1, player);
			}
		} else {
			if(BuildHelper.getBlock(world,x+1, y, z) != Blocks.air && BuildHelper.getBlock(world,x-1, y, z) != Blocks.air && BuildHelper.getBlock(world,x, y, z+1) != Blocks.air && BuildHelper.getBlock(world,x, y, z-1) != Blocks.air && BuildHelper.getBlock(world,x, y-1, z) != Blocks.air) {
				BuildHelper.setBlock(world,x, y, z, liquid);
				IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibLava);
				IBHelper.sound(world, Config.SOUND, x, y, z);
				IBHelper.effectFull(world, Config.PARTICLE, x, y, z);
				IBHelper.msg(player, Strings.CREATE_LAVA_1, Colors.a);
				IBHelper.xp(world, player, Config.XP_AMOUNT);
				if(Config.USE_WANDS) {
					player.getCurrentEquippedItem().damageItem(1, player);
				}
			} else {
				IBHelper.msg(player, Strings.ERROR_LAVA_MAX.replace("%i%",String.valueOf(Config.MAX_LIQUID)), Colors.c);
			}
		}
		
		BuildHelper.counter = 0;
		BuildHelper.c2 = 0;
		BuildHelper.c5 = 0;
		BuildHelper.built = false;
    }
}