package com.slymask3.instantblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import com.slymask3.instantblocks.utility.InstantBlocksFunctions;

public class BlockInstantUp extends BlockLadder {
	private InstantBlocksFunctions ibf = new InstantBlocksFunctions();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
	public boolean canSeeSky;
	public int side = 0;
	
    public BlockInstantUp() {
        super();
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_ESCAPE_LADDER);
        setHardness(0.4F);
        setResistance(2000F);
        setStepSound(Block.soundTypeLadder);
        setTickRandomly(true);
    }
    
    public int quantityDropped(Random random) {
        return 1;
    }
	
    public static IIcon[] textures = new IIcon[6];
    
	public void registerBlockIcons(IIconRegister ir) {
		if (config.animated.getBoolean(true)) {
			textures[0] = ir.registerIcon("instantblocks:escape_ladder");
			textures[1] = ir.registerIcon("instantblocks:escape_ladder");
			textures[2] = ir.registerIcon("instantblocks:escape_ladder");
			textures[3] = ir.registerIcon("instantblocks:escape_ladder");
			textures[4] = ir.registerIcon("instantblocks:escape_ladder");
			textures[5] = ir.registerIcon("instantblocks:escape_ladder");
		} else {
			textures[0] = ir.registerIcon("ladder");
			textures[1] = ir.registerIcon("ladder");
			textures[2] = ir.registerIcon("ladder");
			textures[3] = ir.registerIcon("ladder");
			textures[4] = ir.registerIcon("ladder");
			textures[5] = ir.registerIcon("ladder");
		}
	}
    
	public IIcon getIcon(int side, int meta) {
		return textures[side];
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
	
	/*public void func_149797_b(int par1) {
        float f = 0.125F;

        if (par1 == 2) {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (par1 == 3) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (par1 == 4) {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (par1 == 5) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }*/
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	if (world.canBlockSeeTheSky(x, y+1, z) == true) {
			ibf.msg(player, ibf.upError, Colors.c);
			return true;
		}
    	
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achUp);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		/*int stone = Block.stone.blockID;
		int ladder = Block.ladder.blockID;
		int torch = Block.torchWood.blockID;*/
		
		Block stone = Blocks.stone;
		Block ladder = Blocks.ladder;
		Block torch = Blocks.torch;
		Block air = Blocks.air;
		
		int i = y - 1;
		int n = 0;
		while(world.canBlockSeeTheSky(x, i+1, z) == false) {
			i++;
			ibf.build(world, x-1, y-1, z-1, stone, 3, 1, 3);
			ibf.build(world, x-1, i, z-1, stone, 3, 1, 3);
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
		ibf.keepBlocks(world, x, y, z, mb.ibUp);
		ibf.xp(world, player, config.xp);
		ibf.sound(world, config.sound, x, y, z);
		ibf.effectFull(world, "reddust", x, y, z);
		ibf.msg(player, "\u00a7aInstant Escape Ladder created " + (i - y) + " blocks up.", Colors.a);
		
		return true;
    }
}