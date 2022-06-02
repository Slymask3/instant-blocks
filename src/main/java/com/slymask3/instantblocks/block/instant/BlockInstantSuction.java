package com.slymask3.instantblocks.block.instant;

import com.mojang.math.Vector3f;
import com.slymask3.instantblocks.block.BlockInstantLiquid;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

public class BlockInstantSuction extends BlockInstantLiquid {
    public BlockInstantSuction() {
        super(Block.Properties.of(Material.STONE)
                        .strength(1.5F, 2000F)
                        .sound(SoundType.STONE)
                , null, Blocks.AIR);
        setErrorMessage(Strings.ERROR_SUCTION.replace("%i%",String.valueOf(Config.Common.MAX_FILL.get())));
		this.create = Strings.CREATE_SUCTION;
		this.create1 = Strings.CREATE_SUCTION_1;
        this.isSuction = true;
    }
}