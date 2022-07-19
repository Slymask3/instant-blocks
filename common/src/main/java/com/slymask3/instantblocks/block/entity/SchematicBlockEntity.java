package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class SchematicBlockEntity extends InstantBlockEntity {
	public String schematic;
	public boolean center;
	public boolean ignoreAir;

	public SchematicBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.SCHEMATIC, pos, state);
		this.schematic = "";
		this.center = false;
		this.ignoreAir = false;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.schematic = nbt.getString("Schematic");
		this.center = nbt.getBoolean("Center");
		this.ignoreAir = nbt.getBoolean("IgnoreAir");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putString("Schematic", this.schematic);
		nbt.putBoolean("Center", this.center);
		nbt.putBoolean("IgnoreAir", this.ignoreAir);
	}

	public void update(String schematic, boolean center, boolean ignoreAir) {
		this.schematic = schematic;
		this.center = center;
		this.ignoreAir = ignoreAir;
		this.markUpdated();
	}
}