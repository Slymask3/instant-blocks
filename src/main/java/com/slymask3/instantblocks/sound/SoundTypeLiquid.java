package com.slymask3.instantblocks.sound;

import net.minecraft.block.Block;

public class SoundTypeLiquid extends Block.SoundType {
    public String sound;
	
	public SoundTypeLiquid(String soundPath, float volume, float pitch) {
        super(soundPath, volume, pitch);
        sound = soundPath;
    }

	@Override
    public String getBreakSound() {
        return sound;
    }

	@Override
    public String getStepResourcePath() {
        return sound;
    }

	@Override
    public String func_150496_b() {
        return sound;
    }
}