package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class ColorBlockEntity extends InstantBlockEntity {
	public int color;

	public ColorBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.COLOR, pos, state);
		this.color = 0x00FFFFFF;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.color = nbt.getInt("Color");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Color",this.color);
	}
}