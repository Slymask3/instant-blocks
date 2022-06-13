package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.init.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class SchematicBlockEntity extends InstantBlockEntity {
	public String schematic;
	public boolean center;

	public SchematicBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.SCHEMATIC.get(), pos, state);
		this.schematic = "";
		this.center = false;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		schematic = nbt.getString("Schematic");
		center = nbt.getBoolean("Center");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putString("Schematic", schematic);
		nbt.putBoolean("Center", center);
	}
}