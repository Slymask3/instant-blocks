package com.slymask3.instantblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

public class BlockInstantGlassDome extends Block {
	private InstantBlocksFunctions ibf = new InstantBlocksFunctions();
	private ConfigurationHandler config = new ConfigurationHandler();
	public static InstantBlocks ib = new InstantBlocks();
	private ModBlocks mb = new ModBlocks();
	private ModItems mi = new ModItems();
	
    public BlockInstantGlassDome(boolean b) {
        super(Material.glass);
        setCreativeTab(InstantBlocksTab.INSTANTBLOCKS_TAB);
        setBlockName("instantblocks:" + Names.Blocks.IB_GLASS_DOME);
        setHardness(0.5F);
        setResistance(2000F);
        setStepSound(Block.soundTypeGlass);
    }
    
    public int quantityDropped(Random random) {
        return 1;
    }
	
    public int getRenderBlockPass() {
        return 0;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public static IIcon side;
    
	public void registerBlockIcons(IIconRegister ir) {
		side = ir.registerIcon("instantblocks:glassdome_side");
	}
    
	public IIcon getIcon(int side, int meta) {
		if (side == 0) {
			return Blocks.stone.getIcon(0, 0);
		} else if (side == 1) {
			return Blocks.glass.getIcon(0, 0);
		} else if (side>=2 && side<=5) {
			return this.side;
		}
		return blockIcon;
	}
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack is = player.getCurrentEquippedItem();
    	
		if (config.useWands == true) {
			if (is != null && (is.getItem() == mi.ibWandWood || is.getItem() == mi.ibWandStone || is.getItem() == mi.ibWandIron || is.getItem() == mi.ibWandGold || is.getItem() == mi.ibWandDiamond)) {
				is.damageItem(1, player);
				//player.triggerAchievement(ib.achGlassDome);
			} else {
				ibf.msg(player, ibf.wandReq, Colors.c);
				return true;
			}
		}
		
		build(world, x, y, z);
			
		//world.setBlock(x, y, z, ib.ibGlassDome.blockID);
		
		ibf.keepBlocks(world, x, y, z, mb.ibGlassDome);
		ibf.xp(world, player, config.xp);
			
		ibf.sound(world, config.sound, x, y, z);
    	ibf.effectFull(world, "reddust", x, y, z);
    	ibf.msg(player, ibf.domeCreate, Colors.a);
    		
    	return true;
    }

