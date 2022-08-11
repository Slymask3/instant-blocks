package com.slymask3.instantblocks.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;

public class BuildSound {
    private final BlockPos pos;
    private final SoundEvent placeSound, breakSound;
    private final float volume;

    public BuildSound(BlockPos pos, SoundEvent placeSound, SoundEvent breakSound, float volume) {
        this.pos = pos;
        this.placeSound = placeSound;
        this.breakSound = breakSound;
        this.volume = volume;
    }

    public BlockPos getBlockPos() {
        return this.pos;
    }

    public SoundEvent getPlaceSound() {
        return this.placeSound;
    }

    public String getPlaceSoundString() {
        return this.placeSound != null ? this.placeSound.getLocation().toString() : "";
    }

    public SoundEvent getBreakSound() {
        return this.breakSound;
    }

    public String getBreakSoundString() {
        return this.breakSound != null ? this.breakSound.getLocation().toString() : "";
    }

    public float getVolume() {
        return this.volume;
    }
}
