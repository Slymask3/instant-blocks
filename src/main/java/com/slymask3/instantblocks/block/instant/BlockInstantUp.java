package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockLadderIB;
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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockInstantUp extends BlockLadderIB {
	
	public boolean canSeeSky;
	public int side = 0;
	
    public BlockInstantUp() {
        super(ModBlocks.ibUp, Names.Blocks.IB_ESCAPE_LADDER, Material.circuits, Block.soundTypeLadder, 0.4F);
		setResistance(2000F);
        setTextures(Textures.EscapeLadder.SIDE);
        setTickRandomly(true);
        setBlockTextureName(Textures.EscapeLadder.SIDE);
    }
    
	public void func_149797_b(int par1) {
        float f = 0.125F;
        if (par1 == 2) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            side = 2;
        }
        if (par1 == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            side = 3;
        }
        if (par1 == 4) {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            side = 4;
        }
        if (par1 == 5) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            side = 5;
        }
    }
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if (world.canBlockSeeTheSky(x, y+1, z)) {
    		IBHelper.msg(player, Strings.ERROR_ESCAPE_LADDER, Colors.c);
			return true;
		}
    	
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (Config.USE_WANDS) {
			if (is != null && (is.getItem() == ModItems.ibWandWood || is.getItem() == ModItems.ibWandStone || is.getItem() == ModItems.ibWandIron || is.getItem() == ModItems.ibWandGold || is.getItem() == ModItems.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achUp);
			} else {
				IBHelper.msg(player, Strings.ERROR_WAND, Colors.c);
				return true;
			}
		}
		
		Block stone = Blocks.stone;
		Block ladder = Blocks.ladder;
		Block torch = Blocks.torch;
		Block air = Blocks.air;
		
		int i = y - 1;
		int n = 0;
		while(!world.canBlockSeeTheSky(x, i+1, z)) {
			i++;
			BuildHelper.build(world, x-1, y-1, z-1, stone, 3, 1, 3);
			BuildHelper.build(world, x-1, i, z-1, stone, 3, 1, 3);
			world.setBlock(x, i, z, air);
			
			if (side == 2) {
				world.setBlock(x, i, z, ladder, 2, 0);
				
				world.setBlock(x, y, z-1, air);
				world.setBlock(x, y+1, z-1, air);
					
				for (int m = y + 6; m < i; m = m + 6) {
					world.setBlock(x, m, z-1, torch);
				}
			} else if (side == 3) {
				world.setBlock(x, i, z, ladder, 3, 0);
					
				world.setBlock(x, y, z+1, air);
				world.setBlock(x, y+1, z+1, air);
					
				for (int m = y + 6; m < i; m = m + 6) {
					world.setBlock(x, m, z+1, torch);
				}
			} else if (side == 4) {
				world.setBlock(x, i, z, ladder, 4, 0);
					
				world.setBlock(x-1, y, z, air);
				world.setBlock(x-1, y+1, z, air);
					
				for (int m = y + 6; m < i; m = m + 6) {
					world.setBlock(x-1, m, z, torch);
				}
			} else if (side == 5) {
				world.setBlock(x, i, z, ladder, 5, 0);
					
				world.setBlock(x+1, y, z, air);
				world.setBlock(x+1, y+1, z, air);
					
				for (int m = y + 6; m < i; m = m + 6) {
					world.setBlock(x+1, m, z, torch);
				}
			}
		}
		
		/************************ Functions ************************/
		IBHelper.keepBlocks(world, x, y, z, ModBlocks.ibUp);
		IBHelper.xp(world, player, Config.XP_AMOUNT);
		IBHelper.sound(world, Config.SOUND, x, y, z);
		IBHelper.effectFull(world, "reddust", x, y, z);
		IBHelper.msg(player, Strings.CREATE_ESCAPE_LADDER.replace("%i%",String.valueOf(i-y)), Colors.a);
		
		return true;
    }
}