	private void build(World world, int x, int y, int z) {
		/*int glass = Block.glass.blockID;
		int stone = Block.stone.blockID;
		int torch = Block.torchWood.blockID;*/
		
		Block glass = Blocks.glass;
		Block stone = Blocks.stone;
		Block torch = Blocks.torch;
		Block air = Blocks.air;
		
		/************************ Layer 0 ************************/
		world.setBlock(x-4,y,z+2,stone);
		world.setBlock(x-4,y,z+1,stone);
		world.setBlock(x-4,y,z,stone);
		world.setBlock(x-4,y,z-1,stone);
		world.setBlock(x-4,y,z-2,stone);

		world.setBlock(x-3,y,z+3,stone);
		world.setBlock(x-3,y,z+2,stone);
		world.setBlock(x-3,y,z+1,stone);
		world.setBlock(x-3,y,z,stone);
		world.setBlock(x-3,y,z-1,stone);
		world.setBlock(x-3,y,z-2,stone);
		world.setBlock(x-3,y,z-3,stone);

		world.setBlock(x-2,y,z+4,stone);
		world.setBlock(x-2,y,z+3,stone);
		world.setBlock(x-2,y,z+2,stone);
		world.setBlock(x-2,y,z+1,stone);
		world.setBlock(x-2,y,z,stone);
		world.setBlock(x-2,y,z-1,stone);
		world.setBlock(x-2,y,z-2,stone);
		world.setBlock(x-2,y,z-3,stone);
		world.setBlock(x-2,y,z-4,stone);

		world.setBlock(x-1,y,z+4,stone);
		world.setBlock(x-1,y,z+3,stone);
		world.setBlock(x-1,y,z+2,stone);
		world.setBlock(x-1,y,z+1,stone);
		world.setBlock(x-1,y,z,stone);
		world.setBlock(x-1,y,z-1,stone);
		world.setBlock(x-1,y,z-2,stone);
		world.setBlock(x-1,y,z-3,stone);
		world.setBlock(x-1,y,z-4,stone);

		world.setBlock(x,y,z+4,stone);
		world.setBlock(x,y,z+3,stone);
		world.setBlock(x,y,z+2,stone);
		world.setBlock(x,y,z+1,stone);
		world.setBlock(x,y,z,stone); //0, 0, 0
		world.setBlock(x,y,z-1,stone);
		world.setBlock(x,y,z-2,stone);
		world.setBlock(x,y,z-3,stone);
		world.setBlock(x,y,z-4,stone);

		world.setBlock(x+1,y,z+4,stone);
		world.setBlock(x+1,y,z+3,stone);
		world.setBlock(x+1,y,z+2,stone);
		world.setBlock(x+1,y,z+1,stone);
		world.setBlock(x+1,y,z,stone);
		world.setBlock(x+1,y,z-1,stone);
		world.setBlock(x+1,y,z-2,stone);
		world.setBlock(x+1,y,z-3,stone);
		world.setBlock(x+1,y,z-4,stone);

		world.setBlock(x+2,y,z+4,stone);
		world.setBlock(x+2,y,z+3,stone);
		world.setBlock(x+2,y,z+2,stone);
		world.setBlock(x+2,y,z+1,stone);
		world.setBlock(x+2,y,z,stone);
		world.setBlock(x+2,y,z-1,stone);
		world.setBlock(x+2,y,z-2,stone);
		world.setBlock(x+2,y,z-3,stone);
		world.setBlock(x+2,y,z-4,stone);

		world.setBlock(x+3,y,z+3,stone);
		world.setBlock(x+3,y,z+2,stone);
		world.setBlock(x+3,y,z+1,stone);
		world.setBlock(x+3,y,z,stone);
		world.setBlock(x+3,y,z-1,stone);
		world.setBlock(x+3,y,z-2,stone);
		world.setBlock(x+3,y,z-3,stone);
		
		world.setBlock(x+4,y,z+2,stone);
		world.setBlock(x+4,y,z+1,stone);
		world.setBlock(x+4,y,z,stone);
		world.setBlock(x+4,y,z-1,stone);
		world.setBlock(x+4,y,z-2,stone);
		
		/************************ Layer 1 ************************/
		world.setBlock(x-4,y+1,z+2,glass);
		world.setBlock(x-4,y+1,z+1,glass);
		world.setBlock(x-4,y+1,z,glass);
		world.setBlock(x-4,y+1,z-1,glass);
		world.setBlock(x-4,y+1,z-2,glass);

		world.setBlock(x-3,y+1,z+3,glass);
		world.setBlock(x-3,y+1,z+2,glass);
		world.setBlock(x-3,y+1,z+1,air);
		world.setBlock(x-3,y+1,z,torch);
		world.setBlock(x-3,y+1,z-1,air);
		world.setBlock(x-3,y+1,z-2,glass);
		world.setBlock(x-3,y+1,z-3,glass);

		world.setBlock(x-2,y+1,z+4,glass);
		world.setBlock(x-2,y+1,z+3,glass);
		world.setBlock(x-2,y+1,z+2,air);
		world.setBlock(x-2,y+1,z+1,air);
		world.setBlock(x-2,y+1,z,air);
		world.setBlock(x-2,y+1,z-1,air);
		world.setBlock(x-2,y+1,z-2,air);
		world.setBlock(x-2,y+1,z-3,glass);
		world.setBlock(x-2,y+1,z-4,glass);

		world.setBlock(x-1,y+1,z+4,glass);
		world.setBlock(x-1,y+1,z+3,air);
		world.setBlock(x-1,y+1,z+2,air);
		world.setBlock(x-1,y+1,z+1,air);
		world.setBlock(x-1,y+1,z,air);
		world.setBlock(x-1,y+1,z-1,air);
		world.setBlock(x-1,y+1,z-2,air);
		world.setBlock(x-1,y+1,z-3,air);
		world.setBlock(x-1,y+1,z-4,glass);

		world.setBlock(x,y+1,z+4,glass);
		world.setBlock(x,y+1,z+3,air);
		world.setBlock(x,y+1,z+2,torch);
		world.setBlock(x,y+1,z+1,air);
		world.setBlock(x,y+1,z,air);
		world.setBlock(x,y+1,z-1,air);
		world.setBlock(x,y+1,z-2,torch);
		world.setBlock(x,y+1,z-3,air);
		world.setBlock(x,y+1,z-4,glass);

		world.setBlock(x+1,y+1,z+4,glass);
		world.setBlock(x+1,y+1,z+3,air);
		world.setBlock(x+1,y+1,z+2,air);
		world.setBlock(x+1,y+1,z+1,air);
		world.setBlock(x+1,y+1,z,air);
		world.setBlock(x+1,y+1,z-1,air);
		world.setBlock(x+1,y+1,z-2,air);
		world.setBlock(x+1,y+1,z-3,air);
		world.setBlock(x+1,y+1,z-4,glass);

		world.setBlock(x+2,y+1,z+4,glass);
		world.setBlock(x+2,y+1,z+3,glass);
		world.setBlock(x+2,y+1,z+2,air);
		world.setBlock(x+2,y+1,z+1,air);
		world.setBlock(x+2,y+1,z,air);
		world.setBlock(x+2,y+1,z-1,air);
		world.setBlock(x+2,y+1,z-2,air);
		world.setBlock(x+2,y+1,z-3,glass);
		world.setBlock(x+2,y+1,z-4,glass);

		world.setBlock(x+3,y+1,z+3,glass);
		world.setBlock(x+3,y+1,z+2,glass);
		world.setBlock(x+3,y+1,z+1,air);
		world.setBlock(x+3,y+1,z,torch);
		world.setBlock(x+3,y+1,z-1,air);
		world.setBlock(x+3,y+1,z-2,glass);
		world.setBlock(x+3,y+1,z-3,glass);
		
		world.setBlock(x+4,y+1,z+2,glass);
		world.setBlock(x+4,y+1,z+1,glass);
		world.setBlock(x+4,y+1,z,glass);
		world.setBlock(x+4,y+1,z-1,glass);
		world.setBlock(x+4,y+1,z-2,glass);
		
		/************************ Layer 2 ************************/
		world.setBlock(x-4,y+2,z+2,glass);
		world.setBlock(x-4,y+2,z+1,glass);
		world.setBlock(x-4,y+2,z,glass);
		world.setBlock(x-4,y+2,z-1,glass);
		world.setBlock(x-4,y+2,z-2,glass);

		world.setBlock(x-3,y+2,z+3,glass);
		world.setBlock(x-3,y+2,z+2,glass);
		world.setBlock(x-3,y+2,z+1,air);
		world.setBlock(x-3,y+2,z,air);
		world.setBlock(x-3,y+2,z-1,air);
		world.setBlock(x-3,y+2,z-2,glass);
		world.setBlock(x-3,y+2,z-3,glass);

		world.setBlock(x-2,y+2,z+4,glass);
		world.setBlock(x-2,y+2,z+3,glass);
		world.setBlock(x-2,y+2,z+2,air);
		world.setBlock(x-2,y+2,z+1,air);
		world.setBlock(x-2,y+2,z,air);
		world.setBlock(x-2,y+2,z-1,air);
		world.setBlock(x-2,y+2,z-2,air);
		world.setBlock(x-2,y+2,z-3,glass);
		world.setBlock(x-2,y+2,z-4,glass);

		world.setBlock(x-1,y+2,z+4,glass);
		world.setBlock(x-1,y+2,z+3,air);
		world.setBlock(x-1,y+2,z+2,air);
		world.setBlock(x-1,y+2,z+1,air);
		world.setBlock(x-1,y+2,z,air);
		world.setBlock(x-1,y+2,z-1,air);
		world.setBlock(x-1,y+2,z-2,air);
		world.setBlock(x-1,y+2,z-3,air);
		world.setBlock(x-1,y+2,z-4,glass);

		world.setBlock(x,y+2,z+4,glass);
		world.setBlock(x,y+2,z+3,air);
		world.setBlock(x,y+2,z+2,air);
		world.setBlock(x,y+2,z+1,air);
		world.setBlock(x,y+2,z,air);
		world.setBlock(x,y+2,z-1,air);
		world.setBlock(x,y+2,z-2,air);
		world.setBlock(x,y+2,z-3,air);
		world.setBlock(x,y+2,z-4,glass);

		world.setBlock(x+1,y+2,z+4,glass);
		world.setBlock(x+1,y+2,z+3,air);
		world.setBlock(x+1,y+2,z+2,air);
		world.setBlock(x+1,y+2,z+1,air);
		world.setBlock(x+1,y+2,z,air);
		world.setBlock(x+1,y+2,z-1,air);
		world.setBlock(x+1,y+2,z-2,air);
		world.setBlock(x+1,y+2,z-3,air);
		world.setBlock(x+1,y+2,z-4,glass);

		world.setBlock(x+2,y+2,z+4,glass);
		world.setBlock(x+2,y+2,z+3,glass);
		world.setBlock(x+2,y+2,z+2,air);
		world.setBlock(x+2,y+2,z+1,air);
		world.setBlock(x+2,y+2,z,air);
		world.setBlock(x+2,y+2,z-1,air);
		world.setBlock(x+2,y+2,z-2,air);
		world.setBlock(x+2,y+2,z-3,glass);
		world.setBlock(x+2,y+2,z-4,glass);

		world.setBlock(x+3,y+2,z+3,glass);
		world.setBlock(x+3,y+2,z+2,glass);
		world.setBlock(x+3,y+2,z+1,air);
		world.setBlock(x+3,y+2,z,air);
		world.setBlock(x+3,y+2,z-1,air);
		world.setBlock(x+3,y+2,z-2,glass);
		world.setBlock(x+3,y+2,z-3,glass);
		
		world.setBlock(x+4,y+2,z+2,glass);
		world.setBlock(x+4,y+2,z+1,glass);
		world.setBlock(x+4,y+2,z,glass);
		world.setBlock(x+4,y+2,z-1,glass);
		world.setBlock(x+4,y+2,z-2,glass);
		
		/************************ Layer 3 ************************/
		world.setBlock(x-4,y+3,z+2,glass);
		world.setBlock(x-4,y+3,z+1,glass);
		world.setBlock(x-4,y+3,z,glass);
		world.setBlock(x-4,y+3,z-1,glass);
		world.setBlock(x-4,y+3,z-2,glass);

		world.setBlock(x-3,y+3,z+3,glass);
		world.setBlock(x-3,y+3,z+2,glass);
		world.setBlock(x-3,y+3,z+1,air);
		world.setBlock(x-3,y+3,z,air);
		world.setBlock(x-3,y+3,z-1,air);
		world.setBlock(x-3,y+3,z-2,glass);
		world.setBlock(x-3,y+3,z-3,glass);

		world.setBlock(x-2,y+3,z+4,glass);
		world.setBlock(x-2,y+3,z+3,glass);
		world.setBlock(x-2,y+3,z+2,air);
		world.setBlock(x-2,y+3,z+1,air);
		world.setBlock(x-2,y+3,z,air);
		world.setBlock(x-2,y+3,z-1,air);
		world.setBlock(x-2,y+3,z-2,air);
		world.setBlock(x-2,y+3,z-3,glass);
		world.setBlock(x-2,y+3,z-4,glass);

		world.setBlock(x-1,y+3,z+4,glass);
		world.setBlock(x-1,y+3,z+3,air);
		world.setBlock(x-1,y+3,z+2,air);
		world.setBlock(x-1,y+3,z+1,air);
		world.setBlock(x-1,y+3,z,air);
		world.setBlock(x-1,y+3,z-1,air);
		world.setBlock(x-1,y+3,z-2,air);
		world.setBlock(x-1,y+3,z-3,air);
		world.setBlock(x-1,y+3,z-4,glass);

		world.setBlock(x,y+3,z+4,glass);
		world.setBlock(x,y+3,z+3,air);
		world.setBlock(x,y+3,z+2,air);
		world.setBlock(x,y+3,z+1,air);
		world.setBlock(x,y+3,z,air);
		world.setBlock(x,y+3,z-1,air);
		world.setBlock(x,y+3,z-2,air);
		world.setBlock(x,y+3,z-3,air);
		world.setBlock(x,y+3,z-4,glass);

		world.setBlock(x+1,y+3,z+4,glass);
		world.setBlock(x+1,y+3,z+3,air);
		world.setBlock(x+1,y+3,z+2,air);
		world.setBlock(x+1,y+3,z+1,air);
		world.setBlock(x+1,y+3,z,air);
		world.setBlock(x+1,y+3,z-1,air);
		world.setBlock(x+1,y+3,z-2,air);
		world.setBlock(x+1,y+3,z-3,air);
		world.setBlock(x+1,y+3,z-4,glass);

		world.setBlock(x+2,y+3,z+4,glass);
		world.setBlock(x+2,y+3,z+3,glass);
		world.setBlock(x+2,y+3,z+2,air);
		world.setBlock(x+2,y+3,z+1,air);
		world.setBlock(x+2,y+3,z,air);
		world.setBlock(x+2,y+3,z-1,air);
		world.setBlock(x+2,y+3,z-2,air);
		world.setBlock(x+2,y+3,z-3,glass);
		world.setBlock(x+2,y+3,z-4,glass);

		world.setBlock(x+3,y+3,z+3,glass);
		world.setBlock(x+3,y+3,z+2,glass);
		world.setBlock(x+3,y+3,z+1,air);
		world.setBlock(x+3,y+3,z,air);
		world.setBlock(x+3,y+3,z-1,air);
		world.setBlock(x+3,y+3,z-2,glass);
		world.setBlock(x+3,y+3,z-3,glass);
		
		world.setBlock(x+4,y+3,z+2,glass);
		world.setBlock(x+4,y+3,z+1,glass);
		world.setBlock(x+4,y+3,z,glass);
		world.setBlock(x+4,y+3,z-1,glass);
		world.setBlock(x+4,y+3,z-2,glass);
		
		/************************ Layer 4 ************************/
		world.setBlock(x-3,y+4,z+1,glass);
		world.setBlock(x-3,y+4,z,glass);
		world.setBlock(x-3,y+4,z-1,glass);
		
		world.setBlock(x-2,y+4,z+2,glass);
		world.setBlock(x-2,y+4,z+1,air);
		world.setBlock(x-2,y+4,z,air);
		world.setBlock(x-2,y+4,z-1,air);
		world.setBlock(x-2,y+4,z-2,glass);

		world.setBlock(x-1,y+4,z+3,glass);
		world.setBlock(x-1,y+4,z+2,air);
		world.setBlock(x-1,y+4,z+1,air);
		world.setBlock(x-1,y+4,z,air);
		world.setBlock(x-1,y+4,z-1,air);
		world.setBlock(x-1,y+4,z-2,air);
		world.setBlock(x-1,y+4,z-3,glass);

		world.setBlock(x,y+4,z+3,glass);
		world.setBlock(x,y+4,z+2,air);
		world.setBlock(x,y+4,z+1,air);
		world.setBlock(x,y+4,z,air);
		world.setBlock(x,y+4,z-1,air);
		world.setBlock(x,y+4,z-2,air);
		world.setBlock(x,y+4,z-3,glass);

		world.setBlock(x+1,y+4,z+3,glass);
		world.setBlock(x+1,y+4,z+2,air);
		world.setBlock(x+1,y+4,z+1,air);
		world.setBlock(x+1,y+4,z,air);
		world.setBlock(x+1,y+4,z-1,air);
		world.setBlock(x+1,y+4,z-2,air);
		world.setBlock(x+1,y+4,z-3,glass);

		world.setBlock(x+2,y+4,z+2,glass);
		world.setBlock(x+2,y+4,z+1,air);
		world.setBlock(x+2,y+4,z,air);
		world.setBlock(x+2,y+4,z-1,air);
		world.setBlock(x+2,y+4,z-2,glass);

		world.setBlock(x+3,y+4,z+1,glass);
		world.setBlock(x+3,y+4,z,glass);
		world.setBlock(x+3,y+4,z-1,glass);
		
		/************************ Layer 5 ************************/
		world.setBlock(x-2,y+5,z+1,glass);
		world.setBlock(x-2,y+5,z,glass);
		world.setBlock(x-2,y+5,z-1,glass);

		world.setBlock(x-1,y+5,z+2,glass);
		world.setBlock(x-1,y+5,z+1,air);
		world.setBlock(x-1,y+5,z,air);
		world.setBlock(x-1,y+5,z-1,air);
		world.setBlock(x-1,y+5,z-2,glass);

		world.setBlock(x,y+5,z+2,glass);
		world.setBlock(x,y+5,z+1,air);
		world.setBlock(x,y+5,z,air);
		world.setBlock(x,y+5,z-1,air);
		world.setBlock(x,y+5,z-2,glass);

		world.setBlock(x+1,y+5,z+2,glass);
		world.setBlock(x+1,y+5,z+1,air);
		world.setBlock(x+1,y+5,z,air);
		world.setBlock(x+1,y+5,z-1,air);
		world.setBlock(x+1,y+5,z-2,glass);

		world.setBlock(x+2,y+5,z+1,glass);
		world.setBlock(x+2,y+5,z,glass);
		world.setBlock(x+2,y+5,z-1,glass);
		
		/************************ Layer 5 (2) ************************/
		world.setBlock(x-1,y+5,z+1,glass);
		world.setBlock(x-1,y+5,z,glass);
		world.setBlock(x-1,y+5,z-1,glass);

		world.setBlock(x,y+5,z+1,glass);
		world.setBlock(x,y+5,z,glass);
		world.setBlock(x,y+5,z-1,glass);

		world.setBlock(x+1,y+5,z+1,glass);
		world.setBlock(x+1,y+5,z,glass);
		world.setBlock(x+1,y+5,z-1,glass);
	}
}