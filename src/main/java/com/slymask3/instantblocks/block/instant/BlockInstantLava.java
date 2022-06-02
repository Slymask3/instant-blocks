package com.slymask3.instantblocks.block.instant;

import com.slymask3.instantblocks.block.BlockInstantLiquid;
import com.slymask3.instantblocks.handler.Config;
import com.slymask3.instantblocks.reference.Strings;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.util.ForgeSoundType;

public class BlockInstantLava extends BlockInstantLiquid {
	public BlockInstantLava() {
		super(Block.Properties.of(Material.LAVA)
				.strength(1.5F, 2000F)
				.sound(new ForgeSoundType(1.0F,1.0F, () -> SoundEvents.BUCKET_FILL_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA,() -> SoundEvents.BUCKET_EMPTY_LAVA))
		, Blocks.AIR, Blocks.LAVA);
		setErrorMessage(Strings.ERROR_LAVA_MAX.replace("%i%",String.valueOf(Config.Common.MAX_LIQUID.get())));
		this.create = Strings.CREATE_LAVA;
		this.create1 = Strings.CREATE_LAVA_1;
		this.particle = "largesmoke";
	}

//	public void onEntityCollidedWithBlock(Level world, int x, int y, int z, Entity entity) {
//        entity.attackEntityFrom(DamageSource.lava, 1);
//        entity.setFire(5);
//    }
}