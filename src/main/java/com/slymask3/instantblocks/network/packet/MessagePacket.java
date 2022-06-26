package com.slymask3.instantblocks.network.packet;

import com.slymask3.instantblocks.network.ClientPacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MessagePacket {
	public String message;
	public String variable;
	public int x,y,z;
	public boolean effects;

	public MessagePacket(String message, String variable, int x, int y, int z, boolean effects) {
		this.message = message;
		this.variable = variable;
		this.x = x;
		this.y = y;
		this.z = z;
		this.effects = effects;
	}

	public static void encode(MessagePacket message, FriendlyByteBuf buffer) {
		buffer.writeUtf(message.message);
		buffer.writeUtf(message.variable);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeBoolean(message.effects);
	}

	public static MessagePacket decode(FriendlyByteBuf buffer) {
		String message = buffer.readUtf();
		String variable = buffer.readUtf();
		int x = buffer.readInt();
		int y = buffer.readInt();
		int z = buffer.readInt();
		boolean effects = buffer.readBoolean();
		return new MessagePacket(message,variable,x,y,z,effects);
	}

	public static class Handler {
		public static void handle(MessagePacket message, Supplier<NetworkEvent.Context> context) {
			context.get().enqueueWork(() -> {
				DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handle(message, context));
			});
			context.get().setPacketHandled(true);
		}
	}
}