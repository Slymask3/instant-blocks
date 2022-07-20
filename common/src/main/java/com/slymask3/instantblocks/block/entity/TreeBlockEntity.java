package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class TreeBlockEntity extends InstantBlockEntity {
	public int type;
	public boolean hollowLogs, hollowLeaves, airInside;

	public TreeBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.TREE, pos, state);
		this.type = 0;
		this.hollowLogs = true;
		this.hollowLeaves = true;
		this.airInside = true;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.type = nbt.getInt("Type");
		this.hollowLogs = nbt.getBoolean("HollowLogs");
		this.hollowLeaves = nbt.getBoolean("HollowLeaves");
		this.airInside = nbt.getBoolean("AirInside");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("Type", this.type);
		nbt.putBoolean("HollowLogs", this.hollowLogs);
		nbt.putBoolean("HollowLeaves", this.hollowLeaves);
		nbt.putBoolean("AirInside", this.airInside);
	}

	public void update(int type, boolean hollowLogs, boolean hollowLeaves, boolean airInside) {
		this.type = type;
		this.hollowLogs = hollowLogs;
		this.hollowLeaves = hollowLeaves;
		this.airInside = airInside;
		this.markUpdated();
	}
}