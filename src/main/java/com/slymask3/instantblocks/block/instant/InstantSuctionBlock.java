package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantSuctionBlock extends InstantLiquidBlock {
    public InstantSuctionBlock() {
        super(Block.Properties.of(Material.STONE)
                .strength(1.5F)
                .sound(SoundType.STONE)
        , Config.Common.DISABLE_SUCTION, null, Blocks.AIR);
        setErrorMessage(Strings.ERROR_SUCTION);
        this.create = Strings.CREATE_SUCTION;
		this.create1 = Strings.CREATE_SUCTION_1;
        this.isSuction = true;
    }
}