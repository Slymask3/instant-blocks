package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.network.FriendlyByteBuf;

public class MessagePacket extends AbstractPacket {
	public final String message;
	public final String variable;

	public MessagePacket(String message, String variable) {
		super(PacketHelper.PacketID.MESSAGE);
		this.message = message;
		this.variable = variable;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		MessagePacket message = (MessagePacket)packet;
		buffer.writeUtf(message.message);
		buffer.writeUtf(message.variable);
		return buffer;
	}

	public static MessagePacket decode(FriendlyByteBuf buffer) {
		String message = buffer.readUtf();
		String variable = buffer.readUtf();
		return new MessagePacket(message,variable);
	}
}