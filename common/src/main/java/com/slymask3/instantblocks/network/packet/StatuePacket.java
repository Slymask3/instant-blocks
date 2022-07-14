package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketID;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

public class StatuePacket extends AbstractPacket {
	public final int _x;
	public final int _y;
	public final int _z;
	public final String _username;
	public final boolean _head;
	public final boolean _body;
	public final boolean _armLeft;
	public final boolean _armRight;
	public final boolean _legLeft;
	public final boolean _legRight;
	public final boolean _rgb;

	public StatuePacket(int x, int y, int z, String username, boolean head, boolean body, boolean armLeft, boolean armRight, boolean legLeft, boolean legRight, boolean rgb) {
		super(PacketID.STATUE);
		_x = x;
		_y = y;
		_z = z;
		_username = username;
		_head = head;
		_body = body;
		_armLeft = armLeft;
		_armRight = armRight;
		_legLeft = legLeft;
		_legRight = legRight;
		_rgb = rgb;
	}

	public FriendlyByteBuf getBuffer() {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
		buffer.writeInt(this._x);
		buffer.writeInt(this._y);
		buffer.writeInt(this._z);
		buffer.writeUtf(this._username);
		buffer.writeBoolean(this._head);
		buffer.writeBoolean(this._body);
		buffer.writeBoolean(this._armLeft);
		buffer.writeBoolean(this._armRight);
		buffer.writeBoolean(this._legLeft);
		buffer.writeBoolean(this._legRight);
		buffer.writeBoolean(this._rgb);
		return buffer;
	}

	public static void encode(StatuePacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
		buffer.writeUtf(message._username);
		buffer.writeBoolean(message._head);
		buffer.writeBoolean(message._body);
		buffer.writeBoolean(message._armLeft);
		buffer.writeBoolean(message._armRight);
		buffer.writeBoolean(message._legLeft);
		buffer.writeBoolean(message._legRight);
		buffer.writeBoolean(message._rgb);
	}

	public static StatuePacket decode(FriendlyByteBuf buffer) {
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		String username = buffer.readUtf();
		boolean head = buffer.readBoolean();
		boolean body = buffer.readBoolean();
		boolean armLeft = buffer.readBoolean();
		boolean armRight = buffer.readBoolean();
		boolean legLeft = buffer.readBoolean();
		boolean legRight = buffer.readBoolean();
		boolean rgb = buffer.readBoolean();
		return new StatuePacket(x,y,z,username,head,body,armLeft,armRight,legLeft,legRight,rgb);
	}
}