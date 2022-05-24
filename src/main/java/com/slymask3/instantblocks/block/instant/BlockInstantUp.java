package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockLadderIB;
import com.slymask3.instantblocks.init.ModBlocks;
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
import net.minecraft.world.World;

public class BlockInstantUp extends BlockLadderIB {
	public int side = 0;
	
    public BlockInstantUp() {
        super(ModBlocks.ibUp, Names.Blocks.IB_ESCAPE_LADDER, Material.circuits, Block.soundTypeLadder, 0.4F);
        setTextures(Textures.EscapeLadder.SIDE);
        setTickRandomly(true);
        setBlockTextureName(Textures.EscapeLadder.SIDE);
    }
    
	public void func_149797_b(int par1) {
        float f = 0.125F;
        if(par1 == 2) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            side = 2;
        }
        if(par1 == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            side = 3;
        }
        if(par1 == 4) {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            side = 4;
        }
        if(par1 == 5) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            side = 5;
        }
    }

	public boolean canActivate(World world, int x, int y, int z, EntityPlayer player) {
		if(world.canBlockSeeTheSky(x, y+1, z)) {
			IBHelper.msg(player, Strings.ERROR_ESCAPE_LADDER, Colors.c);
			return false;
		}
		return true;
	}

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block stone = Blocks.stone;
		Block ladder = Blocks.ladder;
		Block torch = Blocks.torch;
		Block air = Blocks.air;

		int i = y - 1;
		while(!world.canBlockSeeTheSky(x, i+1, z)) {
			i++;
			BuildHelper.build(world, x-1, y-1, z-1, stone, 3, 1, 3);
			BuildHelper.build(world, x-1, i, z-1, stone, 3, 1, 3);
			BuildHelper.setBlock(world,x, i, z, air);

			if(side == 2) {
				BuildHelper.setBlock(world,x, i, z, ladder, 2, 0);

				BuildHelper.setBlock(world,x, y, z-1, air);
				BuildHelper.setBlock(world,x, y+1, z-1, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x, m, z-1, torch);
				}
			} else if(side == 3) {
				BuildHelper.setBlock(world,x, i, z, ladder, 3, 0);

				BuildHelper.setBlock(world,x, y, z+1, air);
				BuildHelper.setBlock(world,x, y+1, z+1, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x, m, z+1, torch);
				}
			} else if(side == 4) {
				BuildHelper.setBlock(world,x, i, z, ladder, 4, 0);

				BuildHelper.setBlock(world,x-1, y, z, air);
				BuildHelper.setBlock(world,x-1, y+1, z, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x-1, m, z, torch);
				}
			} else if(side == 5) {
				BuildHelper.setBlock(world,x, i, z, ladder, 5, 0);

				BuildHelper.setBlock(world,x+1, y, z, air);
				BuildHelper.setBlock(world,x+1, y+1, z, air);

				for(int m = y + 6; m < i; m = m + 6) {
					BuildHelper.setBlock(world,x+1, m, z, torch);
				}
			}
		}
		setCreateMsg(Strings.CREATE_ESCAPE_LADDER.replace("%i%",String.valueOf(i-y)));
	}
}