package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.init.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class TreeBlockEntity extends InstantBlockEntity {
	public int type;
	public boolean fullLog;
	public boolean fullLeaves;

	public TreeBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.TREE.get(), pos, state);
		this.type = 0;
		this.fullLog = false;
		this.fullLeaves = false;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		type = nbt.getInt("Type");
		fullLog = nbt.getBoolean("FullLog");
		fullLeaves = nbt.getBoolean("FullLeaves");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Type", type);
		nbt.putBoolean("FullLog", fullLog);
		nbt.putBoolean("FullLeaves", fullLeaves);
	}
}