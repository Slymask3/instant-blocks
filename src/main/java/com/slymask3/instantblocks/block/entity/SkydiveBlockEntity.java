package com.slymask3.instantblocks.block.entity;

import com.slymask3.instantblocks.init.ModTiles;
import com.slymask3.instantblocks.util.Colors;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

public class SkydiveBlockEntity extends InstantBlockEntity {
	public String[] color = new String[11];
	public int[] colorCode = new int[11];

	public SkydiveBlockEntity(BlockPos pos, BlockState state) {
		super(ModTiles.SKYDIVE.get(), pos, state);
		for(int i=0; i<color.length; i++) {
			String color = Colors.indexRainbowToString(i);
			this.color[i] = color;
			this.colorCode[i] = Colors.textToColor(color).getRGB();
		}
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		for(int i=0; i<color.length; i++) {
			this.color[i] = nbt.getString("Color" + i);
			this.colorCode[i] = nbt.getInt("ColorCode" + i);
		}
	}

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		for(int i=0; i<color.length; i++) {
			nbt.putString("Color" + i, color[i]);
			nbt.putInt("ColorCode" + i, colorCode[i]);
		}
	}
	
	public void setColorCode(int i, String input) {
		this.colorCode[i] = Colors.textToColor(input).getRGB();
	}
	
	public void setColor(int i, String input) {
		this.color[i] = input;
	}
}