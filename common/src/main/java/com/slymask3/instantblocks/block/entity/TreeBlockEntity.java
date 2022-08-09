package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.config.entry.HugeTree;
import com.slymask3.instantblocks.core.ModTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class TreeBlockEntity extends InstantBlockEntity {
	public HugeTree tree;
	public boolean hollowLogs, hollowLeaves, airInside;
	public int hugeTreesIndex;

	public TreeBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.TREE, pos, state);
		this.tree = new HugeTree();
		this.hollowLogs = true;
		this.hollowLeaves = true;
		this.airInside = true;
		this.hugeTreesIndex = -1;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		String name = nbt.getString("TreeName");
		int type = nbt.getInt("TreeType");
		String logs = nbt.getString("TreeLogs");
		String leaves = nbt.getString("TreeLeaves");
		this.tree = new HugeTree(name,HugeTree.Type.values()[type],logs,leaves);
		this.hollowLogs = nbt.getBoolean("HollowLogs");
		this.hollowLeaves = nbt.getBoolean("HollowLeaves");
		this.airInside = nbt.getBoolean("AirInside");
		this.hugeTreesIndex = nbt.getInt("HugeTreeIndex");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putString("TreeName", this.tree.name);
		nbt.putInt("TreeType", this.tree.type.ordinal());
		nbt.putString("TreeLogs", this.tree.logs);
		nbt.putString("TreeLeaves", this.tree.leaves);
		nbt.putBoolean("HollowLogs", this.hollowLogs);
		nbt.putBoolean("HollowLeaves", this.hollowLeaves);
		nbt.putBoolean("AirInside", this.airInside);
		nbt.putInt("HugeTreeIndex", this.hugeTreesIndex);
	}

	public void update(HugeTree tree, boolean hollowLogs, boolean hollowLeaves, boolean airInside, int hugeTreesIndex) {
		this.tree = tree;
		this.hollowLogs = hollowLogs;
		this.hollowLeaves = hollowLeaves;
		this.airInside = airInside;
		this.hugeTreesIndex = hugeTreesIndex;
		this.markUpdated();
	}
}