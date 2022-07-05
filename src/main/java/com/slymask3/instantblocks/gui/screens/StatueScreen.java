package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.block.entity.StatueBlockEntity;
import com.slymask3.instantblocks.network.PacketHandler;
import com.slymask3.instantblocks.network.packet.StatuePacket;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class StatueScreen extends InstantScreen {
	private EditBox input;
	private Checkbox head, body, armLeft, armRight, legLeft, legRight;
	private Checkbox rgbMode;
	private final StatueBlockEntity tileEntity;

	public StatueScreen(Player player, Level world, int x, int y, int z) {
		super(player, world, x, y, z, "ib.gui.statue.title");
		this.tileEntity = (StatueBlockEntity)world.getBlockEntity(new BlockPos(x,y,z));
	}

	@Override
	public void init() {
		super.init();

		int x_left = this.width / 2 - 4 - 150;
		int x_right = this.width / 2 + 4;
		int y = this.height / 4 + 32;
		int slot = 22;

		this.head = new Checkbox(x_left, y, 150, 20, Component.translatable("ib.gui.statue.head"), tileEntity.head) {
			public void onPress() {
				super.onPress();
				tileEntity.head = this.selected();
			}
		};
		this.body = new Checkbox(x_right, y, 150, 20, Component.translatable("ib.gui.statue.body"), tileEntity.body) {
			public void onPress() {
				super.onPress();
				tileEntity.body = this.selected();
			}
		};
		this.armLeft = new Checkbox(x_left, y+(slot), 150, 20, Component.translatable("ib.gui.statue.arm.left"), tileEntity.armLeft) {
			public void onPress() {
				super.onPress();
				tileEntity.armLeft = this.selected();
			}
		};
		this.armRight = new Checkbox(x_right, y+(slot), 150, 20, Component.translatable("ib.gui.statue.arm.right"), tileEntity.armRight) {
			public void onPress() {
				super.onPress();
				tileEntity.armRight = this.selected();
			}
		};
		this.legLeft = new Checkbox(x_left, y+(slot*2), 150, 20, Component.translatable("ib.gui.statue.leg.left"), tileEntity.legLeft) {
			public void onPress() {
				super.onPress();
				tileEntity.legLeft = this.selected();
			}
		};
		this.legRight = new Checkbox(x_right, y+(slot*2), 150, 20, Component.translatable("ib.gui.statue.leg.right"), tileEntity.legRight) {
			public void onPress() {
				super.onPress();
				tileEntity.legRight = this.selected();
			}
		};
		this.rgbMode = new Checkbox(x_left, y+(slot*3), 150, 20, Component.translatable("ib.gui.statue.rgb"), tileEntity.rgb) {
			public void onPress() {
				super.onPress();
				tileEntity.rgb = this.selected();
			}
		};

		this.input = new EditBox(this.font, this.width / 2 - 4 - 150, 50, 300+8, 20, Component.literal("Input")) {
			public void insertText(String textToWrite) {
				super.insertText(textToWrite);
				tileEntity.username = this.getValue();
			}
			public void deleteChars(int pNum) {
				super.deleteChars(pNum);
				tileEntity.username = this.getValue();
			}
		};

		this.addRenderableWidget(this.head);
		this.addRenderableWidget(this.body);
		this.addRenderableWidget(this.armLeft);
		this.addRenderableWidget(this.armRight);
		this.addRenderableWidget(this.legLeft);
		this.addRenderableWidget(this.legRight);
		this.addRenderableWidget(this.rgbMode);
		this.addRenderableWidget(this.input);

		this.setInitialFocus(this.input);
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("ib.gui.statue.input"), this.width / 2 - 4 - 150, 37, 10526880);
		this.font.draw(poseStack, Component.translatable("ib.gui.statue.select"), this.width / 2 - 3 - 150, this.height / 4 + 8 + 12, 10526880);
		this.font.draw(poseStack, Component.translatable("ib.gui.statue.rgb.text"), this.width / 2 - 3 - 150, this.height / 4 + 32 + 88, 10526880);
	}
	
	public void sendInfo() {
		PacketHandler.sendToServer(new StatuePacket(this.x, this.y, this.z, input.getValue(), head.selected(), body.selected(), armLeft.selected(), armRight.selected(), legLeft.selected(), legRight.selected(), rgbMode.selected()));
	}
}