package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class StatuePacket extends InstantPacket {
	public final String _username;
	public final boolean _head;
	public final boolean _body;
	public final boolean _armLeft;
	public final boolean _armRight;
	public final boolean _legLeft;
	public final boolean _legRight;
	public final boolean _rgb;

	public StatuePacket(boolean activate, BlockPos pos, String username, boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight, boolean rgb) {
		super(PacketHelper.PacketID.STATUE,activate,pos);
		_username = username;
		_head = head;
		_body = body;
		_armLeft = armLeft;
		_armRight = armRight;
		_legLeft = legLeft;
		_legRight = legRight;
		_rgb = rgb;
	}

	public <PKT extends AbstractPacket> FriendlyByteBuf write(PKT packet, FriendlyByteBuf buffer) {
		buffer = super.write(packet,buffer);
		StatuePacket message = (StatuePacket)packet;
		buffer.writeUtf(message._username);
		buffer.writeBoolean(message._head);
		buffer.writeBoolean(message._body);
		buffer.writeBoolean(message._armLeft);
		buffer.writeBoolean(message._armRight);
		buffer.writeBoolean(message._legLeft);
		buffer.writeBoolean(message._legRight);
		buffer.writeBoolean(message._rgb);
		return buffer;
	}

	public static StatuePacket decode(FriendlyByteBuf buffer) {
		boolean activate = buffer.readBoolean();
		BlockPos pos = buffer.readBlockPos();
		String username = buffer.readUtf();
		boolean head = buffer.readBoolean();
		boolean body = buffer.readBoolean();
		boolean armLeft = buffer.readBoolean();
		boolean armRight = buffer.readBoolean();
		boolean legLeft = buffer.readBoolean();
		boolean legRight = buffer.readBoolean();
		boolean rgb = buffer.readBoolean();
		return new StatuePacket(activate,pos,username,head,body,armLeft,armRight,legLeft,legRight,rgb);
	}
}