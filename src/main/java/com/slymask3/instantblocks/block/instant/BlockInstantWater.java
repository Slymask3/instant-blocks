package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstantLiquid;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Names;
import com.slymask3.instantblocks.reference.Strings;
import com.slymask3.instantblocks.reference.Textures;
import com.slymask3.instantblocks.sound.SoundTypeLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockInstantWater extends BlockInstantLiquid {
    public BlockInstantWater() {
        super(Names.Blocks.IB_WATER, Material.water, new SoundTypeLiquid("random.splash", 0.5F, 1.0F), 0.5F, Blocks.air, Blocks.water);
        setTextures(Blocks.water);
        setTextureBooleans(false, false, false, false, false, false);
        setLightOpacity(3);
        setBlockTextureName(Textures.Water.SIDE);
		setErrorMessage(Strings.ERROR_WATER_MAX.replace("%i%",String.valueOf(Config.MAX_LIQUID)));
		this.create = Strings.CREATE_WATER;
		this.create1 = Strings.CREATE_WATER_1;
        this.particle = "splash";
    }

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.extinguish();
    }
}