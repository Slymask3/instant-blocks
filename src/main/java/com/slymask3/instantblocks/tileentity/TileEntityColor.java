package com.slymask3.instantblocks.tileentity;

import com.slymask3.instantblocks.init.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class TileEntityColor extends TileEntityInstant {
	public int color;

	public TileEntityColor(BlockPos pos, BlockState state) {
		super(ModTiles.COLOR.get(), pos, state);
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