package com.slymask3.instantblocks.network;

import com.slymask3.instantblocks.block.instant.BlockInstantSkydive;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSkydive {
	int _x, _y, _z;
	int _colors_amount;
	int[] _colors;
	int _radius;
	boolean _tp;

	public PacketSkydive(int x, int y, int z, int colors_amount, int[] colors, int radius, boolean tp) {
		_x = x;
		_y = y;
		_z = z;
		_colors_amount = colors_amount;
		_colors = colors;
		_radius = radius;
		_tp = tp;
	}

	public static void encode(PacketSkydive message, FriendlyByteBuf buffer) {
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

	public static PacketSkydive decode(FriendlyByteBuf buffer) {
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
		return new PacketSkydive(x,y,z,colors_amount,colors,radius,tp);
	}

	public static class Handler {
		public static void handle(PacketSkydive message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				Player player = context.get().getSender();
				Level world = player.getLevel();

				BlockInstantSkydive block = (BlockInstantSkydive)BuildHelper.getBlock(world,message._x, message._y, message._z);
				block.build(world,message._x, message._y, message._z, player, message._colors, message._radius, message._tp);
				block.afterBuild(world,message._x, message._y, message._z, player);
			});
			context.get().setPacketHandled(true);
		}
	}
}