package com.slymask3.instantblocks.builder;

import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;

public class BuildSound {
    private final BlockPos pos;
    private final SoundEvent placeSound, breakSound;
    private final float volume;
    private final ClientHelper.Particles particles;

    public BuildSound(BlockPos pos, SoundEvent placeSound, SoundEvent breakSound, float volume, ClientHelper.Particles particles) {
        this.pos = pos;
        this.placeSound = placeSound;
        this.breakSound = breakSound;
        this.volume = volume;
        this.particles = particles;
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

    public ClientHelper.Particles getParticles() {
        return this.particles;
    }
}
