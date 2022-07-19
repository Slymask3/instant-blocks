package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class TreeBlockEntity extends InstantBlockEntity {
	public int type;
	public boolean fullLog, fullLeaves, air;

	public TreeBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.TREE, pos, state);
		this.type = 0;
		this.fullLog = true;
		this.fullLeaves = true;
		this.air = true;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		type = nbt.getInt("Type");
		fullLog = nbt.getBoolean("FullLog");
		fullLeaves = nbt.getBoolean("FullLeaves");
		air = nbt.getBoolean("Air");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Type", type);
		nbt.putBoolean("FullLog", fullLog);
		nbt.putBoolean("FullLeaves", fullLeaves);
		nbt.putBoolean("Air", air);
	}

	public void update(int type, boolean fullLog, boolean fullLeaves, boolean air) {
		this.type = type;
		this.fullLog = fullLog;
		this.fullLeaves = fullLeaves;
		this.air = air;
		this.markUpdated();
	}
}