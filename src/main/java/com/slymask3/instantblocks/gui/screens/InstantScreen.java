package com.slymask3.instantblocks.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class InstantScreen extends Screen {
	protected final Level world;
	protected final int x, y, z;
	protected final Player player;

	protected Button done, cancel;
	private String doneText;

	public InstantScreen(Player player, Level world, int x, int y, int z, String title) {
		super(new TranslatableComponent(title));
		this.player = player;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.doneText = "ib.gui.generate";
	}

	public void setDoneText(String doneText) {
		this.doneText = doneText;
	}

	@Override
	public void init() {
		this.done = this.addRenderableWidget(new Button(this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, new TranslatableComponent(doneText), (p_88642_) -> {
			this.sendInfo();
			this.onClose();
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

	public Minecraft getMinecraftInstance() {
		return this.minecraft;
	}

	public Font getFontRenderer() {
		return this.font;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void sendInfo() {}
}