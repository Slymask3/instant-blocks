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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockInstantLava extends BlockInstantLiquid {
	public BlockInstantLava() {
		super(Names.Blocks.IB_LAVA, Material.lava, new SoundTypeLiquid("random.fizz", 0.5F, 1.0F), 0.5F, Blocks.air, Blocks.lava);
		setTextures(Blocks.lava);
		setTextureBooleans(false, false, false, false, false, false);
		setLightLevel(1.0F);
		setBlockTextureName(Textures.Lava.SIDE);
		setErrorMessage(Strings.ERROR_LAVA_MAX.replace("%i%",String.valueOf(Config.MAX_LIQUID)));
		this.create = Strings.CREATE_LAVA;
		this.create1 = Strings.CREATE_LAVA_1;
		this.particle = "largesmoke";
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        entity.attackEntityFrom(DamageSource.lava, 1);
        entity.setFire(5);
    }
}