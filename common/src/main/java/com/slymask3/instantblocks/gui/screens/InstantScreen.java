package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class InstantScreen extends Screen {
	protected final Level world;
	protected final BlockPos pos;
	protected final Player player;

	protected Button done, cancel;
	private String doneText;

	public InstantScreen(Player player, Level world, BlockPos pos, String title) {
		super(new TranslatableComponent(title));
		this.player = player;
		this.world = world;
		this.pos = pos;
		this.doneText = "ib.gui.generate";
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	@Override
	public void init() {
		this.done = this.addRenderableWidget(new Button(this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, new TranslatableComponent(doneText), (p_88642_) -> {
			this.sendInfo(true);
			this.close();
		}));
		this.cancel = this.addRenderableWidget(new Button(this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, CommonComponents.GUI_CANCEL, (p_88642_) -> {
			this.onClose();
		}));

		addRenderableWidget(this.done);
		addRenderableWidget(this.cancel);
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTicks);
		poseStack.pushPose();
		this.font.drawShadow(poseStack, this.title, this.width / 2 - this.font.width(this.title) / 2, 20, 16777215);
		this.renderLabels(poseStack,mouseX,mouseY);
		poseStack.popPose();
	}

	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {}

	public boolean isPauseScreen() {
		return false;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Minecraft getMinecraft() {
		return this.minecraft;
	}

	public void sendInfo(boolean activate) {}

	public void onClose() {
		this.sendInfo(false);
		this.close();
	}

	private void close() {
		this.minecraft.setScreen(null);
	}
}