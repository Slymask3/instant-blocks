package com.slymask3.instantblocks.block.instant;

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

public class BlockInstantGlassDome extends BlockIB {
	
    public BlockInstantGlassDome() {
        super(ModBlocks.ibGlassDome, Names.Blocks.IB_GLASS_DOME, Material.glass, Block.soundTypeGlass, 0.5F);
        setTextures(Blocks.stone, Blocks.glass, Textures.GlassDome.SIDE);
        setTextureBooleans(false, false, true, true, true, true);
        setCreateMsg(Strings.domeCreate);
        setBlockTextureName(Textures.GlassDome.SIDE);
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
    
	public void build(World world, int x, int y, int z) {
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