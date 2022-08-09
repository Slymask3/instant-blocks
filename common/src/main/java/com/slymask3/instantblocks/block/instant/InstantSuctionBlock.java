package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.InstantLiquidBlock;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class InstantSuctionBlock extends InstantLiquidBlock {
    public InstantSuctionBlock() {
        super(Block.Properties.of(Material.METAL)
                .strength(1.5F)
                .sound(SoundType.METAL)
        , null, Blocks.AIR);
        this.setErrorMessage(Strings.ERROR_SUCTION);
        this.setCreateMessages(Strings.CREATE_SUCTION,Strings.CREATE_SUCTION_1);
    }

    public boolean isEnabled() {
        return Common.CONFIG.ENABLE_SUCTION();
    }
}