package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.PacketID;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;

public class SkydivePacket extends AbstractPacket {
	public final int _x;
	public final int _y;
	public final int _z;
	public final int _colors_amount;
	public final int[] _colors;
	public final int _radius;
	public final boolean _tp;

	public SkydivePacket(int x, int y, int z, int colors_amount, int[] colors, int radius, boolean tp) {
		super(PacketID.SKYDIVE);
		_x = x;
		_y = y;
		_z = z;
		_colors_amount = colors_amount;
		_colors = colors;
		_radius = radius;
		_tp = tp;
	}

	public FriendlyByteBuf getBuffer() {
		FriendlyByteBuf buffer = new FriendlyByteBuf(Unpooled.buffer());
		buffer.writeInt(this._x);
		buffer.writeInt(this._y);
		buffer.writeInt(this._z);
		buffer.writeInt(this._colors_amount);
		for(int i=0; i < this._colors.length; i++) {
			buffer.writeInt(this._colors[i]);
		}
		buffer.writeInt(this._radius);
		buffer.writeBoolean(this._tp);
		return buffer;
	}

	public static void encode(SkydivePacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
		buffer.writeInt(message._colors_amount);
		for(int i=0; i < message._colors.length; i++) {
			buffer.writeInt(message._colors[i]);
		}
		buffer.writeInt(message._radius);
		buffer.writeBoolean(message._tp);
	}

	public static SkydivePacket decode(FriendlyByteBuf buffer) {
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		int colors_amount = buffer.readInt();
		int[] colors = new int[colors_amount];
		for(int i=0; i < colors_amount; i++) {
			colors[i] = buffer.readInt();
		}
		int radius = buffer.readInt();
		boolean tp = buffer.readBoolean();
		return new SkydivePacket(x,y,z,colors_amount,colors,radius,tp);
	}
}