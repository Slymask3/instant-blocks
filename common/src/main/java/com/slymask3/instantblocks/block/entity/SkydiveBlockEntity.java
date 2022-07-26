package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.config.IConfig;
import com.slymask3.instantblocks.core.ModTiles;
import com.slymask3.instantblocks.util.ColorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class SkydiveBlockEntity extends InstantBlockEntity {
	public String[] color = new String[11];
	public int[] colorCode = new int[11];
	public boolean teleport;
	public int radius;
	public int colorSetsIndex;

	public SkydiveBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.SKYDIVE, pos, state);
		List<IConfig.ColorSet> colorSets = Common.CONFIG.SKYDIVE_PRESETS();
		for(int i=0; i<color.length; i++) {
			String color = colorSets.size() > 0 && i < colorSets.get(0).colors.size() ? colorSets.get(0).colors.get(i) : "";
			this.color[i] = color;
			this.colorCode[i] = ColorHelper.textToColor(color).getRGB();
		}
		this.teleport = true;
		this.radius = Common.CONFIG.SKYDIVE_RADIUS();
		this.colorSetsIndex = colorSets.size() > 0 ? 0 : -1;
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		for(int i=0; i<color.length; i++) {
			this.color[i] = nbt.getString("Color" + i);
			this.colorCode[i] = nbt.getInt("ColorCode" + i);
		}
		this.teleport = nbt.getBoolean("Teleport");
		this.radius = nbt.getInt("Radius");
		this.colorSetsIndex = nbt.getInt("ColorSetIndex");
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		for(int i=0; i<color.length; i++) {
			nbt.putString("Color" + i, color[i]);
			nbt.putInt("ColorCode" + i, colorCode[i]);
		}
		nbt.putBoolean("Teleport", teleport);
		nbt.putInt("Radius", radius);
		nbt.putInt("ColorSetIndex", colorSetsIndex);
	}

	public void update(String[] color, int radius, boolean teleport, int colorSetsIndex) {
		this.color = color;
		this.colorCode = new int[color.length];
		for(int i=0; i<color.length; i++) {
			this.colorCode[i] = ColorHelper.textToColor(color[i]).getRGB();
		}
		this.teleport = teleport;
		this.radius = radius;
		this.colorSetsIndex = colorSetsIndex;
		this.markUpdated();
	}
}