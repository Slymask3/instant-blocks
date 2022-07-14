package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.ModTiles;
import com.slymask3.instantblocks.util.SchematicHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class SchematicBlockEntity extends InstantBlockEntity {
	public ArrayList<String> schematics;
	public String schematic;
	public boolean center;
	public boolean ignoreAir;

	public SchematicBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.SCHEMATIC, pos, state);
		this.schematics = SchematicHelper.getSchematics();
		this.schematic = "";
		this.center = false;
		this.ignoreAir = false;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.schematics = new ArrayList<>();
		CompoundTag list = nbt.getCompound("List");
		for(String key : list.getAllKeys()) {
			this.schematics.add(list.getString(key));
		}
		this.schematic = nbt.getString("Schematic");
		this.center = nbt.getBoolean("Center");
		this.ignoreAir = nbt.getBoolean("IgnoreAir");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		CompoundTag list = new CompoundTag();
		for(int i=0; i<this.schematics.size(); i++) {
			list.putString(String.valueOf(i),this.schematics.get(i));
		}
		nbt.put("List", list);
		nbt.putString("Schematic", this.schematic);
		nbt.putBoolean("Center", this.center);
		nbt.putBoolean("IgnoreAir", this.ignoreAir);
	}

	public void updateSchematics() {
		this.schematics = SchematicHelper.getSchematics();
		this.saveWithFullMetadata();
		getLevel().sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(), Block.UPDATE_ALL_IMMEDIATE);
	}
}