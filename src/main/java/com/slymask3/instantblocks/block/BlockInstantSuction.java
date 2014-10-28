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

public class BlockInstantSuction extends Block {
	private BuildHelper ibf = new BuildHelper();
	private ConfigurationHandler config = new ConfigurationHandler();
	private InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantSuction() {
        super(Material.rock);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_SUCTION);
        setHardness(1.5F);
        setResistance(2000F);
        setStepSound(Block.soundTypeStone);
        //setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }
    
    public int quantityDropped(Random random) {
        return 1;
    }
	
    public static IIcon[] textures = new IIcon[6];
    
	public void registerBlockIcons(IIconRegister ir) {
		textures[0] = ir.registerIcon("instantblocks:absorb_0");
		textures[1] = ir.registerIcon("instantblocks:absorb_0");
		textures[2] = ir.registerIcon("instantblocks:absorb_0");
		textures[3] = ir.registerIcon("instantblocks:absorb_0");
		textures[4] = ir.registerIcon("instantblocks:absorb_0");
		textures[5] = ir.registerIcon("instantblocks:absorb_0");
	}
    
	public IIcon getIcon(int side, int meta) {
		return textures[side];
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				//NO DMG ITEM? 1.7.10
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		ibf.checkSuck(world, x, y, z);
		ibf.buildSuck(world, x, y, z);
		ibf.checkSuckUndo(world, x, y, z);
		
		if (config.effect == true) {
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 1.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z + 1.2D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y - 0.2D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x - 0.2D, (double)y + 0.5D, (double)z + 0.5D, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("cloud", (double)x + 0.5D, (double)y + 0.5D, (double)z - 0.2D, 0.0D, 0.0D, 0.0D);
		}
		
		if (ibf.sucked == true) {
			if (ibf.liq == 1) {
				world.setBlock(x, y, z, mb.ibWater);
				ibf.keepBlocks(world, x, y, z, mb.ibSucker);
				ibf.sound(world, config.sound, x, y, z);
				ibf.effectFull(world, "reddust", x, y, z);
				if (ibf.counter == 1) {
					ibf.msg(player, "\u00a7aSucked in " + (ibf.counter) + " Water Block.", Colors.a);
				} else {
					ibf.msg(player, "\u00a7aSucked in " + (ibf.counter) + " Water Blocks.", Colors.a);
				}
				ibf.xp(world, player, config.xp);
				
				if (config.useWands == true) {
					is.damageItem(1, player);
				}
				
				//player.triggerAchievement(ib.achSuckerW);
			} else if (ibf.liq == 2) {
				world.setBlock(x, y, z, mb.ibLava);
				ibf.keepBlocks(world, x, y, z, mb.ibSucker);
				ibf.sound(world, config.sound, x, y, z);
				ibf.effectFull(world, "reddust", x, y, z);
				if (ibf.counter == 1) {
					ibf.msg(player, "\u00a7aSucked in " + (ibf.counter) + " Lava Block.", Colors.a);
				} else {
					ibf.msg(player, "\u00a7aSucked in " + (ibf.counter) + " Lava Blocks.", Colors.a);
				}
				ibf.xp(world, player, config.xp);
				
				is.damageItem(1, player);

				//player.triggerAchievement(ib.achSuckerL);
			}
		} else {
			if (ibf.liq == 0) {
				ibf.msg(player, "\u00a7cNo liquids found.", Colors.c);
			} else if (ibf.liq == -1) {
				ibf.msg(player, "\u00a7cPrevented from sucking in over " + config.maxSuck + " Water/Lava Blocks.", Colors.c);
			}
		}
		
		ibf.counter = 0;
		ibf.liq = 0;
		ibf.cs = 0;
		ibf.cs2 = 0;
		ibf.sucked = false;
		
		return true;
    }
}