package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstantLiquid;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockInstantSuction extends BlockInstantLiquid {
    public BlockInstantSuction() {
        super(Names.Blocks.IB_SUCTION, Material.rock, Block.soundTypeStone, 1.5F, null, Blocks.air);
        setTextures(Textures.Suction.SIDE);
        setBlockTextureName(Textures.Suction.SIDE);
		setErrorMessage(Strings.ERROR_SUCTION.replace("%i%",String.valueOf(Config.MAX_FILL)));
		this.create = Strings.CREATE_SUCTION;
		this.create1 = Strings.CREATE_SUCTION_1;
        this.isSuction = true;
    }
}