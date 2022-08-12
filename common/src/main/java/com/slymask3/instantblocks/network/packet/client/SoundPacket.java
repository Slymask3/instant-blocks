package com.slymask3.instantblocks.network.packet.client;

import com.slymask3.instantblocks.builder.BuildSound;
import com.slymask3.instantblocks.network.PacketHelper;
import com.slymask3.instantblocks.network.packet.AbstractPacket;
import com.slymask3.instantblocks.util.ClientHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.ArrayList;
import java.util.List;

public class SoundPacket extends AbstractPacket {
	public final List<BuildSound> buildSounds;

	public SoundPacket(List<BuildSound> buildSounds) {
		super(PacketHelper.PacketID.SOUND);
		this.buildSounds = buildSounds;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		SoundPacket message = (SoundPacket)packet;
		buffer.writeInt(message.buildSounds.size());
		for(BuildSound buildSound : message.buildSounds) {
			buffer.writeBlockPos(buildSound.getBlockPos());
			buffer.writeUtf(buildSound.getPlaceSoundString());
			buffer.writeUtf(buildSound.getBreakSoundString());
			buffer.writeFloat(buildSound.getVolume());
			buffer.writeInt(buildSound.getParticles().ordinal());
		}
		return buffer;
	}

	public static SoundPacket decode(FriendlyByteBuf buffer) {
		List<BuildSound> buildSounds = new ArrayList<>();
		int size = buffer.readInt();
		for(int i=0; i<size; i++) {
			BlockPos pos = buffer.readBlockPos();
			String placeSoundString = buffer.readUtf();
			String breakSoundString = buffer.readUtf();
			SoundEvent placeSound = !placeSoundString.isEmpty() ? new SoundEvent(new ResourceLocation(placeSoundString)) : null;
			SoundEvent breakSound = !breakSoundString.isEmpty() ? new SoundEvent(new ResourceLocation(breakSoundString)) : null;
			float volume = buffer.readFloat();
			int particles = buffer.readInt();
			buildSounds.add(new BuildSound(pos,placeSound,breakSound,volume,ClientHelper.Particles.values()[particles]));
		}
		return new SoundPacket(buildSounds);
	}
}