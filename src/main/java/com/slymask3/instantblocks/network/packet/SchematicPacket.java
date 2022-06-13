package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.block.instant.InstantSchematicBlock;
import com.slymask3.instantblocks.util.BuildHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SchematicPacket {
	public int _x, _y, _z;
	public String _schematic;
	public boolean _center, _air;

	public SchematicPacket(int x, int y, int z, String schematic, boolean center, boolean air) {
		_x = x;
		_y = y;
		_z = z;
		_schematic = schematic;
		_center = center;
		_air = air;
	}

	public static void encode(SchematicPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message._x);
		buffer.writeInt(message._y);
		buffer.writeInt(message._z);
		buffer.writeUtf(message._schematic);
		buffer.writeBoolean(message._center);
		buffer.writeBoolean(message._air);
	}

	public static SchematicPacket decode(FriendlyByteBuf buffer) {
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		String schematic = buffer.readUtf();
		boolean center = buffer.readBoolean();
		boolean air = buffer.readBoolean();
		return new SchematicPacket(x,y,z,schematic,center,air);
	}

	public static class Handler {
		public static void handle(SchematicPacket message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				Player player = context.get().getSender();
				Level world = player.getLevel();

				InstantSchematicBlock block = (InstantSchematicBlock)BuildHelper.getBlock(world,message._x, message._y, message._z);
				if(block.build(world,message._x, message._y, message._z, player, message._schematic, message._center, message._air)) {
					block.afterBuild(world,message._x, message._y, message._z, player);
				}
			});
			context.get().setPacketHandled(true);
		}
	}
}