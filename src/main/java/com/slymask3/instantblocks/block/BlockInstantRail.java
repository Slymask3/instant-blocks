package com.slymask3.instantblocks.block;

import com.slymask3.instantblocks.init.ModBlocks;
import com.slymask3.instantblocks.reference.Names;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInstantRail extends BlockDirectionalIB {

	public BlockInstantRail() {
		super(ModBlocks.ibRail, Names.Blocks.IB_RAIL, Material.rock, Block.soundTypeStone, 1.5F);
	}

}
