package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstant;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockInstantGlassDome extends BlockInstant {
    public BlockInstantGlassDome() {
        super(Names.Blocks.IB_GLASS_DOME, Material.glass, Block.soundTypeGlass, 0.5F);
        setTextures(Blocks.stone, Blocks.glass, Textures.GlassDome.SIDE);
        setTextureBooleans(false, false, true, true, true, true);
        setCreateMessage(Strings.CREATE_DOME);
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

	public void build(World world, int x, int y, int z, EntityPlayer player) {
		Block glass = Blocks.glass;
		Block stone = Blocks.stone;
		Block torch = Blocks.torch;
		Block air = Blocks.air;
		
		/************************ Layer 0 ************************/
		BuildHelper.setBlock(world,x-4,y,z+2,stone);
		BuildHelper.setBlock(world,x-4,y,z+1,stone);
		BuildHelper.setBlock(world,x-4,y,z,stone);
		BuildHelper.setBlock(world,x-4,y,z-1,stone);
		BuildHelper.setBlock(world,x-4,y,z-2,stone);

		BuildHelper.setBlock(world,x-3,y,z+3,stone);
		BuildHelper.setBlock(world,x-3,y,z+2,stone);
		BuildHelper.setBlock(world,x-3,y,z+1,stone);
		BuildHelper.setBlock(world,x-3,y,z,stone);
		BuildHelper.setBlock(world,x-3,y,z-1,stone);
		BuildHelper.setBlock(world,x-3,y,z-2,stone);
		BuildHelper.setBlock(world,x-3,y,z-3,stone);

		BuildHelper.setBlock(world,x-2,y,z+4,stone);
		BuildHelper.setBlock(world,x-2,y,z+3,stone);
		BuildHelper.setBlock(world,x-2,y,z+2,stone);
		BuildHelper.setBlock(world,x-2,y,z+1,stone);
		BuildHelper.setBlock(world,x-2,y,z,stone);
		BuildHelper.setBlock(world,x-2,y,z-1,stone);
		BuildHelper.setBlock(world,x-2,y,z-2,stone);
		BuildHelper.setBlock(world,x-2,y,z-3,stone);
		BuildHelper.setBlock(world,x-2,y,z-4,stone);

		BuildHelper.setBlock(world,x-1,y,z+4,stone);
		BuildHelper.setBlock(world,x-1,y,z+3,stone);
		BuildHelper.setBlock(world,x-1,y,z+2,stone);
		BuildHelper.setBlock(world,x-1,y,z+1,stone);
		BuildHelper.setBlock(world,x-1,y,z,stone);
		BuildHelper.setBlock(world,x-1,y,z-1,stone);
		BuildHelper.setBlock(world,x-1,y,z-2,stone);
		BuildHelper.setBlock(world,x-1,y,z-3,stone);
		BuildHelper.setBlock(world,x-1,y,z-4,stone);

		BuildHelper.setBlock(world,x,y,z+4,stone);
		BuildHelper.setBlock(world,x,y,z+3,stone);
		BuildHelper.setBlock(world,x,y,z+2,stone);
		BuildHelper.setBlock(world,x,y,z+1,stone);
		BuildHelper.setBlock(world,x,y,z,stone); //0, 0, 0
		BuildHelper.setBlock(world,x,y,z-1,stone);
		BuildHelper.setBlock(world,x,y,z-2,stone);
		BuildHelper.setBlock(world,x,y,z-3,stone);
		BuildHelper.setBlock(world,x,y,z-4,stone);

		BuildHelper.setBlock(world,x+1,y,z+4,stone);
		BuildHelper.setBlock(world,x+1,y,z+3,stone);
		BuildHelper.setBlock(world,x+1,y,z+2,stone);
		BuildHelper.setBlock(world,x+1,y,z+1,stone);
		BuildHelper.setBlock(world,x+1,y,z,stone);
		BuildHelper.setBlock(world,x+1,y,z-1,stone);
		BuildHelper.setBlock(world,x+1,y,z-2,stone);
		BuildHelper.setBlock(world,x+1,y,z-3,stone);
		BuildHelper.setBlock(world,x+1,y,z-4,stone);

		BuildHelper.setBlock(world,x+2,y,z+4,stone);
		BuildHelper.setBlock(world,x+2,y,z+3,stone);
		BuildHelper.setBlock(world,x+2,y,z+2,stone);
		BuildHelper.setBlock(world,x+2,y,z+1,stone);
		BuildHelper.setBlock(world,x+2,y,z,stone);
		BuildHelper.setBlock(world,x+2,y,z-1,stone);
		BuildHelper.setBlock(world,x+2,y,z-2,stone);
		BuildHelper.setBlock(world,x+2,y,z-3,stone);
		BuildHelper.setBlock(world,x+2,y,z-4,stone);

		BuildHelper.setBlock(world,x+3,y,z+3,stone);
		BuildHelper.setBlock(world,x+3,y,z+2,stone);
		BuildHelper.setBlock(world,x+3,y,z+1,stone);
		BuildHelper.setBlock(world,x+3,y,z,stone);
		BuildHelper.setBlock(world,x+3,y,z-1,stone);
		BuildHelper.setBlock(world,x+3,y,z-2,stone);
		BuildHelper.setBlock(world,x+3,y,z-3,stone);
		
		BuildHelper.setBlock(world,x+4,y,z+2,stone);
		BuildHelper.setBlock(world,x+4,y,z+1,stone);
		BuildHelper.setBlock(world,x+4,y,z,stone);
		BuildHelper.setBlock(world,x+4,y,z-1,stone);
		BuildHelper.setBlock(world,x+4,y,z-2,stone);
		
		/************************ Layer 1 ************************/
		BuildHelper.setBlock(world,x-4,y+1,z+2,glass);
		BuildHelper.setBlock(world,x-4,y+1,z+1,glass);
		BuildHelper.setBlock(world,x-4,y+1,z,glass);
		BuildHelper.setBlock(world,x-4,y+1,z-1,glass);
		BuildHelper.setBlock(world,x-4,y+1,z-2,glass);

		BuildHelper.setBlock(world,x-3,y+1,z+3,glass);
		BuildHelper.setBlock(world,x-3,y+1,z+2,glass);
		BuildHelper.setBlock(world,x-3,y+1,z+1,air);
		BuildHelper.setBlock(world,x-3,y+1,z,torch);
		BuildHelper.setBlock(world,x-3,y+1,z-1,air);
		BuildHelper.setBlock(world,x-3,y+1,z-2,glass);
		BuildHelper.setBlock(world,x-3,y+1,z-3,glass);

		BuildHelper.setBlock(world,x-2,y+1,z+4,glass);
		BuildHelper.setBlock(world,x-2,y+1,z+3,glass);
		BuildHelper.setBlock(world,x-2,y+1,z+2,air);
		BuildHelper.setBlock(world,x-2,y+1,z+1,air);
		BuildHelper.setBlock(world,x-2,y+1,z,air);
		BuildHelper.setBlock(world,x-2,y+1,z-1,air);
		BuildHelper.setBlock(world,x-2,y+1,z-2,air);
		BuildHelper.setBlock(world,x-2,y+1,z-3,glass);
		BuildHelper.setBlock(world,x-2,y+1,z-4,glass);

		BuildHelper.setBlock(world,x-1,y+1,z+4,glass);
		BuildHelper.setBlock(world,x-1,y+1,z+3,air);
		BuildHelper.setBlock(world,x-1,y+1,z+2,air);
		BuildHelper.setBlock(world,x-1,y+1,z+1,air);
		BuildHelper.setBlock(world,x-1,y+1,z,air);
		BuildHelper.setBlock(world,x-1,y+1,z-1,air);
		BuildHelper.setBlock(world,x-1,y+1,z-2,air);
		BuildHelper.setBlock(world,x-1,y+1,z-3,air);
		BuildHelper.setBlock(world,x-1,y+1,z-4,glass);

		BuildHelper.setBlock(world,x,y+1,z+4,glass);
		BuildHelper.setBlock(world,x,y+1,z+3,air);
		BuildHelper.setBlock(world,x,y+1,z+2,torch);
		BuildHelper.setBlock(world,x,y+1,z+1,air);
		BuildHelper.setBlock(world,x,y+1,z,air);
		BuildHelper.setBlock(world,x,y+1,z-1,air);
		BuildHelper.setBlock(world,x,y+1,z-2,torch);
		BuildHelper.setBlock(world,x,y+1,z-3,air);
		BuildHelper.setBlock(world,x,y+1,z-4,glass);

		BuildHelper.setBlock(world,x+1,y+1,z+4,glass);
		BuildHelper.setBlock(world,x+1,y+1,z+3,air);
		BuildHelper.setBlock(world,x+1,y+1,z+2,air);
		BuildHelper.setBlock(world,x+1,y+1,z+1,air);
		BuildHelper.setBlock(world,x+1,y+1,z,air);
		BuildHelper.setBlock(world,x+1,y+1,z-1,air);
		BuildHelper.setBlock(world,x+1,y+1,z-2,air);
		BuildHelper.setBlock(world,x+1,y+1,z-3,air);
		BuildHelper.setBlock(world,x+1,y+1,z-4,glass);

		BuildHelper.setBlock(world,x+2,y+1,z+4,glass);
		BuildHelper.setBlock(world,x+2,y+1,z+3,glass);
		BuildHelper.setBlock(world,x+2,y+1,z+2,air);
		BuildHelper.setBlock(world,x+2,y+1,z+1,air);
		BuildHelper.setBlock(world,x+2,y+1,z,air);
		BuildHelper.setBlock(world,x+2,y+1,z-1,air);
		BuildHelper.setBlock(world,x+2,y+1,z-2,air);
		BuildHelper.setBlock(world,x+2,y+1,z-3,glass);
		BuildHelper.setBlock(world,x+2,y+1,z-4,glass);

		BuildHelper.setBlock(world,x+3,y+1,z+3,glass);
		BuildHelper.setBlock(world,x+3,y+1,z+2,glass);
		BuildHelper.setBlock(world,x+3,y+1,z+1,air);
		BuildHelper.setBlock(world,x+3,y+1,z,torch);
		BuildHelper.setBlock(world,x+3,y+1,z-1,air);
		BuildHelper.setBlock(world,x+3,y+1,z-2,glass);
		BuildHelper.setBlock(world,x+3,y+1,z-3,glass);
		
		BuildHelper.setBlock(world,x+4,y+1,z+2,glass);
		BuildHelper.setBlock(world,x+4,y+1,z+1,glass);
		BuildHelper.setBlock(world,x+4,y+1,z,glass);
		BuildHelper.setBlock(world,x+4,y+1,z-1,glass);
		BuildHelper.setBlock(world,x+4,y+1,z-2,glass);
		
		/************************ Layer 2 ************************/
		BuildHelper.setBlock(world,x-4,y+2,z+2,glass);
		BuildHelper.setBlock(world,x-4,y+2,z+1,glass);
		BuildHelper.setBlock(world,x-4,y+2,z,glass);
		BuildHelper.setBlock(world,x-4,y+2,z-1,glass);
		BuildHelper.setBlock(world,x-4,y+2,z-2,glass);

		BuildHelper.setBlock(world,x-3,y+2,z+3,glass);
		BuildHelper.setBlock(world,x-3,y+2,z+2,glass);
		BuildHelper.setBlock(world,x-3,y+2,z+1,air);
		BuildHelper.setBlock(world,x-3,y+2,z,air);
		BuildHelper.setBlock(world,x-3,y+2,z-1,air);
		BuildHelper.setBlock(world,x-3,y+2,z-2,glass);
		BuildHelper.setBlock(world,x-3,y+2,z-3,glass);

		BuildHelper.setBlock(world,x-2,y+2,z+4,glass);
		BuildHelper.setBlock(world,x-2,y+2,z+3,glass);
		BuildHelper.setBlock(world,x-2,y+2,z+2,air);
		BuildHelper.setBlock(world,x-2,y+2,z+1,air);
		BuildHelper.setBlock(world,x-2,y+2,z,air);
		BuildHelper.setBlock(world,x-2,y+2,z-1,air);
		BuildHelper.setBlock(world,x-2,y+2,z-2,air);
		BuildHelper.setBlock(world,x-2,y+2,z-3,glass);
		BuildHelper.setBlock(world,x-2,y+2,z-4,glass);

		BuildHelper.setBlock(world,x-1,y+2,z+4,glass);
		BuildHelper.setBlock(world,x-1,y+2,z+3,air);
		BuildHelper.setBlock(world,x-1,y+2,z+2,air);
		BuildHelper.setBlock(world,x-1,y+2,z+1,air);
		BuildHelper.setBlock(world,x-1,y+2,z,air);
		BuildHelper.setBlock(world,x-1,y+2,z-1,air);
		BuildHelper.setBlock(world,x-1,y+2,z-2,air);
		BuildHelper.setBlock(world,x-1,y+2,z-3,air);
		BuildHelper.setBlock(world,x-1,y+2,z-4,glass);

		BuildHelper.setBlock(world,x,y+2,z+4,glass);
		BuildHelper.setBlock(world,x,y+2,z+3,air);
		BuildHelper.setBlock(world,x,y+2,z+2,air);
		BuildHelper.setBlock(world,x,y+2,z+1,air);
		BuildHelper.setBlock(world,x,y+2,z,air);
		BuildHelper.setBlock(world,x,y+2,z-1,air);
		BuildHelper.setBlock(world,x,y+2,z-2,air);
		BuildHelper.setBlock(world,x,y+2,z-3,air);
		BuildHelper.setBlock(world,x,y+2,z-4,glass);

		BuildHelper.setBlock(world,x+1,y+2,z+4,glass);
		BuildHelper.setBlock(world,x+1,y+2,z+3,air);
		BuildHelper.setBlock(world,x+1,y+2,z+2,air);
		BuildHelper.setBlock(world,x+1,y+2,z+1,air);
		BuildHelper.setBlock(world,x+1,y+2,z,air);
		BuildHelper.setBlock(world,x+1,y+2,z-1,air);
		BuildHelper.setBlock(world,x+1,y+2,z-2,air);
		BuildHelper.setBlock(world,x+1,y+2,z-3,air);
		BuildHelper.setBlock(world,x+1,y+2,z-4,glass);

		BuildHelper.setBlock(world,x+2,y+2,z+4,glass);
		BuildHelper.setBlock(world,x+2,y+2,z+3,glass);
		BuildHelper.setBlock(world,x+2,y+2,z+2,air);
		BuildHelper.setBlock(world,x+2,y+2,z+1,air);
		BuildHelper.setBlock(world,x+2,y+2,z,air);
		BuildHelper.setBlock(world,x+2,y+2,z-1,air);
		BuildHelper.setBlock(world,x+2,y+2,z-2,air);
		BuildHelper.setBlock(world,x+2,y+2,z-3,glass);
		BuildHelper.setBlock(world,x+2,y+2,z-4,glass);

		BuildHelper.setBlock(world,x+3,y+2,z+3,glass);
		BuildHelper.setBlock(world,x+3,y+2,z+2,glass);
		BuildHelper.setBlock(world,x+3,y+2,z+1,air);
		BuildHelper.setBlock(world,x+3,y+2,z,air);
		BuildHelper.setBlock(world,x+3,y+2,z-1,air);
		BuildHelper.setBlock(world,x+3,y+2,z-2,glass);
		BuildHelper.setBlock(world,x+3,y+2,z-3,glass);
		
		BuildHelper.setBlock(world,x+4,y+2,z+2,glass);
		BuildHelper.setBlock(world,x+4,y+2,z+1,glass);
		BuildHelper.setBlock(world,x+4,y+2,z,glass);
		BuildHelper.setBlock(world,x+4,y+2,z-1,glass);
		BuildHelper.setBlock(world,x+4,y+2,z-2,glass);
		
		/************************ Layer 3 ************************/
		BuildHelper.setBlock(world,x-4,y+3,z+2,glass);
		BuildHelper.setBlock(world,x-4,y+3,z+1,glass);
		BuildHelper.setBlock(world,x-4,y+3,z,glass);
		BuildHelper.setBlock(world,x-4,y+3,z-1,glass);
		BuildHelper.setBlock(world,x-4,y+3,z-2,glass);

		BuildHelper.setBlock(world,x-3,y+3,z+3,glass);
		BuildHelper.setBlock(world,x-3,y+3,z+2,glass);
		BuildHelper.setBlock(world,x-3,y+3,z+1,air);
		BuildHelper.setBlock(world,x-3,y+3,z,air);
		BuildHelper.setBlock(world,x-3,y+3,z-1,air);
		BuildHelper.setBlock(world,x-3,y+3,z-2,glass);
		BuildHelper.setBlock(world,x-3,y+3,z-3,glass);

		BuildHelper.setBlock(world,x-2,y+3,z+4,glass);
		BuildHelper.setBlock(world,x-2,y+3,z+3,glass);
		BuildHelper.setBlock(world,x-2,y+3,z+2,air);
		BuildHelper.setBlock(world,x-2,y+3,z+1,air);
		BuildHelper.setBlock(world,x-2,y+3,z,air);
		BuildHelper.setBlock(world,x-2,y+3,z-1,air);
		BuildHelper.setBlock(world,x-2,y+3,z-2,air);
		BuildHelper.setBlock(world,x-2,y+3,z-3,glass);
		BuildHelper.setBlock(world,x-2,y+3,z-4,glass);

		BuildHelper.setBlock(world,x-1,y+3,z+4,glass);
		BuildHelper.setBlock(world,x-1,y+3,z+3,air);
		BuildHelper.setBlock(world,x-1,y+3,z+2,air);
		BuildHelper.setBlock(world,x-1,y+3,z+1,air);
		BuildHelper.setBlock(world,x-1,y+3,z,air);
		BuildHelper.setBlock(world,x-1,y+3,z-1,air);
		BuildHelper.setBlock(world,x-1,y+3,z-2,air);
		BuildHelper.setBlock(world,x-1,y+3,z-3,air);
		BuildHelper.setBlock(world,x-1,y+3,z-4,glass);

		BuildHelper.setBlock(world,x,y+3,z+4,glass);
		BuildHelper.setBlock(world,x,y+3,z+3,air);
		BuildHelper.setBlock(world,x,y+3,z+2,air);
		BuildHelper.setBlock(world,x,y+3,z+1,air);
		BuildHelper.setBlock(world,x,y+3,z,air);
		BuildHelper.setBlock(world,x,y+3,z-1,air);
		BuildHelper.setBlock(world,x,y+3,z-2,air);
		BuildHelper.setBlock(world,x,y+3,z-3,air);
		BuildHelper.setBlock(world,x,y+3,z-4,glass);

		BuildHelper.setBlock(world,x+1,y+3,z+4,glass);
		BuildHelper.setBlock(world,x+1,y+3,z+3,air);
		BuildHelper.setBlock(world,x+1,y+3,z+2,air);
		BuildHelper.setBlock(world,x+1,y+3,z+1,air);
		BuildHelper.setBlock(world,x+1,y+3,z,air);
		BuildHelper.setBlock(world,x+1,y+3,z-1,air);
		BuildHelper.setBlock(world,x+1,y+3,z-2,air);
		BuildHelper.setBlock(world,x+1,y+3,z-3,air);
		BuildHelper.setBlock(world,x+1,y+3,z-4,glass);

		BuildHelper.setBlock(world,x+2,y+3,z+4,glass);
		BuildHelper.setBlock(world,x+2,y+3,z+3,glass);
		BuildHelper.setBlock(world,x+2,y+3,z+2,air);
		BuildHelper.setBlock(world,x+2,y+3,z+1,air);
		BuildHelper.setBlock(world,x+2,y+3,z,air);
		BuildHelper.setBlock(world,x+2,y+3,z-1,air);
		BuildHelper.setBlock(world,x+2,y+3,z-2,air);
		BuildHelper.setBlock(world,x+2,y+3,z-3,glass);
		BuildHelper.setBlock(world,x+2,y+3,z-4,glass);

		BuildHelper.setBlock(world,x+3,y+3,z+3,glass);
		BuildHelper.setBlock(world,x+3,y+3,z+2,glass);
		BuildHelper.setBlock(world,x+3,y+3,z+1,air);
		BuildHelper.setBlock(world,x+3,y+3,z,air);
		BuildHelper.setBlock(world,x+3,y+3,z-1,air);
		BuildHelper.setBlock(world,x+3,y+3,z-2,glass);
		BuildHelper.setBlock(world,x+3,y+3,z-3,glass);
		
		BuildHelper.setBlock(world,x+4,y+3,z+2,glass);
		BuildHelper.setBlock(world,x+4,y+3,z+1,glass);
		BuildHelper.setBlock(world,x+4,y+3,z,glass);
		BuildHelper.setBlock(world,x+4,y+3,z-1,glass);
		BuildHelper.setBlock(world,x+4,y+3,z-2,glass);
		
		/************************ Layer 4 ************************/
		BuildHelper.setBlock(world,x-3,y+4,z+1,glass);
		BuildHelper.setBlock(world,x-3,y+4,z,glass);
		BuildHelper.setBlock(world,x-3,y+4,z-1,glass);
		
		BuildHelper.setBlock(world,x-2,y+4,z+2,glass);
		BuildHelper.setBlock(world,x-2,y+4,z+1,air);
		BuildHelper.setBlock(world,x-2,y+4,z,air);
		BuildHelper.setBlock(world,x-2,y+4,z-1,air);
		BuildHelper.setBlock(world,x-2,y+4,z-2,glass);

		BuildHelper.setBlock(world,x-1,y+4,z+3,glass);
		BuildHelper.setBlock(world,x-1,y+4,z+2,air);
		BuildHelper.setBlock(world,x-1,y+4,z+1,air);
		BuildHelper.setBlock(world,x-1,y+4,z,air);
		BuildHelper.setBlock(world,x-1,y+4,z-1,air);
		BuildHelper.setBlock(world,x-1,y+4,z-2,air);
		BuildHelper.setBlock(world,x-1,y+4,z-3,glass);

		BuildHelper.setBlock(world,x,y+4,z+3,glass);
		BuildHelper.setBlock(world,x,y+4,z+2,air);
		BuildHelper.setBlock(world,x,y+4,z+1,air);
		BuildHelper.setBlock(world,x,y+4,z,air);
		BuildHelper.setBlock(world,x,y+4,z-1,air);
		BuildHelper.setBlock(world,x,y+4,z-2,air);
		BuildHelper.setBlock(world,x,y+4,z-3,glass);

		BuildHelper.setBlock(world,x+1,y+4,z+3,glass);
		BuildHelper.setBlock(world,x+1,y+4,z+2,air);
		BuildHelper.setBlock(world,x+1,y+4,z+1,air);
		BuildHelper.setBlock(world,x+1,y+4,z,air);
		BuildHelper.setBlock(world,x+1,y+4,z-1,air);
		BuildHelper.setBlock(world,x+1,y+4,z-2,air);
		BuildHelper.setBlock(world,x+1,y+4,z-3,glass);

		BuildHelper.setBlock(world,x+2,y+4,z+2,glass);
		BuildHelper.setBlock(world,x+2,y+4,z+1,air);
		BuildHelper.setBlock(world,x+2,y+4,z,air);
		BuildHelper.setBlock(world,x+2,y+4,z-1,air);
		BuildHelper.setBlock(world,x+2,y+4,z-2,glass);

		BuildHelper.setBlock(world,x+3,y+4,z+1,glass);
		BuildHelper.setBlock(world,x+3,y+4,z,glass);
		BuildHelper.setBlock(world,x+3,y+4,z-1,glass);
		
		/************************ Layer 5 ************************/
		BuildHelper.setBlock(world,x-2,y+5,z+1,glass);
		BuildHelper.setBlock(world,x-2,y+5,z,glass);
		BuildHelper.setBlock(world,x-2,y+5,z-1,glass);

		BuildHelper.setBlock(world,x-1,y+5,z+2,glass);
		BuildHelper.setBlock(world,x-1,y+5,z+1,air);
		BuildHelper.setBlock(world,x-1,y+5,z,air);
		BuildHelper.setBlock(world,x-1,y+5,z-1,air);
		BuildHelper.setBlock(world,x-1,y+5,z-2,glass);

		BuildHelper.setBlock(world,x,y+5,z+2,glass);
		BuildHelper.setBlock(world,x,y+5,z+1,air);
		BuildHelper.setBlock(world,x,y+5,z,air);
		BuildHelper.setBlock(world,x,y+5,z-1,air);
		BuildHelper.setBlock(world,x,y+5,z-2,glass);

		BuildHelper.setBlock(world,x+1,y+5,z+2,glass);
		BuildHelper.setBlock(world,x+1,y+5,z+1,air);
		BuildHelper.setBlock(world,x+1,y+5,z,air);
		BuildHelper.setBlock(world,x+1,y+5,z-1,air);
		BuildHelper.setBlock(world,x+1,y+5,z-2,glass);

		BuildHelper.setBlock(world,x+2,y+5,z+1,glass);
		BuildHelper.setBlock(world,x+2,y+5,z,glass);
		BuildHelper.setBlock(world,x+2,y+5,z-1,glass);
		
		/************************ Layer 5 (2) ************************/
		BuildHelper.setBlock(world,x-1,y+5,z+1,glass);
		BuildHelper.setBlock(world,x-1,y+5,z,glass);
		BuildHelper.setBlock(world,x-1,y+5,z-1,glass);

		BuildHelper.setBlock(world,x,y+5,z+1,glass);
		BuildHelper.setBlock(world,x,y+5,z,glass);
		BuildHelper.setBlock(world,x,y+5,z-1,glass);

		BuildHelper.setBlock(world,x+1,y+5,z+1,glass);
		BuildHelper.setBlock(world,x+1,y+5,z,glass);
		BuildHelper.setBlock(world,x+1,y+5,z-1,glass);
	}
}