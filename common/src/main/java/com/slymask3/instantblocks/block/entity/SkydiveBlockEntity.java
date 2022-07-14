package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.core.Config;
import com.slymask3.instantblocks.core.ModTiles;
import com.slymask3.instantblocks.util.ColorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class SkydiveBlockEntity extends InstantBlockEntity {
	public final String[] color = new String[11];
	public final int[] colorCode = new int[11];
	public boolean teleport;
	public int radius;

	public SkydiveBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.SKYDIVE, pos, state);
		for(int i=0; i<color.length; i++) {
			String color = ColorHelper.indexRainbowToString(i);
			this.color[i] = color;
			this.colorCode[i] = ColorHelper.textToColor(color).getRGB();
		}
		this.teleport = true;
		this.radius = Config.Common.SKYDIVE_RADIUS;
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
	}
	
	public void setColorCode(int i, String input) {
		this.colorCode[i] = ColorHelper.textToColor(input).getRGB();
	}
	
	public void setColor(int i, String input) {
		this.color[i] = input;
	}
}