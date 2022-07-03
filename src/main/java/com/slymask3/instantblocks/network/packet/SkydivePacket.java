package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.block.instant.InstantSkydiveBlock;
import com.slymask3.instantblocks.util.Helper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SkydivePacket {
	public final int _x;
	public final int _y;
	public final int _z;
	public final int _colors_amount;
	public final int[] _colors;
	public final int _radius;
	public final boolean _tp;

	public SkydivePacket(int x, int y, int z, int colors_amount, int[] colors, int radius, boolean tp) {
		_x = x;
		_y = y;
		_z = z;
		_colors_amount = colors_amount;
		_colors = colors;
		_radius = radius;
		_tp = tp;
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

	public static class Handler {
		public static void handle(SkydivePacket message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				Player player = context.get().getSender();
				Level world = player.getLevel();

				InstantSkydiveBlock block = (InstantSkydiveBlock) Helper.getBlock(world,message._x, message._y, message._z);
				if(block.build(world,message._x, message._y, message._z, player, message._colors, message._radius, message._tp)) {
					block.afterBuild(world,message._x, message._y, message._z, player);
				}
			});
			context.get().setPacketHandled(true);
		}
	}
}