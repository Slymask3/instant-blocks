package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slymask3.instantblocks.Common;
import com.slymask3.instantblocks.block.entity.StatueBlockEntity;
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

	public StatueScreen(Player player, Level world, BlockPos pos) {
		super(player, world, pos, "ib.gui.statue.title");
	}

	@Override
	public void init() {
		super.init();

		StatueBlockEntity blockEntity = (StatueBlockEntity)world.getBlockEntity(pos);

		int x_left = this.width / 2 - 4 - 150;
		int x_right = this.width / 2 + 4;
		int y = this.height / 4 + 32;
		int slot = 22;

		this.head = new Checkbox(x_left, y, 150, 20, Component.translatable("ib.gui.statue.head"), blockEntity.head);
		this.body = new Checkbox(x_right, y, 150, 20, Component.translatable("ib.gui.statue.body"), blockEntity.body);
		this.armLeft = new Checkbox(x_left, y+(slot), 150, 20, Component.translatable("ib.gui.statue.arm.left"), blockEntity.armLeft);
		this.armRight = new Checkbox(x_right, y+(slot), 150, 20, Component.translatable("ib.gui.statue.arm.right"), blockEntity.armRight);
		this.legLeft = new Checkbox(x_left, y+(slot*2), 150, 20, Component.translatable("ib.gui.statue.leg.left"), blockEntity.legLeft);
		this.legRight = new Checkbox(x_right, y+(slot*2), 150, 20, Component.translatable("ib.gui.statue.leg.right"), blockEntity.legRight);
		this.rgbMode = new Checkbox(x_left, y+(slot*3), 150, 20, Component.translatable("ib.gui.statue.rgb"), blockEntity.rgb);

		this.input = new EditBox(this.font, this.width / 2 - 4 - 150, 50, 300+8, 20, Component.literal("Input"));
		this.input.setValue(blockEntity.username);

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

	public void sendInfo(boolean activate) {
		Common.NETWORK.sendToServer(new StatuePacket(activate, this.pos, input.getValue(), head.selected(), body.selected(), armLeft.selected(), armRight.selected(), legLeft.selected(), legRight.selected(), rgbMode.selected()));
	}
}